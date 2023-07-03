package mfrf.micro_machinery.recipes;


import com.google.gson.JsonObject;
import mfrf.micro_machinery.item.MMCastBase;
import mfrf.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.micro_machinery.recipes.atomization.AtomizationRecipe;
import mfrf.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.micro_machinery.recipes.centrifuge.CentrifugeRecipe;
import mfrf.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.micro_machinery.recipes.weld.WeldRecipe;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import mfrf.micro_machinery.utils.RecipeFluidStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeHelper {

    public static KlinItemToFluidRecipe GetKlinItemToFluidRecipe(ItemStack stackInSlot1, ItemStack stackInSlot2, RecipeManager manager) {
        if (!(stackInSlot1.isEmpty() && stackInSlot2.isEmpty())) {
            boolean isSingle = stackInSlot1.isEmpty() || stackInSlot2.isEmpty() || stackInSlot1.equals(stackInSlot2, true);
            List<KlinItemToFluidRecipe> collect = getRecipeListByType(manager, MMRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE);
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
        List<KlinFluidToItemRecipe> collect = getRecipeListByType(manager, MMRecipeSerializers.Type.KLIN_FLUID_TP_ITEM_RECIPE_TYPE);
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
        List<AnvilRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.FORGE_ANVIL_RECIPE_TYPE);
        for (AnvilRecipe recipe : recipeListByType) {
            if (recipe.getInput().test(input)) {
                return recipe;
            }
        }
        return null;
    }

    public static EtcherRecipe getEtcherRecipe(ItemStack input, RecipeManager manager) {
        List<EtcherRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.ETCHER_RECIPE_RECIPE_TYPE);
        for (EtcherRecipe recipe : recipeListByType) {
            if (recipe.getInput().test(input) && recipe.getCountInput() <= input.getCount()) {
                return recipe;
            }
        }
        return null;
    }

    public static BlastFurnaceRecipe getBlastFurnaceRecipe(ItemStack input, RecipeManager manager) {
        List<BlastFurnaceRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.BLAST_FURNACE_RECIPE_RECIPE_TYPE);
        for (BlastFurnaceRecipe blastFurnaceRecipe : recipeListByType) {
            if (blastFurnaceRecipe.getInput().test(input)) {
                return blastFurnaceRecipe;
            }
        }
        return null;
    }

    public static ElectrolysisRecipe getElectrolysisRecipe(ItemStack input, RecipeManager manager) {
        List<ElectrolysisRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.ELECTROLYSIS_RECIPE_RECIPE_TYPE);
        for (ElectrolysisRecipe electrolysisRecipe : recipeListByType) {
            if (electrolysisRecipe.getInput().test(input)) {
                return electrolysisRecipe;
            }
        }
        return null;
    }

    public static CutterRecipe getCutterRecipe(ItemStack input, RecipeManager manager) {
        List<CutterRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.CUTTER_RECIPE_TYPE);
        for (CutterRecipe cutterRecipe : recipeListByType) {
            if (cutterRecipe.getInput().test(input)) {
                return cutterRecipe;
            }
        }
        return null;
    }

    public static CentrifugeRecipe getCentrifugeRecipe(ItemStack input, RecipeManager manager) {
        List<CentrifugeRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.CENTRIFUGE_RECIPE_TYPE);
        for (CentrifugeRecipe centrifugeRecipe : recipeListByType) {
            if (centrifugeRecipe.getInput().test(input)) {
                return centrifugeRecipe;
            }
        }
        return null;
    }

    public static FluidCrashRecipe getFluidCrashRecipe(FluidStack a, FluidStack b, RecipeManager manager) {
        List<FluidCrashRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.FLUID_CRASH_RECIPE_TYPE);
        for (FluidCrashRecipe fluidCrashRecipe : recipeListByType) {
            Fluid fluidA = ForgeRegistries.FLUIDS.getValue(fluidCrashRecipe.fluidA);
            Fluid fluidB = ForgeRegistries.FLUIDS.getValue(fluidCrashRecipe.fluidB);
            if ((a.getFluid().equals(fluidA) && b.getFluid().equals(fluidB)) || (b.getFluid().equals(fluidA) && a.getFluid().equals(fluidB))) {
                return fluidCrashRecipe;
            }
        }
        return null;
    }

    public static AtomizationRecipe getAtomizationRecipe(FluidStack stack, RecipeManager manager) {
        List<AtomizationRecipe> recipeListByType = getRecipeListByType(manager, MMRecipeSerializers.Type.ATOMIZATION_RECIPE_TYPE);
        for (AtomizationRecipe atomizationRecipe : recipeListByType) {
            if (atomizationRecipe.input.test(stack))
                return atomizationRecipe;
        }
        return null;

    }

    public static weldRecipeAndShrinkItemStacks getWeldRecipe(RecipeManager recipeManager, ItemStackHandler input) {
        int slots = input.getSlots();
        ArrayList<ItemStack> inputStacks = new ArrayList<>(slots);
        for (int i = 0; i < slots; i++) {
            inputStacks.add(input.getStackInSlot(i));
        }

        if (inputStacks.stream().allMatch(ItemStack::isEmpty))
            return null;

        List<WeldRecipe> recipeListByType = getRecipeListByType(recipeManager, MMRecipeSerializers.Type.WELD_RECIPE_TYPE);
        for (WeldRecipe weldRecipe : recipeListByType) {
            LinkedList<IngredientStack> inputs = weldRecipe.getInputs();
            int[] shrinkItemStacks = new int[inputs.size()];

            for (int matchIndex = 0; matchIndex < slots; matchIndex++) {
                ItemStack itemStack = inputStacks.get(matchIndex);
                for (IngredientStack ingredientStack : inputs) {
                    if (ingredientStack.test(itemStack)) {
                        shrinkItemStacks[matchIndex] = ingredientStack.getCount();
                        inputs.remove(matchIndex);
                    }
                }
            }

            if (inputs.isEmpty()) {
                return new weldRecipeAndShrinkItemStacks(weldRecipe, shrinkItemStacks);
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
        return stackinslot.getItem() == output.getItem() && stackinslot.getCount() + output.getCount() <= stackinslot.getMaxStackSize();
    }

    public static Fluid getFluidByName(String name) {
        return ForgeRegistries.FLUIDS.getValue(new ResourceLocation(name));
    }

    public static <cast extends Recipe<?>> List<cast> getRecipeListByType(RecipeManager manager, RecipeType<cast> type) {
        return manager.getRecipes().stream().filter(iRecipe -> iRecipe.getType() == type).map(iRecipe -> (cast) iRecipe).collect(Collectors.toList());
    }

    public static boolean testItemStackWithIngredient(ItemStack stack, Ingredient ingredient, int count) {
        return ingredient.test(stack) && stack.getCount() >= count;
    }

    public static ItemStack getItemStackFormJsonObject(JsonObject object) {
        Item itemOutput = ShapedRecipe.itemFromJson(object.getAsJsonObject("item"));
        int countOutput = object.get("count").getAsInt();
        return new ItemStack(itemOutput, countOutput);
    }

    public static ResourceLocation getFluidNameFromJsonObject(JsonObject object) {
        return ResourceLocation.tryParse(object.get("fluid_name").getAsString());
    }

    public static int getFluidAmountFromJsonObject(JsonObject object) {
        return object.get("amount").getAsInt();
    }

    public static RecipeFluidStack getFluidStackFromJsonObject(JsonObject object) {
        return new RecipeFluidStack(getFluidNameFromJsonObject(object), getFluidAmountFromJsonObject(object));
    }

    public static class weldRecipeAndShrinkItemStacks {
        public final WeldRecipe weldRecipe;
        public final int[] shrinkCounts;

        weldRecipeAndShrinkItemStacks(WeldRecipe weldRecipe, int[] shrinkCounts) {
            this.weldRecipe = weldRecipe;
            this.shrinkCounts = shrinkCounts;
        }
    }
}

