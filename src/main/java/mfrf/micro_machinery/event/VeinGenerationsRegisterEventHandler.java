package mfrf.micro_machinery.event;

import mfrf.micro_machinery.worldgen.VeinFeature;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeinGenerationsRegisterEventHandler {
    public static List<VeinFeatureConfig> registerys = new ArrayList<>();

    public static VeinFeatureConfig addConfig(VeinFeatureConfig config) {
        registerys.add(config);
        return config;
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        registerys.forEach((config) -> {
            //todo data generator
        });
    }
}
