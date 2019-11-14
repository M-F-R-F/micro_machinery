package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;

public class RecipeHelper {

    public static KlinRecipe CanKlinSmelt(ItemStack stackInSlot, ItemStack stackInSlot1, FluidTank tank) {
        for (KlinRecipe tofluidrecipe : ModRecipes.tofluidrecipes) {
            if ((stackInSlot == tofluidrecipe.input1 && stackInSlot1 == tofluidrecipe.input2) || (stackInSlot == tofluidrecipe.input2 && stackInSlot1 == tofluidrecipe.input1) && (tank.getFluid().getFluid() == tofluidrecipe.outputfluidstack.getFluid())) {
                if ((stackInSlot.getCount() >= tofluidrecipe.input1.getCount() && stackInSlot1.getCount() == tofluidrecipe.input2.getCount()) || (stackInSlot.getCount() == tofluidrecipe.input2.getCount() && stackInSlot1.getCount() == tofluidrecipe.input1.getCount()) && ((tank.getFluidAmount() + tofluidrecipe.outputfluidstack.amount) <= 2000))
                    return tofluidrecipe;
            }
        }
        return null;
    }

    public static void KlinSmelt(TileEntityKlin klin) {
        KlinRecipe recipe = klin.getRecipeinsmelting();
        if (klin.getItemhandler().getStackInSlot(0).getItem() == recipe.input1.getItem()) {
            klin.extractItem(0, recipe.input1.getCount(), false);
            klin.extractItem(1, recipe.input2.getCount(), false);
            klin.fill(recipe.outputfluidstack, false);
        } else {
            klin.extractItem(0, recipe.input2.getCount(), false);
            klin.extractItem(1, recipe.input1.getCount(), false);
            klin.fill(recipe.outputfluidstack, false);
        }
    }
}
