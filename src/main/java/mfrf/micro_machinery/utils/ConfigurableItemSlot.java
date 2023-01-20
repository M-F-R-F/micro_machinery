package mfrf.micro_machinery.utils;

import mfrf.micro_machinery.Config;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.function.Consumer;

public class ConfigurableItemSlot implements IItemHandler, INBTSerializable<CompoundTag> {

    private LinkedList<ItemStack> stacks = new LinkedList<>();
    private int max_stack_size;

    public ConfigurableItemSlot(int max_stack_size) {
        this.max_stack_size = max_stack_size;
    }

    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    public boolean containItem() {
        return !stacks.isEmpty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        ListTag stacksNBT = new ListTag();
        for (ItemStack stack : stacks) {
            stacksNBT.add(stack.serializeNBT());
        }
        compoundNBT.put("stacks", stacksNBT);
        compoundNBT.putInt("max_stack_size", max_stack_size);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag stacks = nbt.getList("stacks", Constants.NBT.TAG_COMPOUND);
        for (INBT inbt : stacks) {
            this.stacks.add(ItemStack.read(((CompoundTag) inbt)));
        }
        max_stack_size = nbt.getInt("max_stack_size");
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
            int insertSpace = Integer.min(stackInSlot.getMaxStackSize(), max_stack_size) - stackInSlot.getCount();
            int remain = Math.max(stack.getCount() - insertSpace, 0);
            ItemStack returnV = stack.copy();
            returnV.setCount(remain);

            if (!simulate) {
                ItemStack inserted = stackInSlot.copy();
                inserted.setCount(remain < 0 ? Integer.min(stack.getMaxStackSize(), max_stack_size) : stack.getCount() + stackInSlot.getCount());
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
        return Math.min(stacks.get(slot).getMaxStackSize(), max_stack_size);
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

    public boolean atLimit() {
        return getSlots() >= Config.CONVEY_BELT_STACK_SIZE_LIMIT.get();
    }

    public int spaceRemain() {
        return atLimit() ? 0 : Config.CONVEY_BELT_STACK_SIZE_LIMIT.get() - getSlots();
    }

}
