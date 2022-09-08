package mfrf.dbydd.micro_machinery.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.util.Direction;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiblockStructureMaps {
    private static HashMap<String, StructureMap> structures = null;

    private void readStructures() {
        structures = new HashMap<>();
        try {
            List<IResource> resources = Minecraft.getInstance().getResourceManager().getAllResources(new ResourceLocation(Micro_Machinery.NAME, "structures/new_system/"));
            for (IResource resource : resources) {
                JsonObject jsonObject = JSONUtils.fromJson(new InputStreamReader(resource.getInputStream()));

                String identifier = JSONUtils.getString(jsonObject, "identifier");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HashMap<String, StructureMap> getStructures() {
        if (structures == null) {
            readStructures();
        }
        return structures;
    }

    @Nullable
    public Pair<String, StructureMap> findStructure(World world, BlockPos pos) {
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
            MMBlockMainPartBase.MAP.get(id).pack(world, center, direction);

            mapWithDirections.get(direction).entrySet().stream()
                    .filter(vec3iBlockEntry -> vec3iBlockEntry.getKey().equals(Vec3i.NULL_VECTOR))
                    .forEach(vec3iBlockEntry -> {
                        BlockPos currentPos = center.add(vec3iBlockEntry.getKey());
                        if (!(world.getBlockState(currentPos).getBlock() instanceof MMBlockMultiBlockComponentInterface)) {
                            MMBlockMultiBlockPart
                        } else {
                            MMBlockMultiBlockComponentInterface blockComponentInterface = (MMBlockMultiBlockComponentInterface) world.getBlockState(currentPos).getBlock();
                            blockComponentInterface.link(center, world, currentPos);
                        }
                    });
        }
    }


}
