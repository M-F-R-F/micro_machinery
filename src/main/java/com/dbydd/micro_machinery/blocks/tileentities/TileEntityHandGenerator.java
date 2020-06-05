package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileentityHandGenerator extends TileEntity implements ITickable, IEnergyStorage {

    private static final int MAX_GENERATE_TIME = 20;
    private int progress = 0;
    private boolean isGenerating = false;


    private boolean pushEnergyBehind() {
        EnumFacing behind = world.getBlockState(pos).getValue(BlockHorizontal.FACING);
        TileEntity te = world.getTileEntity(pos.offset(behind));
        if (te != null) {
            if (te.hasCapability(CapabilityEnergy.ENERGY, behind.getOpposite())) {
                te.getCapability(CapabilityEnergy.ENERGY, behind.getOpposite()).receiveEnergy(20, false);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        compound.setInteger("progress", progress);
        compound.setBoolean("isgenerating", isGenerating);
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        progress = compound.getInteger("progress");
        isGenerating = compound.getBoolean("isgenerating");
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (facing == world.getBlockState(pos).getValue(BlockHorizontal.FACING).getOpposite())
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing == world.getBlockState(pos).getValue(BlockHorizontal.FACING).getOpposite())
            return (T) this;
        return super.getCapability(capability, facing);
    }

    public void actived() {
        this.isGenerating = true;
        markDirty();
    }

    @Override
    public void update() {
        if(world.isRemote) {
            if (progress >= MAX_GENERATE_TIME) {
                isGenerating = false;
                progress = 0;
                markDirty();
            } else {
                progress++;
                pushEnergyBehind();
                markDirty();
            }
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isGenerating() {
        return isGenerating;
    }
}
