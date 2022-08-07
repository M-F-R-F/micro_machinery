package mfrf.dbydd.micro_machinery.utils;

import mfrf.dbydd.micro_machinery.Config;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Queue;

public class NBTItemContainer implements INBTSerializable<ListNBT>, IItemHandler {
    private Queue<CompoundNBT> items = new ArrayDeque<>();
    private ItemStack cache = ItemStack.EMPTY;


    public void put(CompoundNBT nbt) {
        items.offer(nbt);
    }

    public void put(ItemStack stack) {
        items.offer(stack.serializeNBT());
    }

    public ItemStack get()

    @Nullable
    public CompoundNBT ejectNbt() {
        return items.poll();
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT inbts = new ListNBT();
        inbts.add(cache.serializeNBT());
        inbts.addAll(items);
        return inbts;
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        cache = ItemStack.read(nbt.getCompound(0));
        for (int i = 1; i < nbt.size(); i++) {
            items.offer(nbt.getCompound(i));
        }
    }

    @Override
    public int getSlots() {
        return Math.max(items.size() + (cache.isEmpty() ? 0 : 1), Config.CONVEY_BELT_STACK_SIZE_LIMIT.get());
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }
}
