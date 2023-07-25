//package mfrf.micro_machinery.mixins;//package mfrf.micro_machinery.mixins;
//
//import net.minecraft.block.Blocks;
//import net.minecraft.data.worldgen.BiomeDefaultFeatures;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.DefaultBiomeFeatures;
//import net.minecraft.world.gen.GenerationStage;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraft.world.gen.placement.CountRangeConfig;
//import net.minecraft.world.gen.placement.DepthAverageConfig;
//import net.minecraft.world.gen.placement.Placement;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(BiomeDefaultFeatures.class)
//public abstract class MixinDefaultBiomeFeatures {
//
//    @Inject(
//            method = "addOres",
//            at = @At("HEAD"),
//            cancellable = true,
//            remap = false
//    )
//    private static void onAddOres(Biome biomeIn, CallbackInfo ci) {
//        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.defaultBlockState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 16))));
//        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.defaultBlockState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
//        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.defaultBlockState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
//        ci.cancel();
//    }
//
//}
