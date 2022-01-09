package mfrf.dbydd.micro_machinery.blocks.machines.weld;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockWeld extends MMBlockTileProviderBase {
    public BlockWeld(Properties properties) {
        super(properties, "weld");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileWeld();
    }
}
