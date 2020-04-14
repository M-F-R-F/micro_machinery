package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.init.ModRecipes;
import com.dbydd.micro_machinery.items.ItemCast;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class KlinFluidRecipe {


    public ItemStack output = ItemStack.EMPTY;
    public ItemCast cast = null;
    public Fluid inputfluid = null;
    public int cooldown = 0;


    public KlinFluidRecipe(ItemStack output, Fluid inputfluid, Item cast, int cooldown) {
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = (ItemCast) cast;
        this.cooldown = cooldown;

        ModRecipes.klinFluidRecipes.add(this);
    }

}