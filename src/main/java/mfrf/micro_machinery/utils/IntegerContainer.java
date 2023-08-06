package mfrf.micro_machinery.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.DataSlot;

public class IntegerContainer extends DataSlot {
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

    public IntegerContainer(int current) {
        this.current = current;
        this.min = 0;
        this.max = 0;
    }

    public IntegerContainer() {
        this.min = -1;
        this.max = -1;
        this.current = -1;
    }

    @Override
    public int get() {
        return getCurrent();
    }

    @Override
    public void set(int pValue) {
        setCurrent(pValue);
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

    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("min", min);
        compoundNBT.putInt("max", max);
        compoundNBT.putInt("current", current);
        return compoundNBT;
    }

    //in purpose of change config
    public void deserializeNBT(CompoundTag nbt) {
        this.current = nbt.getInt("current");
        this.max = Math.max(nbt.getInt("max"), max);
        this.min = Math.min(nbt.getInt("min"), min);
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

    public int selfAdd() {
        current += selfAddValue();
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }

    protected int selfAddValue() {
        return 1;
    }

    public int selfSubtract() {
        current -= selfSubtractValue();
        if (current > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }

    protected int selfSubtractValue() {
        return 1;
    }

    public boolean atMaxValue() {
        return current >= max;
    }

    public boolean atMinValue() {
        return current <= min;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void resetValue() {
        this.current = min;
    }

    public int getRemainSpace() {
        return max - current;
    }

    public int getUsableCount() {
        return current - min;
    }

    public double getPercent() {
        return (double) current / (double) max;
    }

    public float getPrecisePercent() {
        return (float) current / (float) max;
    }
//    public IIntArray toIntArray() {
//        return new IIntArray() {
//            @Override
//            public int get(int index) {
//                switch (index) {
//                    case 0:
//                        return min;
//                    case 1:
//                        return max;
//                    case 2:
//                        return current;
//                }
//                return 0;
//            }
//
//            @Override
//            public void set(int index, int value) {
//                switch (index) {
//                    case 0:
//                        setMin(value);
//                        break;
//                    case 1:
//                        setMax(value);
//                        break;
//                    case 2:
//                        add(value, false);
//                        break;
//                }
//            }
//
//            @Override
//            public int size() {
//                return 3;
//            }
//        };
//    }

}
