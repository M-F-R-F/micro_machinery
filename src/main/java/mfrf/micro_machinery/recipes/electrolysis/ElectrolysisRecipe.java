package mfrf.micro_machinery.recipes.electrolysis;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.IngredientStack;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

import javax.annotation.Nullable;

public class ElectrolysisRecipe extends RecipeBase {
    private final IngredientStack input;
    private final ItemStack output;
    private final int Time;

    public ElectrolysisRecipe(IngredientStack input, ItemStack output, int time, ResourceLocation id) {
        super(id);
        this.input = input;
        this.output = output;
        Time = time;
    }

    public IngredientStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTime() {
        return Time;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.ELECTROLYSIS_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.ELECTROLYSIS_RECIPE_RECIPE_TYPE.get();
    }

    public static class Serializer  implements RecipeSerializer<ElectrolysisRecipe> {

        @Override
        public ElectrolysisRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            IngredientStack input = IngredientStack.ReadFromJson(json.getAsJsonObject("input_stack"));
            JsonObject output = json.getAsJsonObject("output");
            int count = output.get("count").getAsInt();
            Item item = ShapedRecipe.itemFromJson(output.getAsJsonObject("item"));
            int time = json.get("time").getAsInt();
            return new ElectrolysisRecipe(input, new ItemStack(item, count), time, recipeId);
        }

        @Nullable
        @Override
        public ElectrolysisRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            IngredientStack ingredientStack = IngredientStack.ReadFromBuffer(buffer);
            ItemStack itemStack = buffer.readItem();
            int i = buffer.readInt();
            return new ElectrolysisRecipe(ingredientStack, itemStack, i, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ElectrolysisRecipe recipe) {
            recipe.input.serializeToBuffer(buffer);
            buffer.writeItemStack(recipe.output,false);
            buffer.writeInt(recipe.Time);
        }
    }
}
