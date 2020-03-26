package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.dbydd.micro_machinery.recipes.oregen.veins.VeinGenRecipe;
import com.dbydd.micro_machinery.worldgen.SpecialGenerator;
import com.dbydd.micro_machinery.worldgen.VeinGenerator;
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

    //    public static final List<OreGenRecipe> netherOreGenerators = new ArrayList<OreGenRecipe>();
    public static final List<OreGenRecipe> endOreGenerators = new ArrayList<OreGenRecipe>();
    public static final List<WorldGenerator> worldGenerators = new ArrayList<WorldGenerator>();
    public static final List<SpecialGenerator> worldSpecialGenerators = new ArrayList<SpecialGenerator>();
    public static final List<VeinGenerator> worldVeinGenerators = new ArrayList<VeinGenerator>();

    public static final TreeMap<Double, IBlockState> testOreGen = new TreeMap<Double, IBlockState>();

    public static void initMaps(){
        testOreGen.put(0.9d, ModBlocks.BLOCKCOBALT.getDefaultState());
    }
    public static final VeinGenRecipe test = new VeinGenRecipe(0.8d, 0.2d, 1, 4, 2, 2, 45, 70, testOreGen, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.CUSTOM);


    public static final OreGenRecipe copper1 = new OreGenRecipe(ModBlocks.ORECOPPER.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 8, 48, 6, 8, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe copper2 = new OreGenRecipe(ModBlocks.ORECOPPER.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 8, 3, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe tin1 = new OreGenRecipe(ModBlocks.ORETIN.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 16, 56, 8, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe tin2 = new OreGenRecipe(ModBlocks.ORETIN.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(1)}, 0, 0, 255, 16, 2, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe silver1 = new OreGenRecipe(ModBlocks.ORESILVER.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 2, 32, 4, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe silver2 = new OreGenRecipe(ModBlocks.ORESILVER.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 6, 2, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe nickel1 = new OreGenRecipe(ModBlocks.ORENICKEL.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 2, 48, 4, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe nickel2 = new OreGenRecipe(ModBlocks.ORENICKEL.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 4, 3, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe pyrolusite = new OreGenRecipe(ModBlocks.OREPYROLUSITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState()}, 0, 30, 255, 4, 3, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe graphite = new OreGenRecipe(ModBlocks.OREGRAPHITE.getDefaultState(), new IBlockState[]{Blocks.COAL_ORE.getDefaultState()}, 0, 0, 255, 3, 2, true, 16, 0, 255, OreGenEvent.GenerateMinable.EventType.COAL);
    public static final OreGenRecipe chromite1 = new OreGenRecipe(ModBlocks.ORECHROMITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 4, 20, 4, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe chromite2 = new OreGenRecipe(ModBlocks.ORECHROMITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 6, 2, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe ilmenite1 = new OreGenRecipe(ModBlocks.OREILMENITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 0, 16, 2, 3, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe Ilmenite2 = new OreGenRecipe(ModBlocks.OREILMENITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 4, 3, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe ferromanganese1 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE.getDefaultState(), new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 6, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe ferromanganese2 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE.getDefaultState(), new IBlockState[]{Blocks.IRON_ORE.getDefaultState()}, 0, 0, 255, 4, 3, true, 8, 0, 128, OreGenEvent.GenerateMinable.EventType.IRON);
    public static final OreGenRecipe bauxite = new OreGenRecipe(ModBlocks.OREBAUXITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 70, 56, 2, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe nolanite = new OreGenRecipe(ModBlocks.ORENOLANITE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 2, 4, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe iron = new OreGenRecipe(Blocks.IRON_ORE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 0, 56, 8, 12, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);
    public static final OreGenRecipe coal = new OreGenRecipe(Blocks.COAL_ORE.getDefaultState(), new IBlockState[]{Blocks.STONE.getDefaultState(), Blocks.STONE.getStateFromMeta(1), Blocks.STONE.getStateFromMeta(3), Blocks.STONE.getStateFromMeta(5)}, 0, 0, 110, 16, 20, false, 0, 0, 0, OreGenEvent.GenerateMinable.EventType.CUSTOM);


}
