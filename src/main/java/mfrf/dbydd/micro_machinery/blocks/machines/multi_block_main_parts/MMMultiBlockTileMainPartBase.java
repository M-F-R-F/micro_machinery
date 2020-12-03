package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockMainPart;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

public abstract class MMMultiBlockTileMainPartBase extends MMTileBase implements IMultiBlockMainPart {
    private CompoundNBT compoundNBTTileReplaced = null;

    public MMMultiBlockTileMainPartBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (compoundNBTTileReplaced != null) {
            compound.put("tile_replaced", compoundNBTTileReplaced);
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("tile_replaced")) {
            compoundNBTTileReplaced = compound.getCompound("tile_replaced");
        }
    }

    public void saveTileBeenReplaced(CompoundNBT compoundNBT) {
        compoundNBTTileReplaced = compoundNBT;
    }
}
