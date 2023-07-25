package mfrf.micro_machinery.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RandomUtils {
    private static final Random random = new Random();

    public static boolean outputBooleanByChance(Supplier<Double> supplier, double chance) {
        double d = supplier.get();
        return d <= chance;
    }

    @Nullable
    public static void setRandmonBlockByList(RandomSource rand, Map<Double, BlockState> list, BlockState blockState, Consumer<BlockState> set) {
        double d = rand.nextDouble();
        Double sum = 0.0d;
        int size = list.size();
        int time = 0;
        for (Map.Entry<Double, BlockState> pair : list.entrySet()) {
            sum += pair.getKey();
            if (sum >= d || time == size) {
                sum = 0.0d;
                set.accept(pair.getValue());
                return;
            } else time++;
        }
    }

    public static int nextRandomInt() {
        return org.apache.commons.lang3.RandomUtils.nextInt();
    }

    public static class RangeI {
        private final int left;
        private final int right;

        public RangeI(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public RangeI(CompoundTag nbt) {
            left = nbt.getInt("l");
            right = nbt.getInt("r");
        }

        public boolean inRange(int i) {
            return left <= i && i < right;
        }

        public CompoundTag toNbt() {
            CompoundTag compoundNBT = new CompoundTag();
            compoundNBT.putInt("l", left);
            compoundNBT.putInt("r", right);
            return compoundNBT;
        }
    }

    public static class RollListI<T> {
        public HashMap<RangeI, T> list = new HashMap<>();
        public int bound = 0;

        public RollListI(Map<T, Integer> roll_list) {
            for (Map.Entry<T, Integer> tIntegerEntry : roll_list.entrySet()) {
                list.put(new RangeI(bound, bound += tIntegerEntry.getValue()), tIntegerEntry.getKey());
            }
        }

        public RollListI(HashMap<RangeI, T> list, int bound) {
            this.list = list;
            this.bound = bound;
        }

        public T roll(RandomSource random) {
            if (list.size() == 1) {
                return list.values().stream().findAny().get();
            }
            int i = random.nextInt(bound);
            return list.entrySet().stream().filter(rangeITEntry -> rangeITEntry.getKey().inRange(i)).findAny().get().getValue();
        }
    }
}

