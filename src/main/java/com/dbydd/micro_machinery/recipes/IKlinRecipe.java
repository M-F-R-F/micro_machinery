package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.recipes.KlinRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class IKlinRecipe {

    public ItemStack input1 = null;
    public ItemStack input2 = null;
    public FluidStack outputfluidstack = null;


    public IKlinRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2) {
        this.input1 = input1;
        this.input2 = input2;
        this.outputfluidstack = outputfluidstack;

        KlinRecipes.recipes.add(this);
    }

}
