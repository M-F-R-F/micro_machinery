package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.worldgen.MMConfiguredFeatures;
import mfrf.micro_machinery.registry_lists.worldgen.MMPlacedConfiguredFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MiscDataEntryGeneration extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, MMConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, MMPlacedConfiguredFeatures::bootstrap);

    public MiscDataEntryGeneration(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", MicroMachinery.MODID));
    }
}
