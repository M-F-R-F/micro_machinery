package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.redstone_io;


import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.ComponentEvent;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileRedstoneInterface extends MMTileMultiBlockComponentInterface {
    public TileRedstoneInterface(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.REDSTONE_INTERFACE.get(), state, pos);
    }

    public int getRedStoneSignalLevel() {
        return level.getBestNeighborSignal(getBlockPos());
    }


    public void powerChange(int power) {
        ((MMTileMainPartBase) level.getBlockEntity(mainPart)).componentEvent(new ComponentEvent(this, power, key));
    }
}
