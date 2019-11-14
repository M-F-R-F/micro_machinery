package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.KlinFluidRecipe;
import com.dbydd.micro_machinery.recipes.KlinRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static List<KlinFluidRecipe> fluidRecipes = new ArrayList<KlinFluidRecipe>();
    public static List<KlinRecipe> tofluidrecipes = new ArrayList<KlinRecipe>();

    public static final KlinRecipe test = new KlinRecipe(new FluidStack(ModFluids.APPLE_JUICE, 144), new ItemStack(Items.APPLE, 1), new ItemStack(Items.APPLE, 1), 12);
}
