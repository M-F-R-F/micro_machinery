package com.dbydd.micro_machinery.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum Predicates implements Predicate<BlockState> {
    STONE(ForgeRegistries.BLOCKS.getValues().stream().flatMap(block -> block.getStateContainer().getValidStates().stream()).filter(blockState -> blockState.getMaterial() == Material.ROCK).collect(Collectors.toList())),
    NETHER(Arrays.asList(Blocks.MAGMA_BLOCK.getDefaultState(), Blocks.NETHERRACK.getDefaultState(), Blocks.GRAVEL.getDefaultState(), Blocks.SOUL_SAND.getDefaultState())),
    END(Arrays.asList(Blocks.END_STONE.getDefaultState()));

    private final Collection<BlockState> blocks;

    Predicates(Collection<BlockState> blocks) {
        this.blocks = blocks;
    }

    @Override
    public boolean test(BlockState block) {
        return blocks.contains(block);
    }
}
