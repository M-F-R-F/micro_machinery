package mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces.redstone_io;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockRedstoneInterface extends MMBlockMultiBlockComponentInterface {
    public BlockRedstoneInterface(Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public @Nullable
    BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileRedstoneInterface(pPos, pState);
    }

    @Override
    public @Nullable
    <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return null;
    }


    @Override
    public void neighborChanged(BlockState pState, Level worldIn, BlockPos pos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!worldIn.isClientSide()) {
            int bestNeighborSignal = worldIn.getBestNeighborSignal(pos);
            ((TileRedstoneInterface) worldIn.getBlockEntity(pos)).powerChange(bestNeighborSignal);
        }

    }
}
