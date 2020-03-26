package com.dbydd.micro_machinery.worldgen.predicates;

import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class PredicateBase implements Predicate<IBlockState> {

    private final List<IBlockState> blocklist;

    public PredicateBase(IBlockState[] list) {
        this.blocklist = new ArrayList<IBlockState>(Arrays.asList(list));
    }

    @Override
    public boolean apply(@Nullable IBlockState BlockState) {
        return blocklist.contains(BlockState);
    }

}
