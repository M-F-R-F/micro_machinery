package org.mfrf.micro_machienry.blocks.machines.ter_test;

import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.tileentity.TileEntity;

public class TerTestTile extends TileEntity {
    public TerTestTile() {
        super(RegisteredTileEntityTypes.TEST_TILE_TYPE.get());
    }
}
