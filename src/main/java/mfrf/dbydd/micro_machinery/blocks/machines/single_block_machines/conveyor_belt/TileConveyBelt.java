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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class TileConveyBelt extends MMTileBase implements ITickableTileEntity {
    public StackArray array;
    private int interval = 0;

    public TileConveyBelt() {
        super(RegisteredTileEntityTypes.TILE_CONVEY_BELT.get());
        BlockConveyorBelt conveyorBelt = (BlockConveyorBelt) getBlockState().getBlock();
        array = new StackArray(conveyorBelt.properties_speed_stack_interval_supplier.b.get(), conveyorBelt.properties_speed_stack_interval_supplier.a.get());
    }

    @Override
    public void tick() {
        //todo remake
//        if (!world.isRemote()) {
//            List<StackArray.CallbackSlot> popped = array.popAll();
//
//            Direction out = getBlockState().get(BlockConveyorBelt.FACING);
//            EnumConveyorConnectState out_state = getBlockState().get(BlockConveyorBelt.OUT_STATE);
//            if (!popped.isEmpty()) {
//                boolean toConveyorBeltOnly = false;
//                BlockPos out_pos = getPos().offset(out);
//                if (out_state == EnumConveyorConnectState.DOWN) {
//                    out_pos = out_pos.down();
//                    toConveyorBeltOnly = true;
//                }
//                if (out_state == EnumConveyorConnectState.UP) {
//                    out_pos = out_pos.up();
//                    toConveyorBeltOnly = true;
//                }
//
//                TileEntity tileEntity = world.getTileEntity(out_pos);
//                if (toConveyorBeltOnly) {
//                    if (tileEntity instanceof TileConveyBelt) {
//                        TileConveyBelt conveyBelt = (TileConveyBelt) tileEntity;
//                        if (conveyBelt.array.notFull()) {
//                            conveyBelt.array.receive(popped);
//                            markDirty();
//                            conveyBelt.markDirty();
//                        }
//                    }
//                } else {
//                    tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, out.getOpposite()).ifPresent(iItemHandler -> {
//                        for (StackArray.CallbackSlot callbackSlot : popped) {
//
//                            ItemStack outS = ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.func(), false);
//                            if (!outS.isEmpty()) {
//                                ItemEntity itemEntity = new ItemEntity(world, this.pos.getX(), this.pos.getY() + 0.6, this.pos.getZ(), outS);
//                                itemEntity.setDefaultPickupDelay();
//                                world.addEntity(itemEntity);
//                            }
//
//                        }
//                    });
//                }
//            }
//        }
    }

    //    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//
//    }


    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        array.deserializeNBT(compound.getCompound("array"));
        interval = compound.getInt("interval");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("array", array.serializeNBT());
        write.putInt("interval", interval);
        return write;
    }

    public static class StackArray implements INBTSerializable<CompoundNBT>, IItemHandler {
        private LinkedList<Stack> stacks = new LinkedList<>();
        private int max = 0;
        private int time;

        public StackArray(int max, int timeToTransfer) {
            this.max = max;
            this.time = timeToTransfer;
        }

        public StackArray() {

        }

        public boolean notFull() {
            return stacks.size() < max;
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

        public boolean tick() {
            AtomicBoolean bool = new AtomicBoolean(false);
            stacks.forEach(s -> bool.set(bool.get() || s.tick()));
            return bool.get();
        }

        public List<CallbackSlot> popAll() {
            return stacks.stream().filter(Stack::popAble).map(Stack::getStack).map(CallbackSlot::new).collect(Collectors.toList());
        }

        class CallbackSlot {

            final ItemStack held;

            CallbackSlot(ItemStack held) {
                this.held = held;
            }

            public ItemStack func() {
                stacks.remove(held);
                return held;
            }
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

        public boolean in(ItemStack stack) {
            return stacks.add(new Stack(stack.copy(), time));
        }

        public void receive(List<CallbackSlot> in) {
            int space = max - stacks.size();
            for (int i = 0; i < Math.min(space, in.size()); i++) {
                in(in.get(i).func());
            }

        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (stacks.size() >= max || slot >= max)
                return stack;
            Stack stackSaved;

            if (stacks.get(slot) != null) {
                stackSaved = stacks.get(slot);
            } else {
                stackSaved = new Stack(stack.copy(), time);
                if (!simulate) {
                    stacks.set(slot, stackSaved);
                }
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
                return stack.stack.getCount();
            }
            return 64;
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return true;
        }

        public class Stack implements INBTSerializable<CompoundNBT> {
            private ItemStack stack = ItemStack.EMPTY;
            private int time = 0;

            public Stack(ItemStack stack, int time) {
                this.stack = stack;
                this.time = time;
            }

            public Stack(ItemStack stack) {
                this.stack = stack;
            }

            public Stack(int time) {
                this.time = time;
            }

            public Stack() {
            }

            public ItemStack getStack() {
                return stack;
            }

            public boolean tick() {
                --time;
                return popAble();
            }

            public boolean popAble() {
                return time <= 0;
            }

            @Override
            public CompoundNBT serializeNBT() {
                CompoundNBT compoundNBT = new CompoundNBT();
                CompoundNBT stack = this.stack.serializeNBT();
                compoundNBT.put("stack", stack);
                compoundNBT.putInt("time", time);
                return compoundNBT;
            }

            @Override
            public void deserializeNBT(CompoundNBT nbt) {
                stack = ItemStack.read(nbt.getCompound("stack"));
                time = nbt.getInt("time");
            }
        }
    }
}
