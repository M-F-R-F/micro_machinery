package com.dbydd.micro_machinery.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class FluidRecipe {

    public static List<FluidRecipe> fluidRecipes = new ArrayList<FluidRecipe>();

    public ItemStack output = null;
    public FluidStack inputfluidstack = null;


    public FluidRecipe(FluidStack inputfluidstack, ItemStack output) {
        this.output = output;
        this.inputfluidstack = inputfluidstack;

        fluidRecipes.add(this);
    }

}