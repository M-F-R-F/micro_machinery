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
    public static final KlinRecipe molten_copper = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORECOPPER, 1), 24);
    public static final KlinRecipe molten_tin = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_TIN, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORETIN, 1), 12);
    public static final KlinRecipe molten_bronze = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_BRONZE, 576), new ItemStack(ModItems.INGOTCOPPER, 3), new ItemStack(ModItems.INGOTTIN, 1), 20);

    //fluidtomaterial
    public static final KlinFluidRecipe ingot_copper = new KlinFluidRecipe(new ItemStack(ModItems.INGOTCOPPER), ModFluids.MOLTEN_COPPER, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe ingot_tin = new KlinFluidRecipe(new ItemStack(ModItems.INGOTTIN), ModFluids.MOLTEN_TIN, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe ingot_bronze = new KlinFluidRecipe(new ItemStack(ModItems.INGOTBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe stick_copper = new KlinFluidRecipe(new ItemStack(ModItems.STICKCOPPER), ModFluids.MOLTEN_COPPER, ModItems.CAST_STICK, 30);
    public static final KlinFluidRecipe stick_bronze = new KlinFluidRecipe(new ItemStack(ModItems.STICKBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_STICK, 30);
    public static final KlinFluidRecipe gear_bronze = new KlinFluidRecipe(new ItemStack(ModItems.GEARBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_GEAR, 160);
    public static final KlinFluidRecipe bronze_sword0  = new KlinFluidRecipe(new ItemStack(ModItems.BRONZE_SWORD0), ModFluids.MOLTEN_BRONZE, ModItems.CAST_SWORD, 360);
}
