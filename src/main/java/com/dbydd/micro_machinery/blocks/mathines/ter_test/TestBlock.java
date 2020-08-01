package com.dbydd.micro_machinery.blocks.mathines.ter_test;

import com.dbydd.micro_machinery.blocks.mathines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class TestBlock extends MMBlockTileProviderBase {
    public TestBlock(Properties properties, String name) {
        super(properties, name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TerTestTile();
    }
}
