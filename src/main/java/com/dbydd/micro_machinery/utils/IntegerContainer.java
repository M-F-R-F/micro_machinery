package com.dbydd.micro_machinery.utils;

import net.minecraft.nbt.CompoundNBT;

public class IntegerContainer {
    private int min;
    private int max;
    private int current;

    public IntegerContainer(int min, int max) {
        this.min = min;
        this.max = max;
        this.current = 0;
    }

    public IntegerContainer(int min, int max, int current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("min", min);
        compoundNBT.putInt("max", max);
        compoundNBT.putInt("current", current);
        return compoundNBT;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.current = nbt.getInt("current");
        this.max = nbt.getInt("max");
        this.min = nbt.getInt("min");
    }

    public int add(int value, boolean simulate) {
        int i = current + value;
        if (!simulate) {
            if (i > max) {
                int r = max - current;
                current = max;
                return r;
            } else {
                current += value;
                return 0;
            }
        } else {
            if (i > max) {
                return max - current;
            } else {
                return 0;
            }
        }
    }

    public int minus(int value, boolean simulate) {
        int i = current - value;
        if (simulate) {
            if (i < min) {
                int r = current - min;
                current = min;
                return r;
            } else {
                current -= value;
                return value;
            }
        } else {
            if (i < min) {
                return current - min;
            } else {
                return value;
            }
        }
    }

    public int self_add() {
        current++;
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }

    public int self_substract() {
        current--;
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }

    public boolean atMaxValue() {
        return current == max;
    }

    public boolean atMinValue() {
        return current == min;
    }

    public int getCurrent() {
        return current;
    }

    public void resetValue() {
        this.current = min;
    }

}
