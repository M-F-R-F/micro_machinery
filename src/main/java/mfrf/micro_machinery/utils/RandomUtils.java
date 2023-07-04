package mfrf.micro_machinery.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static boolean outputBooleanByChance(Random rand, double chance) {
        double d = rand.nextDouble();
        return d <= chance;
    }

    public static BlockState outputRandmonBlockByList(Random rand, List<Pair<Double, Block>> list) {
        Double d = rand.nextDouble();
        Double sum = 0.0d;
        int size = list.size();
        int time = 0;
        for (Pair<Double, Block> pair : list) {
            sum += pair.getFirst();
            if (sum >= d || time == size) {
                sum = 0.0d;
                return pair.getSecond().defaultBlockState();
            } else time++;
        }
        return Blocks.STONE.defaultBlockState();
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

