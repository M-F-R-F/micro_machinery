package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

public class ModGenerators {
    public static final List<OreGenRecipe> oreGeneratorRecipes = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> netherOreGenerators = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> endOreGenerators = new ArrayList<OreGenRecipe>();
    public static final List<WorldGenerator> worldGenerators = new ArrayList<WorldGenerator>();

    public static final OreGenRecipe test = new OreGenRecipe(ModBlocks.BLOCKBRONZE, new Block[]{Blocks.STONE, Blocks.GRASS, Blocks.DIRT}, 0, 1, 255, 16, 4);
}
