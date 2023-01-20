package mfrf.micro_machinery.recipes.blast_furnace;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.IngredientStack;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

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
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.BLAST_FURNACE_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.BLAST_FURNACE_RECIPE_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BlastFurnaceRecipe> {

        @Override
        public BlastFurnaceRecipe read(ResourceLocation recipeId, JsonObject json) {
            IngredientStack ingredientStack = IngredientStack.ReadFromJson(json.get("input_stack").getAsJsonObject());
            JsonObject output = json.getAsJsonObject("output");
            Item item = JSONUtils.getItem(output, "item");
            int count = JSONUtils.getInt(output, "count");
            int cook_time = json.get("time").getAsInt();
            return new BlastFurnaceRecipe(ingredientStack, new ItemStack(item, count), cook_time, recipeId);
        }

        @Nullable
        @Override
        public BlastFurnaceRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            return new BlastFurnaceRecipe(IngredientStack.ReadFromBuffer(buffer), buffer.readItemStack(), buffer.readInt(), recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, BlastFurnaceRecipe recipe) {
            recipe.input.serializeToBuffer(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeInt(recipe.cookTime);
        }
    }
}
