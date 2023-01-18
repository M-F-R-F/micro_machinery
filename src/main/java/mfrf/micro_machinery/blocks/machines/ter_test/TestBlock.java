package mfrf.micro_machinery.blocks.machines.ter_test;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class TestBlock extends MMBlockTileProviderBase {
    public TestBlock(Properties properties, String name) {
        super(properties, name);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TerTestTile();
    }
}
