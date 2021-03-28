package mfrf.dbydd.micro_machinery.blocks.machines.cutter;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.tileentity.TileEntityType;

public class TileCutter extends MMTileBase {
    public TileCutter() {
        super(RegisteredTileEntityTypes.TILE_CUTTER.get());
    }
}
