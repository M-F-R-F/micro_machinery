package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemContainer implements IItemHandler {

    private final IItemHandler itemHandler;
    private final boolean canExtract;
    private final boolean canInsert;

    public ItemContainer(IItemHandler handler, boolean canInsert, boolean canExtract) {
        this.itemHandler = handler;
        this.canInsert = canInsert;
        this.canExtract = canExtract;
    }

    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int i) {
        return itemHandler.getStackInSlot(i);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int i, @Nonnull ItemStack itemStack, boolean b) {
        if (canInsert) {
            return itemHandler.insertItem(i, itemStack, b);
        }
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int i, int i1, boolean b) {
        if (canExtract) {
            return itemHandler.extractItem(i, i1, b);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int i) {
        return itemHandler.getSlotLimit(i);
    }

    @Override
    public boolean isItemValid(int i, @Nonnull ItemStack itemStack) {
        return itemHandler.isItemValid(i, itemStack);
    }
}
