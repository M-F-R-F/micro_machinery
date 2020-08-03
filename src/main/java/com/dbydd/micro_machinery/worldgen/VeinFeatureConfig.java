package com.dbydd.micro_machinery.worldgen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class VeinFeatureConfig implements IFeatureConfig {
    private final Double veinGenChance;
    private final Double generateChancePerOre;
    private final int range;
    private final int oreStratum;
    private final int oreDepositHeight;
    private final int stoneHeight;
    private final int minHeight;
    private final int maxHeight;
    private final int veinHeight;
    private final Map<Double, Block> oreGenList;
    private final Predicates predicate;

    public VeinFeatureConfig(Double veinGenChance, Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, Map<Double, Block> oreGenList, Predicates predicate) {
        this.veinGenChance = veinGenChance;
        this.generateChancePerOre = generateChancePerOre;
        this.oreStratum = oreStratum;
        this.oreDepositHeight = oreDepositHeight;
        this.stoneHeight = stoneHeight;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinHeight = (oreDepositHeight + stoneHeight) * oreStratum - stoneHeight;
        this.oreGenList = oreGenList;
        this.predicate = predicate;
        if (range > 32) {
            this.range = 32;
        } else this.range = range;
    }

    public VeinFeatureConfig(Dynamic<?> dynamic) {
        this.veinGenChance = dynamic.get("vein_gen_chance").asDouble(0);
        this.generateChancePerOre = dynamic.get("generate_chance_per_ore").asDouble(0);
        this.range = dynamic.get("range").asInt(0);
        this.oreStratum = dynamic.get("ore_stratum").asInt(0);
        this.oreDepositHeight = dynamic.get("ore_deposit_height").asInt(0);
        this.stoneHeight = dynamic.get("stone_height").asInt(0);
        this.minHeight = dynamic.get("min_height").asInt(0);
        this.veinHeight = dynamic.get("vein_height").asInt(0);
        this.maxHeight = dynamic.get("max_height").asInt(0);
        this.oreGenList = dynamic.get("ore_gen_list").<Double, Block>asMap(dynamic1 -> dynamic.asDouble(0), dynamic2 -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(dynamic2.asString(null))));
        this.predicate = Predicates.valueOf(dynamic.get("predicate").asString("STONE"));
    }

    public Double getVeinGenChance() {
        return veinGenChance;
    }

    public Double getGenerateChancePerOre() {
        return generateChancePerOre;
    }

    public int getRange() {
        return range;
    }

    public int getOreStratum() {
        return oreStratum;
    }

    public int getOreDepositHeight() {
        return oreDepositHeight;
    }

    public int getStoneHeight() {
        return stoneHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinHeight() {
        return veinHeight;
    }

    public Map<Double, Block> getOreGenList() {
        return oreGenList;
    }

    public Predicates getPredicate() {
        return predicate;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        ImmutableMap.Builder<T, T> ore_gen_list_builder = ImmutableMap.builder();
        builder.put(ops.createString("vein_gen_chance"), ops.createDouble(veinGenChance));
        builder.put(ops.createString("generate_chance_per_ore"), ops.createDouble(generateChancePerOre));
        builder.put(ops.createString("range"), ops.createInt(range));
        builder.put(ops.createString("ore_stratum"), ops.createInt(oreStratum));
        builder.put(ops.createString("ore_deposit_height"), ops.createInt(oreDepositHeight));
        builder.put(ops.createString("stone_height"), ops.createInt(stoneHeight));
        builder.put(ops.createString("min_height"), ops.createInt(minHeight));
        builder.put(ops.createString("vein_height"), ops.createInt(veinHeight));
        oreGenList.forEach((_Double, block) -> ore_gen_list_builder.put(ops.createDouble(_Double), ops.createString(block.getRegistryName().getPath())))
        ;
        builder.put(ops.createString("ore_gen_list"), ops.createMap(ore_gen_list_builder.build()));
        builder.put(ops.createString("predicate"), ops.createString(predicate.name()));
        return new Dynamic<>(ops, ops.createMap(builder.build()));
    }
}
