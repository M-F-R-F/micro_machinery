package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import com.dbydd.micro_machinery.vector.FluxFlowVector;
import com.dbydd.micro_machinery.vector.FluxPowerVector;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public abstract class MMFEMachineBase extends TileEntity implements IMMFEStorage {

    protected final int maxEnergyCapacity;
    protected final int lossValue;
    protected int energyStored;
    protected EnumMMFETileEntityStatus status;
    protected EnumInfluenceDirection influenceDirection;

    MMFEMachineBase(int level, int maxEnergyCapacity, EnumMMFETileEntityStatus status, int lossValue) {
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.status = status;
        this.lossValue = lossValue;
    }

    @Override
    public int loss(int FENeedToLoss) {
        return FENeedToLoss - lossValue;
    }

    @Override
    public int getLossValue() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return status != EnumMMFETileEntityStatus.INPUT;
    }

    @Override
    public boolean canReceive() {
        return status == EnumMMFETileEntityStatus.INPUT;
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergyCapacity;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (maxReceive + energyStored >= energyStored) {
            if (!simulate) energyStored = maxEnergyCapacity;
            return maxReceive + energyStored - maxEnergyCapacity;
        } else {
            if (!simulate)
                energyStored += maxReceive;
            return 0;
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int output = 0;
        if (maxExtract >= energyStored) {
            output = energyStored;
            if (!simulate) energyStored = 0;
            return output;
        } else {
            if (!simulate)
                energyStored -= maxExtract;
            return maxExtract;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energystored", energyStored);
        compound.setString("status", status.name());
        compound.setString("influenceDirection",influenceDirection.name() );
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.influenceDirection = EnumInfluenceDirection.valueOf(compound.getString("influenceDirection"));
        this.status = EnumMMFETileEntityStatus.valueOf(compound.getString("status"));
        this.energyStored = compound.getInteger("energystored");
    }

    @Override
    public FluxPowerVector generateInfluences() {
        return null;
    }

    @Override
    public void ActionForgneticForce(FluxPowerVector force) {

    }

    @Override
    public FluxFlowVector getForgneticForce() {
        return null;
    }

    @Override
    public FluxFlowVector getPreviousForgneticForce() {
        return null;
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return null;
    }

    @Override
    public void updateState() {

    }

}
