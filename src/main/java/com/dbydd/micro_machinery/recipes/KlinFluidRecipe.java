package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.init.ModRecipes;
import com.dbydd.micro_machinery.items.ItemCast;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class KlinFluidRecipe {


    public ItemStack output = null;
    public ItemCast cast = null;
    public Fluid inputfluid = null;


    public KlinFluidRecipe(ItemStack output, Fluid inputfluid, Item cast) {
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = (ItemCast) cast;

        ModRecipes.fluidRecipes.add(this);
    }

}