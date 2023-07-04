package mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockCreativeEnergyCell extends MMBlockTileProviderBase {

    public BlockCreativeEnergyCell(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileCreativeEnergyCell(pPos, pState);
    }

}
