package mfrf.dbydd.micro_machinery.recipes;


import mfrf.dbydd.micro_machinery.items.MMCastBase;
import mfrf.dbydd.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.dbydd.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.dbydd.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.dbydd.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeHelper {

    public static KlinItemToFluidRecipe GetKlinItemToFluidRecipe(ItemStack stackInSlot1, ItemStack stackInSlot2, RecipeManager manager) {
        if (!(stackInSlot1.isEmpty() && stackInSlot2.isEmpty())) {
            boolean isSingle = false;
            if (stackInSlot1.isEmpty() || stackInSlot2.isEmpty() || stackInSlot1.isItemEqual(stackInSlot2)) {
                isSingle = true;
            }
            List<KlinItemToFluidRecipe> collect = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE);
            for (KlinItemToFluidRecipe klinItemToFluidRecipe : collect) {
                if (klinItemToFluidRecipe.isIssingle() == isSingle) {
                    boolean issingle = klinItemToFluidRecipe.isIssingle();
                    if (issingle) {
                        if (testItemStackWithIngredient(new ItemStack(stackInSlot1.isEmpty() ? stackInSlot2.getItem() : stackInSlot1.getItem(), Math.min(stackInSlot1.getCount() + stackInSlot2.getCount(), 64)), klinItemToFluidRecipe.getInput(), klinItemToFluidRecipe.getCount())) {
                            return klinItemToFluidRecipe;
                        }
                    } else {
                        if ((testItemStackWithIngredient(stackInSlot1, klinItemToFluidRecipe.getInput1(), klinItemToFluidRecipe.getCount1()) || testItemStackWithIngredient(stackInSlot2, klinItemToFluidRecipe.getInput1(), klinItemToFluidRecipe.getCount1())) && (testItemStackWithIngredient(stackInSlot1, klinItemToFluidRecipe.getInput2(), klinItemToFluidRecipe.getCount2()) || testItemStackWithIngredient(stackInSlot2, klinItemToFluidRecipe.getInput2(), klinItemToFluidRecipe.getCount2()))) {
                            return klinItemToFluidRecipe;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static KlinFluidToItemRecipe GetKlinFluidRecipe(FluidStack fluidStack, ItemStack castslot, RecipeManager manager) {
        List<KlinFluidToItemRecipe> collect = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.KLIN_FLUID_TP_ITEM_RECIPE_TYPE);
        Item cast = castslot.getItem();
        if (cast instanceof MMCastBase) {
            for (KlinFluidToItemRecipe recipe : collect) {
                if (fluidStack.getFluid() == recipe.getInputfluid().getFluid() && recipe.getInputfluid().getAmount() <= fluidStack.getAmount() && recipe.getCast() == ((MMCastBase) cast).type) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static AnvilRecipe getForgingAnvilRecipe(ItemStack input, RecipeManager manager) {
        List<AnvilRecipe> recipeListByType = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.FORGE_ANVIL_RECIPE_TYPE);
        for (AnvilRecipe recipe : recipeListByType) {
            if (recipe.getInput().test(input)) {
                return recipe;
            }
        }
        return null;
    }

    public static EtcherRecipe getEtcherRecipe(ItemStack input, RecipeManager manager) {
        List<EtcherRecipe> recipeListByType = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.ETCHER_RECIPE_RECIPE_TYPE);
        for (EtcherRecipe recipe : recipeListByType) {
            if (recipe.getInput().test(input) && recipe.getCountInput() <= input.getCount()) {
                return recipe;
            }
        }
        return null;
    }

    public static BlastFurnaceRecipe getBlastFurnaceRecipe(ItemStack input, RecipeManager manager) {
        List<BlastFurnaceRecipe> recipeListByType = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.BLAST_FURNACE_RECIPE_RECIPE_TYPE);
        for (BlastFurnaceRecipe blastFurnaceRecipe : recipeListByType) {
            if (blastFurnaceRecipe.getInput().test(input)) {
                return blastFurnaceRecipe;
            }
        }
        return null;
    }

    public static ElectrolysisRecipe getElectrolysisRecipe(ItemStack input, RecipeManager manager) {
        List<ElectrolysisRecipe> recipeListByType = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.ELECTROLYSIS_RECIPE_RECIPE_TYPE);
        for (ElectrolysisRecipe electrolysisRecipe : recipeListByType) {
            if (electrolysisRecipe.getInput().test(input)) {
                return electrolysisRecipe;
            }
        }
        return null;
    }


    public static boolean isStackABiggerThanStackB(ItemStack stackA, ItemStack stackB) {
        return (stackA.getItem() == stackB.getItem()) && (stackA.getCount() >= stackB.getCount());
    }

    public static boolean canInsert(ItemStack stackinslot, ItemStack output) {
        if (stackinslot.isEmpty()) {
            return true;
        }
        if (stackinslot.getItem() == output.getItem() && stackinslot.getCount() + output.getCount() <= stackinslot.getMaxStackSize()) {
            return true;
        }
        return false;
    }

    public static Fluid getFluidByName(String name) {
        return ForgeRegistries.FLUIDS.getValue(new ResourceLocation(name));
    }

    public static <cast> List<cast> getRecipeListByType(RecipeManager manager, IRecipeType<?> type) {
        return manager.getRecipes().stream().filter(iRecipe -> iRecipe.getType() == type).map(iRecipe -> (cast) iRecipe).collect(Collectors.toList());
    }

    public static boolean testItemStackWithIngredient(ItemStack stack, Ingredient ingredient, int count) {
        return ingredient.test(stack) && stack.getCount() >= count;
    }

}

