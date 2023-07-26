package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.worldgen.MMPlacedConfiguredFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//        BlockPropertys blockPropertys = new BlockPropertys(packOutput, lookupProvider, MicroMachinery.MODID, event.getExistingFileHelper());

//        generator.addProvider(event.includeServer(), blockPropertys);
//        generator.addProvider(event.includeClient(), new BlockTextures(packOutput, event.getExistingFileHelper()));
//        generator.addProvider(event.includeClient(), new ItemTextures(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new MiscDataEntryGeneration(packOutput, lookupProvider));
    }
}
