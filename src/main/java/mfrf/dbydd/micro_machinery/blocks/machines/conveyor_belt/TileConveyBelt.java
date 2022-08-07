package mfrf.dbydd.micro_machinery.blocks.machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.ConfigurableItemSlot;
import mfrf.dbydd.micro_machinery.utils.ItemContainer;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileConveyBelt extends TileEntity implements ITickableTileEntity {
    private ConfigurableItemSlot slot = new ConfigurableItemSlot();

    public TileConveyBelt(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
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
            BlockState blockState = getBlockState();
            EnumConveyorConnectState enumConveyorConnectState = blockState.get(BlockConveyorBelt.CONNECT_STATE);
            Direction direction = blockState.get(BlockConveyorBelt.CONVEYOR_HORIZONTAL_DIRECTION_STATE);
            BlockPos targetPos = pos.offset(direction);
            BlockPos fromPos = pos.offset(direction.getOpposite());
            AtomicBoolean eject = new AtomicBoolean(true);
            switch (enumConveyorConnectState) {
                case VERTICAL_CONNECTED_UP: {
                    targetPos = targetPos.up();
                    eject.set(false);
                    break;
                }
                case VERTICAL_CONNECTED_DOWN: {
                    targetPos = targetPos.down();
                    eject.set(false);
                    break;
                }
                case CONNECTED: {
                    eject.set(false);
                }
            }

            TileEntity targetEntity = world.getTileEntity(targetPos);
            if (targetEntity != null) {
                targetEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite()).ifPresent(
                        iItemHandler -> {
                            ConfigurableItemSlot.poppedStack poppedStack = slot.tryPopOneStack();
                            if (poppedStack != null) {
                                poppedStack.consumed.accept(ItemHandlerHelper.insertItem(iItemHandler, poppedStack.stack.copy(), false));
                                eject.set(false);
                                markDirty();
                                //todo input, eject,checkBug
                            }
                        }
                );
            }
        }
    }
}
