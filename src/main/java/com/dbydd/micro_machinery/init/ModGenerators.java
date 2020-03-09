package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

public class ModGenerators {
    public static final List<OreGenRecipe> oreGeneratorRecipes = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> netherOreGenerators = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> endOreGenerators = new ArrayList<OreGenRecipe>();
    public static final List<WorldGenerator> worldGenerators = new ArrayList<WorldGenerator>();

    public static final OreGenRecipe copper1 = new OreGenRecipe(ModBlocks.ORECOPPER, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 8, 48, 6, 8);
    public static final OreGenRecipe copper2 = new OreGenRecipe(ModBlocks.ORECOPPER, new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 8, 3);

//    public static final OreGenRecipe tin1 = new OreGenRecipe(ModBlocks.ORETIN, new Block[]{Blocks.STONE}, 0, 16, 56, 8, 4);
//    public static final OreGenRecipe tin2 = new OreGenRecipe(ModBlocks.ORETIN, new Block[]{Blocks.STONE, 1}, 0, 0, 255, 16, 2);
//    public static final OreGenRecipe silver1 = new OreGenRecipe(ModBlocks.ORESILVER, new Block[]{Blocks.STONE}, 0, 2, 32, 4, 4);
//    public static final OreGenRecipe silver2 = new OreGenRecipe(ModBlocks.ORESILVER, new Block[]{Blocks.STONE, 3}, 0, 0, 255, 6, 2);
//    public static final OreGenRecipe nickel1 = new OreGenRecipe(ModBlocks.ORENICKEL, new Block[]{Blocks.STONE}, 0, 2, 48, 4, 4);
//    public static final OreGenRecipe nickel2 = new OreGenRecipe(ModBlocks.ORENICKEL, new Block[]{Blocks.STONE, 5}, 0, 0, 255, 4, 3);
//    public static final OreGenRecipe pyrolusite = new OreGenRecipe(ModBlocks.OREPYROLUSITE, new Block[]{Blocks.SANDSTONE, Blocks.RED_SANDSTONE}, 0, 30, 255, 4, 3);
//    public static final OreGenRecipe graphite = new OreGenRecipe(ModBlocks.OREGRAPHITE, new Block[]{Blocks.COAL_ORE}, 0, 0, 255, 3, 2);
//    public static final OreGenRecipe chromite1 = new OreGenRecipe(ModBlocks.ORECHROMITE, new Block[]{Blocks.STONE}, 0, 4, 20, 4, 4);
//    public static final OreGenRecipe chromite2 = new OreGenRecipe(ModBlocks.ORECHROMITE, new Block[]{Blocks.STONE, 5}, 0, 0, 255, 6, 2);
//    public static final OreGenRecipe ilmenite1 = new OreGenRecipe(ModBlocks.OREILMENITE, new Block[]{Blocks.STONE}, 0, 0, 16, 2, 3);
//    public static final OreGenRecipe Ilmenite2 = new OreGenRecipe(ModBlocks.OREILMENITE, new Block[]{Blocks.STONE, 3}, 0, 0, 255, 4, 3);
//    public static final OreGenRecipe ferromanganese1 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE, new Block[]{Blocks.STONE, 3}, 0, 0, 255, 6, 4);
//    public static final OreGenRecipe ferromanganese2 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE, new Block[]{Blocks.IRON_ORE}, 0, 0, 255, 4, 3);
//    public static final OreGenRecipe bauxite = new OreGenRecipe(ModBlocks.OREBAUXITE, new Block[]{Blocks.GRAVEL, Blocks.DIRT}, 0, 32, 56, 2, 4);
//    public static final OreGenRecipe nolanite = new OreGenRecipe(ModBlocks.ORENOLANITE, new Block[]{Blocks.STONE}, 0, 0, 255, 2, 4);

}
