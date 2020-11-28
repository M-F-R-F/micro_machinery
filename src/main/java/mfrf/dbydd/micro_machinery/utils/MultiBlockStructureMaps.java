package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockAccessory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.resources.IResource;
import net.minecraft.util.Direction;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiBlockStructureMaps {

    public static ArrayList<String> NAMES = new ArrayList<>();
    private static HashMap<String, MultiBlockPosBox> STRUCTURE_MAPS = null;

    private static void ReadStructure() {
        STRUCTURE_MAPS = new HashMap<>();
        NAMES.add("blast_furnace");
        try {
            for (String name : NAMES) {
                IResource resource = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Micro_Machinery.NAME, "structures/" + name + ".json"));
                STRUCTURE_MAPS.put(name, MultiBlockPosBox.readJson(JSONUtils.fromJson(new InputStreamReader(resource.getInputStream()))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, MultiBlockPosBox> getStructureMaps() {
        if (STRUCTURE_MAPS == null) {
            ReadStructure();
        }
        return STRUCTURE_MAPS;
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

        public Block getActiveBlock() {
            return blocks[activeBlock.getX()][activeBlock.getY()][activeBlock.getZ()];
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


        public boolean match(BlockPos pos, World world, Direction direction) {
            if (world.getBlockState(pos).getBlock() == getActiveBlock()) {
                BlockPos rotatedBlockPos = MathUtil.rotateBlockPos(activeBlock, direction, MathUtil.MATRIX_ROT_NORTH_TO_NORTH_IDENTITY);
                BlockPos rotatedSize = MathUtil.rotateBlockPos(size, direction, MathUtil.MATRIX_ROT_NORTH_TO_NORTH_IDENTITY);

                int posOffsetX = pos.getX() - rotatedBlockPos.getX();
                int posOffsetY = pos.getY() - rotatedBlockPos.getY();
                int posOffsetZ = pos.getZ() - rotatedBlockPos.getZ();
                int xHalfSize = (int) Math.round(rotatedSize.getX()%2 == 1?rotatedSize.getX() : (rotatedSize.getX() - 1) / 2.0);
                int yHalfSize = (int) Math.round(rotatedSize.getY()%2 == 1?rotatedSize.getY() : (rotatedSize.getY() - 1) / 2.0);
                int zHalfSize = (int) Math.round(rotatedSize.getZ()%2 == 1?rotatedSize.getZ() : (rotatedSize.getZ() - 1) / 2.0);

                for (int xOffset1 = -rotatedSize.getX() + xHalfSize, xOffset2 = 0; xOffset1 < rotatedSize.getX(); xOffset1++, xOffset2++) {
                    for (int yOffset1 = -rotatedSize.getY() + yHalfSize, yOffset2 = 0; yOffset1 < rotatedSize.getY(); yOffset1++, yOffset2++) {
                        for (int zOffset1 = -rotatedSize.getZ() + zHalfSize, zOffset2 = 0; zOffset1 < rotatedSize.getZ(); zOffset1++, zOffset2++) {

                            if (world.getBlockState(pos.add(posOffsetX + xOffset1, posOffsetY + yOffset1, posOffsetZ + zOffset1)).getBlock() != blocks[xOffset2][yOffset2][zOffset2]) {
                                return false;
                            }

                        }
                    }
                }
                return true;

            }
            return false;
        }

//        public MultiBlockPosBox getRotatedBox(Direction direction){
//
//        }
    }
}
