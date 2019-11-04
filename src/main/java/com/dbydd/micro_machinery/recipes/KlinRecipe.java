package com.dbydd.micro_machinery.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class KlinRecipe {

    public static List<KlinRecipe> tofluidrecipes = new ArrayList<KlinRecipe>();

    public ItemStack input1 = null;
    public ItemStack input2 = null;
    public FluidStack outputfluidstack = null;
    public int fuelvalue;


    public KlinRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2, int fuelvalue) {
        this.input1 = input1;
        this.input2 = input2;
        this.outputfluidstack = outputfluidstack;
        this.fuelvalue = fuelvalue;

        tofluidrecipes.add(this);
    }

}
