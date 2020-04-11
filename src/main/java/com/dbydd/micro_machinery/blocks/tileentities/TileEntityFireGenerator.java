package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityFireGenerator extends MMFEMachineBase implements ITickable, IFluidHandler {

    private ItemStackHandler fuelHandler = new ItemStackHandler(1);
    private FluidTank tank = new FluidTank(4000);
    private int maxBurnTime = 0;
    private int currentBurnTime = 0;
    private int generateFEPerTick = 0;
    private int waterNeededPerTick = 0;
    private boolean isGenerating = false;

    public TileEntityFireGenerator(int maxEnergyCapacity) {
        super(maxEnergyCapacity, EnumMMFETileEntityStatus.OUTPUT, 0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", fuelHandler.serializeNBT());
        compound.setInteger("maxBurnTime",maxBurnTime);
        compound.setInteger("currentBurnTime",currentBurnTime);
        compound.setInteger("generateFEPerTick",generateFEPerTick);
        compound.setInteger("waterNeededPerTick",waterNeededPerTick);
        compound.setBoolean("isGenerating",isGenerating);
        return super.writeToNBT(tank.writeToNBT(compound));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        tank.readFromNBT(compound);
        isGenerating = compound.getBoolean("isGenerating");
        waterNeededPerTick = compound.getInteger("waterNeededPerTick");
        generateFEPerTick = compound.getInteger("generateDEPerTick");
        currentBurnTime = compound.getInteger("currentBurnTime");
        maxBurnTime = compound.getInteger("maxBurnTime");
        fuelHandler.deserializeNBT(compound.getCompoundTag("inventory"));
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) fuelHandler;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this;
        if (capability == CapabilityEnergy.ENERGY) return (T) this;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
        if (capability == CapabilityEnergy.ENERGY) return true;
        return super.hasCapability(capability, facing);
    }


    @Override
    public void update() {



    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (resource.getFluid().getBlock() == Blocks.WATER) return tank.fill(resource, doFill);
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return tank.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }
}
