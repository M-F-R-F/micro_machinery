package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.fluid_io;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileFluidInterface extends MMTileMultiBlockComponentInterface {
    public TileFluidInterface(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.FLUID_INTERFACE.get(), state, pos);
    }
}
