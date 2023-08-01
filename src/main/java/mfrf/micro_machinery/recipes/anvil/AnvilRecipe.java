package mfrf.micro_machinery.recipes.anvil;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class AnvilRecipe extends RecipeBase {
    private final int rankNeed;
    private final ItemStack output;
    private final Ingredient input;

    public AnvilRecipe(Ingredient input, ItemStack output, int rankNeed, ResourceLocation id) {
        super(id);
        this.input = input;
        this.output = output;
        this.rankNeed = rankNeed;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getRankNeed() {
        return rankNeed;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.FORGE_ANVIL_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.FORGE_ANVIL_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AnvilRecipe> {

        @Override
        public AnvilRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = json.getAsJsonObject("output");
            int tier = json.get("tier").getAsInt();
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));
            ItemStack item = RecipeHelper.getItemStackOutPutFormJsonObject(output);
            return new AnvilRecipe(input, item, tier, recipeId);
        }

        @Override
        public AnvilRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int rank = buffer.readInt();
            Ingredient read = Ingredient.fromNetwork(buffer);
            ItemStack itemStack = buffer.readItem();
            return new AnvilRecipe(read, itemStack, rank, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AnvilRecipe recipe) {
            buffer.writeInt(recipe.rankNeed);
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output, false);
        }
    }
}
