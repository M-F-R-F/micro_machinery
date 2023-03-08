package mfrf.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockCreativeEnergyCell extends MMBlockTileProviderBase {

    public BlockCreativeEnergyCell(Properties properties) {
        super(properties, "creative_energy_cell");
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileCreativeEnergyCell(pPos, pState)
    }

}
