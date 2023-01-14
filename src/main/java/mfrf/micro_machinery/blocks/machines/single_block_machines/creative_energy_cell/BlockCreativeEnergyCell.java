package mfrf.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockCreativeEnergyCell extends MMBlockTileProviderBase {

    public BlockCreativeEnergyCell(Properties properties) {
        super(properties, "creative_energy_cell");
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileCreativeEnergyCell();
    }
}
