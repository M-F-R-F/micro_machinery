package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class RecipeHelper {

    public static KlinRecipe GetKlinRecipe(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (KlinRecipe tofluidrecipe : ModRecipes.tofluidrecipes) {
            if ((stackInSlot.getItem() == tofluidrecipe.input1.getItem() && stackInSlot1.getItem() == tofluidrecipe.input2.getItem()) || (stackInSlot.getItem() == tofluidrecipe.input2.getItem() && stackInSlot1.getItem() == tofluidrecipe.input1.getItem())) {
                if ((stackInSlot.getCount() >= tofluidrecipe.input1.getCount() && stackInSlot1.getCount() >= tofluidrecipe.input2.getCount()) || (stackInSlot.getCount() >= tofluidrecipe.input2.getCount() && stackInSlot1.getCount() >= tofluidrecipe.input1.getCount()))
                    return tofluidrecipe;
            }
        }
        return null;
    }
}
