package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
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

        private ArrayList<BlockNode> blockNodes;
        private ArrayList<BlockPos> accessories;

        MultiBlockPosBox(ArrayList<BlockNode> blockNodes, ArrayList<BlockPos> accessories) {
            this.blockNodes = blockNodes;
            this.accessories = accessories;
        }

        public static MultiBlockPosBox readJson(JsonObject jsonObject) {

            JsonArray accessoryList = JSONUtils.getJsonArray(jsonObject, "accessory_list");
            JsonArray blockNodeList = JSONUtils.getJsonArray(jsonObject, "block_node_list");

            ArrayList<BlockPos> accessoryArrayList = new ArrayList<>();
            ArrayList<BlockNode> blockNodes = new ArrayList<>();

            for (JsonElement jsonElement : accessoryList) {
                accessoryArrayList.add(MathUtil.getPosFromJsonObject(jsonElement.getAsJsonObject()));
            }

            for (JsonElement jsonElement : blockNodeList) {
                blockNodes.add(BlockNode.fromJsonObject(jsonElement.getAsJsonObject()));
            }

            return new MultiBlockPosBox(blockNodes, accessoryArrayList);

        }

        public JsonObject convertToJson() {
            JsonObject json = new JsonObject();
            JsonArray blockNodeList = new JsonArray();
            JsonArray accessoryList = new JsonArray();

            for (BlockNode blockNode : this.blockNodes) {
                blockNodeList.add(blockNode.toJsonObject());
            }
            json.add("block_node_list",blockNodeList);

            for (BlockPos accessory : accessories) {
                JsonObject jsonObject = new JsonObject();
                MathUtil.convertPosInToJsonObject(accessory, jsonObject);
                accessoryList.add(jsonObject);
            }
            json.add("accessory_list",accessoryList);

            return json;
        }

//        public CompoundNBT convertToNbt() {
//            CompoundNBT compoundNBT = new CompoundNBT();
//
//            CompoundNBT blockPosMapY = new CompoundNBT();
//            for (int offsetY = 0; offsetY < size.getY(); offsetY++) {
//                CompoundNBT blockPosMapX = new CompoundNBT();
//                for (int offsetX = 0; offsetX < size.getX(); offsetX++) {
//                    CompoundNBT blockPosMapZ = new CompoundNBT();
//                    for (int offsetZ = 0; offsetZ < size.getZ(); offsetZ++) {
//                        blockPosMapZ.putString("pos_at_" + offsetX + offsetY + offsetZ, ForgeRegistries.BLOCKS.getKey(blocks[offsetX][offsetY][offsetZ]).toString());
//                    }
//                    blockPosMapX.put("x_col" + offsetX, blockPosMapZ);
//                }
//                blockPosMapY.put("y_row" + offsetY, blockPosMapX);
//            }
//
//            compoundNBT.put("block_structure_map", blockPosMapY);
//
//            ListNBT accessoryList = new ListNBT();
//            for (BlockPos accessory : accessories) {
//                accessoryList.add(NBTUtil.writeBlockPos(accessory));
//            }
//
//            compoundNBT.put("accessory_list", accessoryList);
//            compoundNBT.putLong("activeBlock", activeBlock.toLong());
//
//            return compoundNBT;
//        }

        public boolean matchAll(BlockPos pos, World world) {

            for (BlockNode blockNode : blockNodes) {
                if (world.getBlockState(pos.add(blockNode.pos)).getBlock() != blockNode.block)
                    return false;
            }

            return true;
        }

        public MultiBlockStructureMaps.MultiBlockPosBox rotateTo(Direction direction) {
            ArrayList<BlockNode> blockNodes = new ArrayList<>();
            ArrayList<BlockPos> accessories = new ArrayList<>();

            for (BlockNode blockNode : this.blockNodes) {
                blockNodes.add(new BlockNode(MathUtil.rotateBlockPosToDirection(blockNode.pos, direction), blockNode.block));
            }

            for (BlockPos accessory : this.accessories) {
                accessories.add(MathUtil.rotateBlockPosToDirection(accessory, direction));
            }

            return new MultiBlockPosBox(blockNodes, accessories);
        }

        public static class BlockNode {
            BlockPos pos;
            Block block;


            public BlockNode(BlockPos pos, Block block) {
                this.pos = pos;
                this.block = block;
            }

            public static BlockNode fromJsonObject(JsonObject jsonObject) {
                return new BlockNode(MathUtil.getPosFromJsonObject(jsonObject), ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(jsonObject.get("block").getAsString())));
            }

            public JsonObject toJsonObject() {
                JsonObject jsonObject = new JsonObject();

                MathUtil.convertPosInToJsonObject(pos, jsonObject);
                jsonObject.addProperty("block", ForgeRegistries.BLOCKS.getKey(block).toString());

                return jsonObject;
            }

        }

    }
}
