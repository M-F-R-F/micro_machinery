package mfrf.micro_machinery.block.machines.single_block_machines.generator;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.gui.generator.GeneratorContainer;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.text.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileGenerator extends MMTileBase implements MenuProvider {
    private final FluidTank tank = new FluidTank(2000, (fluidStack) -> fluidStack.getFluid() == Fluids.WATER);
    private final ItemStackHandler fuel_handler = new ItemStackHandler(1);
    private FEContainer energyContainer = new FEContainer(0, 40000) {
        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }

        @Override
        public int selfAdd() {
            int add = add(400, false);
            markDirty2();
            return add;
        }
    };
    private IntegerContainer burnTimeContainer = new IntegerContainer(0, 0);
    private boolean isBurning = false;
//    private GeneratorEnergyAndFuelIntArray array = new GeneratorEnergyAndFuelIntArray();

    public TileGenerator(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_GENERATOR_TYPE.get(), pos, state);
    }

    public boolean isBurning() {
        return isBurning;
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void load(CompoundTag compound) {
        tank.readFromNBT(compound.getCompound("tank"));
        fuel_handler.deserializeNBT(compound.getCompound("fuel_slot"));
        energyContainer.deserializeNBT(compound.getCompound("energy_container"));
        burnTimeContainer.deserializeNBT(compound.getCompound("burn_time_container"));
        isBurning = compound.getBoolean("isburning");
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("tank", tank.writeToNBT(new CompoundTag()));
        pTag.put("fuel_slot", fuel_handler.serializeNBT());
        pTag.put("energy_container", energyContainer.serializeNBT());
        pTag.put("burn_time_container", burnTimeContainer.serializeNBT());
        pTag.putBoolean("isburning", isBurning);

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER && !isBackDirection(side)) {
            return LazyOptional.of(() -> tank).cast();
        }
        if (cap == ForgeCapabilities.ITEM_HANDLER && !isBackDirection(side)) {
            return LazyOptional.of(() -> fuel_handler).cast();
        }
        if (cap == ForgeCapabilities.ENERGY && isBackDirection(side)) {
            return LazyOptional.of(() -> energyContainer).cast();
        }

        return super.getCapability(cap, side);
    }

    public FEContainer getEnergyContainer() {
        return energyContainer;
    }

    public IntegerContainer getBurnTimeContainer() {
        return burnTimeContainer;
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileGenerator generator) {
            if (generator.isBurning) {
                generator.burnTimeContainer.selfAdd();
                generator.tank.drain(1, IFluidHandler.FluidAction.EXECUTE);
                if (!generator.tank.isEmpty()) {
                    generator.energyContainer.selfAdd();
                }
                generator.markDirty2();
            } else {
                generator.tryToGetFuel();
            }

            if (generator.burnTimeContainer.atMaxValue()) {
                generator.burnTimeContainer = new IntegerContainer(0, 0);
                generator.isBurning = false;
                BlockGenerator.setIsGenerating(false, world, pos);
                generator.markDirty2();
            }

            generator.tryToPushEnergy();

        }
    }

    private void tryToGetFuel() {
        if (!energyContainer.atMaxValue()) {
            ItemStack stackInSlot = fuel_handler.getStackInSlot(0);
            if (!stackInSlot.isEmpty() && !tank.isEmpty()) {
                int burnTime = ForgeHooks.getBurnTime(stackInSlot, RecipeType.SMELTING);
                if (burnTime != 0) {
                    stackInSlot.shrink(1);
                    fuel_handler.setStackInSlot(0, stackInSlot);
                    this.burnTimeContainer = new IntegerContainer(0, burnTime);
                    isBurning = true;
                    BlockGenerator.setIsGenerating(isBurning, level, getBlockPos());
                    markDirty2();
                }
            }
        }
    }

    private void tryToPushEnergy() {
        if (!level.isClientSide() && !energyContainer.atMinValue()) {
            Direction backDirection = getBackDirection();
            if (backDirection != null) {
                energyContainer = pushEnergyToDirection(backDirection, energyContainer);
            }
            markDirty2();
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int sycID, Inventory inventory, Player player) {
        return new GeneratorContainer(sycID, inventory, this.getBlockPos(), this.level);
    }

    public ItemStackHandler getFuelHandler() {
        return fuel_handler;
    }
//
//    public static class GeneratorEnergyAndFuelIntArray implements IIntArray {
//
//        private final int[] iArray = {0, 0, 0, 0};
//
//        @Override
//        public int get(int index) {
//            return iArray[index];
//        }
//
//        @Override
//        public void set(int index, int value) {
//            iArray[index] = value;
//        }
//
//        @Override
//        public int size() {
//            return 4;
//        }
//    }
}
