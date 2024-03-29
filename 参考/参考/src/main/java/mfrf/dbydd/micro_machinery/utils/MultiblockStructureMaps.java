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
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MultiblockStructureMaps extends JsonReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static HashMap<String, StructureMap> structures = null;
    private static HashMap<String, MMBlockMainPartBase> structure_main_block_maps = new HashMap<>();

    public MultiblockStructureMaps() {
        super(GSON, "structures/new_system");
    }

    public static void combine(String name, MMBlockMainPartBase block) {
        structure_main_block_maps.put(name, block);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        structures = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonObject> resourceLocationJsonObjectEntry : objectIn.entrySet()) {

            JsonObject jsonObject = resourceLocationJsonObjectEntry.getValue();

            String identifier = JSONUtils.getString(jsonObject, "identifier");
            structures.put(identifier, new StructureMap(jsonObject));
        }
    }

    public static HashMap<String, StructureMap> getStructures() {
        return structures;
    }

    @Nullable
    public static Triple<String, StructureMap, Direction> findStructure(World world, BlockPos pos) {
        Block center = world.getBlockState(pos).getBlock();
        for (Map.Entry<String, StructureMap> stringStructureMapEntry : getStructures().entrySet()) {
            Direction direction = stringStructureMapEntry.getValue().checkAllDirection(world, pos, center);
            if (direction != null) {
                return Triple.of(stringStructureMapEntry.getKey(), stringStructureMapEntry.getValue(), direction);
            }
        }
        return null;
    }

    public static MMBlockMainPartBase getMainPart(String id) {
        return structure_main_block_maps.get(id);
    }

    public static class StructureMap {
        private final HashMap<Direction, HashMap<Vec3i, Pair<Block, Vec3i>>> mapWithDirections = new HashMap<>(4);

        public StructureMap(JsonObject object) {
            HashMap<Vec3i, Pair<Block, Vec3i>> vec3iBlockHashMap = readJson(object);
            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, vec3iBlockHashMap)));
        }

        public StructureMap(HashMap<Vec3i, Pair<Block, Vec3i>> map) {
            Direction.Plane.HORIZONTAL.forEach(direction -> mapWithDirections.put(direction, rotateTo(direction, map)));
        }

        //default direction is north;
        public static HashMap<Vec3i, Pair<Block, Vec3i>> readJson(JsonObject object) {
            HashMap<Vec3i, Pair<Block, Vec3i>> blockPosBlockHashMap = new HashMap<>();

            JsonArray block_nodes = JSONUtils.getJsonArray(object, "blocks");
            for (JsonElement element : block_nodes) {
                JsonObject blockNode = (JsonObject) element;
                Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(blockNode.get("block").getAsString()));
                Vec3i vec3i = new Vec3i(
                        JSONUtils.getInt(blockNode, "xO"),
                        JSONUtils.getInt(blockNode, "yO"),
                        JSONUtils.getInt(blockNode, "zO"));

                blockPosBlockHashMap.put(vec3i, Pair.of(block, vec3i));
            }
            return blockPosBlockHashMap;
        }

        public JsonObject toJson(String id) {
            HashMap<Vec3i, Pair<Block, Vec3i>> map = mapWithDirections.get(Direction.NORTH);
            JsonObject jsonObject = new JsonObject();

            JsonArray jsonElements = new JsonArray();
            map.forEach((vec3i, block) -> {
                JsonObject blockNode = new JsonObject();
                blockNode.addProperty("block", block.getKey().getRegistryName().toString());
                blockNode.addProperty("xO", vec3i.getX());
                blockNode.addProperty("yO", vec3i.getY());
                blockNode.addProperty("zO", vec3i.getZ());
                jsonElements.add(blockNode);
            });

            jsonObject.addProperty("identifier", id);
            jsonObject.add("blocks", jsonElements);
            return jsonObject;
        }

        private static HashMap<Vec3i, Pair<Block, Vec3i>> rotateTo(Direction direction, final HashMap<Vec3i, Pair<Block, Vec3i>> from) {
            HashMap<Vec3i, Pair<Block, Vec3i>> directionalMap = new HashMap<>();
            for (Map.Entry<Vec3i, Pair<Block, Vec3i>> vec3iBlockEntry : from.entrySet()) {
                directionalMap.put(MathUtil.rotateBlockPosToDirection(vec3iBlockEntry.getKey(), direction), Pair.of(vec3iBlockEntry.getValue().getKey(), vec3iBlockEntry.getValue().getValue()));
            }
            return directionalMap;
        }

        @Nullable
        private Direction checkAllDirection(World world, BlockPos center, Block centerBlock) {
            if (centerBlock != mapWithDirections.get(Direction.NORTH).get(Vec3i.NULL_VECTOR).getKey()) {
                return null;
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                HashMap<Vec3i, Pair<Block, Vec3i>> vec3iBlockHashMap = mapWithDirections.get(direction);

                if (check(world, center, vec3iBlockHashMap)) {
                    return direction;
                }
            }
            return null;
        }

        public static boolean check(World world, BlockPos center, HashMap<Vec3i, Pair<Block, Vec3i>> map) {
            for (Map.Entry<Vec3i, Pair<Block, Vec3i>> vec3iBlockEntry : map.entrySet()) {
                if (world.getBlockState(center.add(vec3iBlockEntry.getKey())).getBlock() != vec3iBlockEntry.getValue().getKey()) {
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

                                blockComponentInterface.link(center, world, vec3iBlockEntry.getValue().getValue(), currentPos);
                            }
                        }
                    });
        }
    }

    public static StructureMap create(World world, BlockPos pos1, BlockPos pos2, BlockPos center) {
        HashMap<Vec3i, Pair<Block, Vec3i>> map = new HashMap<>();
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
                        Vec3i key = new Vec3i(x - center.getX(), y - center.getY(), z - center.getZ());
                        map.put(key, Pair.of(blockState.getBlock(), key));
                    }
                }
            }
        }
        return new StructureMap(map);
    }
}
