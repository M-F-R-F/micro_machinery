package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class KlinFluidRecipe {


    private ItemStack output = null;
    private FluidStack inputfluidstack = null;


    public KlinFluidRecipe(FluidStack inputfluidstack, ItemStack output) {
        this.output = output;
        this.inputfluidstack = inputfluidstack;

        ModRecipes.fluidRecipes.add(this);
    }

}