package org.mfrf.micro_machienry.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static boolean outputBooleanByChance(Random rand, double chance) {
        double d = rand.nextDouble();
        return d <= chance;
    }

    public static BlockState outputRandmonBlockByList(Random rand, Map<Double, Block> map) {
        Double d = rand.nextDouble();
        Double sum = 0.0d;
        int size = map.size();
        int time = 0;
        for (Map.Entry<Double, Block> entry : map.entrySet()) {
            sum += entry.getKey();
            if (sum >= d || time == size) {
                sum = 0.0d;
                return entry.getValue().getDefaultState();
            } else time++;
        }
        return Blocks.STONE.getDefaultState();
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

        public RangeI(CompoundNBT nbt) {
            left = nbt.getInt("l");
            right = nbt.getInt("r");
        }

        public boolean inRange(int i) {
            return left <= i && i < right;
        }

        public CompoundNBT toNbt() {
            CompoundNBT compoundNBT = new CompoundNBT();
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

        public T roll(Random random) {
            if (list.size() == 1) {
                return list.values().stream().findAny().get();
            }
            int i = random.nextInt(bound);
            return list.entrySet().stream().filter(rangeITEntry -> rangeITEntry.getKey().inRange(i)).findAny().get().getValue();
        }
    }
}

