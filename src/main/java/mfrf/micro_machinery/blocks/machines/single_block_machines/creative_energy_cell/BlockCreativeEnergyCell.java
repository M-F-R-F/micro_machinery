package mfrf.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.IBlockReader;

public class BlockCreativeEnergyCell extends MMBlockTileProviderBase {

    public BlockCreativeEnergyCell(Properties properties) {
        super(properties, "creative_energy_cell");
    }

    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileCreativeEnergyCell();
    }
}
