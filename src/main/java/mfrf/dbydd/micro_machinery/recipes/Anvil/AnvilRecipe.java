package mfrf.dbydd.micro_machinery.recipes.Anvil;

import mfrf.dbydd.micro_machinery.registery_lists.RegisteredRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AnvilRecipe implements IRecipe<RecipeWrapper> {
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
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.FORGE_ANVIL_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.FORGE_ANVIL_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AnvilRecipe> {

        @Override
        public AnvilRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = JSONUtils.getJsonObject(json, "output");
            int tier = JSONUtils.getInt(json, "tier");
            Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
            Item item = JSONUtils.getItem(output, "item");
            int count = JSONUtils.getInt(output, "count");
            return new AnvilRecipe(input, new ItemStack(item, count), tier, recipeId);
        }

        @Override
        public AnvilRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int rank = buffer.readInt();
            Ingredient read = Ingredient.read(buffer);
            ItemStack itemStack = buffer.readItemStack();
            return new AnvilRecipe(read, itemStack, rank, recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, AnvilRecipe recipe) {
            buffer.writeInt(recipe.rankNeed);
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.output);
        }
    }
}
