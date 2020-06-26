package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.dbydd.micro_machinery.recipes.oregen.veins.VeinGenRecipe;
import com.dbydd.micro_machinery.worldgen.EndVeinGenerator;
import com.dbydd.micro_machinery.worldgen.SpecialGenerator;
import com.dbydd.micro_machinery.worldgen.VeinGenerator;
import com.dbydd.micro_machinery.worldgen.predicates.PredicateEnd;
import com.dbydd.micro_machinery.worldgen.predicates.PredicateNether;
import com.dbydd.micro_machinery.worldgen.predicates.StonePredicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ModGenerators {
    public static final List<OreGenRecipe> oreGeneratorRecipes = new ArrayList<OreGenRecipe>();
    public static final List<OreGenRecipe> oreSpecialGeneratorRecipes = new ArrayList<OreGenRecipe>();
    public static final List<VeinGenRecipe> veinGeneratorRecipes = new ArrayList<VeinGenRecipe>();

    public static final List<EndVeinGenerator> endVeinGenerators = new ArrayList<EndVeinGenerator>();
    public static final List<WorldGenerator> worldGenerators = new ArrayList<WorldGenerator>();
    public static final List<SpecialGenerator> worldSpecialGenerators = new ArrayList<SpecialGenerator>();
    public static final List<VeinGenerator> worldVeinGenerators = new ArrayList<VeinGenerator>();

    public static final TreeMap<Double, IBlockState> OreCopper = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreTin = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreIron = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreGold = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreCoal = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreIlmenite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreSilver = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OrePyrolusite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreChromite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreBauxite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreNickel = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreNolanite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> OreFerroManganese = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> NetherOrePyrolusite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> NetherOreGraphite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> NetherOreBauxite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> NetherOreFerroManganese = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> NetherOreNolanite = new TreeMap<Double, IBlockState>();
    public static final TreeMap<Double, IBlockState> EndOreTunstite = new TreeMap<Double, IBlockState>();

    public static void initMaps() {
        OreCopper.put(0.8d, ModBlocks.ORECOPPER.getDefaultState());
        OreCopper.put(0.15d, ModBlocks.ORETIN.getDefaultState());
        OreCopper.put(0.05d, Blocks.GOLD_ORE.getDefaultState());
        OreTin.put(0.8d, ModBlocks.ORETIN.getDefaultState());
        OreTin.put(0.2d, ModBlocks.ORECOPPER.getDefaultState());
        OreIron.put(0.8d, Blocks.IRON_ORE.getDefaultState());
        OreIron.put(0.09d, Blocks.GOLD_ORE.getDefaultState());
        OreIron.put(0.11d, ModBlocks.ORENICKEL.getDefaultState());
        OreGold.put(0.7d, Blocks.GOLD_ORE.getDefaultState());
        OreGold.put(0.15d, ModBlocks.ORENOLANITE.getDefaultState());
        OreGold.put(0.1d, ModBlocks.ORESILVER.getDefaultState());
        OreGold.put(0.05d, ModBlocks.ORECOPPER.getDefaultState());
        OreCoal.put(0.8d, Blocks.COAL_ORE.getDefaultState());
        OreCoal.put(0.1d, Blocks.COAL_BLOCK.getDefaultState());
        OreCoal.put(0.07d, ModBlocks.OREGRAPHITE.getDefaultState());
        OreCoal.put(0.03d, Blocks.DIAMOND_ORE.getDefaultState());
        OreIlmenite.put(0.5d, Blocks.IRON_ORE.getDefaultState());
        OreIlmenite.put(0.4d, ModBlocks.OREILMENITE.getDefaultState());
        OreIlmenite.put(0.1d, ModBlocks.OREFERROMANGANESE.getDefaultState());
        OreSilver.put(0.7d, ModBlocks.ORESILVER.getDefaultState());
        OreSilver.put(0.2d, Blocks.LAPIS_ORE.getDefaultState());
        OreSilver.put(0.1d, ModBlocks.ORECOPPER.getDefaultState());
        OrePyrolusite.put(0.8d, ModBlocks.OREPYROLUSITE.getDefaultState());
        OrePyrolusite.put(0.2d, ModBlocks.OREFERROMANGANESE.getDefaultState());
        OreChromite.put(0.6d, Blocks.IRON_ORE.getDefaultState());
        OreChromite.put(0.3d, ModBlocks.ORECHROMITE.getDefaultState());
        OreChromite.put(0.1d, ModBlocks.ORENICKEL.getDefaultState());
        OreBauxite.put(0.6d, ModBlocks.OREBAUXITE.getDefaultState());
        OreBauxite.put(0.3d, Blocks.CLAY.getDefaultState());
        OreBauxite.put(0.06d, ModBlocks.ORECOPPER.getDefaultState());
        OreBauxite.put(0.04d, Blocks.IRON_ORE.getDefaultState());
        OreNickel.put(0.6d, ModBlocks.ORENICKEL.getDefaultState());
        OreNickel.put(0.25d, Blocks.IRON_ORE.getDefaultState());
        OreNickel.put(0.1d, ModBlocks.ORECOPPER.getDefaultState());
        OreNickel.put(0.05d, ModBlocks.OREFERROMANGANESE.getDefaultState());
        OreNolanite.put(0.7d, Blocks.IRON_ORE.getDefaultState());
        OreNolanite.put(0.2d, ModBlocks.ORENOLANITE.getDefaultState());
        OreNolanite.put(0.1d, ModBlocks.OREILMENITE.getDefaultState());
        OreFerroManganese.put(0.5d, Blocks.IRON_ORE.getDefaultState());
        OreFerroManganese.put(0.3d, ModBlocks.OREFERROMANGANESE.getDefaultState());
        OreFerroManganese.put(0.2d, ModBlocks.OREPYROLUSITE.getDefaultState());
        NetherOrePyrolusite.put(0.8d, ModBlocks.OREPYROLUSITE_NETHER.getDefaultState());
        NetherOrePyrolusite.put(0.2d, ModBlocks.OREFERROMANGANESE_NETHER.getDefaultState());
        NetherOreGraphite.put(0.8d, ModBlocks.OREGRAPHITE_NETHER.getDefaultState());
        NetherOreGraphite.put(0.2d, Blocks.COAL_BLOCK.getDefaultState());
        NetherOreBauxite.put(0.7d, ModBlocks.OREBAUXITE_NETHER.getDefaultState());
        NetherOreBauxite.put(0.2d, Blocks.CLAY.getDefaultState());
        NetherOreBauxite.put(0.1d, Blocks.GRAVEL.getDefaultState());
        NetherOreFerroManganese.put(0.7d, ModBlocks.OREFERROMANGANESE_NETHER.getDefaultState());
        NetherOreFerroManganese.put(0.3d, ModBlocks.OREPYROLUSITE_NETHER.getDefaultState());
        NetherOreNolanite.put(0.6d, ModBlocks.ORENOLANITE_NETHER.getDefaultState());
        NetherOreNolanite.put(0.25d, ModBlocks.OREBAUXITE_NETHER.getDefaultState());
        NetherOreNolanite.put(0.15d, Blocks.QUARTZ_ORE.getDefaultState());
        EndOreTunstite.put(1.0d, ModBlocks.ORETUNSTITE.getDefaultState());
    }

    public static final VeinGenRecipe copper = new VeinGenRecipe(0.01d, 0.2d, 8, 3, 3, 3, 3, 62, OreCopper, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe tin = new VeinGenRecipe(0.006d, 0.2d, 7, 2, 5, 3, 32, 80, OreTin, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe iron = new VeinGenRecipe(0.0045d, 0.2d, 8, 4, 3, 3, 3, 80, OreIron, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe gold = new VeinGenRecipe(0.0035d, 0.2d, 6, 3, 3, 3, 3, 32, OreGold, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe coal = new VeinGenRecipe(0.01d, 0.2d, 9, 5, 3, 3, 3, 120, OreCoal, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe ilmenite = new VeinGenRecipe(0.004d, 0.2d, 6, 3, 3, 3, 3, 32, OreIlmenite, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe silver = new VeinGenRecipe(0.005d, 0.2d, 6, 3, 3, 3, 3, 48, OreSilver, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe pyrolusite = new VeinGenRecipe(0.004d, 0.2d, 6, 2, 4, 3, 3, 80, OrePyrolusite, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe chromite = new VeinGenRecipe(0.005d, 0.2d, 7, 3, 3, 3, 3, 62, OreChromite, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe bauxite = new VeinGenRecipe(0.007d, 0.2d, 6, 1, 4, 3, 48, 80, OreBauxite, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe nickel = new VeinGenRecipe(0.0055d, 0.2d, 8, 3, 3, 3, 3, 62, OreNickel, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe nolanite = new VeinGenRecipe(0.005d, 0.2d, 6, 3, 3, 3, 3, 48, OreNolanite, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe ferromanganese = new VeinGenRecipe(0.0005d, 0.2d, 6, 3, 3, 3, 3, 48, OreFerroManganese, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);
    public static final VeinGenRecipe netherpyrolusite = new VeinGenRecipe(0.05d, 0.2d, 6, 3, 3, 3, 60, 120, NetherOrePyrolusite, new PredicateNether(), OreGenEvent.GenerateMinable.EventType.QUARTZ);
    public static final VeinGenRecipe nethergraphite = new VeinGenRecipe(0.05d, 0.2d, 6, 3, 3, 3, 3, 48, NetherOreGraphite, new PredicateNether(), OreGenEvent.GenerateMinable.EventType.QUARTZ);
    public static final VeinGenRecipe netherbauxite = new VeinGenRecipe(0.05d, 0.2d, 6, 3, 3, 3, 48, 120, NetherOreBauxite, new PredicateNether(), OreGenEvent.GenerateMinable.EventType.QUARTZ);
    public static final VeinGenRecipe netherferromanganese = new VeinGenRecipe(0.05d, 0.2d, 6, 3, 3, 3, 3, 120, NetherOreFerroManganese, new PredicateNether(), OreGenEvent.GenerateMinable.EventType.QUARTZ);
    public static final VeinGenRecipe nethernolanite = new VeinGenRecipe(0.05d, 0.2d, 6, 3, 3, 3, 3, 120, NetherOreNolanite, new PredicateNether(), OreGenEvent.GenerateMinable.EventType.QUARTZ);

    public static final VeinGenRecipe tunstite = new VeinGenRecipe(0.075d, 0.04d, 8, 5, 3, 3, 3, 62, EndOreTunstite, new PredicateEnd());
}
