package mfrf.dbydd.micro_machinery.recipes.etcher;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
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

import javax.annotation.Nullable;

public class EtcherRecipe implements IRecipe<RecipeWrapper> {
    private final int fePerTick;
    private final int feNeed;
    private final Ingredient input;
    private final ItemStack output;
    private final int countInput;
    private final ResourceLocation id;

    public EtcherRecipe(int fePerTick, int feNeed, Ingredient input, ItemStack output, int countInput, ResourceLocation id) {
        this.fePerTick = fePerTick;
        this.feNeed = feNeed;
        this.input = input;
        this.output = output;
        this.countInput = countInput;
        this.id = id;
    }

    public int getFePerTick() {
        return fePerTick;
    }

    public int getFeNeed() {
        return feNeed;
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
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
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
        return RegisteredRecipeSerializers.ETCHER_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.ETCHER_RECIPE_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<EtcherRecipe> {

        @Override
        public EtcherRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = JSONUtils.getJsonObject(json, "output");
            Item itemOutput = JSONUtils.getItem(output, "item");
            int countOutput = JSONUtils.getInt(output, "count");

            JsonObject input = JSONUtils.getJsonObject(json, "input");
            Ingredient inputIngredient = Ingredient.deserialize(input.getAsJsonObject("ingredient"));
            int countInput = JSONUtils.getInt(input, "count");

            int fePerTick = JSONUtils.getInt(json, "fe_per_tick");
            int feNeed = JSONUtils.getInt(json, "fe_need");
            return new EtcherRecipe(fePerTick, feNeed, inputIngredient, new ItemStack(itemOutput, countOutput), countInput, recipeId);
        }

        @Nullable
        @Override
        public EtcherRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int fePerTick = buffer.readInt();
            int feNeed = buffer.readInt();
            Ingredient inputIngredient = Ingredient.read(buffer);
            int inputCount = buffer.readInt();
            ItemStack output = buffer.readItemStack();

            return new EtcherRecipe(fePerTick, feNeed, inputIngredient, output, inputCount, recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, EtcherRecipe recipe) {
            buffer.writeInt(recipe.fePerTick);
            buffer.writeInt(recipe.feNeed);
            recipe.input.write(buffer);
            buffer.writeInt(recipe.countInput);
            buffer.writeItemStack(recipe.output);
        }
    }
}
