package mfrf.micro_machinery.registry_lists.worldgen;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class MMConfiguredFeatures {
    private static final HashMap<String, ConfiguredVeinFeatureEntry> registries = new HashMap<>();


    public static final ConfiguredVeinFeatureEntry
            TEST = ConfiguredVeinFeatureEntry.of("test", new VeinFeatureConfig(
            Map.of(
                    .5, Blocks.BEACON.defaultBlockState(),
                    0.5, Blocks.DIAMOND_BLOCK.defaultBlockState()
            ),
            1.0,
            1.0,
            32,
            3,
            16,
            16,
            32,
            64,
            new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD)
    ));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        registries.forEach((name, feature) -> {
            context.register(feature.key, feature.feature.get());
        });
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MicroMachinery.MODID, name.toLowerCase(Locale.ROOT)));
    }

    private record ConfiguredVeinFeatureEntry(ResourceKey<ConfiguredFeature<?, ?>> key,
                                              Supplier<ConfiguredFeature<?, ?>> feature) {
        public static ConfiguredVeinFeatureEntry of(String name, VeinFeatureConfig featureConfig) {
            return new ConfiguredVeinFeatureEntry(registerKey(name), () -> new ConfiguredFeature<>(MMFeatures.VEIN_FEATURE.get(), featureConfig));
        }
    }
}
