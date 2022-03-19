package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.BlockUtilPlaceHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

        private ArrayList<BlockNode> blockNodes;
        private ArrayList<AccessoryNode> accessories;

        MultiBlockPosBox(ArrayList<BlockNode> blockNodes, ArrayList<AccessoryNode> accessories) {
            this.blockNodes = blockNodes;
            this.accessories = accessories;
        }

        public static MultiBlockPosBox readJson(JsonObject jsonObject) {

            JsonArray blockNodeList = JSONUtils.getJsonArray(jsonObject, "block_node_list");

            ArrayList<AccessoryNode> accessoryArrayList = new ArrayList<>();
            ArrayList<BlockNode> blockNodes = new ArrayList<>();


            for (JsonElement jsonElement : blockNodeList) {
                JsonObject object = jsonElement.getAsJsonObject();
                if (object.has("direction")) {
                    accessoryArrayList.add(AccessoryNode.fromJsonObject(object));
                }
                blockNodes.add(BlockNode.fromJsonObject(object));
            }

            return new MultiBlockPosBox(blockNodes, accessoryArrayList);

        }

        public ArrayList<BlockNode> getBlockNodes() {
            return blockNodes;
        }

        public ArrayList<AccessoryNode> getAccessories() {
            return accessories;
        }

        public JsonObject convertToJson() {
            JsonObject json = new JsonObject();
            JsonArray blockNodeList = new JsonArray();

            for (BlockNode blockNode : this.blockNodes) {
                blockNodeList.add(blockNode.toJsonObject());
            }

            json.add("block_node_list", blockNodeList);

            return json;
        }

        public boolean matchAll(BlockPos pos, World world) {

            for (BlockNode blockNode : blockNodes) {
                BlockPos blockPos = pos.add(blockNode.pos);
                if (blockNode instanceof AccessoryNode) {
                    AccessoryNode node = (AccessoryNode) blockNode;
                    if (!node.test(world.getBlockState(blockPos))) {
                        return false;
                    }
                } else {
                    if (world.getBlockState(blockPos).getBlock() != blockNode.block)
                        return false;
                }
            }
            return true;
        }

        public MultiBlockStructureMaps.MultiBlockPosBox rotateTo(Direction direction) {
            ArrayList<BlockNode> blockNodes = new ArrayList<>();
            ArrayList<AccessoryNode> accessories = new ArrayList<>();

            for (AccessoryNode accessory : this.accessories) {
                accessories.add(accessory.rotateToDirectionAndReturnValue(direction));
            }
            for (BlockNode blockNode : this.blockNodes) {
                blockNodes.add(blockNode.rotateToDirectionAndReturnValue(direction));
            }

            return new MultiBlockPosBox(blockNodes, accessories);
        }

        public static class BlockNode {
            protected BlockPos pos;
            protected final Block block;

            public BlockNode(BlockPos pos, Block block) {
                this.pos = pos;
                this.block = block;
            }

            public static BlockNode fromJsonObject(JsonObject jsonObject) {
                return new BlockNode(MathUtil.getPosFromJsonObject(jsonObject), ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(jsonObject.get("block").getAsString())));
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


            public BlockNode rotateToDirectionAndReturnValue(Direction direction) {
                BlockPos pos = MathUtil.rotateBlockPosToDirection(this.pos, direction);
                return new BlockNode(pos, block);
            }

        }

        public static class AccessoryNode extends BlockNode {

            private final Direction direction;
            private final int arg1;
            private final int arg2;


            public AccessoryNode(BlockPos pos, Block block, Direction direction, int arg1, int arg2) {
                super(pos, block);
                this.direction = direction;
                this.arg1 = arg1;
                this.arg2 = arg2;
            }

            public AccessoryNode(BlockNode node, Direction direction, int arg1, int arg2) {
                super(node.pos, node.block);
                this.direction = direction;
                this.arg1 = arg1;
                this.arg2 = arg2;
            }

            @Override
            public JsonObject toJsonObject() {
                JsonObject jsonObject = super.toJsonObject();
                jsonObject.addProperty("direction", direction.getHorizontalIndex());
                jsonObject.addProperty("arg1", arg1);
                jsonObject.addProperty("arg2", arg2);

                return jsonObject;
            }

            public static AccessoryNode fromJsonObject(JsonObject jsonObject) {

                Direction direction = Direction.byHorizontalIndex(jsonObject.get("direction").getAsInt());
                int arg1 = jsonObject.get("arg1").getAsInt();
                int arg2 = jsonObject.get("arg2").getAsInt();
                return new AccessoryNode(BlockNode.fromJsonObject(jsonObject), direction, arg1, arg2);
            }

            public void rotateToDirection(Direction direction) {
                this.pos = MathUtil.rotateBlockPosToDirection(this.pos, direction);
            }

            public AccessoryNode rotateToDirectionAndReturnValue(Direction direction) {
                return new AccessoryNode(super.rotateToDirectionAndReturnValue(direction), direction, arg1, arg2);
            }

            public Direction getDirection() {
                return direction;
            }

            public int getArg1() {
                return arg1;
            }

            public int getArg2() {
                return arg2;
            }

            public boolean test(BlockState blockState) {
                return blockState.getBlock() == block && blockState.get(BlockUtilPlaceHolder.FACING) == direction;
            }
        }


    }
}
