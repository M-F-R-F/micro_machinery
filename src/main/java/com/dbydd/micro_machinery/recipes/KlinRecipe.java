package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class KlinRecipe {


    public static ItemStack input1 = null;
    public static ItemStack input2 = null;
    public static FluidStack outputfluidstack = null;
    public static int melttime;


    public KlinRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2, int melttime) {
        this.input1 = input1;
        this.input2 = input2;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;

        ModRecipes.tofluidrecipes.add(this);
    }

}
