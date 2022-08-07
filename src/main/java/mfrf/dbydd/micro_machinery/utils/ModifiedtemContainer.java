package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ModifiedtemContainer implements IItemHandler {
    private Slot[] slots;

    public ModifiedtemContainer(int size, IntegerContainer slotSize) {
        this.slots = new Slot[size];
        for (int i = 0; i < size; i++) {
            slots[i] = new Slot(slotSize);
        }
    }

    @Override
    public int getSlots() {
        return slots.length;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        Slot slotInstance = slots[slot];
        ItemStack itemStack = ItemStack.read(slotInstance.itemStack);
        itemStack.setCount(slotInstance.size.getCurrent());
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return slots[slot].insert(stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return slots[slot].extract(amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return slots[0].size.getMax();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return insertItem(slot, stack, true).equals(stack);
    }


    private class Slot {
        private CompoundNBT itemStack;
        private IntegerContainer size;

        public Slot(ItemStack itemStack, IntegerContainer size) {
            this.itemStack = itemStack.getTag();
            this.size = size;
        }

        public Slot(IntegerContainer size) {
            itemStack = ItemStack.EMPTY.getTag();
        }

        /**
         * @param stackToInsert stack to insert
         * @return same as ItemHandler, the remains.
         */
        public ItemStack insert(ItemStack stackToInsert, boolean simulate) {
            ItemStack currentStack = ItemStack.read(itemStack);
            ItemStack copied = stackToInsert.copy();
            copied.setCount(1);
            if (currentStack.isEmpty()) {
                itemStack = copied.getTag();
                size.setCurrent(stackToInsert.getCount());
                return ItemStack.EMPTY;
            } else {
                if (currentStack.getTag().equals(copied.getTag())) {
                    int count_to_insert = stackToInsert.getCount();
                    int remainSpace = size.getRemainSpace();
                    if (remainSpace > count_to_insert) {
                        size.add(count_to_insert, simulate);
                    } else {
                        size.add(remainSpace, simulate);
                        int i = count_to_insert - remainSpace;
                        ItemStack copy2 = copied.copy();
                        copy2.setCount(i);
                        return copy2;

                    }
                }
            }
            return stackToInsert;
        }

        /**
         * @param count count that expect to be extract
         * @return actual extracted
         */
        public ItemStack extract(int count, boolean simulate) {
            ItemStack currentStack = ItemStack.read(itemStack);
            if (!currentStack.isEmpty()) {
                ItemStack copy = currentStack.copy();
                int minus = size.minus(count, simulate);
                copy.setCount(minus);

                if (size.atMinValue() && !simulate) {
                    itemStack = ItemStack.EMPTY.getTag();
                }

                return copy;

            }
            return ItemStack.EMPTY;
        }

        public CompoundNBT getItemStack() {
            return itemStack;
        }

        public IntegerContainer getSize() {
            return size;
        }
    }
}
