package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.ArrayList;
import java.util.List;

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
