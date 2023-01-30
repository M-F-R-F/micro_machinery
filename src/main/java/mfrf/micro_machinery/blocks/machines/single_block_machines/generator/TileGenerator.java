package mfrf.micro_machinery.blocks.machines.single_block_machines.generator;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.generator.GeneratorContainer;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslatableComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileGenerator extends MMTileBase implements  MenuProvider {
    private FluidTank tank = new FluidTank(2000, (fluidStack) -> fluidStack.getFluid() == Fluids.WATER);
    private ItemStackHandler fuel_handler = new ItemStackHandler(1);
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

    public TileGenerator() {
        super(RegisteredBlockEntityTypes.TILE_GENERATOR_TYPE.get());
    }

    public boolean isBurning() {
        return isBurning;
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void read(CompoundTag compound) {
        tank.readFromNBT(compound.getCompound("tank"));
        fuel_handler.deserializeNBT(compound.getCompound("fuel_slot"));
        energyContainer.deserializeNBT(compound.getCompound("energy_container"));
        burnTimeContainer.deserializeNBT(compound.getCompound("burn_time_container"));
        isBurning = compound.getBoolean("isburning");
        super.read(compound);
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        compound.put("tank", tank.writeToNBT(new CompoundTag()));
        compound.put("fuel_slot", fuel_handler.serializeNBT());
        compound.put("energy_container", energyContainer.serializeNBT());
        compound.put("burn_time_container", burnTimeContainer.serializeNBT());
        compound.putBoolean("isburning", isBurning);

        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && !isBackDirection(side)) {
            return LazyOptional.of(() -> tank).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !isBackDirection(side)) {
            return LazyOptional.of(() -> fuel_handler).cast();
        }
        if (cap == CapabilityEnergy.ENERGY && isBackDirection(side)) {
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

    @Override
    public void tick() {
        if (!world.isClientSide()) {
            if (isBurning) {
                burnTimeContainer.selfAdd();
                tank.drain(1, IFluidHandler.FluidAction.EXECUTE);
                if (!tank.isEmpty()) {
                    energyContainer.selfAdd();
                }
                markDirty2();
            } else {
                tryToGetFuel();
            }

            if (burnTimeContainer.atMaxValue()) {
                burnTimeContainer = new IntegerContainer(0, 0);
                isBurning = false;
                BlockGenerator.setIsburning(isBurning, world, pos);
                markDirty2();
            }

            tryToPushEnergy();

        }
    }

    private void tryToGetFuel() {
        if (!energyContainer.atMaxValue()) {
            ItemStack stackInSlot = fuel_handler.getStackInSlot(0);
            if (!stackInSlot.isEmpty() && !tank.isEmpty()) {
                int burnTime = ForgeHooks.getBurnTime(stackInSlot);
                if (burnTime != 0) {
                    stackInSlot.shrink(1);
                    fuel_handler.setStackInSlot(0, stackInSlot);
                    this.burnTimeContainer = new IntegerContainer(0, burnTime);
                    isBurning = true;
                    BlockGenerator.setIsburning(isBurning, world, pos);
                    markDirty2();
                }
            }
        }
    }

    private void tryToPushEnergy() {
        if (!world.isClientSide() && !energyContainer.atMinValue()) {
            Direction backDirection = getBackDirection();
            if (backDirection != null) {
                energyContainer = pushEnergyToDirection(backDirection, energyContainer);
            }
            markDirty2();
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslatableComponent("generator");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, Player player) {
        return new GeneratorContainer(sycID, inventory, this.pos, this.world);
    }

    public ItemStackHandler getFuelHandler() {
        return fuel_handler;
    }

    public static class GeneratorEnergyAndFuelIntArray implements IIntArray {

        private int[] iArray = {0, 0, 0, 0};

        @Override
        public int get(int index) {
            return iArray[index];
        }

        @Override
        public void set(int index, int value) {
            iArray[index] = value;
        }

        @Override
        public int size() {
            return 4;
        }
    }
}
