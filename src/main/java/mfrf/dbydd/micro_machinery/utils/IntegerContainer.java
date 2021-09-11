package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;

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

    public IntegerContainer() {
        this.min = -1;
        this.max = -1;
        this.current = -1;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        setChanged();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        setChanged();
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
                setChanged();
                return r;
            } else {
                current += value;
                setChanged();
                return value;
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
        if (!simulate) {
            if (i < min) {
                int r = current - min;
                current = min;
                setChanged();
                return r;
            } else {
                current -= value;
                setChanged();
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

    public int selfAdd() {
        current++;
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        setChanged();
        return current;
    }

    public int selfSubtract() {
        current--;
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        setChanged();
        return current;
    }

    public boolean atMaxValue() {
        return current >= max;
    }

    public boolean atMinValue() {
        return current == min;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        setChanged();
    }

    public void resetValue() {
        this.current = min;
        setChanged();
    }

    public IIntArray toIntArray() {
        return new IIntArray() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return min;
                    case 1:
                        return max;
                    case 2:
                        return current;
                }
                return 0;
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        setMin(value);
                        break;
                    case 1:
                        setMax(value);
                        break;
                    case 2:
                        add(value, false);
                        break;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public void setChanged() {

    }

}
