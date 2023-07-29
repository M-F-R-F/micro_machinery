package mfrf.micro_machinery.registry_lists.worldgen;

import com.mojang.datafixers.util.Pair;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.worldgen.VeinStructure;
import mfrf.micro_machinery.worldgen.VeinStructureConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class MMStructures {
    public static final HashMap<String, StructureComp> MAPS = new HashMap<>();
    public static final HashMap<String, Holder<StructurePlacement>> PLACEMENTS = new HashMap<>();
    public static final ArrayList<Pair<BlockState, Double>>

            OreTin = new ArrayList<>(),
            OreIron = new ArrayList<>(),
            OreGold = new ArrayList<>(),
            OreCoal = new ArrayList<>(),
            OreIlmenite = new ArrayList<>(),
            OreSilver = new ArrayList<>(),
            OreManganese = new ArrayList<>(),
            OreChromite = new ArrayList<>(),
            OreBauxite = new ArrayList<>(),
            OreNickel = new ArrayList<>(),
            OreNolanite = new ArrayList<>(),
            NetherOrePyrolusite = new ArrayList<>(),
            NetherOreGraphite = new ArrayList<>(),
            NetherOreBauxite = new ArrayList<>(),
            NetherOreFerroManganese = new ArrayList<>(),
            NetherOreNolanite = new ArrayList<>(),
            EndOreTunstite = new ArrayList<>();

    public static final StructureComp
            COPPER = StructureComp.of("copper", new VeinStructureConfig(OreCopper, 0.1, 0.2, 8, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            TIN = StructureComp.of("tin", new VeinStructureConfig(OreTin, 0.008, 0.2, 7, 2, 5, 3, 32, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            IRON = StructureComp.of("iron", new VeinStructureConfig(OreIron, 0.0075, 0.2, 8, 4, 3, 3, 3, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            GOLD = StructureComp.of("gold", new VeinStructureConfig(OreGold, 0.04, 0.2, 6, 3, 3, 3, 3, 32, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            COAL = StructureComp.of("coal", new VeinStructureConfig(OreCoal, 0.012, 0.2, 10, 5, 3, 3, 3, 120, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            ILMENITE = StructureComp.of("ilmenite", new VeinStructureConfig(OreIlmenite, 0.006, 0.2, 6, 3, 3, 3, 3, 32, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            SILVER = StructureComp.of("silver", new VeinStructureConfig(OreSilver, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            PYROLUSITE = StructureComp.of("pyrolusite", new VeinStructureConfig(OrePyrolusite, 0.004, 0.2, 6, 2, 4, 3, 16, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            CHROMITE = StructureComp.of("chromite", new VeinStructureConfig(OreChromite, 0.005, 0.2, 7, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            BAUXITE = StructureComp.of("bauxite", new VeinStructureConfig(OreBauxite, 0.007, 0.2, 6, 1, 4, 3, 32, 80, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NICKEL = StructureComp.of("nickel", new VeinStructureConfig(OreNickel, 0.0055, 0.2, 8, 3, 3, 3, 3, 62, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NOLANITE = StructureComp.of("nolanite", new VeinStructureConfig(OreNolanite, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            FERROMANGANESE = StructureComp.of("ferromanganese", new VeinStructureConfig(OreFerroManganese, 0.0005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD))),
            NETHERPYROLUSITE = StructureComp.of("netherpyrolusite", new VeinStructureConfig(NetherOrePyrolusite, 0.005, 0.2, 6, 3, 3, 3, 60, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)), BiomeTags.IS_NETHER),
            NETHERGRAPHITE = StructureComp.of("nethergraphite", new VeinStructureConfig(NetherOreGraphite, 0.005, 0.2, 6, 3, 3, 3, 3, 48, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)), BiomeTags.IS_NETHER),
            NETHERBAUXITE = StructureComp.of("netherbauxite", new VeinStructureConfig(NetherOreBauxite, 0.005, 0.2, 6, 3, 3, 3, 48, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)), BiomeTags.IS_NETHER),
            NETHERFERROMANGANESE = StructureComp.of("netherferromanganese", new VeinStructureConfig(NetherOreFerroManganese, 0.005, 0.2, 6, 3, 3, 3, 3, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)), BiomeTags.IS_NETHER),
            NETHERNOLANITE = StructureComp.of("nethernolanite", new VeinStructureConfig(NetherOreNolanite, 0.005, 0.2, 6, 3, 3, 3, 3, 120, new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)), BiomeTags.IS_NETHER),
            TUNSTITE = StructureComp.of("tunstite", new VeinStructureConfig(EndOreTunstite, 0.0075, 0.04, 8, 5, 3, 3, 3, 62, new BlockMatchTest(Blocks.END_STONE)), BiomeTags.IS_END);


    public static ResourceKey<Structure> registerStructureKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(MicroMachinery.MODID, name.toLowerCase(Locale.ROOT)));
    }

    public static ResourceKey<StructureSet> registerStructureSetKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(MicroMachinery.MODID, name.toLowerCase(Locale.ROOT)));
    }

    public static void bootstrapStructure(BootstapContext<Structure> context) {
        MAPS.values().forEach(structureComp -> context.register(structureComp.key, structureComp.valueS.apply(context)));
    }

    public static void bootstrapLandMark(BootstapContext<StructureSet> context) {
//        MAPS.values().forEach(structureComp -> context.register())
    }

    public record StructureComp(ResourceKey<Structure> key, Function<BootstapContext<Structure>, Structure> valueS,
                                ResourceKey<StructureSet> setKey,
                                Supplier<StructurePlacement> placement) {
        public static StructureComp of(String name, VeinStructureConfig config, TagKey<Biome> biomeTagKey) {
            StructureComp structureComp = new StructureComp(
                    registerStructureKey(name),
                    context -> new VeinStructure(
                            new Structure.StructureSettings(
                                    context.lookup(Registries.BIOME).getOrThrow(biomeTagKey),
                                    Map.of(),
                                    GenerationStep.Decoration.UNDERGROUND_ORES,
                                    TerrainAdjustment.NONE
                            ), config
                    ),
                    registerStructureSetKey(name),
                    () -> new RandomSpreadStructurePlacement((int) (1.0/config.getVeinGenChance()), 1919810, RandomSpreadType.LINEAR, 114514)
            );
            MAPS.put(name, structureComp);
            return structureComp;
        }

        public static StructureComp of(String name, VeinStructureConfig config) {
            return of(name, config, BiomeTags.IS_OVERWORLD);
        }
    }

    static {
        OreCopper.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.8d));
        OreCopper.add(Pair.of(MMBlocks.ORETIN.getKey().get().defaultBlockState(), 0.15d));
        OreCopper.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(), 0.05d));
        OreTin.add(Pair.of(MMBlocks.ORETIN.getKey().get().defaultBlockState(), 0.8d));
        OreTin.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.2d));
        OreIron.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.8d));
        OreIron.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(), 0.09d));
        OreIron.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(), 0.11d));
        OreGold.add(Pair.of(Blocks.GOLD_ORE.defaultBlockState(), 0.7d));
        OreGold.add(Pair.of(MMBlocks.ORENOLANITE.getKey().get().defaultBlockState(), 0.15d));
        OreGold.add(Pair.of(MMBlocks.ORESILVER.getKey().get().defaultBlockState(), 0.1d));
        OreGold.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.05d));
        OreCoal.add(Pair.of(Blocks.COAL_ORE.defaultBlockState(), 0.8d));
        OreCoal.add(Pair.of(Blocks.COAL_BLOCK.defaultBlockState(), 0.1d));
        OreCoal.add(Pair.of(MMBlocks.OREGRAPHITE.getKey().get().defaultBlockState(), 0.07d));
        OreCoal.add(Pair.of(Blocks.DIAMOND_ORE.defaultBlockState(), 0.03d));
        OreIlmenite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.5d));
        OreIlmenite.add(Pair.of(MMBlocks.OREILMENITE.getKey().get().defaultBlockState(), 0.4d));
        OreIlmenite.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(), 0.1d));
        OreSilver.add(Pair.of(MMBlocks.ORESILVER.getKey().get().defaultBlockState(), 0.7d));
        OreSilver.add(Pair.of(Blocks.LAPIS_ORE.defaultBlockState(), 0.2d));
        OreSilver.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.1d));
        OreManganese.add(Pair.of(MMBlocks.OREMANGANESE.getKey().get().defaultBlockState(), 0.8d));
        OreManganese.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(), 0.2d));
        OreChromite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.6d));
        OreChromite.add(Pair.of(MMBlocks.ORECHROMITE.getKey().get().defaultBlockState(), 0.3d));
        OreChromite.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(), 0.1d));
        OreBauxite.add(Pair.of(MMBlocks.OREBAUXITE.getKey().get().defaultBlockState(), 0.6d));
        OreBauxite.add(Pair.of(Blocks.CLAY.defaultBlockState(), 0.3d));
        OreBauxite.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.06d));
        OreBauxite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.04d));
        OreNickel.add(Pair.of(MMBlocks.ORENICKEL.getKey().get().defaultBlockState(), 0.6d));
        OreNickel.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.25d));
        OreNickel.add(Pair.of(MMBlocks.ORECOPPER.getKey().get().defaultBlockState(), 0.1d));
        OreNickel.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(), 0.05d));
        OreNolanite.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.7d));
        OreNolanite.add(Pair.of(MMBlocks.ORENOLANITE.getKey().get().defaultBlockState(), 0.2d));
        OreNolanite.add(Pair.of(MMBlocks.OREILMENITE.getKey().get().defaultBlockState(), 0.1d));
        OreFerroManganese.add(Pair.of(Blocks.IRON_ORE.defaultBlockState(), 0.5d));
        OreFerroManganese.add(Pair.of(MMBlocks.OREFERROMANGANESE.getKey().get().defaultBlockState(), 0.3d));
        OreFerroManganese.add(Pair.of(MMBlocks.OREPYROLUSITE.getKey().get().defaultBlockState(), 0.2d));
        NetherOrePyrolusite.add(Pair.of(MMBlocks.OREPYROLUSITE_NETHER.getKey().get().defaultBlockState(), 0.8d));
        NetherOrePyrolusite.add(Pair.of(MMBlocks.OREFERROMANGANESE_NETHER.getKey().get().defaultBlockState(), 0.2d));
        NetherOreGraphite.add(Pair.of(MMBlocks.OREGRAPHITE_NETHER.getKey().get().defaultBlockState(), 0.8d));
        NetherOreGraphite.add(Pair.of(Blocks.COAL_BLOCK.defaultBlockState(), 0.2d));
        NetherOreBauxite.add(Pair.of(MMBlocks.OREBAUXITE_NETHER.getKey().get().defaultBlockState(), 0.7d));
        NetherOreBauxite.add(Pair.of(Blocks.CLAY.defaultBlockState(), 0.2d));
        NetherOreBauxite.add(Pair.of(Blocks.GRAVEL.defaultBlockState(), 0.1d));
        NetherOreFerroManganese.add(Pair.of(MMBlocks.OREFERROMANGANESE_NETHER.getKey().get().defaultBlockState(), 0.7d));
        NetherOreFerroManganese.add(Pair.of(MMBlocks.OREPYROLUSITE_NETHER.getKey().get().defaultBlockState(), 0.3d));
        NetherOreNolanite.add(Pair.of(MMBlocks.ORENOLANITE_NETHER.getKey().get().defaultBlockState(), 0.6d));
        NetherOreNolanite.add(Pair.of(MMBlocks.OREBAUXITE_NETHER.getKey().get().defaultBlockState(), 0.25d));
        NetherOreNolanite.add(Pair.of(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 0.15d));
        EndOreTunstite.add(Pair.of(MMBlocks.ORETUNSTITE.getKey().get().defaultBlockState(), 1.0d));
    }

}
