package mfrf.dbydd.micro_machinery.blocks.machines.conveyor_belt;

import com.google.common.collect.Lists;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.ConfigurableItemSlot;
import mfrf.dbydd.micro_machinery.utils.ItemContainer;
import mfrf.dbydd.micro_machinery.utils.TickRegularTimerPartialSerializeAbleFactory;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class TileConveyBelt extends MMTileBase implements ITickableTileEntity {
    static VoxelShape INSIDE_BOWL_SHAPE = Block.makeCuboidShape(2.0D, 11.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    static VoxelShape COLLECTION_AREA_SHAPE = INSIDE_BOWL_SHAPE;

    private List<TickRegularTimerPartialSerializeAbleFactory<TileConveyBelt>.TickRegularTimerPartialSerializeAble> timers = AttributeUtil.getTimers(this);
    private ConfigurableItemSlot slot = new ConfigurableItemSlot(AttributeUtil.getMaxStackItemCount(this));
    private ItemStack inTransfer = ItemStack.EMPTY;

    public TileConveyBelt() {
        super(RegisteredTileEntityTypes.TILE_CONVEY_BELT.get());
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == null) {
            return getCapability(cap);
        }
        switch (side) {
            case UP: {
                return LazyOptional.of(() -> new ItemContainer(slot, true, false)).cast();
            }
            case DOWN: {
                return LazyOptional.of(() -> new ItemContainer(slot, false, true)).cast();
            }
            default: {
                return LazyOptional.of(() -> new ItemContainer(slot, true, true)).cast();
            }
        }
    }

    @Override
    public void tick() {
        if (!slot.isEmpty()) {

            if (inTransfer.isEmpty()) {
                ConfigurableItemSlot.poppedStack poppedStack = slot.tryPopOneStack();
                inTransfer = poppedStack.stack.copy();
                poppedStack.consumed.accept(ItemStack.EMPTY);
                markDirty();
            }
        }

        timers.forEach(tickRegularTimerPartialSerializeAble -> tickRegularTimerPartialSerializeAble.tick(this));
    }


    static class AttributeUtil {
        private static HashMap<Block, List<TickRegularTimerPartialSerializeAbleFactory<TileConveyBelt>>> cache = new HashMap<>();
        private static HashMap<Block, Integer> cache2 = new HashMap<>();

        public static List<TickRegularTimerPartialSerializeAbleFactory<TileConveyBelt>.TickRegularTimerPartialSerializeAble> getTimers(TileConveyBelt identifier) {
            BlockConveyorBelt block = (BlockConveyorBelt) identifier.getBlockState().getBlock();
            if (!cache.containsKey(block)) {
                TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier = block.properties_speed_stack_interval_supplier;
                Integer speed = properties_speed_stack_interval_supplier.a.get();
                Integer stackItemCountToConvey = properties_speed_stack_interval_supplier.b.get();
                Integer extractItemInterval = properties_speed_stack_interval_supplier.c.get();
                cache.put(block, Lists.newArrayList(
                        new TickRegularTimerPartialSerializeAbleFactory<>(
                                //================================ convey item stack or eject them========================================
                                (tileConveyBelt) -> {
                                    if (!tileConveyBelt.inTransfer.isEmpty()) {
                                        ArgumentsCollection argumentsCollection = new ArgumentsCollection(tileConveyBelt);
                                        AtomicBoolean eject = new AtomicBoolean(true);


                                        switch (argumentsCollection.enumConveyorConnectState) {
                                            case UP: {
                                                argumentsCollection.targetPos = argumentsCollection.upTarget;
                                                eject.set(false);
                                                break;
                                            }
                                            case DOWN: {
                                                argumentsCollection.targetPos = argumentsCollection.targetPos.down();
                                                eject.set(false);
                                                break;
                                            }
                                            case CONNECTED: {
                                                eject.set(false);
                                            }
                                        }


                                        TileEntity targetEntity = tileConveyBelt.world.getTileEntity(argumentsCollection.targetPos);
                                        if (targetEntity != null) {
                                            targetEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, argumentsCollection.direction.getOpposite()).ifPresent(
                                                    iItemHandler -> {
                                                        tileConveyBelt.inTransfer = ItemHandlerHelper.insertItem(iItemHandler, tileConveyBelt.inTransfer, false);
                                                        eject.set(false);
                                                    }
                                            );
                                        }


                                        if (eject.get()) {
                                            InventoryHelper.spawnItemStack(tileConveyBelt.world, argumentsCollection.upTarget.getX(), argumentsCollection.upTarget.getY(), argumentsCollection.upTarget.getZ(), tileConveyBelt.inTransfer.copy());
                                            tileConveyBelt.inTransfer = ItemStack.EMPTY;
                                        }

                                        tileConveyBelt.markDirty();
                                    }
                                },
                                //======================================================
                                speed),
                        new TickRegularTimerPartialSerializeAbleFactory<>(
                                //================================extract item stack========================================
                                tileConveyBelt -> {
                                    if (!tileConveyBelt.slot.atLimit()) {
                                        ArgumentsCollection argumentsCollection = new ArgumentsCollection(tileConveyBelt);
                                        TileEntity fromEntity = tileConveyBelt.world.getTileEntity(argumentsCollection.fromPos);
                                        if (fromEntity != null && fromEntity.getType() != RegisteredTileEntityTypes.TILE_CONVEY_BELT.get()) {
                                            fromEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, argumentsCollection.direction.getOpposite())
                                                    .ifPresent(iItemHandler -> {
                                                        for (int i = 0; i < iItemHandler.getSlots(); i++) {
                                                            ItemStack stackInSlot = iItemHandler.getStackInSlot(i);
                                                            if (!stackInSlot.isEmpty()) {
                                                                ItemStack copy = stackInSlot.copy();
                                                                int extracted = Math.min(stackInSlot.getCount(), stackItemCountToConvey);
                                                                copy.setCount(extracted);
                                                                stackInSlot.shrink(extracted);

                                                                ItemHandlerHelper.insertItem(tileConveyBelt.slot, copy, false);
                                                                break;
                                                            }
                                                        }

                                                    });
                                        }
                                        tileConveyBelt.markDirty();
                                    }
                                },
                                //======================================================
                                extractItemInterval)
                ));
            }
            return cache.get(block).stream().map(TickRegularTimerPartialSerializeAbleFactory::build).collect(Collectors.toList());
        }

        public static int getMaxStackItemCount(TileConveyBelt identifier) {
            BlockConveyorBelt block = (BlockConveyorBelt) identifier.getBlockState().getBlock();
            if (!cache2.containsKey(block)) {
                cache2.put(block, block.properties_speed_stack_interval_supplier.b.get());
            }
            return cache2.get(block);
        }

        static class ArgumentsCollection {
            public BlockState blockState;
            public EnumConveyorConnectState enumConveyorConnectState;
            public Direction direction;
            public BlockPos targetPos;
            public BlockPos fromPos;
            public BlockPos upTarget;

            public ArgumentsCollection(TileConveyBelt tileConveyBelt) {
                blockState = tileConveyBelt.getBlockState();
                enumConveyorConnectState = blockState.get(BlockConveyorBelt.LIFT);
                direction = blockState.get(BlockConveyorBelt.CONVEYOR_HORIZONTAL_DIRECTION_STATE);
                targetPos = tileConveyBelt.pos.offset(direction);
                fromPos = tileConveyBelt.pos.offset(direction.getOpposite());
                upTarget = targetPos.up();
            }

        }

    }
}