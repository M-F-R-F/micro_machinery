package com.dbydd.micro_machinery.worldgen.predicates;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class PredicateEnd extends PredicateBase {
    private static final IBlockState[] list = {Blocks.END_STONE.getDefaultState()};

    public PredicateEnd() {
        super(list);
    }
}
