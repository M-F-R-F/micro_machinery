package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.*;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MultiblockStructureMaps extends JsonReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static HashMap<String, StructureMap> structures = null;

    public MultiblockStructureMaps() {
        super(GSON, "structures/new_system");
    }

//    private static void readStructures() {
//        structures = new HashMap<>();
//        try {
//            List<IResource> resources = Minecraft.getInstance().getIntegratedServer().getResourceManager().getAllResources(new ResourceLocation(Micro_Machinery.NAME, "structures/new_system/"));
//            for (IResource resource : resources) {
//                JsonObject jsonObject = JSONUtils.fromJson(new InputStreamReader(resource.getInputStream()));
//
//                String identifier = JSONUtils.getString(jsonObject, "identifier");
//                structures.put(identifier, new StructureMap(jsonObject));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        //todo damn, fixit
        structures = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonObject> resourceLocationJsonObjectEntry : objectIn.entrySet()) {

            JsonObject jsonObject = resourceLocationJsonObjectEntry.getValue();

            String identifier = JSONUtils.getString(jsonObject, "identifier");
            structures.put(identifier, new StructureMap(jsonObject));
        }
    }

    public static HashMap<String, StructureMap> getStructures() {
////        if (structures == null) {
//        readStructures();
////        }
        return structures;
    }

    @Nullable
    public static Pair<String, StructureMap> findStructure(World world, BlockPos pos) {
        Block center = world.getBlockState(pos).getBlock();
        for (Map.Entry<String, StructureMap> stringStructureMapEntry : getStructures().entrySet()) {
            HashMap<Vec3i, Block> vec3iBlockHashMap = stringStructureMapEntry.getValue().checkAllDirection(world, pos, center);
            if (vec3iBlockHashMap != null) {
                return Pair.of(stringStructureMapEntry.getKey(), stringStructureMapEntry.getValue());
            }
        }
        return null;
    }


    public static class StructureMap {
        private final HashMap<Direction, HashMap<Vec3i, Block>> mapWithDirections = new HashMap<>(4);

        public StructureMap(JsonObject object) {
            HashMap<Vec3i, Block> vec3iBlockHashMap = readJson(object);
            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, vec3iBlockHashMap)));
        }

        public StructureMap(HashMap<Vec3i, Block> map) {
            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, map)));
        }

        //default direction is north;
        //todo toJson
        public static HashMap<Vec3i, Block> readJson(JsonObject object) {
            HashMap<Vec3i, Block> blockPosBlockHashMap = new HashMap<>();

            JsonArray block_nodes = JSONUtils.getJsonArray(object, "blocks");
            for (JsonElement element : block_nodes) {
                JsonObject blockNode = (JsonObject) element;
                Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(blockNode.get("block").getAsString()));
                blockPosBlockHashMap.put(new Vec3i(
                                JSONUtils.getInt(blockNode, "xO"),
                                JSONUtils.getInt(blockNode, "yO"),
                                JSONUtils.getInt(blockNode, "zO")
                        ),
                        block);
            }
            return blockPosBlockHashMap;
        }

        public JsonObject toJson(String id) {
            HashMap<Vec3i, Block> map = mapWithDirections.get(Direction.NORTH);
            JsonObject jsonObject = new JsonObject();

            JsonArray jsonElements = new JsonArray();
            map.forEach((vec3i, block) -> {
                JsonObject blockNode = new JsonObject();
                blockNode.addProperty("block", block.getRegistryName().toString());
                blockNode.addProperty("xO", vec3i.getX());
                blockNode.addProperty("yO", vec3i.getY());
                blockNode.addProperty("zO", vec3i.getZ());
                jsonElements.add(blockNode);
            });

            jsonObject.addProperty("identifier", id);
            jsonObject.add("blocks", jsonElements);
            return jsonObject;
        }

        private static HashMap<Vec3i, Block> rotateTo(Direction direction, final HashMap<Vec3i, Block> from) {
            HashMap<Vec3i, Block> directionalMap = new HashMap<>();
            for (Map.Entry<Vec3i, Block> vec3iBlockEntry : from.entrySet()) {
                directionalMap.put(MathUtil.rotateBlockPosToDirection(vec3iBlockEntry.getKey(), direction), vec3iBlockEntry.getValue());
            }
            return directionalMap;
        }

        @Nullable
        public HashMap<Vec3i, Block> checkAllDirection(World world, BlockPos center, Block centerBlock) {
            if (centerBlock != mapWithDirections.get(Direction.NORTH).get(Vec3i.NULL_VECTOR)) {
                return null;
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                HashMap<Vec3i, Block> vec3iBlockHashMap = mapWithDirections.get(direction);

                if (check(world, center, vec3iBlockHashMap)) {
                    return vec3iBlockHashMap;
                }
            }
            return null;
        }

        public static boolean check(World world, BlockPos center, HashMap<Vec3i, Block> map) {
            for (Map.Entry<Vec3i, Block> vec3iBlockEntry : map.entrySet()) {
                if (world.getBlockState(center.add(vec3iBlockEntry.getKey())).getBlock() != vec3iBlockEntry.getValue()) {
                    return false;
                }
            }
            return true;
        }

        public void construct(Direction direction, World world, BlockPos center, String id) {
            MMBlockMainPartBase.MAP.get(id).pack(world, center, direction, center);

            mapWithDirections.get(direction).entrySet().stream()
                    .forEach(vec3iBlockEntry -> {
                        if (!vec3iBlockEntry.getKey().equals(Vec3i.NULL_VECTOR)) {

                            BlockPos currentPos = center.add(vec3iBlockEntry.getKey());
                            if (!(world.getBlockState(currentPos).getBlock() instanceof MMBlockMultiBlockComponentInterface)) {
                                RegisteredBlocks.MULTIBLOCK_PART.pack(world, currentPos, direction, center);
                            } else {
                                MMBlockMultiBlockComponentInterface blockComponentInterface = (MMBlockMultiBlockComponentInterface) world.getBlockState(currentPos).getBlock();
                                blockComponentInterface.link(center, world, vec3iBlockEntry.getKey(), currentPos);
                            }
                        }
                    });
        }
    }

    public static StructureMap create(World world, BlockPos pos1, BlockPos pos2, BlockPos center) {
        HashMap<Vec3i, Block> map = new HashMap<>();
        int xMax = Math.max(pos1.getX(), pos2.getX());
        int yMax = Math.max(pos1.getY(), pos2.getY());
        int zMax = Math.max(pos1.getZ(), pos2.getZ());

        int xMin = Math.min(pos1.getX(), pos2.getX());
        int yMin = Math.min(pos1.getY(), pos2.getY());
        int zMin = Math.min(pos1.getZ(), pos2.getZ());

        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    BlockState blockState = world.getBlockState(new BlockPos(x, y, z));
                    if (!blockState.isAir()) {
                        map.put(new Vec3i(x - center.getX(), y - center.getY(), z - center.getZ()), blockState.getBlock());
                    }
                }
            }
        }
        return new StructureMap(map);
    }
}
