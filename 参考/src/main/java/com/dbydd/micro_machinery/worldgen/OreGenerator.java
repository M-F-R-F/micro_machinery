package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreGenerator extends WorldGenMinable {
    OreGenRecipe recipe;
    BlockPos middleOre;

    public OreGenerator(IBlockState state, OreGenRecipe recipe) {
        super(state, recipe.getCount(), new CustomPredicate(recipe));
        this.recipe = recipe;
    }

    public OreGenerator(IBlockState state, OreGenRecipe recipe, boolean isSpecialGenerate) {
        super(state, recipe.getCount());
        this.recipe = recipe;
    }

    public OreGenerator(IBlockState state, int normalOreSize, OreGenRecipe recipe) {
        super(state, normalOreSize, new CustomPredicate(recipe));
        this.recipe = recipe;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
            for (int i = 0; i < recipe.getGenerateCountPerChunk(); ++i) {
                int posX = position.getX() + rand.nextInt(16);
                int posY = recipe.getMinHeight() + rand.nextInt(recipe.getAllowedYOffset());
                int posZ = position.getZ() + rand.nextInt(16);
                this.middleOre = new BlockPos(posX, posY, posZ);
                super.generate(worldIn, rand, middleOre);
            }

        return true;
    }

    static class CustomPredicate implements Predicate<IBlockState> {
        private final OreGenRecipe recipe;

        private CustomPredicate(OreGenRecipe recipe) {
            this.recipe = recipe;
        }

        public boolean apply(IBlockState p_apply_1_) {
            return p_apply_1_ != null && recipe.isBlockMatch(p_apply_1_);
        }

    }
}
