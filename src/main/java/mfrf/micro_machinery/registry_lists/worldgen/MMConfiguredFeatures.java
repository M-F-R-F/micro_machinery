package mfrf.micro_machinery.registry_lists.worldgen;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class MMConfiguredFeatures {
    public static final HashMap<String, ConfiguredVeinFeatureEntry> STRING_CONFIGURED_VEIN_FEATURE_ENTRY_HASH_MAP = new HashMap<>();
    public static final ArrayList<Pair<BlockState, Double>>
            OreCopper = new ArrayList<>(),
            OreTin = new ArrayList<>(),
            OreIron = new ArrayList<>(),
            OreGold = new ArrayList<>(),
            OreCoal = new ArrayList<>(),
            OreIlmenite = new ArrayList<>(),
            OreSilver = new ArrayList<>(),
            OrePyrolusite = new ArrayList<>(),
            OreChromite = new ArrayList<>(),
            OreBauxite = new ArrayList<>(),
            OreNickel = new ArrayList<>(),
            OreNolanite = new ArrayList<>(),
            OreFerroManganese = new ArrayList<>(),
            NetherOrePyrolusite = new ArrayList<>(),
            NetherOreGraphite = new ArrayList<>(),
            NetherOreBauxite = new ArrayList<>(),
            NetherOreFerroManganese = new ArrayList<>(),
            NetherOreNolanite = new ArrayList<>(),
            EndOreTunstite = new ArrayList<>();


    public static final ConfiguredVeinFeatureEntry
            COPPER = ConfiguredVeinFeatureEntry.of("copper", new VeinFeatureConfig(OreCopper, 0.1, 0.2, 8, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            TIN = ConfiguredVeinFeatureEntry.of("tin", new VeinFeatureConfig(OreTin, 0.008, 0.2, 7, 2, 5, 3, 32, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            IRON = ConfiguredVeinFeatureEntry.of("iron", new VeinFeatureConfig(OreIron, 0.0075, 0.2, 8, 4, 3, 3, 3, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            GOLD = ConfiguredVeinFeatureEntry.of("gold", new VeinFeatureConfig(OreGold, 0.04, 0.2, 6, 3, 3, 3, 3, 32, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            COAL = ConfiguredVeinFeatureEntry.of("coal", new VeinFeatureConfig(OreCoal, 0.012, 0.2, 10, 5, 3, 3, 3, 120, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            ILMENITE = ConfiguredVeinFeatureEntry.of("ilmenite", new VeinFeatureConfig(OreIlmenite, 0.006, 0.2, 6, 3, 3, 3, 3, 32, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            SILVER = ConfiguredVeinFeatureEntry.of("silver", new VeinFeatureConfig(OreSilver, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            PYROLUSITE = ConfiguredVeinFeatureEntry.of("pyrolusite", new VeinFeatureConfig(OrePyrolusite, 0.004, 0.2, 6, 2, 4, 3, 16, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            CHROMITE = ConfiguredVeinFeatureEntry.of("chromite", new VeinFeatureConfig(OreChromite, 0.005, 0.2, 7, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            BAUXITE = ConfiguredVeinFeatureEntry.of("bauxite", new VeinFeatureConfig(OreBauxite, 0.007, 0.2, 6, 1, 4, 3, 32, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NICKEL = ConfiguredVeinFeatureEntry.of("nickel", new VeinFeatureConfig(OreNickel, 0.0055, 0.2, 8, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NOLANITE = ConfiguredVeinFeatureEntry.of("nolanite", new VeinFeatureConfig(OreNolanite, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            FERROMANGANESE = ConfiguredVeinFeatureEntry.of("ferromanganese", new VeinFeatureConfig(OreFerroManganese, 0.0005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NETHERPYROLUSITE = ConfiguredVeinFeatureEntry.of("netherpyrolusite", new VeinFeatureConfig(NetherOrePyrolusite, 0.005, 0.2, 6, 3, 3, 3, 60, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES))),
            NETHERGRAPHITE = ConfiguredVeinFeatureEntry.of("nethergraphite", new VeinFeatureConfig(NetherOreGraphite, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES))),
            NETHERBAUXITE = ConfiguredVeinFeatureEntry.of("netherbauxite", new VeinFeatureConfig(NetherOreBauxite, 0.005, 0.2, 6, 3, 3, 3, 48, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES))),
            NETHERFERROMANGANESE = ConfiguredVeinFeatureEntry.of("netherferromanganese", new VeinFeatureConfig(NetherOreFerroManganese, 0.005, 0.2, 6, 3, 3, 3, 3, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES))),
            NETHERNOLANITE = ConfiguredVeinFeatureEntry.of("nethernolanite", new VeinFeatureConfig(NetherOreNolanite, 0.005, 0.2, 6, 3, 3, 3, 3, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES))),
            TUNSTITE = ConfiguredVeinFeatureEntry.of("tunstite", new VeinFeatureConfig(EndOreTunstite, 0.0075, 0.04, 8, 5, 3, 3, 3, 62, new BlockMatchTest(Blocks.END_STONE)));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        STRING_CONFIGURED_VEIN_FEATURE_ENTRY_HASH_MAP.forEach((name, feature) -> {
            context.register(feature.key, feature.feature.get());
        });
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MicroMachinery.MODID, name.toLowerCase(Locale.ROOT)));
    }

    record ConfiguredVeinFeatureEntry(ResourceKey<ConfiguredFeature<?, ?>> key,
                                              Supplier<ConfiguredFeature<?, ?>> feature,VeinFeatureConfig config) {
        public static ConfiguredVeinFeatureEntry of(String name, VeinFeatureConfig featureConfig) {
            ConfiguredVeinFeatureEntry configuredVeinFeatureEntry = new ConfiguredVeinFeatureEntry(registerKey(name), () -> new ConfiguredFeature<>(MMFeatures.VEIN_FEATURE.get(), featureConfig),featureConfig);
            STRING_CONFIGURED_VEIN_FEATURE_ENTRY_HASH_MAP.put(name, configuredVeinFeatureEntry);
            return configuredVeinFeatureEntry;
        }
    }


    static {
        OreCopper.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.8d));
        OreCopper.add(Pair.of(MMBlocks.ORETIN.getKey().get().defaultBlockState(),0.15d));
        OreCopper.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(),0.05d));
        OreTin.add(Pair.of(MMBlocks.ORETIN.getKey().get().defaultBlockState(),0.8d));
        OreTin.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.2d));
        OreIron.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.8d));
        OreIron.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(),0.09d));
        OreIron.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(),0.11d));
        OreGold.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(),0.7d));
        OreGold.add(Pair.of(MMBlocks.ORENOLANITE.getKey().get().defaultBlockState(),0.15d));
        OreGold.add(Pair.of(MMBlocks.ORESILVER.getKey().get().defaultBlockState(),0.1d));
        OreGold.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.05d));
        OreCoal.add(Pair.of(Blocks.COAL_ORE.defaultBlockState(),0.8d));
        OreCoal.add(Pair.of(Blocks.COAL_BLOCK.defaultBlockState(),0.1d));
        OreCoal.add(Pair.of(MMBlocks.OREGRAPHITE.getKey().get().defaultBlockState(),0.07d));
        OreCoal.add(Pair.of(Blocks.DIAMOND_ORE.defaultBlockState(),0.03d));
        OreIlmenite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.5d));
        OreIlmenite.add(Pair.of(MMBlocks.OREILMENITE.getKey().get().defaultBlockState(),0.4d));
        OreIlmenite.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(),0.1d));
        OreSilver.add(Pair.of(MMBlocks.ORESILVER.getKey().get().defaultBlockState(),0.7d));
        OreSilver.add(Pair.of(Blocks.LAPIS_ORE.defaultBlockState(),0.2d));
        OreSilver.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.1d));
        OrePyrolusite.add(Pair.of(MMBlocks.OREPYROLUSITE.getKey().get().defaultBlockState(),0.8d));
        OrePyrolusite.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(),0.2d));
        OreChromite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.6d));
        OreChromite.add(Pair.of(MMBlocks.ORECHROMITE.getKey().get().defaultBlockState(),0.3d));
        OreChromite.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(),0.1d));
        OreBauxite.add(Pair.of(MMBlocks.OREBAUXITE.getKey().get().defaultBlockState(),0.6d));
        OreBauxite.add(Pair.of(Blocks.CLAY.defaultBlockState(),0.3d));
        OreBauxite.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.06d));
        OreBauxite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.04d));
        OreNickel.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(),0.6d));
        OreNickel.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.25d));
        OreNickel.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(),0.1d));
        OreNickel.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(),0.05d));
        OreNolanite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.7d));
        OreNolanite.add(Pair.of(MMBlocks.ORENOLANITE.getKey().get().defaultBlockState(),0.2d));
        OreNolanite.add(Pair.of(MMBlocks.OREILMENITE.getKey().get().defaultBlockState(),0.1d));
        OreFerroManganese.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(),0.5d));
        OreFerroManganese.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(),0.3d));
        OreFerroManganese.add(Pair.of(MMBlocks.OREPYROLUSITE.getKey().get().defaultBlockState(),0.2d));
        NetherOrePyrolusite.add(Pair.of(MMBlocks.OREPYROLUSITE_NETHER.getKey().get().defaultBlockState(),0.8d));
        NetherOrePyrolusite.add(Pair.of(MMBlocks.OREFERROMANGANESE_NETHER.getKey().get().defaultBlockState(),0.2d));
        NetherOreGraphite.add(Pair.of(MMBlocks.OREGRAPHITE_NETHER.getKey().get().defaultBlockState(),0.8d));
        NetherOreGraphite.add(Pair.of(Blocks.COAL_BLOCK.defaultBlockState(),0.2d));
        NetherOreBauxite.add(Pair.of(MMBlocks.OREBAUXITE_NETHER.getKey().get().defaultBlockState(),0.7d));
        NetherOreBauxite.add(Pair.of(Blocks.CLAY.defaultBlockState(),0.2d));
        NetherOreBauxite.add(Pair.of(Blocks.GRAVEL.defaultBlockState(),0.1d));
        NetherOreFerroManganese.add(Pair.of(MMBlocks.OREFERROMANGANESE_NETHER.getKey().get().defaultBlockState(),0.7d));
        NetherOreFerroManganese.add(Pair.of(MMBlocks.OREPYROLUSITE_NETHER.getKey().get().defaultBlockState(),0.3d));
        NetherOreNolanite.add(Pair.of(MMBlocks.ORENOLANITE_NETHER.getKey().get().defaultBlockState(),0.6d));
        NetherOreNolanite.add(Pair.of(MMBlocks.OREBAUXITE_NETHER.getKey().get().defaultBlockState(),0.25d));
        NetherOreNolanite.add(Pair.of(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(),0.15d));
        EndOreTunstite.add(Pair.of(MMBlocks.ORETUNSTITE.getKey().get().defaultBlockState(),1.0d));
    }
}
