package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.dbydd.micro_machinery.worldgen.SpecialGenerator;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.ArrayList;
import java.util.List;

public class ModGenerators {
    public static final List<OreGenRecipe> oreGeneratorRecipes = new ArrayList<OreGenRecipe>();
    public static final List<OreGenRecipe> oreSpecialGeneratorRecipes = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> netherOreGenerators = new ArrayList<OreGenRecipe>();
    //    public static final List<OreGenRecipe> endOreGenerators = new ArrayList<OreGenRecipe>();
    public static final List<WorldGenerator> worldGenerators = new ArrayList<WorldGenerator>();
    public static final List<SpecialGenerator> worldSpecialGenerators = new ArrayList<SpecialGenerator>();

    public static final OreGenRecipe copper1 = new OreGenRecipe(ModBlocks.ORECOPPER, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 8, 48, 6, 8, false, null);
    public static final OreGenRecipe copper2 = new OreGenRecipe(ModBlocks.ORECOPPER, new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 8, 3, false, null);
    public static final OreGenRecipe tin1 = new OreGenRecipe(ModBlocks.ORETIN, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 16, 56, 8, 4, false, null);
    public static final OreGenRecipe tin2 = new OreGenRecipe(ModBlocks.ORETIN, new IBlockState[]{Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE)}, 0, 0, 255, 16, 2, false, null);
    public static final OreGenRecipe silver1 = new OreGenRecipe(ModBlocks.ORESILVER, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 2, 32, 4, 4, false, null);
    public static final OreGenRecipe silver2 = new OreGenRecipe(ModBlocks.ORESILVER, new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 6, 2, false, null);
    public static final OreGenRecipe nickel1 = new OreGenRecipe(ModBlocks.ORENICKEL, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 2, 48, 4, 4, false, null);
    public static final OreGenRecipe nickel2 = new OreGenRecipe(ModBlocks.ORENICKEL, new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 4, 3, false, null);
    public static final OreGenRecipe pyrolusite = new OreGenRecipe(ModBlocks.OREPYROLUSITE, new IBlockState[]{Blocks.SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState()}, 0, 30, 255, 4, 3, false, null);
    public static final OreGenRecipe graphite = new OreGenRecipe(ModBlocks.OREGRAPHITE, new IBlockState[]{Blocks.COAL_ORE.getDefaultState()}, 0, 0, 255, 3, 2, false, OreGenEvent.GenerateMinable.EventType.COAL);
    public static final OreGenRecipe chromite1 = new OreGenRecipe(ModBlocks.ORECHROMITE, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 4, 20, 4, 4, false, null);
    public static final OreGenRecipe chromite2 = new OreGenRecipe(ModBlocks.ORECHROMITE, new IBlockState[]{Blocks.STONE.getStateFromMeta(5)}, 0, 0, 255, 6, 2, false, null);
    public static final OreGenRecipe ilmenite1 = new OreGenRecipe(ModBlocks.OREILMENITE, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 0, 16, 2, 3, false, null);
    public static final OreGenRecipe Ilmenite2 = new OreGenRecipe(ModBlocks.OREILMENITE, new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 4, 3, false, null);
    public static final OreGenRecipe ferromanganese1 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE, new IBlockState[]{Blocks.STONE.getStateFromMeta(3)}, 0, 0, 255, 6, 4, false, null);
    public static final OreGenRecipe ferromanganese2 = new OreGenRecipe(ModBlocks.OREFERROMANGANESE, new IBlockState[]{Blocks.IRON_ORE.getDefaultState()}, 0, 0, 255, 4, 3, false, OreGenEvent.GenerateMinable.EventType.COAL);
    public static final OreGenRecipe bauxite = new OreGenRecipe(ModBlocks.OREBAUXITE, new IBlockState[]{Blocks.GRAVEL.getDefaultState(), Blocks.DIRT.getDefaultState()}, 0, 70, 56, 2, 4, false, OreGenEvent.GenerateMinable.EventType.DIRT);
    public static final OreGenRecipe nolanite = new OreGenRecipe(ModBlocks.ORENOLANITE, new IBlockState[]{Blocks.STONE.getDefaultState()}, 0, 0, 255, 2, 4, false, null);

}
