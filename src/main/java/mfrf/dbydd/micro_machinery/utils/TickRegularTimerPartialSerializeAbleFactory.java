package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.function.Consumer;

public class TickRegularTimerPartialSerializeAbleFactory<T> {
    private final int interval;
    private final Consumer<T> action;

    public TickRegularTimerPartialSerializeAbleFactory(Consumer<T> action, int interval) {
        this.action = action;
        this.interval = interval;
    }

    public TickRegularTimerPartialSerializeAble build() {
        return new TickRegularTimerPartialSerializeAble();
    }

    public class TickRegularTimerPartialSerializeAble implements INBTSerializable<CompoundNBT> {
        public int tick = 0;
        private int interval = 0;

        TickRegularTimerPartialSerializeAble() {
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putInt("tick", tick);
            return null;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            tick = nbt.getInt("tick");
        }

        public void tick(T arg) {
            tick++;
            if (tick >= interval) {
                action.accept(arg);
            }
        }

    }


}
