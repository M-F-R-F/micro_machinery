package org.mfrf.micro_machienry.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;

public class TileRedstoneInterface extends MMTileMultiBlockComponentInterface {
    public TileRedstoneInterface() {
        super(RegisteredTileEntityTypes.REDSTONE_INTERFACE.get());
    }

    public int getLevel() {
        return world.getRedstonePowerFromNeighbors(pos);
    }


    public void powerChange(int power) {
        ((MMTileMainPartBase) world.getTileEntity(mainPart)).redstoneSignalChange(power, key);
    }
}
