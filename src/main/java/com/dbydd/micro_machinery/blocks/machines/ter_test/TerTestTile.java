package com.dbydd.micro_machinery.blocks.machines.ter_test;

import com.dbydd.micro_machinery.registery_lists.Registered_Tileentitie_Types;
import net.minecraft.tileentity.TileEntity;

public class TerTestTile extends TileEntity {
    public TerTestTile() {
        super(Registered_Tileentitie_Types.TEST_TILE_TYPE.get());
    }
}
