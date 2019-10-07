package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityBase extends TileEntity implements ITickable {

    public static ItemStackHandler itemhandler;
    public static String customName;

    public static ItemStackHandler getItemhandler() {
        return itemhandler;
    }

    public static void setItemhandler(ItemStackHandler itemhandler) {
        TileEntityBase.itemhandler = itemhandler;
    }

    public static String getCustomName() {
        return customName;
    }


    @Override
    public abstract boolean hasCapability(Capability<?> capability, EnumFacing facing);

    @Override
    public abstract <T> T getCapability(Capability<T> capability, EnumFacing facing);

    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("Micro_Machinery" + customName);
    }

    @Override
    public abstract void readFromNBT(NBTTagCompound compound);

    @Override
    public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public abstract void update();

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public abstract int getField(int id);

    public abstract void setField(int id, int value);

}


