package mfrf.micro_machinery.worldgen.test;

import com.mojang.serialization.Codec;
import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class TestStructure extends StructureFeature<NoneFeatureConfiguration> {

    public TestStructure(Codec<NoneFeatureConfiguration> p_197165_) {
        super(p_197165_, TestStructure.gen());
    }

    private static PieceGeneratorSupplier<NoneFeatureConfiguration> gen() {
        return pContext -> Optional.of((p_197326_, p_197327_) -> {
            MicroMachinery.LOGGER.info("triggered test generate");
        });
    }
}
