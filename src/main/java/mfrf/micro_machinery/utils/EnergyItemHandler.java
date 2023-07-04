package mfrf.micro_machinery.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class EnergyItemHandler implements ICapabilityProvider, IEnergyStorage {

    private final ItemStack stack;
    private final FEContainer dirtyContainer;

    public EnergyItemHandler(ItemStack stack, FEContainer container) {
        this.stack = stack;
        this.dirtyContainer = container;
        if (!stack.getOrCreateTag().contains("fe_container")) {
            stack.getOrCreateTag().put("fe_container", container.serializeNBT());
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) return LazyOptional.of(() -> this).cast();
        return LazyOptional.empty();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        AtomicInteger ret = new AtomicInteger();
        getContainer().accept(container -> ret.set(container.receiveEnergy(maxReceive, simulate)));
        return ret.get();
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        AtomicInteger ret = new AtomicInteger();
        getContainer().accept(container -> ret.set(container.extractEnergy(maxExtract, simulate)));
        return ret.get();
    }

    @Override
    public int getEnergyStored() {
        AtomicInteger atomicInteger = new AtomicInteger();
        getContainer().accept(container -> atomicInteger.set(container.getEnergyStored()));
        return atomicInteger.get();
    }

    @Override
    public int getMaxEnergyStored() {
        AtomicInteger atomicInteger = new AtomicInteger();
        getContainer().accept(container -> atomicInteger.set(container.getMaxEnergyStored()));
        return atomicInteger.get();
    }

    @Override
    public boolean canExtract() {
        AtomicBoolean ret = new AtomicBoolean(false);
        getContainer().accept(container -> ret.set(container.canExtract()));
        return ret.get();
    }

    @Override
    public boolean canReceive() {
        AtomicBoolean ret = new AtomicBoolean(false);
        getContainer().accept(container -> ret.set(container.canReceive()));
        return ret.get();
    }

    public Consumer<Consumer<FEContainer>> getContainer() {
        dirtyContainer.deserializeNBT(stack.getOrCreateTag().getCompound("fe_container"));
        return feContainerConsumer -> {
            feContainerConsumer.accept(dirtyContainer);
            stack.getOrCreateTag().put("fe_container", dirtyContainer.serializeNBT());
        };
    }

}
