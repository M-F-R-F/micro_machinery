package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityKlin extends TileEntityBase implements IFluidTank {

    public static FluidTank fluidHandler = new FluidTank(2000);
    public static int heatwastepersec = 20, heatlimit = 3600, slot = 4, heat, timer1, timer2;

    public TileEntityKlin() {
        setItemhandler(slot);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) itemhandler;
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) fluidHandler;
        return getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        //todo
        writeToNBT(compound);
        itemhandler.deserializeNBT(compound.getCompoundTag("Inventory"));
        heat = compound.getInteger("heat");
//        this.cookTime = compound.getInteger("CookTime");
//        this.totalCookTime = compound.getInteger("CookTimeTotal");
//        this.currentBurnTime = getItemBurnTime((ItemStack)itemhandler.getStackInSlot(2));

        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        //todo
        compound.setInteger("heat", heat);
//        compound.setInteger("CookTime", (short)this.cookTime);
//        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        compound.setTag("Inventory", itemhandler.serializeNBT());

        if (this.hasCustomName()) compound.setString("CustomName", getCustomName());
        return compound;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < slot; i++) {
            if (itemhandler.getStackInSlot(i) != ItemStack.EMPTY) return false;
        }
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public String getName() {
        return getCustomName();
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        return fluidHandler.getFluid();
    }

    @Override
    public int getFluidAmount() {
        return fluidHandler.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return fluidHandler.getCapacity();
    }

    @Override
    public FluidTankInfo getInfo() {
        return fluidHandler.getInfo();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return fluidHandler.fill(resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return fluidHandler.drain(maxDrain, doDrain);
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public void clear() {

    }


    @Override
    public void update() {

    }
//todo
}
