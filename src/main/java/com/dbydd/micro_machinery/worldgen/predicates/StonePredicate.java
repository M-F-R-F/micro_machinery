package com.dbydd.micro_machinery.worldgen.predicates;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import javax.annotation.Nullable;

public class StonePredicate implements Predicate<IBlockState> {

    @Override
    public boolean apply(@Nullable IBlockState BlockState) {
        if (BlockState != null && BlockState.getBlock() == Blocks.STONE) {
            BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType) BlockState.getValue(BlockStone.VARIANT);
            return blockstone$enumtype.isNatural();
        } else {
            return false;
        }
    }
}
