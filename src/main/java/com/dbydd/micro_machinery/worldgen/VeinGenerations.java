package com.dbydd.micro_machinery.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeinGenerations {
    public static List<VeinFeatureConfig> registerys = new ArrayList<>();

    public VeinGenerations(VeinFeatureConfig config) {
        registerys.add(config);
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        registerys.forEach((config) -> {
            for (Biome biome : ForgeRegistries.BIOMES) {
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, VeinFeature.VEIN_FEATURE.get().withConfiguration(config).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(config.getVeinGenChance().floatValue(),config.getMinHeight(), config.getVeinHeight()+config.getStoneHeight(),config.getMaxHeight()))));
            }
        });
    }
}
