package mfrf.micro_machinery.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;
import java.util.Map;

public class VeinFeatureConfig implements FeatureConfiguration {
    public static final Codec<VeinFeatureConfig> CODEC = RecordCodecBuilder.create((p_67849_) -> {
        return p_67849_.group(
                Codec.simpleMap(Codec.DOUBLE, BlockState.CODEC, Keyable.forStrings(List.of("chance")::stream)).fieldOf("block_map").forGetter((p_161027_) -> {
                    return p_161027_.blocks;
                }),
                Codec.doubleRange(0, 1D).fieldOf("vein_gen_chance").forGetter(
                        VeinFeatureConfig::getVeinGenChance
                ),
                Codec.doubleRange(0, 1D).fieldOf("generate_chance_per_ore").forGetter(
                        VeinFeatureConfig::getGenerateChancePerOre
                ),
                Codec.intRange(0, 128).fieldOf("range").forGetter(
                        VeinFeatureConfig::getRange
                ),
                Codec.intRange(0, 128).fieldOf("ore_stratum").forGetter(
                        VeinFeatureConfig::getOreStratum
                ),
                Codec.intRange(0, 128).fieldOf("ore_deposit_height").forGetter(
                        VeinFeatureConfig::getOreDepositHeight
                ),
                Codec.intRange(0, 128).fieldOf("stone_height").forGetter(
                        VeinFeatureConfig::getStoneHeight
                ),
                Codec.intRange(0, 128).fieldOf("min_height").forGetter(
                        VeinFeatureConfig::getMinHeight
                ),
                Codec.intRange(0, 128).fieldOf("max_height").forGetter(
                        VeinFeatureConfig::getMaxHeight
                ),
                RuleTest.CODEC.fieldOf("predicate").forGetter(
                        VeinFeatureConfig::getPredicate
                )
        ).apply(p_67849_, VeinFeatureConfig::new);
    });
    private final Map<Double, BlockState> blocks;
    private final Double veinGenChance;
    private final Double generateChancePerOre;
    private final int range;
    private final int oreStratum;
    private final int oreDepositHeight;
    private final int stoneHeight;
    private final int minHeight;
    private final int maxHeight;
    private final int veinHeight;
    private final RuleTest predicate;

    public VeinFeatureConfig(Map<Double, BlockState> blocks, Double veinGenChance, Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, RuleTest predicate) {
        this.blocks = blocks;
        this.veinGenChance = veinGenChance;
        this.generateChancePerOre = generateChancePerOre;
        this.oreStratum = oreStratum;
        this.oreDepositHeight = oreDepositHeight;
        this.stoneHeight = stoneHeight;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinHeight = (oreDepositHeight + stoneHeight) * oreStratum - stoneHeight;
        this.predicate = predicate;
        if (range > 32) {
            this.range = 32;
        } else this.range = range;
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

    public Map<Double, BlockState> getBlocks() {
        return blocks;
    }

    public RuleTest getPredicate() {
        return predicate;
    }

}
