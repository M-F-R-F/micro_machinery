package mfrf.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.ItemContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag 
import net.minecraft.nbt.ListTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileConveyBelt extends MMTileBase implements ITickableBlockEntity {
    public StackArray array;
    private int interval = 0;

    public TileConveyBelt() {
        super(RegisteredBlockEntityTypes.TILE_CONVEY_BELT.get());
        array = new StackArray(() -> ((BlockConveyorBelt) getBlockState().getBlock()).properties_speed_stack_interval_supplier.b.get(), () -> ((BlockConveyorBelt) getBlockState().getBlock()).properties_speed_stack_interval_supplier.a.get());
    }

    @Override
    public void tick() {
        //todo remake
        if (!world.isClientSide()) {
            List<StackArray.CallbackSlot> popped = array.popAll();

            Direction out = getBlockState().get(BlockConveyorBelt.FACING);
            EnumConveyorConnectState out_state = getBlockState().get(BlockConveyorBelt.OUT_STATE);
            if (!popped.isEmpty()) {
                boolean toConveyorBeltOnly = false;
                BlockPos out_pos = getPos().m_142300_(out);
                BlockEntity downT = world.getBlockEntity(out_pos.down());
                BlockEntity upT = world.getBlockEntity(out_pos.up());
                BlockState downS = null;
                BlockState upS = null;
                if (downT instanceof TileConveyBelt) {
                    downS = downT.getBlockState();
                }
                if (upT instanceof TileConveyBelt) {
                    upS = upT.getBlockState();
                }

                if (out_state == EnumConveyorConnectState.DOWN) {
                    out_pos = out_pos.down();
                    if (downS != null && downS.get(BlockConveyorBelt.FACING) == out && downS.get(BlockConveyorBelt.BACK_STATE) && downS.get(BlockConveyorBelt.OUT_STATE) == EnumConveyorConnectState.UP) {
                        toConveyorBeltOnly = true;
                    }
                } else if (out_state == EnumConveyorConnectState.UP) {
                    out_pos = out_pos.up();
                    if (upS != null && upS.get(BlockConveyorBelt.FACING) == out && upS.get(BlockConveyorBelt.BACK_STATE) && upS.get(BlockConveyorBelt.OUT_STATE) == EnumConveyorConnectState.UP) {
                        toConveyorBeltOnly = true;
                    }
                }
                BlockEntity tileEntity = world.getBlockEntity(out_pos);

                if (toConveyorBeltOnly) {
                    TileConveyBelt conveyBelt = (TileConveyBelt) tileEntity;
                    if (conveyBelt.array.notFull()) {
                        conveyBelt.array.receive(popped);
                        conveyBelt.setChanged();
                    }
                } else {
                    tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, out.getOpposite()).ifPresent(iItemHandler -> {
                        for (StackArray.CallbackSlot callbackSlot : popped) {

                            ItemStack outS = ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.func(), false);
                            if (!outS.isEmpty()) {
                                ItemEntity itemEntity = new ItemEntity(world, this.pos.getX(), this.pos.getY() + 0.6, this.pos.getZ(), outS);
                                itemEntity.setDefaultPickupDelay();
                                world.addEntity(itemEntity);
                            }

                        }
                    });
                }
            }
            array.tick();
            setChanged();
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        BlockState blockState = getBlockState();
        Direction direction = blockState.get(BlockConveyorBelt.FACING);
        if (side == Direction.UP || side == Direction.DOWN) {
            return LazyOptional.of(() -> array).cast();
        } else if (side == direction) {
            return LazyOptional.of(() -> new ItemContainer(array, false, true)).cast();
        }

        if (direction.getOpposite() == side) {
            return LazyOptional.of(() -> new ItemContainer(array, blockState.get(BlockConveyorBelt.BACK_STATE), false)).cast();
        }
        if (direction.rotateY() == side) {
            return LazyOptional.of(() -> new ItemContainer(array, blockState.get(BlockConveyorBelt.RIGHT_STATE), false)).cast();
        }
        if (direction.rotateYCCW() == side) {
            return LazyOptional.of(() -> new ItemContainer(array, blockState.get(BlockConveyorBelt.LEFT_STATE), false)).cast();
        }
        return LazyOptional.empty();
    }


    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
//        array.deserializeNBT(compound.getCompound("array"));
        interval = compound.getInt("interval");
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag write = super.write(compound);
//        write.put("array", array.serializeNBT());
        write.putInt("interval", interval);
        return write;
    }

    public static class StackArray implements INBTSerializable<CompoundTag>, IItemHandler {
        private LinkedList<Stack> stacks = new LinkedList<>();
        private Supplier<Integer> max = () -> 0;
        private Supplier<Integer> time;

        public StackArray(Supplier<Integer> max, Supplier<Integer> timeToTransfer) {
            this.max = max;
            this.time = timeToTransfer;
        }

        public StackArray() {

        }

        public boolean notFull() {
            return stacks.size() < max.get();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag compoundNBT = new CompoundTag();
            compoundNBT.putInt("max", max.get());

            ListTag listNBT = new ListTag();
            stacks.forEach(s -> listNBT.add(s.serializeNBT()));
            compoundNBT.put("stacks", listNBT);
            return compoundNBT;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            int maxI = nbt.getInt("max");
            max = () -> maxI;
            for (Tag inbt : nbt.getList("stacks", Tag.TAG_COMPOUND)) {
                Stack stack = new Stack();
                stack.deserializeNBT((CompoundTag) inbt);
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
            return Math.max(max.get(), stacks.size());
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return stacks.get(slot).stack;
        }

        public boolean in(ItemStack stack) {
            return stacks.add(new Stack(stack.copy(), time.get()));
        }

        public void receive(List<CallbackSlot> in) {
            int space = max.get() - stacks.size();
            for (int i = 0; i < Math.min(space, in.size()); i++) {
                in(in.get(i).func());
            }

        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (stacks.size() >= max.get() || slot >= max.get())
                return stack;
            Stack stackSaved;

            if (stacks.get(slot) != null) {
                stackSaved = stacks.get(slot);
            } else {
                stackSaved = new Stack(stack.copy(), time.get());
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

        public class Stack implements INBTSerializable<CompoundTag> {
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
            public CompoundTag serializeNBT() {
                CompoundTag compoundNBT = new CompoundTag();
                CompoundTag stack = this.stack.serializeNBT();
                compoundNBT.put("stack", stack);
                compoundNBT.putInt("time", time);
                return compoundNBT;
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                stack = ItemStack.of(nbt.getCompound("stack"));
                time = nbt.getInt("time");
            }
        }
    }
}
