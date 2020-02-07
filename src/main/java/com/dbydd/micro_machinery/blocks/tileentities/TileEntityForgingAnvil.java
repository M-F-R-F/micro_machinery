package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.items.tools.ToolHammer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityForgingAnvil extends TileEntity {
    private int level;
    private ItemStackHandler inputslot = new ItemStackHandler(2) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if (slot == 1 && !(stack.getItem() instanceof ToolHammer)) return false;
            return true;
        }
    };
    private ItemStackHandler outpotslot = new ItemStackHandler(1);

    public TileEntityForgingAnvil(int level) {
        this.level = level;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == null) return (T) this.inputslot;
        return (T) this.outpotslot;
    }

    public ItemStack getStackInSlot(int slot) {
        return slot <= 2 ? inputslot.getStackInSlot(slot - 1) : outpotslot.getStackInSlot(slot - 1);
    }

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return slot <= 2 ? inputslot.insertItem(slot - 1, stack, false) : outpotslot.insertItem(slot - 1, stack, false);
    }

    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

}
