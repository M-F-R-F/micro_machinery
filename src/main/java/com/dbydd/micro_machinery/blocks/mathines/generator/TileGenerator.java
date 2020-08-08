package com.dbydd.micro_machinery.blocks.mathines.generator;

import com.dbydd.micro_machinery.blocks.mathines.MMTileBase;
import com.dbydd.micro_machinery.registery_lists.Registered_Tileentitie_Types;
import com.dbydd.micro_machinery.utils.FEContainer;
import com.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileGenerator extends MMTileBase implements ITickableTileEntity {
    private FluidTank tank = new FluidTank(2000, (fluidStack) -> fluidStack.getFluid() == Fluids.WATER);
    private ItemStackHandler fuel_handler = new ItemStackHandler(1);
    private FEContainer energyContainer = new FEContainer(0, 80000) {
        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }

        @Override
        public int self_add() {
            return add(200, false);
        }
    };
    private IntegerContainer burnTimeContainer = new IntegerContainer(0, 0);
    private boolean isBurning = false;

    public TileGenerator() {
        super(Registered_Tileentitie_Types.TILE_GENERATOR_TYPE.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        tank.readFromNBT(compound.getCompound("tank"));
        fuel_handler.deserializeNBT(compound.getCompound("fuel_slot"));
        energyContainer.deserializeNBT(compound.getCompound("energy_container"));
        burnTimeContainer.deserializeNBT(compound.getCompound("burn_time_container"));
        isBurning = compound.getBoolean("isburning");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
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

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (isBurning) {
                burnTimeContainer.self_add();
                if (!tank.isEmpty()) {
                    energyContainer.self_add();
                    markDirty2();
                }
            } else {
                tryToGetFuel();
            }

            if(burnTimeContainer.atMaxValue()){
                burnTimeContainer = new IntegerContainer(0,0);
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
            if (!stackInSlot.isEmpty()) {
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

    private void tryToPushEnergy(){
        if(!world.isRemote() && !energyContainer.atMinValue()) {
            Direction backDirection = getBackDirection();
            if (backDirection != null) {
                energyContainer = pushEnergyToDirection(backDirection, energyContainer);
            }
            markDirty2();
        }
    }
}
