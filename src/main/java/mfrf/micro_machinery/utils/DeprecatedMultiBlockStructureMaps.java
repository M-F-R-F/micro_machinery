package mfrf.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockAccessoryPlaceHolder;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockAccessoryPlaceHolder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Deprecated
//todo migrate to data system
public class DeprecatedMultiBlockStructureMaps {

    public static ArrayList<String> NAMES = new ArrayList<>();
    private static HashMap<String, MultiBlockPosBox> STRUCTURE_MAPS = null;

    private static void ReadStructure() {
        STRUCTURE_MAPS = new HashMap<>();
        try {
            for (String name : NAMES) {
                Resource resource = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(MicroMachinery.MODID, "structures/old_system/" + name + ".json"));
                InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
                JsonObject asJsonObject = JsonParser.parseReader(inputStreamReader).getAsJsonObject();
                STRUCTURE_MAPS.put(name, MultiBlockPosBox.readJson(asJsonObject));
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
        private HashMap<String, AccessoryNode> accessories;

        MultiBlockPosBox(ArrayList<BlockNode> blockNodes, HashMap<String, AccessoryNode> accessories) {
            this.blockNodes = blockNodes;
            this.accessories = accessories;
        }

        public static MultiBlockPosBox readJson(JsonObject jsonObject) {

            JsonArray blockNodeList = JsonUtils.getJsonArray(jsonObject, "block_node_list");

            HashMap<String, AccessoryNode> accessoryArrayList = new HashMap<>();
            ArrayList<BlockNode> blockNodes = new ArrayList<>();


            for (JsonElement jsonElement : blockNodeList) {
                JsonObject object = jsonElement.getAsJsonObject();
                if (object.has("name")) {
                    accessoryArrayList.put(object.get("name").getAsString(), AccessoryNode.fromJsonObject(object.getAsJsonObject("object")));
                }
                blockNodes.add(BlockNode.fromJsonObject(object));
            }

            return new MultiBlockPosBox(blockNodes, accessoryArrayList);

        }

        public ArrayList<BlockNode> getBlockNodes() {
            return blockNodes;
        }

        public HashMap<String, AccessoryNode> getAccessories() {
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

        public boolean matchAll(BlockPos pos, Level world) {

            for (BlockNode blockNode : blockNodes) {
                BlockPos blockPos = pos.offset(blockNode.pos);
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

        public MultiBlockPosBox rotateTo(Direction direction) {
            ArrayList<BlockNode> blockNodes = new ArrayList<>();
            HashMap<String, AccessoryNode> accessories = new HashMap<>();

            for (Map.Entry<String, AccessoryNode> accessory : this.accessories.entrySet()) {
                accessories.put(accessory.getKey(), accessory.getValue().rotateToDirectionAndReturnValue(direction));
            }
            for (BlockNode blockNode : this.blockNodes) {
                blockNodes.add(blockNode.rotateToDirectionAndReturnValue(direction));
            }

            return new MultiBlockPosBox(blockNodes, accessories);
        }

        public static class BlockNode {
            protected Vec3i pos;
            protected final Block block;

            public BlockNode(Vec3i pos, Block block) {
                this.pos = pos;
                this.block = block;
            }

            public static BlockNode fromJsonObject(JsonObject jsonObject) {
                return new BlockNode(MathUtil.getPosFromJsonObject(jsonObject), ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(jsonObject.get("block").getAsString())));
            }

            public Vec3i getPos() {
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
                Vec3i pos = MathUtil.rotateBlockPosToDirection(this.pos, direction);
                return new BlockNode(pos, block);
            }

        }

        public static class AccessoryNode extends BlockNode {

            private final Direction direction;
            private final String arg1;
            private final String arg2;
            private final String arg3;


            public AccessoryNode(BlockPos pos, Block block, Direction direction, String arg1, String arg2, String arg3) {
                super(pos, block);
                this.direction = direction;
                this.arg1 = arg1;
                this.arg2 = arg2;
                this.arg3 = arg3;
            }

            public AccessoryNode(BlockNode node, Direction direction, String arg1, String arg2, String arg3) {
                super(node.pos, node.block);
                this.direction = direction;
                this.arg1 = arg1;
                this.arg2 = arg2;
                this.arg3 = arg3;
            }

            @Override
            public JsonObject toJsonObject() {
                JsonObject jsonObject = super.toJsonObject();
                jsonObject.addProperty("direction", direction.getHorizontalIndex());
                jsonObject.addProperty("arg1", arg1);
                jsonObject.addProperty("arg2", arg2);
                jsonObject.addProperty("arg3", arg3);

                return jsonObject;
            }

            public static AccessoryNode fromJsonObject(JsonObject jsonObject) {

                Direction direction = Direction.byHorizontalIndex(jsonObject.get("direction").getAsInt());
                String arg1 = jsonObject.get("arg1").getAsString();
                String arg2 = jsonObject.get("arg2").getAsString();
                String arg3 = jsonObject.get("arg3").getAsString();
                return new AccessoryNode(BlockNode.fromJsonObject(jsonObject), direction, arg1, arg2, arg3);
            }

            public void rotateToDirection(Direction direction) {
                this.pos = MathUtil.rotateBlockPosToDirection(this.pos, direction);
            }

            public AccessoryNode rotateToDirectionAndReturnValue(Direction direction) {
                return new AccessoryNode(super.rotateToDirectionAndReturnValue(direction), direction, arg1, arg2, arg3);
            }

            public Direction getDirection() {
                return direction;
            }

            public String getArg1() {
                return arg1;
            }

            public String getArg2() {
                return arg2;
            }

            public String getArg3() {
                return arg3;
            }

            public boolean test(BlockState blockState) {
                return blockState.getBlock() == block && blockState.getValue(BlockAccessoryPlaceHolder.FACING) == direction;
            }
        }


    }
}
