package mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces.energy_io;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileFEInterface extends MMTileMultiBlockComponentInterface {
    public TileFEInterface( BlockPos pos,BlockState state) {
        super(MMBlockEntityTypes.FE_INTERFACE.get(), state, pos);
    }
}
