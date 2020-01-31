package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.KlinFluidRecipe;
import com.dbydd.micro_machinery.recipes.KlinRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static final List<KlinFluidRecipe> fluidRecipes = new ArrayList<KlinFluidRecipe>();
    public static final List<KlinRecipe> tofluidrecipes = new ArrayList<KlinRecipe>();

    //tofluid
    public static final KlinRecipe test1 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORECOPPER, 1), 24);
    public static final KlinRecipe test2 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_TIN, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORETIN, 1), 12);
    public static final KlinRecipe test3 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_BRONZE, 576), new ItemStack(ModItems.INGOTCOPPER, 3), new ItemStack(ModItems.INGOTTIN, 1), 20);

    //fluidtomaterial
    public static final KlinFluidRecipe test = new KlinFluidRecipe(new ItemStack(ModItems.INGOTCOPPER), ModFluids.MOLTEN_COPPER, ModItems.CAST_INGOT);
}
