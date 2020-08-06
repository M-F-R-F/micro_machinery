package com.dbydd.micro_machinery.registery_lists.recipes;

import com.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import com.dbydd.micro_machinery.registery_lists.RegisteredFluids;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;

public class KlinRecipe {

    public static final KlinItemToFluidRecipe TEST_ITEM_TO_FLUID_RECIPE = new KlinItemToFluidRecipe(new FluidStack(RegisteredFluids.APPLE_JUICE.fluid.get(), 144), new ItemStack(Items.APPLE), 100);


    public static void init(){

    }

}
