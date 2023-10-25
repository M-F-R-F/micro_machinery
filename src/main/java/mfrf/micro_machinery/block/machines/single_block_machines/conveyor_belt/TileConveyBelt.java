package mfrf.micro_machinery.block.machines.single_block_machines.conveyor_belt;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileConveyBelt extends MMTileBase {
    public StackArray array;
    private int interval = 0;

    public TileConveyBelt(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_CONVEY_BELT.get(), pos, state);
        array = new StackArray(() -> ((BlockConveyorBeltBase) getBlockState().getBlock()).properties_speed_stack_interval_supplier.b.get(), () -> ((BlockConveyorBeltBase) getBlockState().getBlock()).properties_speed_stack_interval_supplier.a.get());
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//        BlockState blockState = getBlockState();
//        Direction direction = blockState.getValue(BlockConveyorBeltBase.FACING);
//        if (side == Direction.UP || side == Direction.DOWN) {
//            return LazyOptional.of(() -> array).cast();
//        } else if (side == direction) {
//            return LazyOptional.of(() -> new ItemContainer(array, false, true)).cast();
//        }
//
//        if (direction.getOpposite() == side) {
//            return LazyOptional.of(() -> new ItemContainer(array, blockState.getValue(BlockConveyorBeltBase.BACK_STATE), false)).cast();
//        }
//        if (direction.getClockWise() == side) {
//            return LazyOptional.of(() -> new ItemContainer(array, blockState.getValue(BlockConveyorBeltBase.RIGHT_STATE), false)).cast();
//        }
//        if (direction.getCounterClockWise() == side) {
//            return LazyOptional.of(() -> new ItemContainer(array, blockState.getValue(BlockConveyorBeltBase.LEFT_STATE), false)).cast();
//        }
        return LazyOptional.empty();
    }


    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
//        array.deserializeNBT(compound.getCompound("array"));
        interval = compound.getInt("interval");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
//        pTag.put("array", array.serializeNBT());
        pTag.putInt("interval", interval);
    }

    public static class StackArray implements INBTSerializable<CompoundTag>, IItemHandler {
        private final LinkedList<Stack> stacks = new LinkedList<>();
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

        public Optional<CallbackSlot> popOnce() {
            return stacks.stream().filter(Stack::popAble).map(Stack::getStack).map(CallbackSlot::new).findAny();
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

            if (!stackSaved.stack.is(stack.getItem()) || count >= maxStackSize) {
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

    public static void straightTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING);
            BlockPos out_pos = pos.relative(out);
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }

    public static void upTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING);
            BlockPos out_pos = pos.relative(out).above();
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }

    public static void downTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING);
            BlockPos out_pos = pos.relative(out).below();
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }

    public static void leftTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING).getCounterClockWise();
            BlockPos out_pos = pos.relative(out);
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }

    public static void rightTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING).getClockWise();
            BlockPos out_pos = pos.relative(out);
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }


    public static void mergeTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {
            List<StackArray.CallbackSlot> popped = conveyBelt.array.popAll();

            Direction out = state.getValue(BlockConveyorBeltBase.FACING).getOpposite();
            BlockPos out_pos = pos.relative(out);
            BlockEntity tileEntity = world.getBlockEntity(out_pos);

            tryOutPut(world, pos, popped, tileEntity, out);
            commonTick(conveyBelt);
        }
    }


    public static void splitTick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileConveyBelt conveyBelt) {

            Direction out = state.getValue(BlockConveyorBeltBase.FACING);
            Direction r = out.getClockWise();
            Direction l = out.getCounterClockWise();
            BlockEntity tileEntity_l = world.getBlockEntity(pos.relative(l));
            BlockEntity tileEntity_r = world.getBlockEntity(pos.relative(r));

            Optional<StackArray.CallbackSlot> popped = conveyBelt.array.popOnce();
            boolean left_or_right = true;
            while (popped.isPresent()) {
                if (left_or_right) {
                    tryOutPut(world, pos, Collections.singletonList(popped.get()), tileEntity_l, l);
                } else {
                    tryOutPut(world, pos, Collections.singletonList(popped.get()), tileEntity_r, r);
                }
                popped = conveyBelt.array.popOnce();
                left_or_right = !left_or_right;
            }
            commonTick(conveyBelt);
        }
    }

    protected static void commonTick(TileConveyBelt conveyBelt) {
        conveyBelt.array.tick();
        conveyBelt.setChanged();
    }

    protected static void tryOutPut(Level world, BlockPos pos, List<StackArray.CallbackSlot> popped, BlockEntity tileEntity, Direction out) {
        if (!popped.isEmpty()) {

            if (tileEntity instanceof TileConveyBelt conveyBelt2) {
                if (conveyBelt2.array.notFull()) {
                    conveyBelt2.array.receive(popped);
                    conveyBelt2.setChanged();
                }
            } else {
                tileEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, out.getOpposite()).ifPresent(iItemHandler -> {
                    for (StackArray.CallbackSlot callbackSlot : popped) {

                        ItemStack outS = ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.func(), false);
                        if (!outS.isEmpty()) {
                            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY() + 0.6, pos.getZ(), outS);
                            itemEntity.setDefaultPickUpDelay();
                            world.addFreshEntity(itemEntity);
                        }

                    }
                });
            }
        }
    }

}
