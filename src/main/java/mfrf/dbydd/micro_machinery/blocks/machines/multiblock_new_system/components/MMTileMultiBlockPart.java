package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MMTileMultiBlockPart extends TileEntity {
    private CompoundNBT packed = new CompoundNBT();


    public MMTileMultiBlockPart(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        packed = compound.getCompound("packed");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("packed", packed);
        return write;
    }

    public void setPacked(CompoundNBT nbt) {
        packed = nbt;
        markDirty();
    }
}
