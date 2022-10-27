package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TileConveyBelt extends MMTileBase implements ITickableTileEntity {

    public TileConveyBelt() {
        super(RegisteredTileEntityTypes.TILE_CONVEY_BELT.get());
    }


//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//
//    }

    @Override
    public void tick() {
    }


    static class AttributeUtil {

        public class StackArray implements INBTSerializable<CompoundNBT>, IItemHandler {
            private LinkedList<Stack> stacks = new LinkedList<>();
            private int max = 0;

            public StackArray(int max) {
                this.max = max;
            }

            @Override
            public CompoundNBT serializeNBT() {
                CompoundNBT compoundNBT = new CompoundNBT();
                compoundNBT.putInt("max", max);

                ListNBT listNBT = new ListNBT();
                stacks.forEach(s -> listNBT.add(s.serializeNBT()));
                compoundNBT.put("stacks", listNBT);
                return compoundNBT;
            }

            @Override
            public void deserializeNBT(CompoundNBT nbt) {
                max = nbt.getInt("max");
                for (INBT inbt : nbt.getList("stacks", Constants.NBT.TAG_COMPOUND)) {
                    Stack stack = new Stack();
                    stack.deserializeNBT((CompoundNBT) inbt);
                    stacks.add(stack);
                }

            }

            public List<ItemStack> popAll(long current, int condition) {
                List<Stack> collect = stacks.stream().filter(stack -> stack.olderThan(current, condition)).collect(Collectors.toList());
                stacks.removeAll(collect);
                return collect.stream().map(Stack::getStack).collect(Collectors.toList());
            }

            public ItemStack popOne(long current, int condition) {
                Optional<Stack> first = stacks.stream().filter(stack -> stack.olderThan(current, condition)).findFirst();
                AtomicReference<ItemStack> ret = new AtomicReference<>(ItemStack.EMPTY);
                first.ifPresent(stack -> {
                    stacks.remove(stack);
                    ret.set(stack.stack);
                });
                return ret.get();
            }


            @Override
            public int getSlots() {
                return Math.max(max, stacks.size());
            }

            @Nonnull
            @Override
            public ItemStack getStackInSlot(int slot) {
                return stacks.get(slot).stack;
            }

            //todo consider time.
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stacks.size() >= max || slot >= max)
                    return stack;
                ItemStack in = stack.copy();
                Stack stackSaved;

                if (stacks.get(slot) != null) {
                    stackSaved = stacks.get(slot);
                } else {
                    stackSaved = new Stack();
                    stacks.set(slot, stackSaved);
                }

                int maxStackSize = stackSaved.stack.getMaxStackSize();
                int count = stackSaved.stack.getCount();

                if (!stackSaved.stack.isItemEqual(stack) || count >= maxStackSize) {
                    return stack;
                }

                int space = maxStackSize - count;
                int remain = stack.getCount() - space;
                if (simulate) {
                    if (remain > 0) {
                        stackSaved.stack.setCount(maxStackSize);
                    } else {
                        stackSaved.stack.setCount(count + stack.getCount());
                    }
                }

                return new ItemStack(stack.getItem(), remain);
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                Stack stack = stacks.get(slot);
                if (stack == null) {
                    return ItemStack.EMPTY;
                }
                int count = stack.stack.getCount();
                int remain = Math.max(count - amount, 0);
                int extracted = count - remain;
                if (simulate) {
                    if (remain == 0) {
                        stacks.remove(slot);
                    } else {
                        stack.stack.setCount(remain);
                    }
                }

                return new ItemStack(stack.stack.getItem(), extracted);
            }

            @Override
            public int getSlotLimit(int slot) {
                Stack stack = stacks.get(slot);
                if (stack != null) {
                    return stack.stack.getMaxStackSize();
                } else {
                    return 64;
                }
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            public class Stack implements INBTSerializable<CompoundNBT> {
                private ItemStack stack = ItemStack.EMPTY;
                private long inTime = 0;

                public Stack(ItemStack stack) {
                    this.stack = stack;
                }

                public Stack() {
                }

                public ItemStack getStack() {
                    return stack;
                }

                public void setStack(ItemStack stack) {
                    this.stack = stack;
                }

                public boolean olderThan(long current, int condition) {
                    return (current - inTime) < condition;
                }

                @Override
                public CompoundNBT serializeNBT() {
                    CompoundNBT compoundNBT = new CompoundNBT();
                    CompoundNBT stack = this.stack.serializeNBT();
                    compoundNBT.put("stack", stack);
                    compoundNBT.putLong("intime", inTime);
                    return compoundNBT;
                }

                @Override
                public void deserializeNBT(CompoundNBT nbt) {
                    stack = ItemStack.read(nbt.getCompound("stack"));
                    inTime = nbt.getLong("intime");
                }
            }
        }

    }
}
