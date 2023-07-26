package mfrf.micro_machinery.mixins;//package mfrf.micro_machinery.mixins;


import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.worldgen.MMConfiguredFeatures;
import mfrf.micro_machinery.registry_lists.worldgen.MMPlacedConfiguredFeatures;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDefaultFeatures.class)
public abstract class MixinDefaultBiomeFeatures {

    @Inject(
            method = "addDefaultOres(Lnet/minecraft/world/level/biome/BiomeGenerationSettings$Builder;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void onAddOres(BiomeGenerationSettings.Builder pBuilder, CallbackInfo ci) {
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.COPPER.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.TIN.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.IRON.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.GOLD.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.COAL.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.ILMENITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.SILVER.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.PYROLUSITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.CHROMITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.BAUXITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NICKEL.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NOLANITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.FERROMANGANESE.key());
        MicroMachinery.LOGGER.info("mixined ores");
    }

    @Inject(
            method = "addNetherDefaultOres(Lnet/minecraft/world/level/biome/BiomeGenerationSettings$Builder;)V",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private static void onAddNetherOres(BiomeGenerationSettings.Builder pBuilder, CallbackInfo ci) {
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NETHERPYROLUSITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NETHERGRAPHITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NETHERBAUXITE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NETHERFERROMANGANESE.key());
        pBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MMPlacedConfiguredFeatures.NETHERNOLANITE.key());
    }

}
