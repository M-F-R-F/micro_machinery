package mfrf.micro_machinery.registry_lists.worldgen;

import it.unimi.dsi.fastutil.Hash;
import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class MMPlacedConfiguredFeatures {
    public static final HashMap<String, PlacedFeatureEntry> PLACED_FEATURES = new HashMap<>();
    public static final HashMap<PlacedFeatureEntry, Holder<PlacedFeature>> HOLDER_FEATURES = new HashMap<>();

    public static final PlacedFeatureEntry
            COPPER = PlacedFeatureEntry.of("copper"),
            TIN = PlacedFeatureEntry.of("tin"),
            IRON = PlacedFeatureEntry.of("iron"),
            GOLD = PlacedFeatureEntry.of("gold"),
            COAL = PlacedFeatureEntry.of("coal"),
            ILMENITE = PlacedFeatureEntry.of("ilmenite"),
            SILVER = PlacedFeatureEntry.of("silver"),
            PYROLUSITE = PlacedFeatureEntry.of("pyrolusite"),
            CHROMITE = PlacedFeatureEntry.of("chromite"),
            BAUXITE = PlacedFeatureEntry.of("bauxite"),
            NICKEL = PlacedFeatureEntry.of("nickel"),
            NOLANITE = PlacedFeatureEntry.of("nolanite"),
            FERROMANGANESE = PlacedFeatureEntry.of("ferromanganese"),
            NETHERPYROLUSITE = PlacedFeatureEntry.of("netherpyrolusite"),
            NETHERGRAPHITE = PlacedFeatureEntry.of("nethergraphite"),
            NETHERBAUXITE = PlacedFeatureEntry.of("netherbauxite"),
            NETHERFERROMANGANESE = PlacedFeatureEntry.of("netherferromanganese"),
            NETHERNOLANITE = PlacedFeatureEntry.of("nethernolanite"),
            TUNSTITE = PlacedFeatureEntry.of("tunstite");


    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MicroMachinery.MODID, name.toLowerCase(Locale.ROOT)));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        for (PlacedFeatureEntry value : PLACED_FEATURES.values()) {
            HOLDER_FEATURES.put(value, context.register(value.key, value.func.apply(features)));
        }
    }

    public record PlacedFeatureEntry(ResourceKey<PlacedFeature> key,
                                     Function<HolderGetter<ConfiguredFeature<?, ?>>, PlacedFeature> func,
                                     @Nullable Holder<PlacedFeature> holder) {
        public static PlacedFeatureEntry of(String name, PlacementModifier... modifiers) {
            PlacedFeatureEntry placedFeatureEntry = new PlacedFeatureEntry(registerKey(name), (features) -> {
                MMConfiguredFeatures.ConfiguredVeinFeatureEntry configuredVeinFeatureEntry = MMConfiguredFeatures.STRING_CONFIGURED_VEIN_FEATURE_ENTRY_HASH_MAP.get(name);
                List<PlacementModifier> placementModifiers = new ArrayList<>();

                placementModifiers.add(HeightRangePlacement.triangle(
                        VerticalAnchor.aboveBottom(configuredVeinFeatureEntry.config().
                                getMinHeight()),
                        VerticalAnchor.aboveBottom(configuredVeinFeatureEntry.config().
                                getMaxHeight()
                        )
                ));
                placementModifiers.add(RandomOffsetPlacement.of(UniformInt.of(0, 16), UniformInt.of(0, 16)));
                placementModifiers.add(RarityFilter.onAverageOnceEvery((int) Math.round(1.0 / configuredVeinFeatureEntry.config().getVeinGenChance())));
//                placementModifiers.add(BiomeFilter.biome());
                placementModifiers.add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE);
                placementModifiers.addAll(Arrays.stream(modifiers).toList());
                PlacedFeature placedFeature = new PlacedFeature(features.getOrThrow(configuredVeinFeatureEntry.key()), placementModifiers);
                return placedFeature;
            }, null
            );
            PLACED_FEATURES.put(name, placedFeatureEntry);
            return placedFeatureEntry;
        }
    }
}
