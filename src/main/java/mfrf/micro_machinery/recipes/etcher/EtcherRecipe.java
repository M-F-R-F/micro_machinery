package mfrf.micro_machinery.recipes.etcher;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.registeried_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class EtcherRecipe implements Recipe<RecipeWrapper> {
    private final int fePerTick;
    private final int time;
    private final Ingredient input;
    private final ItemStack output;
    private final int countInput;
    private final ResourceLocation id;

    public EtcherRecipe(int fePerTick, int feNeed, Ingredient input, ItemStack output, int countInput, ResourceLocation id) {
        this.fePerTick = fePerTick;
        this.time = feNeed;
        this.input = input;
        this.output = output;
        this.countInput = countInput;
        this.id = id;
    }

    public int getFePerTick() {
        return fePerTick;
    }

    public int getTime() {
        return time;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getCountInput() {
        return countInput;
    }

    @Override
    public boolean matches(RecipeWrapper pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv) {
        return null;
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
        return MMRecipeSerializers.ETCHER_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.ETCHER_RECIPE_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<EtcherRecipe> {

        @Override
        public EtcherRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = json.getAsJsonObject("output");
            Item itemOutput = ShapedRecipe.itemFromJson(output.getAsJsonObject("item"));
            int countOutput = output.get("count").getAsInt();

            JsonObject input = json.getAsJsonObject("input");
            Ingredient inputIngredient = Ingredient.fromJson(input.getAsJsonObject("ingredient"));
            int countInput = input.get("count").getAsInt();

            int fePerTick = json.get("fe_per_tick").getAsInt();
            int feNeed = json.get("time").getAsInt();
            return new EtcherRecipe(fePerTick, feNeed, inputIngredient, new ItemStack(itemOutput, countOutput), countInput, recipeId);
        }

        @Nullable
        @Override
        public EtcherRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int fePerTick = buffer.readInt();
            int feNeed = buffer.readInt();
            Ingredient inputIngredient = Ingredient.fromNetwork(buffer);
            int inputCount = buffer.readInt();
            ItemStack output = buffer.readItem();

            return new EtcherRecipe(fePerTick, feNeed, inputIngredient, output, inputCount, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EtcherRecipe recipe) {
            buffer.writeInt(recipe.fePerTick);
            buffer.writeInt(recipe.time);
            recipe.input.toNetwork(buffer);
            buffer.writeInt(recipe.countInput);
            buffer.writeItemStack(recipe.output, false);
        }
    }
}
