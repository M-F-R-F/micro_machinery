package mfrf.dbydd.micro_machinery.utils;

import mfrf.dbydd.micro_machinery.Config;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.function.Consumer;

public class ConfigurableItemSlot implements IItemHandler, INBTSerializable<ListNBT> {

    private LinkedList<ItemStack> stacks = new LinkedList<>();

    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT stacksNBT = new ListNBT();
        for (ItemStack stack : stacks) {
            stacksNBT.add(stack.serializeNBT());
        }
        return stacksNBT;
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        for (INBT inbt : nbt) {
            stacks.add(ItemStack.read(((CompoundNBT) inbt)));
        }
    }

    @Override
    public int getSlots() {
        return Math.max(stacks.size(), Config.CONVEY_BELT_STACK_SIZE_LIMIT.get());
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        ItemStack stackInSlot = getStackInSlot(slot);
        if (ItemHandlerHelper.canItemStacksStack(stack, stackInSlot)) {
            int insertSpace = stackInSlot.getMaxStackSize() - stackInSlot.getCount();
            int remain = Math.max(stack.getCount() - insertSpace, 0);
            ItemStack returnV = stack.copy();
            returnV.setCount(remain);

            if (!simulate) {
                ItemStack inserted = stackInSlot.copy();
                inserted.setCount(remain < 0 ? stack.getMaxStackSize() : stack.getCount() + stackInSlot.getCount());
                stacks.set(slot, inserted);
            }
            return returnV;
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack itemStack = stacks.get(slot);
        int currentCount = itemStack.getCount();
        int extracted = Math.min(currentCount, amount);
        ItemStack copy = itemStack.copy();
        copy.setCount(extracted);
        if (simulate) {
            itemStack.shrink(extracted);
        }
        return copy;
    }


    @Override
    public int getSlotLimit(int slot) {
        return stacks.get(slot).getMaxStackSize();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    @Nullable
    public poppedStack tryPopOneStack() {
        ItemStack itemStack = stacks.get(0);
        return new poppedStack(stack -> {
            if (stack.isEmpty()) {
                stacks.remove(0);
            } else {
                stacks.set(0, stack.copy());
            }
        }, itemStack);
    }

    public static class poppedStack {
        public final Consumer<ItemStack> consumed;
        public final ItemStack stack;

        public poppedStack(Consumer<ItemStack> consumed, ItemStack stack) {
            this.consumed = consumed;
            this.stack = stack;
        }
    }
}
