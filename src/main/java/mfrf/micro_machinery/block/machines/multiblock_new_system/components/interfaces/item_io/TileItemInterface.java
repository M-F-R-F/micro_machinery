package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.item_io;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileItemInterface extends MMTileMultiBlockComponentInterface {
    public TileItemInterface(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.ITEM_INTERFACE.get(), state, pos);
    }
}
