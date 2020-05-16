package com.dbydd.micro_machinery.util;

import net.minecraft.block.state.IBlockState;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandomUtils {
    private static final Random random = new Random();

    public static boolean outputBooleanByChance(Random rand, double chance) {
        double d = rand.nextDouble();
        return d <= chance;
    }

    public static IBlockState outputRandmonBlockByList(Random rand, TreeMap<Double, IBlockState> map) {
        Double d = rand.nextDouble();
        Double sum = 0.0d;
        for (Map.Entry entry : map.entrySet()) {
            sum += (Double) entry.getKey();
            if (sum >= d) {
                sum = 0.0d;
                return (IBlockState) entry.getValue();
            }
        }
        return map.lastEntry().getValue();
    }

    public static int nextRandomInt(){
        return org.apache.commons.lang3.RandomUtils.nextInt();
    }

}

