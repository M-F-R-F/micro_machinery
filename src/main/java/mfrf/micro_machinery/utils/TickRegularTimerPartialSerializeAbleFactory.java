package mfrf.micro_machinery.utils;

import net.minecraft.nbt.CompoundTag;
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

    public class TickRegularTimerPartialSerializeAble implements INBTSerializable<CompoundTag> {
        public int tick = 0;
        private int interval = 0;

        TickRegularTimerPartialSerializeAble() {
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag compoundNBT = new CompoundTag();
            compoundNBT.putInt("tick", tick);
            return null;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
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
