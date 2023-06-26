package org.mfrf.micro_machienry.utils;

import net.minecraftforge.energy.IEnergyStorage;

public abstract class FEContainer extends IntegerContainer implements IEnergyStorage {

    public FEContainer(int min, int max) {
        super(min, max);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (canReceive()) {
            int add = add(maxReceive, simulate);
            return add;
        } else return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (canExtract()) {
            int minus = minus(maxExtract, simulate);
            return minus;
        } else return 0;
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
