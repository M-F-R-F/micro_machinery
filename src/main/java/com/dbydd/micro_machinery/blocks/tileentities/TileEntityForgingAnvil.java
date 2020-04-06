package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.items.tools.ToolHammer;
import com.dbydd.micro_machinery.recipes.RecipeHelper;
import com.dbydd.micro_machinery.recipes.forginganvil.ForgingAnvilRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityForgingAnvil extends TileEntity {
    private int level;
    private int forgetime = 0;
    private ItemStackHandler handler = new ItemStackHandler(3) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if (slot == 1 && !(stack.getItem() instanceof ToolHammer)) return false;
            return true;
        }
    };//0:material,1:hammer,2:output
    public TileEntityForgingAnvil(int level) {
        this.level = level;
    }

    public TileEntityForgingAnvil() {
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.level = compound.getInteger("level");
        this.forgetime = compound.getInteger("forgetime");
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("level", this.level);
        compound.setInteger("forgetime", this.forgetime);
        compound.setTag("Inventory", this.handler.serializeNBT());
        markDirty();
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (T) this.handler;
    }

    public int getField(String id) {
        switch (id) {
            case "level":
                return level;
            case "forgetime":
                return forgetime;
            default:
                return 0;
        }
    }

    public void setField(String id, int data) {
        switch (id) {
            case "level":
                this.level = data;
                break;
            case "forgetime":
                this.forgetime = data;
                break;
        }
    }

    public void setField(int id, int data) {
        switch (id) {
            case 0:
                this.level = data;
                break;
            case 1:
                this.forgetime = data;
                break;
        }
    }

    public void forge() {
        if (!handler.getStackInSlot(1).isEmpty()) {
            ForgingAnvilRecipe recipe = RecipeHelper.getForgingAnvilRecipe(handler.getStackInSlot(0));
            if (recipe != null) {
                boolean caninsert = RecipeHelper.canInsert(handler.getStackInSlot(2), recipe.getOutput());
                if (recipe.getLevel() <= this.level) {
                    forgetime++;
                    if (forgetime >= recipe.getForgetime() && caninsert) {
                        handler.insertItem(2, recipe.getOutput(), false);
                        handler.extractItem(0, recipe.getInput().getCount(), false);
                        forgetime = 0;
                    } else if (!caninsert) {
                        forgetime--;
                    }
                }
            }
            ItemStack hammer = handler.getStackInSlot(1);
            hammer.setItemDamage(hammer.getItemDamage() + 1);
            if (hammer.getItemDamage() >= hammer.getMaxDamage()) hammer.shrink(1);
            markDirty();
            syncToTrackingClients();
        }

    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.getNbtCompound();
        readFromNBT(tag);
    }

    protected final void syncToTrackingClients() {
        if (!this.world.isRemote) {
            SPacketUpdateTileEntity packet = this.getUpdatePacket();
            PlayerChunkMapEntry trackingEntry = ((WorldServer) this.world).getPlayerChunkMap().getEntry(this.pos.getX() >> 4, this.pos.getZ() >> 4);
            if (trackingEntry != null) {
                for (EntityPlayerMP player : trackingEntry.getWatchingPlayers()) {
                    player.connection.sendPacket(packet);
                }
            }
        }
    }

}
