package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.KlinFluidRecipe;
import com.dbydd.micro_machinery.recipes.KlinRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static final List<KlinFluidRecipe> fluidRecipes = new ArrayList<KlinFluidRecipe>();
    public static final List<KlinRecipe> tofluidrecipes = new ArrayList<KlinRecipe>();

    public static final KlinRecipe test = new KlinRecipe(new FluidStack(ModFluids.APPLE_JUICE, 144), new ItemStack(Items.APPLE, 1), new ItemStack(Items.APPLE, 1), 12);
    public static final KlinRecipe test1 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 144), new ItemStack(Items.APPLE, 1), new ItemStack(ModBlocks.ORECOPPER, 1), 12);
}
