package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.dbydd.micro_machinery.recipes.oregen.veins.VeinGenRecipe;
import com.dbydd.micro_machinery.worldgen.SpecialGenerator;
import com.dbydd.micro_machinery.worldgen.VeinGenerator;
import com.dbydd.micro_machinery.worldgen.predicates.StonePredicate;
import net.minecraft.block.state.IBlockState;
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
    public static final VeinGenRecipe test = new VeinGenRecipe(0.01d, 0.2d, 8, 3, 3, 3, 30, 70, testOreGen, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.IRON);




}
