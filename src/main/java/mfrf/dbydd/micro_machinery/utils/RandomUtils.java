package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

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
            sum += (Double) entry.getKey();
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

}

