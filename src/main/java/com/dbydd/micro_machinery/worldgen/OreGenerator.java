package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class OreGenerator extends WorldGenMinable {
    OreGenRecipe recipe;

    public OreGenerator(IBlockState state, int blockCount, OreGenRecipe recipe) {
        super(state, blockCount, new CustomPredicate(recipe));
        this.recipe = recipe;
        ModGenerators.worldGenerators.add(this);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (TerrainGen.generateOre(worldIn, rand, this, position, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < recipe.getGenerateCountPerChunk(); ++i) {
                int posX = position.getX() + rand.nextInt(16);
                int posY = 16 + rand.nextInt(recipe.getAllowedYOffset());
                int posZ = position.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                super.generate(worldIn, rand, blockpos);
            }
        }
        return true;
    }

    static class CustomPredicate implements Predicate<IBlockState> {
        private final OreGenRecipe recipe;

        private CustomPredicate(OreGenRecipe recipe) {
            this.recipe = recipe;
        }

        public boolean apply(IBlockState p_apply_1_) {
            return p_apply_1_ != null && recipe.isBlockMatch(p_apply_1_.getBlock());
        }

    }
}
