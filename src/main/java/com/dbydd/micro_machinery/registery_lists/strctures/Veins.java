package com.dbydd.micro_machinery.registery_lists.strctures;

import com.dbydd.micro_machinery.worldgen.Predicates;
import com.dbydd.micro_machinery.worldgen.VeinFeatureConfig;
import com.dbydd.micro_machinery.worldgen.VeinGenerations;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class Veins {
    public static final Map<Double, Block> TEST_MAP = new HashMap<>();
    public static final VeinGenerations TEST = new VeinGenerations(new VeinFeatureConfig(0.25, 1.0, 32, 2, 10, 1, 1, 256, 20, TEST_MAP, Predicates.STONE));

    static{
        TEST_MAP.put(1.0, Blocks.DIAMOND_BLOCK);
    }

    public static final void init(){

    }
}
