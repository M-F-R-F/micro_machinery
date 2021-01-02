package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
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
        public static final ArrayList<Block> ACCESSORIES = new ArrayList<>();//temporary

        static {
            ACCESSORIES.add(Blocks.LEVER);
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

        public ArrayList<BlockNode> getBlockNodes() {
            return blockNodes;
        }

        public ArrayList<BlockPos> getAccessories() {
            return accessories;
        }

        public JsonObject convertToJson() {
            JsonObject json = new JsonObject();
            JsonArray blockNodeList = new JsonArray();
            JsonArray accessoryList = new JsonArray();

            for (BlockNode blockNode : this.blockNodes) {
                blockNodeList.add(blockNode.toJsonObject());
            }
            json.add("block_node_list", blockNodeList);

            for (BlockPos accessory : accessories) {
                JsonObject jsonObject = new JsonObject();
                MathUtil.convertPosInToJsonObject(accessory, jsonObject);
                accessoryList.add(jsonObject);
            }
            json.add("accessory_list", accessoryList);

            return json;
        }

        public boolean matchAll(BlockPos pos, World world) {

            for (BlockNode blockNode : blockNodes) {
                BlockPos blockPos = pos.add(blockNode.pos);
                if (world.getBlockState(blockPos).getBlock() != blockNode.block)
                    return false;
            }
            return true;
        }

        public MultiBlockStructureMaps.MultiBlockPosBox rotateTo(Direction direction) {
            ArrayList<BlockNode> blockNodes = new ArrayList<>();
            ArrayList<BlockPos> accessories = new ArrayList<>();

            for (BlockPos accessory : this.accessories) {
                accessories.add(MathUtil.rotateBlockPosToDirection(accessory, direction));
            }
            for (BlockNode blockNode : this.blockNodes) {
                BlockPos blockPos = MathUtil.rotateBlockPosToDirection(blockNode.pos, direction);
                if (accessories.contains(blockPos)) {
                    AccessoryNode accessoryNode = (AccessoryNode) blockNode;
                    blockNodes.add(new AccessoryNode(blockPos, accessoryNode.getBlock(), Direction.byHorizontalIndex(accessoryNode.direction.getHorizontalIndex() + direction.getHorizontalIndex()), accessoryNode.index));
                } else {
                    blockNodes.add(new BlockNode(blockPos, blockNode.block));
                }
            }

            return new MultiBlockPosBox(blockNodes, accessories);
        }

        public static class BlockNode {
            private final BlockPos pos;
            private final Block block;

            public BlockNode(BlockPos pos, Block block) {
                this.pos = pos;
                this.block = block;
            }

            public static BlockNode fromJsonObject(JsonObject jsonObject) {
                if (jsonObject.has("index")) {
                    return new AccessoryNode(MathUtil.getPosFromJsonObject(jsonObject), ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(jsonObject.get("block").getAsString())), Direction.byHorizontalIndex(jsonObject.get("direction").getAsInt()), jsonObject.get("index").getAsInt());
                } else {
                    return new BlockNode(MathUtil.getPosFromJsonObject(jsonObject), ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(jsonObject.get("block").getAsString())));
                }
            }

            public BlockPos getPos() {
                return pos;
            }

            public Block getBlock() {
                return block;
            }

            public JsonObject toJsonObject() {
                JsonObject jsonObject = new JsonObject();

                MathUtil.convertPosInToJsonObject(pos, jsonObject);
                jsonObject.addProperty("block", ForgeRegistries.BLOCKS.getKey(block).toString());

                return jsonObject;
            }

        }

        public static class AccessoryNode extends BlockNode {

            private final int index;
            private final Direction direction;

            public AccessoryNode(BlockPos pos, Block block, Direction direction, int index) {
                super(pos, block);
                this.direction = direction;
                this.index = index;
            }

            @Override
            public JsonObject toJsonObject() {
                JsonObject jsonObject = super.toJsonObject();
                jsonObject.addProperty("index", index);
                jsonObject.addProperty("direction", direction.getHorizontalIndex());

                return jsonObject;
            }
        }

    }
}
