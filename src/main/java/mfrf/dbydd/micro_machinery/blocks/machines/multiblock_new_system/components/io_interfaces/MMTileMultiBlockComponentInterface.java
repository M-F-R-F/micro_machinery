package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MMTileMultiBlockComponentInterface extends TileEntity {
    private BlockPos mainPart = null;

    public MMTileMultiBlockComponentInterface(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }


    public void linkTo(BlockPos pos, World world) {
        mainPart = pos;
//        MMTileMultiBlockPart tileEntity = (MMTileMultiBlockPart) world.getTileEntity(pos);
        markDirty();
    }


}
