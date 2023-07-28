package mfrf.micro_machinery.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mfrf.micro_machinery.registry_lists.worldgen.MMStructureThings;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.SinglePieceStructure;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.Optional;

public class VeinStructure extends SinglePieceStructure {
    public VeinStructureConfig getConfig() {
        return config;
    }

    public static final Codec<VeinStructure> CODEC = RecordCodecBuilder.<VeinStructure>mapCodec(instance ->
            instance.group(VeinStructure.settingsCodec(instance),
                    VeinStructureConfig.CODEC.fieldOf("vein_config").forGetter(VeinStructure::getConfig)
            ).apply(instance, VeinStructure::new)).codec();

    private VeinStructureConfig config;

    public VeinStructure(StructureSettings settings, VeinStructureConfig config) {
        super((pRandom, p_226551_, p_226552_) -> new VeinPiece(config, pRandom, p_226551_, p_226552_), config.getRange(), config.getVeinHeight(), settings);
        this.config = config;
        //todo 修改为structure
    }

    @Override
    public StructureType<?> type() {
        return MMStructureThings.VEIN_FEATURE.get();
    }

}
