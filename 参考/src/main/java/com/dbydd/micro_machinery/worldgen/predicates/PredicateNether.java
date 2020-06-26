package com.dbydd.micro_machinery.worldgen.predicates;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class PredicateNether extends PredicateBase {
    private static final IBlockState[] list = {Blocks.NETHERRACK.getDefaultState(), Blocks.MAGMA.getDefaultState()};

    public PredicateNether() {
        super(list);
    }
}
