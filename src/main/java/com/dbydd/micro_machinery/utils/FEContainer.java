package com.dbydd.micro_machinery.utils;

import net.minecraftforge.energy.IEnergyStorage;

public abstract class FEContainer extends IntegerContainer implements IEnergyStorage {

    public FEContainer(int min, int max) {
        super(min, max);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return add(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return minus(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return getCurrent();
    }

    @Override
    public int getMaxEnergyStored() {
        return getMax();
    }

    @Override
    public abstract boolean canExtract();

    @Override
    public abstract boolean canReceive();
}
