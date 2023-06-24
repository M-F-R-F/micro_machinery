package mfrf.micro_machinery.recipes.anvil;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AnvilRecipe implements Recipe<RecipeWrapper> {
    private final int rankNeed;
    private final ItemStack output;
    private final Ingredient input;
    private final ResourceLocation id;

    public AnvilRecipe(Ingredient input, ItemStack output, int rankNeed, ResourceLocation id) {
        this.input = input;
        this.output = output;
        this.rankNeed = rankNeed;
        this.id = id;
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
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.FORGE_ANVIL_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.FORGE_ANVIL_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<AnvilRecipe> {

        @Override
        public AnvilRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = json.getAsJsonObject("output");
            int tier = json.get("tier").getAsInt();
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));
            Item item = ShapedRecipe.itemFromJson(output.getAsJsonObject("item"));
            int count = output.get("count").getAsInt();
            return new AnvilRecipe(input, new ItemStack(item, count), tier, recipeId);
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
            buffer.writeItemStack(recipe.output, false,false);
        }
    }
}
