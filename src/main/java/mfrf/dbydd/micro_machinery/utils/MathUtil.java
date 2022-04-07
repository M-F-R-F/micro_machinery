package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.BlockUtilPlaceHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Require org.apache.commons.math3.
 * Of course you can use other libs,just need a bit of change.
 * note : these matrix is a shit mountain,coordinates of minecraft is too complex.....
 * don't touch these matrix that from line 30 to line 92......
 */
public class MathUtil {
    /**
     * Affine transformation = Linear transformation + transformation
     */
    public static final RealMatrix MATRIX_ROT_WEST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final RealMatrix MATRIX_ROT_SOUTH = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    public static final RealMatrix MATRIX_ROT_EAST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final RealMatrix MATRIX_ROT_NORTH_IDENTITY = new Array2DRowRealMatrix(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });
    public static final RealMatrix BLOCK_STATE_OFFSET_MATRIX = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 0}
    });
    /**
     * Inverse of this matrix is MATRIX_ROT_NORTH_TO_WEST_270DEG.
     */
    public static final RealMatrix MATRIX_ROT_NORTH_TO_EAST_90DEG = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    /**
     * Inverse of this matrix is itself.
     */
    public static final RealMatrix MATRIX_ROT_NORTH_TO_SOUTH_180DEG = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    /**
     * Inverse of this matrix is MATRIX_ROT_NORTH_TO_EAST_90DEG.
     */
    public static final RealMatrix MATRIX_ROT_NORTH_TO_WEST_270DEG = new Array2DRowRealMatrix(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    public static final RealMatrix MATRIX_ROT_NORTH_TO_NORTH_IDENTITY_360DEG = new Array2DRowRealMatrix(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });

    /**
     * @param matrix An Affine-transformation matrix, the 3x3 sub matrix at upper left means linear transformation.
     *               The first three elements in the fourth column means offset,and the final elements should always be 1.
     * @param shape  the VoxelShape need to transform.
     * @return the VoxelShape that been transformed.
     */
    public static VoxelShape transformationVoxelShape(RealMatrix matrix, VoxelShape shape) {
        List<AxisAlignedBB> axisAlignedBBS = shape.toBoundingBoxList();
        RealMatrix realMatrix = matrix.add(BLOCK_STATE_OFFSET_MATRIX);
        VoxelShape returnValue = VoxelShapes.empty();
        for (AxisAlignedBB boundingBox : axisAlignedBBS) {
            RealVector beginPoint = new ArrayRealVector(new double[]{(boundingBox.minX * 16) - 8, boundingBox.minY * 16 - 8, (boundingBox.minZ * 16) - 8, 1});
            RealVector lastPoint = new ArrayRealVector(new double[]{(boundingBox.maxX * 16) - 8, boundingBox.maxY * 16 - 8, (boundingBox.maxZ * 16) - 8, 1});

            RealVector beginPointTransitioned = realMatrix.operate(beginPoint);
            RealVector lastPointTransitioned = realMatrix.operate(lastPoint);

            returnValue = VoxelShapes.or(returnValue, Block.makeCuboidShape(beginPointTransitioned.getEntry(0), beginPointTransitioned.getEntry(1), beginPointTransitioned.getEntry(2), lastPointTransitioned.getEntry(0), lastPointTransitioned.getEntry(1), lastPointTransitioned.getEntry(2)));
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
                    return transformationVoxelShape(MATRIX_ROT_EAST, shape);
                case WEST:
                    return transformationVoxelShape(MATRIX_ROT_WEST, shape);
                case SOUTH:
                    return transformationVoxelShape(MATRIX_ROT_SOUTH, shape);
                default:
                    return transformationVoxelShape(MATRIX_ROT_NORTH_IDENTITY, shape);
            }
        }
        return shape;
    }

    public static BlockPos rotateBlockPosToDirection(BlockPos pos, Direction direction) {
        ArrayRealVector posVector = new ArrayRealVector(new double[]{pos.getX(), pos.getY(), pos.getZ(), 1});
        switch (direction) {
            case NORTH: {
                RealVector operated = MATRIX_ROT_NORTH_TO_SOUTH_180DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case WEST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_WEST_270DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case EAST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_EAST_90DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case SOUTH: {
                return pos;
            }
        }
        return pos;
    }

    public static BlockPos rotateBlockPosToNorth(BlockPos pos, Direction direction) {
        ArrayRealVector posVector = new ArrayRealVector(new double[]{pos.getX(), pos.getY(), pos.getZ(), 1});
        switch (direction) {
            case SOUTH: {
                RealVector operated = MATRIX_ROT_NORTH_TO_SOUTH_180DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case WEST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_EAST_90DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case EAST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_WEST_270DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case NORTH: {
                RealVector operated = MATRIX_ROT_NORTH_TO_NORTH_IDENTITY_360DEG.operate(posVector);
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
        }
        return pos;
    }

    public static BlockPos getOffsetPos(BlockPos pos, BlockPos center) {
        return center.subtract(pos);
    }

    public static MultiBlockStructureMaps.MultiBlockPosBox getNormalizedBlockPosBox(BlockPos pos1, BlockPos pos2, World world, Direction direction, BlockPos activePos) {
        //todo search accessories
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

        ArrayList<MultiBlockStructureMaps.MultiBlockPosBox.BlockNode> blockNodes = new ArrayList<>();
        ArrayList<MultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode> accessories = new ArrayList<>();

        for (int xOffset = 0; xOffset <= differenceX; xOffset++) {
            for (int yOffset = 0; yOffset <= differenceY; yOffset++) {
                for (int zOffset = 0; zOffset <= differenceZ; zOffset++) {

                    BlockPos blockPos = beginPos.add(xOffset, yOffset, zOffset);

                    BlockState blockState = world.getBlockState(blockPos);
                    Block block = blockState.getBlock();

                    if (block != Blocks.AIR) {
                        BlockPos offsetPos = rotateBlockPosToNorth(getOffsetPos(blockPos, activePos), direction);
                        if (block instanceof BlockUtilPlaceHolder) {
                            MultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode accessoryNode = new MultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode(offsetPos, block, blockState.get(BlockUtilPlaceHolder.FACING), -1, -1, "place_holder");
                            accessories.add(accessoryNode);
                            blockNodes.add(accessoryNode);
                        } else {
                            blockNodes.add(new MultiBlockStructureMaps.MultiBlockPosBox.BlockNode(offsetPos, block));
                        }
                    }

                }
            }
        }

        return new MultiBlockStructureMaps.MultiBlockPosBox(blockNodes, accessories);
    }

    public static void convertPosInToJsonObject(BlockPos pos, JsonObject jsonObject) {
        jsonObject.addProperty("xOffset", pos.getX());
        jsonObject.addProperty("yOffset", pos.getY());
        jsonObject.addProperty("zOffset", pos.getZ());
    }

    public static BlockPos getPosFromJsonObject(JsonObject jsonObject) {
        return new BlockPos(jsonObject.get("xOffset").getAsInt(), jsonObject.get("yOffset").getAsInt(), jsonObject.get("zOffset").getAsInt());
    }
}
