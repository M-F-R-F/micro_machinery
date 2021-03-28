package mfrf.dbydd.micro_machinery.blocks.machines.cutter;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockCutter extends MMBlockTileProviderBase {

    public BlockCutter() {
        super(Properties.create(Material.IRON), "cutter");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileCutter();
    }
}
