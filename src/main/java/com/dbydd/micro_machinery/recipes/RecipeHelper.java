package com.dbydd.micro_machinery.recipes;


import com.dbydd.micro_machinery.items.MMCastBase;
import com.dbydd.micro_machinery.recipes.Anvil.AnvilRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import com.dbydd.micro_machinery.registery_lists.RegisteredRecipeSerializers;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeHelper {

    public static KlinItemToFluidRecipe GetKlinItemToFluidRecipe(ItemStack stackInSlot1, ItemStack stackInSlot2, RecipeManager manager) {
        List<KlinItemToFluidRecipe> collect = getRecipeListByType(manager, RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE);
        for (KlinItemToFluidRecipe recipe : collect) {
            if (recipe.isIssingle()) {
                if (!(stackInSlot1.isEmpty() && stackInSlot2.isEmpty())) {
                    if (recipe.isInputTag()) {
                        if ((stackInSlot1.isEmpty() || stackInSlot1.getItem().getTags().contains(recipe.getTag())) && (stackInSlot2.isEmpty() || stackInSlot2.getItem().getTags().contains(recipe.getTag()))) {
                            if (stackInSlot1.getCount() + stackInSlot2.getCount() >= recipe.getInput().getCount()) {
                                return recipe;
                            }
                        }
                    } else {
                        if ((recipe.getInput().getItem() == stackInSlot1.getItem()) || (stackInSlot1.getItem() == stackInSlot2.getItem())) {
                            if (stackInSlot1.getCount() + stackInSlot2.getCount() >= recipe.getInput().getCount()) {
                                return recipe;
                            }
                        }
                    }
                }
            } else if (!(stackInSlot1.isEmpty() || stackInSlot2.isEmpty())) {
                if (((recipe.isInput1Tag() ? stackInSlot1.getItem().getTags().contains(recipe.getTag1()) : (stackInSlot1.getItem() == recipe.getInput1().getItem())) && (recipe.isInput2Tag() ? stackInSlot2.getItem().getTags().contains(recipe.getTag2()) : (stackInSlot2.getItem() == recipe.getInput2().getItem()))) || ((recipe.isInput1Tag() ? stackInSlot2.getItem().getTags().contains(recipe.getTag1()) : (stackInSlot2.getItem() == recipe.getInput1().getItem())) && (recipe.isInput2Tag() ? stackInSlot1.getItem().getTags().contains(recipe.getTag2()) : (stackInSlot1.getItem() == recipe.getInput2().getItem()))))

//                        if ((stackInSlot1.getItem() == recipe.getInput1().getItem()) && (stackInSlot2.getItem() == recipe.getInput2().getItem()) || ((stackInSlot2.getItem() == recipe.getInput1().getItem()) && (stackInSlot1.getItem() == recipe.getInput2().getItem()))) {
                    if ((stackInSlot1.getCount() >= recipe.getInput1().getCount()) && (stackInSlot2.getCount() >= recipe.getInput2().getCount()) || ((stackInSlot2.getCount() >= recipe.getInput1().getCount()) && (stackInSlot1.getCount() >= recipe.getInput2().getCount()))) {
                        return recipe;
                    }
//                        }
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

    public static AnvilRecipe getForgingAnvilRecipe(ItemStack input) {
        for (AnvilRecipe recipe : AnvilRecipe.RECIPES) {
            if (input.isItemEqual(recipe.getInput())) {
                return recipe;
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

}

