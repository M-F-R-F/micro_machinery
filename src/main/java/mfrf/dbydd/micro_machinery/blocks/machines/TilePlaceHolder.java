package mfrf.dbydd.micro_machinery.blocks.machines;

import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import net.minecraft.block.BlockState;

public class TilePlaceHolder extends MMTileBase{
    public TilePlaceHolder() {
        super(Registered_Tileentitie_Types.TILE_PLACEHOLDER.get());
    }
    private BlockState state;
    
}
