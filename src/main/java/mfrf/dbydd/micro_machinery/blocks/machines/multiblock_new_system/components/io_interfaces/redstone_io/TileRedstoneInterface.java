package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;

public class TileRedstoneInterface extends MMTileMultiBlockComponentInterface {
    public TileRedstoneInterface() {
        super(RegisteredTileEntityTypes.REDSTONE_INTERFACE.get());
    }

    public int getLevel() {
        return world.getRedstonePowerFromNeighbors(pos);
    }


}
