package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.recipes.MMRecipeSearlize_DesearlizeHelper;
import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class Klin_Item_To_Fluid_Recipe_Searlize_DesearlizeHelper extends MMRecipeSearlize_DesearlizeHelper<KlinItemToFluidRecipe> {
    @Override
    public KlinItemToFluidRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        boolean issingle = getBooleanFromJsonElement(json.get("issingle"));
        if (issingle) {
            return new KlinItemToFluidRecipe(getFluidStackFromJsonElement(json.get("output")), getItemStackFromJsonElement(json.get("input")), JSONUtils.getInt(json, "melttime"));
        } else {
            return new KlinItemToFluidRecipe(getFluidStackFromJsonElement(json.get("output")), getItemStackFromJsonElement(json.get("input1")), getItemStackFromJsonElement(json.get("input2")), JSONUtils.getInt(json, "melttime"));
        }
    }

    @Nullable
    @Override
    public KlinItemToFluidRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, KlinItemToFluidRecipe recipe) {

    }
}
