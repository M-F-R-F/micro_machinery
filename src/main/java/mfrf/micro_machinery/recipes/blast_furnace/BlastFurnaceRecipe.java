package mfrf.micro_machinery.recipes.blast_furnace;

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

public class BlastFurnaceRecipe extends RecipeBase {
    private final IngredientStack input;
    private final ItemStack output;
    private final int cookTime;

    public BlastFurnaceRecipe(IngredientStack input, ItemStack output, int cookTime, ResourceLocation id) {
        super(id);
        this.input = input;
        this.output = output;
        this.cookTime = cookTime;
    }

    public IngredientStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getCookTime() {
        return cookTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.BLAST_FURNACE_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.BLAST_FURNACE_RECIPE_RECIPE_TYPE.get();
    }

    public static class Serializer  implements RecipeSerializer<BlastFurnaceRecipe> {

        @Override
        public BlastFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            IngredientStack ingredientStack = IngredientStack.ReadFromJson(json.get("input_stack").getAsJsonObject());
            JsonObject output = json.getAsJsonObject("output");
            Item item = ShapedRecipe.itemFromJson(output.getAsJsonObject("item"));
            int count = output.get("count").getAsInt();
            int cook_time = json.get("time").getAsInt();
            return new BlastFurnaceRecipe(ingredientStack, new ItemStack(item, count), cook_time, recipeId);
        }

        @Nullable
        @Override
        public BlastFurnaceRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            return new BlastFurnaceRecipe(IngredientStack.ReadFromBuffer(buffer), buffer.readItem(), buffer.readInt(), recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, BlastFurnaceRecipe recipe) {
            recipe.input.serializeToBuffer(buffer);
            buffer.writeItemStack(recipe.output,false);
            buffer.writeInt(recipe.cookTime);
        }
    }
}
