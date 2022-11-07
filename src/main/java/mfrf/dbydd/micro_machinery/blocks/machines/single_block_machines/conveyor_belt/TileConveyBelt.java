package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.ItemContainer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TileConveyBelt extends MMTileBase implements ITickableTileEntity {
    public AttributeUtil.StackArray array;
    private int lastPopDirection = 0;

    public TileConveyBelt() {
        super(RegisteredTileEntityTypes.TILE_CONVEY_BELT.get());
        BlockConveyorBelt conveyorBelt = (BlockConveyorBelt) getBlockState().getBlock();
        array = new AttributeUtil.StackArray(conveyorBelt.properties_speed_stack_interval_supplier.b.get(), conveyorBelt.properties_speed_stack_interval_supplier.a.get());
    }

    @Override
    public void tick() {
        Stream<Direction> stream = Arrays.stream(Direction.values());
        List<Direction> in = stream.filter(d -> EnumConveyorConnectState.isIn(getBlockState().get(BlockConveyorBelt.getStateProperty(d)))).collect(Collectors.toList());
        List<Direction> out = stream.filter(d -> EnumConveyorConnectState.isOut(getBlockState().get(BlockConveyorBelt.getStateProperty(d)))).collect(Collectors.toList());

        //eject
        if (array.tick() && !out.isEmpty()) {

            AttributeUtil.StackArray.CallbackSlot callbackSlot = array.popOne();

            AtomicBoolean succeed = new AtomicBoolean(false);
            for (int i = 1; i <= ((BlockConveyorBelt) getBlockState().getBlock()).properties_speed_stack_interval_supplier.c.get(); i++) {
                boolean flag = true;

                Direction direction = out.get((i + lastPopDirection) % out.size());
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));

                LazyOptional<IItemHandler> capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite());
                if (!succeed.get() || capability.isPresent()) {
                    capability.ifPresent(iItemHandler -> {
                        behavior(callbackSlot, succeed, iItemHandler);
                    });
                } else {
                    direction = Direction.byHorizontalIndex(lastPopDirection);
                    tileEntity = world.getTileEntity(pos.offset(direction));
                    //todo maybe fine?
                    tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite()).ifPresent(iItemHandler ->
                            behavior(callbackSlot, succeed, iItemHandler)
                    );
                    flag = false;
                }
                if (succeed.get() && flag) {
                    lastPopDirection += i;
                    break;
                }
            }
        }

        if (array.notFull()) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                EnumConveyorConnectState connectState = getBlockState().get(BlockConveyorBelt.getStateProperty(direction));
                if (EnumConveyorConnectState.isIn(connectState)) {

                }
            }
        }
    }

    public LinkedList<AttributeUtil.StackArray.Stack> getStacks() {
        return array.stacks;
    }

    private void behavior(AttributeUtil.StackArray.CallbackSlot callbackSlot, AtomicBoolean succeed, IItemHandler iItemHandler) {
        ItemStack condition = ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.held, true);
        if (condition.isEmpty()) {
            ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.func(), false);
            succeed.set(true);
        } else if (condition.getCount() != callbackSlot.held.getCount()) {
            ItemStack out = ItemHandlerHelper.insertItem(iItemHandler, callbackSlot.held, false);
            new ItemEntity(world, this.pos.getX(), this.pos.getY() + 0.6, this.pos.getZ(), out);
            succeed.set(true);
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (Direction.Plane.HORIZONTAL.test(side)) {
            return LazyOptional.of(() -> determine(getBlockState().get(BlockConveyorBelt.getStateProperty(side)))).cast();
        } else {
            return LazyOptional.of(() -> array).cast();
        }
    }

    ItemContainer determine(EnumConveyorConnectState state) {
        if (state == EnumConveyorConnectState.IN || state == EnumConveyorConnectState.UP_IN) {
            return new ItemContainer(array, true, false);
        }
        if (state == EnumConveyorConnectState.OUT || state == EnumConveyorConnectState.UP_OUT) {
            return new ItemContainer(array, false, true);
        }
        return new ItemContainer(array, false, false);
    }


    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        array.deserializeNBT(compound.getCompound("array"));
        lastPopDirection = compound.getInt("last");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("array", array.serializeNBT());
        write.putInt("last", lastPopDirection);
        return write;
    }

    static class AttributeUtil {

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

            public CallbackSlot popOne() {
                Optional<Stack> first = stacks.stream().filter(Stack::popAble).findAny();
                AtomicReference<ItemStack> ret = new AtomicReference<>(ItemStack.EMPTY);
                first.ifPresent(stack -> {
                    ret.set(stack.stack);
                });

                return new CallbackSlot(ret.get());
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

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stacks.size() >= max || slot >= max)
                    return stack;
                Stack stackSaved;

                if (stacks.get(slot) != null) {
                    stackSaved = stacks.get(slot);
                } else {
                    stackSaved = new Stack(time);
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
}
