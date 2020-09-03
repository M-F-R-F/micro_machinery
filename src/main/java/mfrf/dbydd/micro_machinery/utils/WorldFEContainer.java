package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.IEnergyStorage;

import java.math.BigInteger;

public class WorldFEContainer implements IEnergyStorage {
    private BigInteger min;
    private BigInteger max;
    private BigInteger current;

    public WorldFEContainer(BigInteger min, BigInteger max) {
        this.min = min;
        this.max = max;
        this.current = BigInteger.ZERO;
    }

    public WorldFEContainer(BigInteger min, BigInteger max, BigInteger current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public static WorldFEContainer deserializeNBT(CompoundNBT nbt) {
        return new WorldFEContainer(new BigInteger(nbt.getByteArray("min")), new BigInteger(nbt.getByteArray("max")), new BigInteger(nbt.getByteArray("current")));
    }

    public BigInteger getMin() {
        return min;
    }

    public void setMin(BigInteger min) {
        this.min = min;
    }

    public BigInteger getMax() {
        return max;
    }

    public void setMax(BigInteger max) {
        this.max = max;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putByteArray("min", min.toByteArray());
        compoundNBT.putByteArray("max", max.toByteArray());
        compoundNBT.putByteArray("current", current.toByteArray());
        return compoundNBT;
    }

    public BigInteger add(BigInteger value, boolean simulate) {
        BigInteger i = current.add(value);
        if (!simulate) {
            if (max.compareTo(i) < 0) {
                BigInteger r = max.subtract(current);
                current = max;
                return r;
            } else {
                current = current.add(value);
                return BigInteger.ZERO;
            }
        } else {
            if (max.compareTo(i) < 0) {
                return max.subtract(current);
            } else {
                return BigInteger.ZERO;
            }
        }
    }

    public BigInteger minus(BigInteger value, boolean simulate) {
        BigInteger i = current.subtract(value);
        if (simulate) {
            if (min.compareTo(i) > 0) {
                BigInteger r = current.subtract(min);
                current = min;
                return r;
            } else {
                current = current.subtract(value);
                return value;
            }
        } else {
            if (min.compareTo(i) > 0) {
                return current.subtract(min);
            } else {
                return value;
            }
        }
    }

    public boolean atMaxValue() {
        return current.compareTo(max) == 0;
    }

    public boolean atMinValue() {
        return current.compareTo(min) == 0;
    }

    public BigInteger getCurrent() {
        return current;
    }

    public void setCurrent(BigInteger current) {
        this.current = current;
    }

    public void resetValue() {
        this.current = min;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return add(new BigInteger(String.valueOf(maxReceive)), simulate).intValue();
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return minus(new BigInteger(String.valueOf(maxExtract)), simulate).intValue();
    }

    @Override
    public int getEnergyStored() {
        return getCurrent().intValue();
    }

    @Override
    public int getMaxEnergyStored() {
        return getMax().intValue();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
