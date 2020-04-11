package com.dbydd.micro_machinery.recipes.firegenerator;

import net.minecraft.item.ItemStack;

public class FireGeneratorRecipe {
    private final ItemStack fuel;
    private final int generateFEPerTick;
    private final int maxBurnTime;
    private final int waterNeededPerTick;

    public FireGeneratorRecipe(ItemStack fuel, int generateFEPerTick, int maxBurnTime, int waterNeededPerTick) {
        this.fuel = fuel;
        this.generateFEPerTick = generateFEPerTick;
        this.maxBurnTime = maxBurnTime;
        this.waterNeededPerTick = waterNeededPerTick;
    }
}
