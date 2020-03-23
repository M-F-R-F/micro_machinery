package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class SpecialGenerator extends OreGenerator {
    private final IBlockState specialOre;
    private final IBlockState normalOre;
    private final int normalOreSize;
    private final int specialSize;
    private final int mainMinHeight;
    private final int mainAllowedYOffset;

    public SpecialGenerator(IBlockState state, IBlockState[] replaceBlocks, int normalOreSize, int mainMinHeight, int mainAllowedYOffset, OreGenRecipe recipe) {
        super(replaceBlocks[0], recipe, true);
        this.specialOre = state;
        this.normalOre = recipe.getReplaceBlocks()[0];
        this.normalOreSize = normalOreSize;
        this.specialSize = recipe.getCount();
        this.mainMinHeight = mainMinHeight;
        this.mainAllowedYOffset = mainAllowedYOffset;
    }


    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < recipe.getGenerateCountPerChunk(); ++i) {
            int posX = position.getX() + rand.nextInt(16);
            int posY = mainMinHeight + rand.nextInt(mainAllowedYOffset);
            int posZ = position.getZ() + rand.nextInt(16);
            this.middleOre = new BlockPos(posX, posY, posZ);
//            if (super.generate(worldIn, rand, middleOre) && (posY <= (recipe.getAllowedYOffset() + recipe.getMinHeight()))) {
//                worldIn.setBlockState(middleOre, this.specialOre, 2)
//                return true;
//            }
            specialgenerate(worldIn, rand, middleOre);

        }
        return true;
    }


    private void specialgenerate(World worldIn, Random rand, BlockPos position) {
        float f = rand.nextFloat() * (float) Math.PI;
        double d0 = (double) ((float) (position.getX() + 8) + MathHelper.sin(f) * (float) normalOreSize / 8.0F);
        double d1 = (double) ((float) (position.getX() + 8) - MathHelper.sin(f) * (float) normalOreSize / 8.0F);
        double d2 = (double) ((float) (position.getZ() + 8) + MathHelper.cos(f) * (float) normalOreSize / 8.0F);
        double d3 = (double) ((float) (position.getZ() + 8) - MathHelper.cos(f) * (float) normalOreSize / 8.0F);
        double d4 = (double) (position.getY() + rand.nextInt(3) - 2);
        double d5 = (double) (position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < normalOreSize; ++i) {
            float f1 = (float) i / (float) normalOreSize;
            double d6 = d0 + (d1 - d0) * (double) f1;
            double d7 = d4 + (d5 - d4) * (double) f1;
            double d8 = d2 + (d3 - d2) * (double) f1;
            double d9 = rand.nextDouble() * (double) normalOreSize / 16.0D;
            double d10 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor(d6 - d10 / 2.0D);
            int k = MathHelper.floor(d7 - d11 / 2.0D);
            int l = MathHelper.floor(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1) {
                double d12 = ((double) l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        double d13 = ((double) i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            for (int j2 = l; j2 <= k1; ++j2) {
                                double d14 = ((double) j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    IBlockState state = worldIn.getBlockState(position);
                                    if (canApply(state)) {
                                        if (i <= specialSize && i2 < recipe.getMinHeight() + recipe.getAllowedYOffset()) {
                                            worldIn.setBlockState(blockpos, specialOre, 2);
                                        } else {
                                            worldIn.setBlockState(blockpos, normalOre, 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean canApply(IBlockState p_apply_1_) {
        if (p_apply_1_ != null && p_apply_1_.getBlock() == Blocks.STONE) {
            BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType) p_apply_1_.getValue(BlockStone.VARIANT);
            return blockstone$enumtype.isNatural();
        } else {
            return false;
        }
    }
}
