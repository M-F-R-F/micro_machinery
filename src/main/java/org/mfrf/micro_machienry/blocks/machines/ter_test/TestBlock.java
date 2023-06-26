package org.mfrf.micro_machienry.blocks.machines.ter_test;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
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
