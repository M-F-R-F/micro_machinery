package com.dbydd.micro_machinery.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class KlinRecipes {

    protected static KlinRecipes me;
    public static List<IKlinRecipe> tofluidrecipes = new ArrayList<IKlinRecipe>();

    protected KlinRecipes KlinRecipes() {
        return this;
    }

    public static KlinRecipes getInstance() {
        if (me == null) me = new KlinRecipes();
        return me;
    }

    public FluidStack getKlintofluidResult(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (IKlinRecipe tofluidrecipe : tofluidrecipes) {
            if (stackInSlot == tofluidrecipe.input1) {
                if (stackInSlot1 == tofluidrecipe.input2) return tofluidrecipe.outputfluidstack;
            }
            if (stackInSlot == tofluidrecipe.input2) {
                if (stackInSlot1 == tofluidrecipe.input1) return tofluidrecipe.outputfluidstack;
            }
        }
        return null;
    }
}
