package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityKlin extends TileEntityBase {

    public IFluidHandler fluidHandler = new FluidTank(2000);

    public TileEntityKlin(ItemStackHandler itemhandler, String customName, String blockbyte) {
        super(itemhandler, customName, blockbyte);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.itemhandler;
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this.fluidHandler;
        return super.getCapability(capability, facing);
    }
}
