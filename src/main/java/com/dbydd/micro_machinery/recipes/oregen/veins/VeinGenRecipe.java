package com.dbydd.micro_machinery.recipes.oregen.veins;

import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.worldgen.EndVeinGenerator;
import com.dbydd.micro_machinery.worldgen.VeinGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.TreeMap;
import java.util.function.Predicate;

public class VeinGenRecipe {
    public VeinGenRecipe(Double veinGenChance,Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, TreeMap<Double, IBlockState> oreGenList, Predicate<IBlockState> predicate, OreGenEvent.GenerateMinable.EventType event) {
        ModGenerators.veinGeneratorRecipes.add(this);
        ModGenerators.worldVeinGenerators.add(new VeinGenerator(veinGenChance, generateChancePerOre, range, oreStratum, oreDepositHeight, stoneHeight, minHeight, maxHeight, oreGenList, predicate, event));
    }

    public VeinGenRecipe(Double veinGenChance,Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, TreeMap<Double, IBlockState> oreGenList, Predicate<IBlockState> predicate) {
        ModGenerators.veinGeneratorRecipes.add(this);
        ModGenerators.endVeinGenerators.add(new EndVeinGenerator(veinGenChance, generateChancePerOre, range, oreStratum, oreDepositHeight, stoneHeight, minHeight, maxHeight, oreGenList, predicate));
    }
}
