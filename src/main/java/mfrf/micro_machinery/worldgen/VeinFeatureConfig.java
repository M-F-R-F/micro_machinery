package mfrf.micro_machinery.worldgen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class VeinFeatureConfig implements FeatureConfiguration {
    public static final Codec<VeinFeatureConfig> CODEC = RecordCodecBuilder.create((p_159816_) -> {
        return p_159816_.group(
                Codec.doubleRange(0, 1).fieldOf("veinGenChance").forGetter(VeinFeatureConfig::getVeinGenChance),
                Codec.doubleRange(0, 1).fieldOf("generate_chance_per_ore").forGetter(VeinFeatureConfig::getGenerateChancePerOre),
                Codec.intRange(1, 32).fieldOf("range").forGetter(VeinFeatureConfig::getRange),
                Codec.intRange(0, 256).fieldOf("ore_layers").forGetter(VeinFeatureConfig::getOreLayers),
                Codec.intRange(0, 256).fieldOf("ore_deposit_height").forGetter(VeinFeatureConfig::getOreDepositHeight),
                Codec.intRange(0, 256).fieldOf("stone_height").forGetter(VeinFeatureConfig::getStoneHeight),
                Codec.intRange(0, 256).fieldOf("min_height").forGetter(VeinFeatureConfig::getMinHeight),
                Codec.intRange(0, 256).fieldOf("max_height").forGetter(VeinFeatureConfig::getMaxHeight),
                Codec.compoundList(
                        Codec.doubleRange(0, 1).fieldOf("chance").orElse(0d).codec(),
                        ForgeRegistries.BLOCKS.getCodec().fieldOf("block_resource_location").orElse(Blocks.AIR).codec()
                ).fieldOf("ore_gen_list").forGetter(veinFeatureConfig -> veinFeatureConfig.oreGenList),
                Codec.list(ResourceLocation.CODEC).fieldOf("predicates_tag").forGetter(VeinFeatureConfig::getPredicateLocations)
        ).apply(p_159816_, VeinFeatureConfig::new);
    });
    private final Double veinGenChance;
    private final Double generateChancePerOre;
    private final int range;
    private final int oreLayers;
    private final int oreDepositHeight;
    private final int stoneHeight;
    private final int minHeight;
    private final int maxHeight;
    private final int veinHeight;
    private final List<Pair<Double, Block>> oreGenList;
    private final List<TagKey<Block>> predicate;

    public VeinFeatureConfig(Double veinGenChance, Double generateChancePerOre, int range, int oreLayers, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, List<Pair<Double, Block>> oreGenList, List<ResourceLocation> predicate) {
        this.veinGenChance = veinGenChance;
        this.generateChancePerOre = generateChancePerOre;
        this.oreLayers = oreLayers;
        this.oreDepositHeight = oreDepositHeight;
        this.stoneHeight = stoneHeight;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinHeight = (oreDepositHeight + stoneHeight) * oreLayers - stoneHeight;
        this.oreGenList = oreGenList;
        this.predicate = predicate.stream().map(BlockTags::create).collect(Collectors.toList());
        this.range = range;
    }

//    public VeinFeatureConfig(Dynamic<?> dynamic) {
//        this.veinGenChance = dynamic.get("vein_gen_chance").asDouble(0);
//        this.generateChancePerOre = dynamic.get("generate_chance_per_ore").asDouble(0);
//        this.range = dynamic.get("range").asInt(0);
//        this.oreLayers = dynamic.get("ore_stratum").asInt(0);
//        this.oreDepositHeight = dynamic.get("ore_deposit_height").asInt(0);
//        this.stoneHeight = dynamic.get("stone_height").asInt(0);
//        this.minHeight = dynamic.get("min_height").asInt(0);
//        this.veinHeight = dynamic.get("vein_height").asInt(0);
//        this.maxHeight = dynamic.get("max_height").asInt(0);
//        this.oreGenList = dynamic.get("ore_gen_list").asMap(dynamic1 -> dynamic1.asDouble(0), dynamic2 -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(dynamic2.asString(null))));
//        this.predicate = Predicates.valueOf(dynamic.get("predicate").asString("STONE"));
//    }

    public Double getVeinGenChance() {
        return veinGenChance;
    }

    public Double getGenerateChancePerOre() {
        return generateChancePerOre;
    }

    public int getRange() {
        return range;
    }

    public int getOreLayers() {
        return oreLayers;
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

    public List<Pair<Double, Block>> getOreGenList() {
        return oreGenList;
    }

    public List<TagKey<Block>> getPredicate() {
        return predicate;
    }

    public List<ResourceLocation> getPredicateLocations() {
        return predicate.stream().map(TagKey::location).collect(Collectors.toList());
    }

//    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
//        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
//        builder.put(ops.createString("vein_gen_chance"), ops.createDouble(veinGenChance));
//        builder.put(ops.createString("generate_chance_per_ore"), ops.createDouble(generateChancePerOre));
//        builder.put(ops.createString("range"), ops.createInt(range));
//        builder.put(ops.createString("ore_stratum"), ops.createInt(oreLayers));
//        builder.put(ops.createString("ore_deposit_height"), ops.createInt(oreDepositHeight));
//        builder.put(ops.createString("stone_height"), ops.createInt(stoneHeight));
//        builder.put(ops.createString("min_height"), ops.createInt(minHeight));
//        builder.put(ops.createString("vein_height"), ops.createInt(veinHeight));
//        builder.put(ops.createString("ore_gen_list"), ops.createMap(oreGenList.stream().map(doubleBlockPair -> Pair.of(doubleBlockPair.getFirst(), doubleBlockPair.getSecond().getRegistryName().toString()))));
//        builder.put(ops.createString("predicate"), ops.createString(predicate.name()));
//        return new Dynamic<>(ops, ops.createMap(builder.build()));
//    }


}
