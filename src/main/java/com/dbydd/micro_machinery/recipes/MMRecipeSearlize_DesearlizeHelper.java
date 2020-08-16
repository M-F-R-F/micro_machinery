package com.dbydd.micro_machinery.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.JSONUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class MMRecipeSearlize_DesearlizeHelper<R extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<R> {
    public static ItemStack getItemStackFromJsonElement(JsonElement element) {
        if (element.isJsonObject() && element.getAsJsonObject().has("itemstack")) {
            return ShapedRecipe.deserializeItem(element.getAsJsonObject());

        }
        return ItemStack.EMPTY;
    }

    public static boolean getBooleanFromJsonElement(JsonElement element) {
        return element.getAsBoolean();
    }

    public static FluidStack getFluidStackFromJsonElement(JsonElement element){
        if(element.isJsonObject() && element.getAsJsonObject().has("fluidstack")){
            JsonObject jsonObject = element.getAsJsonObject();
            String fluidname = JSONUtils.getString(jsonObject, "fluid");
            int amount = JSONUtils.getInt(jsonObject, "amount");
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidname));
            if(fluid != null){
             return new FluidStack(fluid, amount);
            }
        }
        return FluidStack.EMPTY;
    }

    @Override
    public final R read(ResourceLocation recipeId, JsonObject json) {
        if (CraftingHelper.processConditions(json, "conditions"))
            return readFromJson(recipeId, json);
        return null;
    }

    public abstract R readFromJson(ResourceLocation recipeId, JsonObject json);
}
