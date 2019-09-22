package com.dbydd.micro_machinery.recipes.CustomRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class IFluidRecipe {

    public static ItemStack output = null;
    public static FluidStack inputfluidstack = null;


    public IFluidRecipe(FluidStack inputfluidstack, ItemStack output) {
        this.output = output;
        this.inputfluidstack = inputfluidstack;
    }

}