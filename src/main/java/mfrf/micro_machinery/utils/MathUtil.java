package mfrf.micro_machinery.utils;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.utils.math.Matrix44d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Math;
import org.joml.Vector4d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Require org.apache.commons.math3.
 * Of course you can use other libs,just need a bit of change.
 * note : these matrix is a shit mountain,coordinates of minecraft is too complex.....
 * don't touch these matrix that from line 30 to line 92......
 * todo rewrite matrix
 */
public class MathUtil {
    /**
     * Affine transformation = Linear transformation + transformation
     */
    public static final Matrix44d MATRIX_44_D_ROT_WEST = new Matrix44d(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final Matrix44d MATRIX_44_D_ROT_SOUTH = new Matrix44d(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    public static final Matrix44d MATRIX_44_D_ROT_EAST = new Matrix44d(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final Matrix44d MATRIX_44_D_ROT_NORTH_IDENTITY = new Matrix44d(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });
    public static final Matrix44d BLOCK_STATE_OFFSET_MATRIX_44_D = new Matrix44d(new double[][]{
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 1}
    });
    /**
     * Inverse of this matrix is MATRIX_ROT_NORTH_TO_WEST_270DEG.
     */
    public static final Matrix44d MATRIX_44_D_ROT_NORTH_TO_EAST_90_DEG = new Matrix44d(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    /**
     * Inverse of this matrix is itself.
     */
    public static final Matrix44d MATRIX_44_D_ROT_NORTH_TO_SOUTH_180_DEG = new Matrix44d(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    /**
     * Inverse of this matrix is MATRIX_ROT_NORTH_TO_EAST_90DEG.
     */
    public static final Matrix44d MATRIX_44_D_ROT_NORTH_TO_WEST_270_DEG = new Matrix44d(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final Matrix44d MATRIX_44_D_ROT_NORTH_TO_NORTH_IDENTITY_360_DEG = new Matrix44d(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });

    /**
     * @param matrix44d An Affine-transformation matrix, the 3x3 sub matrix at upper left means linear transformation.
     *                  The first three elements in the fourth column means.relative,and the final elements should always be 1.
     * @param shape     the VoxelShape need to transform.
     * @return the VoxelShape that been transformed.
     */
    public static VoxelShape transformationVoxelShape(Matrix44d matrix44d, VoxelShape shape) {
        List<AABB> axisAlignedBBS = shape.toAabbs();
        Matrix44d shifted = matrix44d.add(BLOCK_STATE_OFFSET_MATRIX_44_D);
        VoxelShape returnValue = Shapes.empty();
        for (AABB boundingBox : axisAlignedBBS) {
            Vector4d beginPoint = new Vector4d((boundingBox.minX * 16) - 8, boundingBox.minY * 16 - 8, (boundingBox.minZ * 16) - 8, 1);
            Vector4d lastPoint = new Vector4d((boundingBox.maxX * 16) - 8, boundingBox.maxY * 16 - 8, (boundingBox.maxZ * 16) - 8, 1);

            Vector4d beginPointTransitioned = shifted.multiplyL(beginPoint);
            Vector4d lastPointTransitioned = shifted.multiplyL(lastPoint);

            //minmax xyz
            double minX = Math.min(beginPointTransitioned.get(0), lastPointTransitioned.get(0));
            double maxX = Math.max(beginPointTransitioned.get(0), lastPointTransitioned.get(0));
            double minY = Math.min(beginPointTransitioned.get(1), lastPointTransitioned.get(1));
            double maxY = Math.max(beginPointTransitioned.get(1), lastPointTransitioned.get(1));
            double minZ = Math.min(beginPointTransitioned.get(2), lastPointTransitioned.get(2));
            double maxZ = Math.max(beginPointTransitioned.get(2), lastPointTransitioned.get(2));

//            returnValue = Shapes.or(returnValue, Block.box(beginPointTransitioned.get(0), beginPointTransitioned.get(1), beginPointTransitioned.get(2), lastPointTransitioned.get(0), lastPointTransitioned.get(1), lastPointTransitioned.get(2)));
            returnValue = Shapes.or(returnValue, Block.box(minX, minY, minZ, maxX, maxY, maxZ));
        }
        return returnValue;
    }

    /**
     * @param shape     VoxelShape that need to rotate.
     * @param direction The direction of rotate. North is default direction(did nothing).
     * @return VoxelShape that has rotated.
     */
    public static VoxelShape VoxelShapeRotateDirection(VoxelShape shape, Direction direction) {
        if (direction != Direction.UP && direction != Direction.DOWN) {
            switch (direction) {
                case EAST:
                    return transformationVoxelShape(MATRIX_44_D_ROT_EAST, shape);
                case WEST:
                    return transformationVoxelShape(MATRIX_44_D_ROT_WEST, shape);
                case SOUTH:
                    return transformationVoxelShape(MATRIX_44_D_ROT_SOUTH, shape);
                default:
                    return transformationVoxelShape(MATRIX_44_D_ROT_NORTH_IDENTITY, shape);
            }
        }
        return shape;
    }

    public static Vec3i rotateBlockPosToDirection(Vec3i pos, Direction direction) {
        Vector4d posVector = new Vector4d(new double[]{pos.getX(), pos.getY(), pos.getZ(), 1});
        switch (direction) {
            case NORTH: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_SOUTH_180_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case WEST: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_WEST_270_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case EAST: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_EAST_90_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case SOUTH: {
                return pos;
            }
        }
        return pos;
    }

    public static BlockPos rotateBlockPosToNorth(BlockPos pos, Direction direction) {
        Vector4d posVector = new Vector4d(new double[]{pos.getX(), pos.getY(), pos.getZ(), 1});
        switch (direction) {
            case SOUTH: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_SOUTH_180_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case WEST: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_EAST_90_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case EAST: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_WEST_270_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
            case NORTH: {
                Vector4d operated = MATRIX_44_D_ROT_NORTH_TO_NORTH_IDENTITY_360_DEG.multiplyL(posVector);
                return new BlockPos((int) operated.get(0), (int) operated.get(1), (int) operated.get(2));
            }
        }
        return pos;
    }

    public static DeprecatedMultiBlockStructureMaps.MultiBlockPosBox getNormalizedBlockPosBox(BlockPos pos1, BlockPos pos2, Level world, Direction direction, BlockPos activePos) {
        int pos1X = pos1.getX();
        int pos1Y = pos1.getY();
        int pos1Z = pos1.getZ();
        int pos2X = pos2.getX();
        int pos2Y = pos2.getY();
        int pos2Z = pos2.getZ();

        BlockPos beginPos = new BlockPos(Math.min(pos1X, pos2X), Math.min(pos1Y, pos2Y), Math.min(pos1Z, pos2Z));
        BlockPos finalPos = new BlockPos(Math.max(pos1X, pos2X), Math.max(pos1Y, pos2Y), Math.max(pos1Z, pos2Z));

        int differenceX = finalPos.getX() - beginPos.getX();
        int differenceY = finalPos.getY() - beginPos.getY();
        int differenceZ = finalPos.getZ() - beginPos.getZ();

        ArrayList<DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.BlockNode> blockNodes = new ArrayList<>();
        HashMap<String, DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode> accessories = new HashMap<>();

        for (int xOffset = 0; xOffset <= differenceX; xOffset++) {
            for (int yOffset = 0; yOffset <= differenceY; yOffset++) {
                for (int zOffset = 0; zOffset <= differenceZ; zOffset++) {

                    BlockPos blockPos = beginPos.offset(xOffset, yOffset, zOffset);

                    BlockState blockState = world.getBlockState(blockPos);
                    Block block = blockState.getBlock();

                    if (block != Blocks.AIR) {
                        BlockPos offsetPos = rotateBlockPosToNorth(activePos.offset(blockPos), direction);
//                        if (block instanceof BlockAccessoryPlaceHolder) {
//                            DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode accessoryNode = new DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode.relativePos, block, blockState.
//                            get(BlockAccessoryPlaceHolder.FACING), "", "", "place_holder");
//                            accessories.put("accessory$" + xOffset + "$" + yOffset + "$" + zOffset, accessoryNode);
//                            blockNodes.add(accessoryNode);
                        /**
                         * deprecated
                         */
//                    } else {
                        blockNodes.add(new DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.BlockNode(offsetPos, block));
//                    }
                    }

                }
            }
        }

        return new DeprecatedMultiBlockStructureMaps.MultiBlockPosBox(blockNodes, accessories);
    }

    public static void convertPosInToJsonObject(Vec3i pos, JsonObject jsonObject) {
        jsonObject.addProperty("xOffset", pos.getX());
        jsonObject.addProperty("yOffset", pos.getY());
        jsonObject.addProperty("zOffset", pos.getZ());
    }

    public static BlockPos getPosFromJsonObject(JsonObject jsonObject) {
        return new BlockPos(jsonObject.get("xOffset").getAsInt(), jsonObject.get("yOffset").getAsInt(), jsonObject.get("zOffset").getAsInt());
    }
}
