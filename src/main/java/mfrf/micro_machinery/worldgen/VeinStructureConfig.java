package mfrf.micro_machinery.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import com.mojang.datafixers.util.Pair;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public class VeinStructureConfig implements FeatureConfiguration, INBTSerializable<CompoundTag> {
    public static final Codec<VeinStructureConfig> CODEC = RecordCodecBuilder.create((p_67849_) -> {
        return p_67849_.group(
                Codec.pair(BlockState.CODEC.fieldOf("block_state").codec(), Codec.DOUBLE.fieldOf("probabilities").codec()).listOf().fieldOf("block_map").forGetter((p_161027_) -> {
                    return p_161027_.blocks;
                }),
                Codec.doubleRange(0, 1D).fieldOf("vein_gen_chance").forGetter(
                        VeinStructureConfig::getVeinGenChance
                ),
                Codec.doubleRange(0, 1D).fieldOf("generate_chance_per_ore").forGetter(
                        VeinStructureConfig::getGenerateChancePerOre
                ),
                Codec.intRange(0, 128).fieldOf("range").forGetter(
                        VeinStructureConfig::getRange
                ),
                Codec.intRange(0, 128).fieldOf("ore_stratum").forGetter(
                        VeinStructureConfig::getOreStratum
                ),
                Codec.intRange(0, 128).fieldOf("ore_deposit_height").forGetter(
                        VeinStructureConfig::getOreDepositHeight
                ),
                Codec.intRange(0, 128).fieldOf("stone_height").forGetter(
                        VeinStructureConfig::getStoneHeight
                ),
                Codec.intRange(0, 128).fieldOf("min_height").forGetter(
                        VeinStructureConfig::getMinHeight
                ),
                Codec.intRange(0, 128).fieldOf("max_height").forGetter(
                        VeinStructureConfig::getMaxHeight
                ),
                RuleTest.CODEC.fieldOf("predicate").forGetter(
                        VeinStructureConfig::getPredicate
                )
        ).apply(p_67849_, VeinStructureConfig::new);
    });
    private List<Pair<BlockState, Double>> blocks;
    private Double veinGenChance;
    private Double generateChancePerOre;
    private int range;
    private int oreStratum;
    private int oreDepositHeight;
    private int stoneHeight;
    private int minHeight;
    private int maxHeight;
    private int veinHeight;
    private RuleTest predicate;

    public VeinStructureConfig(List<Pair<BlockState, Double>> blocks, Double veinGenChance, Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, RuleTest predicate) {
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

    public VeinStructureConfig() {
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

    public List<Pair<BlockState, Double>> getBlocks() {
        return blocks;
    }

    public RuleTest getPredicate() {
        return predicate;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
