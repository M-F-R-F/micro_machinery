package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class RecipeHelper {

    public static KlinRecipe CanKlinSmelt(ItemStack stackInSlot, ItemStack stackInSlot1, FluidTank tank) {
        for (KlinRecipe tofluidrecipe : ModRecipes.tofluidrecipes) {
            if ((stackInSlot.getItem() == tofluidrecipe.input1.getItem() && stackInSlot1.getItem() == tofluidrecipe.input2.getItem()) || (stackInSlot.getItem() == tofluidrecipe.input2.getItem() && stackInSlot1.getItem() == tofluidrecipe.input1.getItem()) && (tank.getFluid().getFluid() == tofluidrecipe.outputfluidstack.getFluid())) {
                if ((stackInSlot.getCount() >= tofluidrecipe.input1.getCount() && stackInSlot1.getCount() >= tofluidrecipe.input2.getCount()) || (stackInSlot.getCount() >= tofluidrecipe.input2.getCount() && stackInSlot1.getCount() >= tofluidrecipe.input1.getCount()) && ((tank.getFluidAmount() + tofluidrecipe.outputfluidstack.amount) <= tank.getCapacity()))
                    return tofluidrecipe;
            }
        }
        return null;
    }

    public static void KlinSmelt(TileEntityKlin klin) {
        FluidStack result = klin.getResult();
        IFluidHandler fluidhandler = klin.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        fluidhandler.fill(result, true);
    }
}
