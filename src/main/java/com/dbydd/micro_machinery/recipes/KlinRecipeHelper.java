package com.dbydd.micro_machinery.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class KlinRecipeHelper {


    public static FluidStack getKlintofluidResult(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (KlinRecipe tofluidrecipe : KlinRecipe.tofluidrecipes) {
            if (stackInSlot == tofluidrecipe.input1) {
                if (stackInSlot1 == tofluidrecipe.input2) return tofluidrecipe.outputfluidstack;
            }
            if (stackInSlot == tofluidrecipe.input2) {
                if (stackInSlot1 == tofluidrecipe.input1) return tofluidrecipe.outputfluidstack;
            }
        }
        return null;
    }

    public static KlinRecipe getKlintofluidRecipe(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (KlinRecipe tofluidrecipe : KlinRecipe.tofluidrecipes) {
            if (stackInSlot == tofluidrecipe.input1) {
                if (stackInSlot1 == tofluidrecipe.input2) return tofluidrecipe;
            }
            if (stackInSlot == tofluidrecipe.input2) {
                if (stackInSlot1 == tofluidrecipe.input1) return tofluidrecipe;
            }
        }
        return null;
    }
}
