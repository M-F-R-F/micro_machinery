package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETineEntityStatus;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class TileentityEnergyModue extends MMFEMachineBase {

    public TileentityEnergyModue(int level, int maxCapacity, int maxFortage, int maxFurrect, EnumMMFETineEntityStatus status, int materialLossValue) {
        super(level, maxCapacity, maxFortage, maxFurrect, status, materialLossValue);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energystored", energyStored);
        compound.setInteger("fortage",fortage);
        compound.setInteger("furrect",furrect);
        compound.setString("status", status.name());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
       this.status =  EnumMMFETineEntityStatus.valueOf(compound.getString("energyStored"));
       this.furrect = compound.getInteger("furrect");
       this.fortage = compound.getInteger("fortage");
       this.energyStored = compound.getInteger("energystored");
    }

    @Override
    public int getLossValue() {
        return Math.round(fortage/furrect);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return (T) this;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

}