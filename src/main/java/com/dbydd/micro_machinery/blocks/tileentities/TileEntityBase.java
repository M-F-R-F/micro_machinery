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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBase extends TileEntity implements ITickable {

    public static ItemStackHandler itemhandler;
    public static String customName;
    public static String blockbyte;

    public static ItemStackHandler getItemhandler() {
        return itemhandler;
    }

    public static void setItemhandler(ItemStackHandler itemhandler) {
        TileEntityBase.itemhandler = itemhandler;
    }

    public static String getCustomName() {
        return customName;
    }

    public static String getBlockbyte() {
        return blockbyte;
    }

    public static void setBlockbyte(String blockbyte) {
        TileEntityBase.blockbyte = blockbyte;
    }

    public TileEntityBase(ItemStackHandler itemhandler, String customName, String blockbyte) {
        setItemhandler(itemhandler);
        setBlockbyte(blockbyte);
        setCustomName(customName);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        else return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.itemhandler;
        return super.getCapability(capability, facing);
    }

    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("Micro_Machinery" + blockbyte + customName);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemhandler.deserializeNBT(compound.getCompoundTag("Inventory"));

        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.itemhandler.serializeNBT());

        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {

    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

}

