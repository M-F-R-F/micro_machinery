package org.mfrf.micro_machienry.blocks.machines.multiblock_new_system.components.io_interfaces;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class MMTileMultiBlockComponentInterface extends TileEntity {
    protected BlockPos mainPart = null;
    protected Vec3i key = Vec3i.NULL_VECTOR;

    public MMTileMultiBlockComponentInterface(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("main")) {
            mainPart = NBTUtil.readBlockPos(compound.getCompound("main"));
        }
        key = mfrf.dbydd.micro_machinery.utils.NBTUtil.readVEC3I(compound.getCompound("key"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        if (mainPart != null) {
            write.put("main", NBTUtil.writeBlockPos(mainPart));
        }
        write.put("key", mfrf.dbydd.micro_machinery.utils.NBTUtil.writeVEC3I(key));
        return write;
    }


    public void linkTo(BlockPos pos, World world, Vec3i key) {
        this.key = key;
        mainPart = pos;
        ((MMTileMainPartBase) world.getTileEntity(mainPart)).linkComponent(this.pos, key);
        markDirty();
    }

    public void unLink() {
        mainPart = null;
        key = Vec3i.NULL_VECTOR;
        markDirty();
    }


}
