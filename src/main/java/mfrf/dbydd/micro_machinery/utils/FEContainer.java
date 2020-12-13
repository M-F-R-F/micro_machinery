package mfrf.dbydd.micro_machinery.utils;

import net.minecraftforge.energy.IEnergyStorage;

public abstract class FEContainer extends IntegerContainer implements IEnergyStorage {

    public FEContainer(int min, int max) {
        super(min, max);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (canReceive()) {
            return add(maxReceive, simulate);
        } else return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (canExtract()) {
            return minus(maxExtract, simulate);
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
