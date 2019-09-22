package com.dbydd.micro_machinery.recipes.CustomRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class IKlinRecipe {

    public static ItemStack input1 = null;
    public static ItemStack input2 = null;
    public static FluidStack outputfluidstack = null;
    public static int fuelneeded;


    public IKlinRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2, int fuelneeded) {
        this.input1 = input1;
        this.input2 = input2;
        this.outputfluidstack = outputfluidstack;
        this.fuelneeded = fuelneeded;
    }

}
