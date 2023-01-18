package mfrf.micro_machinery.recipes.electrolysis;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.IngredientStack;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

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
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.ELECTROLYSIS_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.ELECTROLYSIS_RECIPE_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ElectrolysisRecipe> {

        @Override
        public ElectrolysisRecipe read(ResourceLocation recipeId, JsonObject json) {
            IngredientStack input = IngredientStack.ReadFromJson(json.getAsJsonObject("input_stack"));
            JsonObject output = json.getAsJsonObject("output");
            int count = output.get("count").getAsInt();
            Item item = JSONUtils.getItem(output, "item");
            int time = json.get("time").getAsInt();
            return new ElectrolysisRecipe(input, new ItemStack(item, count), time, recipeId);
        }

        @Nullable
        @Override
        public ElectrolysisRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            IngredientStack ingredientStack = IngredientStack.ReadFromBuffer(buffer);
            ItemStack itemStack = buffer.readItemStack();
            int i = buffer.readInt();
            return new ElectrolysisRecipe(ingredientStack, itemStack, i, recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, ElectrolysisRecipe recipe) {
            recipe.input.serializeToBuffer(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeInt(recipe.Time);
        }
    }
}
