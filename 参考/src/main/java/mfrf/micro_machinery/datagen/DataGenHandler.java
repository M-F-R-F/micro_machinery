package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.worldgen.vein.VeinFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenHandler {
    private static final RegistryBuilder<Feature<?>> BUILDER = new RegistryBuilder<Feature<?>>()
            .add()
            .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

    public static VeinFeatureConfig addConfig(VeinFeatureConfig config) {
        registerys.add(config);
        return config;
    }

}
