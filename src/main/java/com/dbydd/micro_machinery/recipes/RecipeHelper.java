package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.init.ModRecipes;
import com.dbydd.micro_machinery.util.ItemStackHelper;
import net.minecraft.item.ItemStack;

public class RecipeHelper {

    public static KlinRecipe GetKlinRecipe(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (KlinRecipe tofluidrecipe : ModRecipes.tofluidrecipes) {
            if (ItemStackHelper.areItemStackEqual(stackInSlot, tofluidrecipe.input1) && ItemStackHelper.areItemStackEqual(stackInSlot1, tofluidrecipe.input2) || ItemStackHelper.areItemStackEqual(stackInSlot1, tofluidrecipe.input1) && ItemStackHelper.areItemStackEqual(stackInSlot, tofluidrecipe.input2)) {
                if ((ItemStackHelper.compareCont(stackInSlot, tofluidrecipe.input1) && ItemStackHelper.compareCont(stackInSlot1, tofluidrecipe.input2)) || (ItemStackHelper.compareCont(stackInSlot, tofluidrecipe.input2) && ItemStackHelper.compareCont(stackInSlot1, tofluidrecipe.input1)))
                    return tofluidrecipe;
            }
        }
        return null;
    }
}

