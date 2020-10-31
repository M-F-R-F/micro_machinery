package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockAccessory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Direction;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Require org.apache.commons.math3.
 * Of course you can use other libs,just need a bit of change.
 */
public class MathUtil {
    /**
     * Affine transformation = Linear transformation + transformation
     */
    private static final RealMatrix MATRIX_ROT_WEST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_SOUTH = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_EAST = new Array2DRowRealMatrix(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_NORTH_IDENTITY = new Array2DRowRealMatrix(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix BLOCK_STATE_OFFSET_MATRIX = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 8},
            {0, 0, 0, 0}
    });

    private static final RealMatrix MATRIX_ROT_WEST_TO_NORTH = new Array2DRowRealMatrix(new double[][]{
            {0, 0, -1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_SOUTH_TO_NORTH = new Array2DRowRealMatrix(new double[][]{
            {-1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_EAST_TO_NORTH = new Array2DRowRealMatrix(new double[][]{
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 0, 0},
            {0, 0, 0, 1}
    });
    private static final RealMatrix MATRIX_ROT_NORTH_TO_NORTH_IDENTITY = new Array2DRowRealMatrix(new double[][]{
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

    public static BlockPos normalizeBlockPos(BlockPos pos, Direction direction, RealMatrix offsetMatrix) {
        ArrayRealVector posVector = new ArrayRealVector(new double[]{pos.getX(), pos.getY(), pos.getZ(), 1}).subtract(offsetMatrix.getColumnVector(3));
        switch (direction) {
            case SOUTH: {
                RealVector operated = MATRIX_ROT_NORTH_TO_NORTH_IDENTITY.operate(MATRIX_ROT_SOUTH_TO_NORTH.add(offsetMatrix).operate(posVector));
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case WEST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_NORTH_IDENTITY.operate(MATRIX_ROT_WEST_TO_NORTH.add(offsetMatrix).operate(posVector));
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case EAST: {
                RealVector operated = MATRIX_ROT_NORTH_TO_NORTH_IDENTITY.operate(MATRIX_ROT_EAST_TO_NORTH.add(offsetMatrix).operate(posVector));
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
            case NORTH: {
                RealVector operated = MATRIX_ROT_NORTH_TO_NORTH_IDENTITY.operate(MATRIX_ROT_NORTH_TO_NORTH_IDENTITY.add(offsetMatrix).operate(posVector));
                return new BlockPos(operated.getEntry(0), operated.getEntry(1), operated.getEntry(2));
            }
        }
        return pos;
    }

    public static MultiBlockPosBox getNormalizedBlockPosBox(BlockPos pos1, BlockPos pos2, World world, Direction direction, BlockPos activePos) {
        int pos1X = pos1.getX();
        int pos1Y = pos1.getY();
        int pos1Z = pos1.getZ();
        int pos2X = pos2.getX();
        int pos2Y = pos2.getY();
        int pos2Z = pos2.getZ();

        BlockPos beginPos = new BlockPos(Math.min(pos1X, pos2X), Math.min(pos1Y, pos2Y), Math.min(pos1Z, pos2Z));
        BlockPos finalPos = new BlockPos(Math.max(pos1X, pos2X), Math.max(pos1Y, pos2Y), Math.max(pos1Z, pos2Z));

        int offsetX = finalPos.getX() - beginPos.getX();
        int offsetY = finalPos.getY() - beginPos.getY();
        int offsetZ = finalPos.getZ() - beginPos.getZ();

        MultiBlockPosBox multiBlockPosBox = new MultiBlockPosBox(new BlockPos(offsetX + 1, offsetY + 1, offsetZ + 1), normalizeBlockPos(new BlockPos(finalPos.getX() - activePos.getX(), finalPos.getY() - activePos.getY(), finalPos.getZ() - activePos.getZ()), direction, new Array2DRowRealMatrix(
                new double[][]{
                        {0, 0, 0, (offsetX) / 2.0},
                        {0, 0, 0, (offsetY) / 2.0},
                        {0, 0, 0, (offsetZ) / 2.0},
                        {0, 0, 0, 0}
                }
        )));

        for (int xOffset = 0; xOffset <= offsetX; xOffset++) {
            for (int yOffset = 0; yOffset <= offsetY; yOffset++) {
                for (int zOffset = 0; zOffset <= offsetZ; zOffset++) {
                    multiBlockPosBox.setBlock(world.getBlockState(beginPos.add(xOffset, yOffset, zOffset)).getBlock(), new BlockPos(xOffset, yOffset, zOffset));
                }
            }
        }

        return multiBlockPosBox;
    }

    public static class MultiBlockPosBox {
        public static final ArrayList<Block> VANILLA_ACCESSORIES = new ArrayList<>();

        static {
            VANILLA_ACCESSORIES.add(Blocks.LEVER);
        }

        private final BlockPos size;
        private final BlockPos activeBlock;
        private Block[][][] blocks;
        private ArrayList<BlockPos> accessories = new ArrayList<>();

        public MultiBlockPosBox(BlockPos size, BlockPos activeBlock) {
            this.size = size;
            this.activeBlock = activeBlock;
            this.blocks = new Block[size.getX()][size.getY()][size.getZ()];
        }

        private MultiBlockPosBox(BlockPos size, BlockPos activeBlock, Block[][][] blocks, ArrayList<BlockPos> accessories) {
            this.size = size;
            this.activeBlock = activeBlock;
            this.blocks = blocks;
            this.accessories = accessories;
        }

        public static MultiBlockPosBox readJson(JsonObject jsonObject) {
            JsonObject structureSize = JSONUtils.getJsonObject(jsonObject, "size");
            BlockPos size = new BlockPos(JSONUtils.getInt(structureSize, "x"), JSONUtils.getInt(structureSize, "y"), JSONUtils.getInt(structureSize, "z"));

            BlockPos active_block = BlockPos.fromLong(jsonObject.get("active_block").getAsLong());

            JsonArray accessoryList = JSONUtils.getJsonArray(jsonObject, "accessory_list");
            ArrayList<BlockPos> accessoryArrayList = new ArrayList<>();
            for (JsonElement jsonElement : accessoryList) {
                accessoryArrayList.add(new BlockPos(BlockPos.fromLong(jsonElement.getAsLong())));
            }

            Block[][][] blocks = new Block[size.getX()][size.getY()][size.getZ()];
            JsonObject block_structure_map = JSONUtils.getJsonObject(jsonObject, "block_structure_map");
            for (int offsetY = 0; offsetY < size.getZ(); offsetY++) {
                JsonObject yRow = block_structure_map.getAsJsonObject("y_row" + offsetY);
                for (int offsetX = 0; offsetX < size.getX(); offsetX++) {
                    JsonObject xCol = yRow.getAsJsonObject("x_col" + offsetX);
                    for (int offsetZ = 0; offsetZ < size.getZ(); offsetZ++) {
                        blocks[offsetX][offsetY][offsetZ] = ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(xCol.get("pos_at_" + offsetX + offsetY + offsetZ).getAsString()));
                    }
                }
            }

            return new MultiBlockPosBox(size, active_block, blocks, accessoryArrayList);

        }

        public void setBlock(Block block, BlockPos pos) {
            blocks[pos.getX()][pos.getY()][pos.getZ()] = block;
            if (block instanceof IMultiBlockAccessory || VANILLA_ACCESSORIES.contains(block)) {
                accessories.add(pos);
            }
        }


        public JsonObject convertToJson() {
            JsonObject json = new JsonObject();

            JsonObject blockPosMapY = new JsonObject();
            for (int offsetY = 0; offsetY < size.getZ(); offsetY++) {
                JsonObject blockPosMapX = new JsonObject();
                for (int offsetX = 0; offsetX < size.getX(); offsetX++) {
                    JsonObject blockPosMapZ = new JsonObject();
                    for (int offsetZ = 0; offsetZ < size.getZ(); offsetZ++) {
                        blockPosMapZ.addProperty("pos_at_" + offsetX + offsetY + offsetZ, ForgeRegistries.BLOCKS.getKey(blocks[offsetX][offsetY][offsetZ]).toString());
                    }
                    blockPosMapX.add("x_col" + offsetX, blockPosMapZ);
                }
                blockPosMapY.add("y_row" + offsetY, blockPosMapX);
            }

            json.add("block_structure_map", blockPosMapY);

            JsonArray accessoryList = new JsonArray();
            for (BlockPos accessory : accessories) {
                accessoryList.add(accessory.toLong());
            }

            json.add("accessory_list", accessoryList);

            JsonObject structureSize = new JsonObject();
            int xSize = size.getX();
            int ySize = size.getY();
            int zSize = size.getZ();
            structureSize.addProperty("x", xSize);
            structureSize.addProperty("y", ySize);
            structureSize.addProperty("z", zSize);
            json.add("size", structureSize);


            json.addProperty("active_block", activeBlock.toLong());
            return json;
        }

        public CompoundNBT convertToNbt() {
            CompoundNBT compoundNBT = new CompoundNBT();

            CompoundNBT blockPosMapY = new CompoundNBT();
            for (int offsetY = 0; offsetY < size.getY(); offsetY++) {
                CompoundNBT blockPosMapX = new CompoundNBT();
                for (int offsetX = 0; offsetX < size.getX(); offsetX++) {
                    CompoundNBT blockPosMapZ = new CompoundNBT();
                    for (int offsetZ = 0; offsetZ < size.getZ(); offsetZ++) {
                        blockPosMapZ.putString("pos_at_" + offsetX + offsetY + offsetZ, ForgeRegistries.BLOCKS.getKey(blocks[offsetX][offsetY][offsetZ]).toString());
                    }
                    blockPosMapX.put("x_col" + offsetX, blockPosMapZ);
                }
                blockPosMapY.put("y_row" + offsetY, blockPosMapX);
            }

            compoundNBT.put("block_structure_map", blockPosMapY);

            ListNBT accessoryList = new ListNBT();
            for (BlockPos accessory : accessories) {
                accessoryList.add(NBTUtil.writeBlockPos(accessory));
            }

            compoundNBT.put("accessory_list", accessoryList);
            compoundNBT.putLong("activeBlock", activeBlock.toLong());

            return compoundNBT;
        }
    }
}
