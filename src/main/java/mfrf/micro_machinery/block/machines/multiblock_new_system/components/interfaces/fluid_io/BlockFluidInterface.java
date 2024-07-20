package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.fluid_io;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.MMBlockMultiBlockComponentInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockFluidInterface extends MMBlockMultiBlockComponentInterface {
    public BlockFluidInterface(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileFluidInterface(pPos,pState);
    }
}
