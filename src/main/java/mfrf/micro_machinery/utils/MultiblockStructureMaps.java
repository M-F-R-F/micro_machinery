//package mfrf.micro_machinery.utils;
//
//import com.google.gson.*;
//import com.mojang.realmsclient.util.JsonUtils;
//import mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
//import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
//import mfrf.micro_machinery.registry_lists.MMBlocks;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.Vec3i;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.packs.resources.ResourceManager;
//import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
//import net.minecraft.util.profiling.ProfilerFiller;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.registries.ForgeRegistries;
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.commons.lang3.tuple.Triple;
//
//import javax.annotation.Nullable;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MultiblockStructureMaps extends SimpleJsonResourceReloadListener {
//    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
//    private static HashMap<String, StructureMap> structures = null;
//    private static final HashMap<String, MMBlockMainPartBase> structure_main_block_maps = new HashMap<>();
//
//    public MultiblockStructureMaps() {
//        super(GSON, "structures/new_system");
//    }
//
//    @Override
//    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
//        structures = new HashMap<>();
//        for (Map.Entry<ResourceLocation, JsonElement> resourceLocationJsonObjectEntry : objectIn.entrySet()) {
//
//            JsonObject jsonObject = resourceLocationJsonObjectEntry.getValue().getAsJsonObject();
//
//            String identifier = JsonUtils.getStringOr("identifier", jsonObject, "none");
//            if (!identifier.equals("none")) {
//                structures.put(identifier, new StructureMap(jsonObject));
//            }
//        }
//    }
//
//    public static void combine(String name, MMBlockMainPartBase block) {
//        structure_main_block_maps.put(name, block);
//    }
//
//    public static HashMap<String, StructureMap> getStructures() {
//        return structures;
//    }
//
//    @Nullable
//    public static Triple<String, StructureMap, Direction> findStructure(Level world, BlockPos pos) {
//        Block center = world.getBlockState(pos).getBlock();
//        for (Map.Entry<String, StructureMap> stringStructureMapEntry : getStructures().entrySet()) {
//            Direction direction = stringStructureMapEntry.getValue().checkAllDirection(world, pos, center);
//            if (direction != null) {
//                return Triple.of(stringStructureMapEntry.getKey(), stringStructureMapEntry.getValue(), direction);
//            }
//        }
//        return null;
//    }
//
//    public static MMBlockMainPartBase getMainPart(String id) {
//        return structure_main_block_maps.get(id);
//    }
//
//    public static class StructureMap {
//        private final HashMap<Direction, HashMap<Vec3i, Pair<Block, Vec3i>>> mapWithDirections = new HashMap<>(4);
//
//        public StructureMap(JsonObject object) {
//            HashMap<Vec3i, Pair<Block, Vec3i>> vec3iBlockHashMap = readJson(object);
//            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, vec3iBlockHashMap)));
//        }
//
//        public StructureMap(HashMap<Vec3i, Pair<Block, Vec3i>> map) {
//            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, map)));
//        }
//
//        //default direction is north;
//        public static HashMap<Vec3i, Pair<Block, Vec3i>> readJson(JsonObject object) {
//            HashMap<Vec3i, Pair<Block, Vec3i>> blockPosBlockHashMap = new HashMap<>();
//
//            JsonArray block_nodes = object.getAsJsonArray("blocks");
//            for (JsonElement element : block_nodes) {
//                JsonObject blockNode = (JsonObject) element;
//                Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(blockNode.get("block").getAsString()));
//                Vec3i vec3i = new Vec3i(
//                        blockNode.get("xO").getAsInt(),
//                        blockNode.get("yO").getAsInt(),
//                        blockNode.get("zO").getAsInt());
//
//                blockPosBlockHashMap.put(vec3i, Pair.of(block, vec3i));
//            }
//            return blockPosBlockHashMap;
//        }
//
//        public JsonObject toJson(String id) {
//            HashMap<Vec3i, Pair<Block, Vec3i>> map = mapWithDirections.get(Direction.NORTH);
//            JsonObject jsonObject = new JsonObject();
//
//            JsonArray jsonElements = new JsonArray();
//            map.forEach((vec3i, block) -> {
//                JsonObject blockNode = new JsonObject();
////                blockNode.addProperty("block", block.getKey().block().toString());
//                //todo fixit
//                blockNode.addProperty("xO", vec3i.getX());
//                blockNode.addProperty("yO", vec3i.getY());
//                blockNode.addProperty("zO", vec3i.getZ());
//                jsonElements.add(blockNode);
//            });
//
//            jsonObject.addProperty("identifier", id);
//            jsonObject.add("blocks", jsonElements);
//            return jsonObject;
//        }
//
//        private static HashMap<Vec3i, Pair<Block, Vec3i>> rotateTo(Direction direction, final HashMap<Vec3i, Pair<Block, Vec3i>> from) {
//            HashMap<Vec3i, Pair<Block, Vec3i>> directionalMap = new HashMap<>();
//            for (Map.Entry<Vec3i, Pair<Block, Vec3i>> vec3iBlockEntry : from.entrySet()) {
//                directionalMap.put(MathUtil.rotateBlockPosToDirection(vec3iBlockEntry.getKey(), direction), Pair.of(vec3iBlockEntry.getValue().getKey(), vec3iBlockEntry.getValue().getValue()));
//            }
//            return directionalMap;
//        }
//
//        @Nullable
//        private Direction checkAllDirection(Level world, BlockPos center, Block centerBlock) {
//            if (centerBlock != mapWithDirections.get(Direction.NORTH).get(Vec3i.ZERO).getKey()) {
//                return null;
//            }
//            for (Direction direction : Direction.Plane.HORIZONTAL) {
//                HashMap<Vec3i, Pair<Block, Vec3i>> vec3iBlockHashMap = mapWithDirections.get(direction);
//
//                if (check(world, center, vec3iBlockHashMap)) {
//                    return direction;
//                }
//            }
//            return null;
//        }
//
//        public static boolean check(Level world, BlockPos center, HashMap<Vec3i, Pair<Block, Vec3i>> map) {
//            for (Map.Entry<Vec3i, Pair<Block, Vec3i>> vec3iBlockEntry : map.entrySet()) {
//                if (world.getBlockState(center.offset(vec3iBlockEntry.getKey())).getBlock() != vec3iBlockEntry.getValue().getKey()) {
//                    return false;
//                }
//            }
//            return true;
//        }
//
//        public void construct(Direction direction, Level world, BlockPos center, String id) {
////            MMBlockMainPartBase.MAP.get(id).pack(world, center, direction, center);
//
//            mapWithDirections.get(direction).entrySet().stream()
//                    .forEach(vec3iBlockEntry -> {
//                        if (!vec3iBlockEntry.getKey().equals(Vec3i.ZERO)) {
//
//                            BlockPos currentPos = center.offset(vec3iBlockEntry.getKey());
//                            if (!(world.getBlockState(currentPos).getBlock() instanceof MMBlockMultiBlockComponentInterface blockComponentInterface)) {
////                                MMBlocks.MULTIBLOCK_PART.pack(world, currentPos, direction, center);
//                            } else {
//
//                                blockComponentInterface.link(center, world, vec3iBlockEntry.getValue().getValue(), currentPos);
//                            }
//                        }
//                    });
//        }
//    }
//
//    public static StructureMap create(Level world, BlockPos pos1, BlockPos pos2, BlockPos center) {
//        HashMap<Vec3i, Pair<Block, Vec3i>> map = new HashMap<>();
//        int xMax = Math.max(pos1.getX(), pos2.getX());
//        int yMax = Math.max(pos1.getY(), pos2.getY());
//        int zMax = Math.max(pos1.getZ(), pos2.getZ());
//
//        int xMin = Math.min(pos1.getX(), pos2.getX());
//        int yMin = Math.min(pos1.getY(), pos2.getY());
//        int zMin = Math.min(pos1.getZ(), pos2.getZ());
//
//        for (int x = xMin; x <= xMax; x++) {
//            for (int y = yMin; y <= yMax; y++) {
//                for (int z = zMin; z <= zMax; z++) {
//                    BlockState blockState = world.getBlockState(new BlockPos(x, y, z));
//                    if (!blockState.isAir()) {
//                        Vec3i key = new Vec3i(x - center.getX(), y - center.getY(), z - center.getZ());
//                        map.put(key, Pair.of(blockState.getBlock(), key));
//                    }
//                }
//            }
//        }
//        return new StructureMap(map);
//    }
//}
