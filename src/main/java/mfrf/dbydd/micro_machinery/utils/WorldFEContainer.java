package mfrf.dbydd.micro_machinery.utils;

import com.google.common.primitives.UnsignedLong;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.IEnergyStorage;

public class WorldFEContainer implements IEnergyStorage {
    private UnsignedLong min;
    private UnsignedLong max;
    private UnsignedLong current;

    public WorldFEContainer(UnsignedLong min, UnsignedLong max) {
        this.min = min;
        this.max = max;
        this.current = UnsignedLong.ZERO;
    }

    public WorldFEContainer(UnsignedLong min, UnsignedLong max, UnsignedLong current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public static WorldFEContainer deserializeNBT(CompoundNBT nbt) {
        return new WorldFEContainer(UnsignedLong.fromLongBits(nbt.getLong("min")),UnsignedLong.fromLongBits(nbt.getLong("max")), UnsignedLong.fromLongBits(nbt.getLong("current")));
    }

    public UnsignedLong getMin() {
        return min;
    }

    public void setMin(UnsignedLong min) {
        this.min = min;
    }

    public UnsignedLong getMax() {
        return max;
    }

    public void setMax(UnsignedLong max) {
        this.max = max;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("min", min.toString());
        compoundNBT.putString("max", max.toString());
        compoundNBT.putString("current", current.toString());
        return compoundNBT;
    }

    public UnsignedLong add(UnsignedLong value, boolean simulate) {
        UnsignedLong i = current.plus(value);
        if (!simulate) {
            if (max.compareTo(i) < 0) {
                UnsignedLong r = max.minus(current);
                current = max;
                return r;
            } else {
                current = current.plus(value);
                return UnsignedLong.ZERO;
            }
        } else {
            if (max.compareTo(i) < 0) {
                return max.minus(current);
            } else {
                return UnsignedLong.ZERO;
            }
        }
    }

    public UnsignedLong minus(UnsignedLong value, boolean simulate) {
        UnsignedLong i = current.minus(value);
        if (simulate) {
            if (min.compareTo(i) > 0) {
                UnsignedLong r = current.minus(min);
                current = min;
                return r;
            } else {
                current = current.minus(value);
                return value;
            }
        } else {
            if (min.compareTo(i) > 0) {
                return current.minus(min);
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

    public UnsignedLong getCurrent() {
        return current;
    }

    public void setCurrent(UnsignedLong current) {
        this.current = current;
    }

    public void resetValue() {
        this.current = min;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return add(UnsignedLong.valueOf(maxReceive), simulate).intValue();
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return minus(UnsignedLong.valueOf(maxExtract), simulate).intValue();
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
