package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io;


import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;

public class TileRedstoneInterface extends MMTileMultiBlockComponentInterface {
    public TileRedstoneInterface() {
        super(RegisteredBlockEntityTypes.REDSTONE_INTERFACE.get());
    }

    public int getRedStoneSignalLevel() {
        return level.getBestNeighborSignal(getBlockPos());
    }


    public void powerChange(int power) {
        ((MMTileMainPartBase) level.getBlockEntity(mainPart)).redstoneSignalChange(power, key);
    }
}
