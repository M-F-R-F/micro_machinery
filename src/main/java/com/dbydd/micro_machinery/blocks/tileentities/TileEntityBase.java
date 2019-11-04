package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityBase extends TileEntity implements ITickable, IInventory {

    public static ItemStackHandler itemhandler;
    private static String customName;

    public static ItemStackHandler getItemhandler() {
        return itemhandler;
    }

    public static void setItemhandler(int indexcount) {
        itemhandler = new ItemStackHandler(indexcount);
    }

    public static String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        TileEntityBase.customName = customName;
    }

    public boolean hasCustomName() {
        return customName != null && !this.customName.isEmpty();
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("Micro_Machinery" + customName);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemhandler.setStackInSlot(index, stack);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return itemhandler.extractItem(index, count, false);
    }

    @Override
    public int getSizeInventory() {
        return itemhandler.getSlots();
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return decrStackSize(index, itemhandler.getStackInSlot(index).getCount());
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return itemhandler.getStackInSlot(index);
    }

    @Override
    public abstract boolean hasCapability(Capability<?> capability, EnumFacing facing);

    @Override
    public abstract <T> T getCapability(Capability<T> capability, EnumFacing facing);

    @Override
    public abstract void readFromNBT(NBTTagCompound compound);

    @Override
    public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);

    @Override
    public abstract void openInventory(EntityPlayer player);

    @Override
    public abstract void closeInventory(EntityPlayer player);

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public abstract void update();

}


