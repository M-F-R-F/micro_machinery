package com.dbydd.micro_machinery.recipes;


import com.dbydd.micro_machinery.items.MMCastBase;
import com.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import com.dbydd.micro_machinery.registery_lists.recipes.KlinRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeHelper {

    public static KlinItemToFluidRecipe GetKlinItemToFluidRecipe(ItemStack stackInSlot1, ItemStack stackInSlot2) {
        checkIsEmpty();
        for (KlinItemToFluidRecipe recipe : KlinItemToFluidRecipe.RECIPES) {
            if (recipe.isIssingle()) {
                if ((recipe.getInput().getItem() == stackInSlot1.getItem()) || (stackInSlot1.getItem() == stackInSlot2.getItem())) {
                    if (stackInSlot1.getCount() + stackInSlot2.getCount() >= recipe.getInput().getCount()) {
                        return recipe;
                    }
                }
            } else if ((stackInSlot1.getItem() == recipe.getInput1().getItem()) && (stackInSlot2.getItem() == recipe.getInput2().getItem()) || ((stackInSlot2.getItem() == recipe.getInput1().getItem()) && (stackInSlot1.getItem() == recipe.getInput2().getItem()))) {
                if ((stackInSlot1.getCount() >= recipe.getInput1().getCount()) && (stackInSlot2.getCount() >= recipe.getInput2().getCount()) || ((stackInSlot2.getCount() >= recipe.getInput1().getCount()) && (stackInSlot1.getCount() >= recipe.getInput2().getCount()))) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static KlinFluidToItemRecipe GetKlinFluidRecipe(FluidStack fluidStack, ItemStack castslot) {
        checkIsEmpty();
        Item cast = castslot.getItem();
        if (cast instanceof MMCastBase) {
            for (KlinFluidToItemRecipe recipe : KlinFluidToItemRecipe.RECIPES) {
                if (fluidStack.getFluid() == recipe.getInputfluid().getFluid() && ((MMCastBase) cast).amount <= fluidStack.getAmount() && recipe.getCast() == ((MMCastBase) cast).type) {
                    return recipe;
                }
            }
        }
        return null;
    }

//    public static ForgingAnvilRecipe getForgingAnvilRecipe(ItemStack itemStack) {
//        for (ForgingAnvilRecipe recipe : ModRecipes.forgingAnvilRecipes) {
//            if (areItemStackEqual(itemStack, recipe.getInput())) return recipe;
//        }
//        return null;
//    }


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

    private static void checkIsEmpty(){
        if(KlinItemToFluidRecipe.RECIPES.isEmpty() || KlinFluidToItemRecipe.RECIPES.isEmpty()){
            KlinRecipe.init();
        }
    }

}

