package mfrf.micro_machinery.recipes.weld;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.IngredientStack;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.LinkedList;

public class WeldRecipe extends RecipeBase {
    private final LinkedList<IngredientStack> inputs;
    private final ItemStack output;
    private final int time;

    public WeldRecipe(ResourceLocation id, LinkedList<IngredientStack> inputs, ItemStack output, int time) {
        super(id);
        this.inputs = inputs;
        this.output = output;
        this.time = time;
    }

    public LinkedList<IngredientStack> getInputs() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.WELD_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.WELD_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<WeldRecipe> {

        @Override
        public WeldRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonArray jsonArray = json.getAsJsonArray("input");
            LinkedList<IngredientStack> inputs = new LinkedList<>();
            for (JsonElement jsonElement : jsonArray) {
                inputs.add(IngredientStack.ReadFromJson(jsonElement.getAsJsonObject()));
            }
            ItemStack output = RecipeHelper.getItemStackFormJsonObject(json.getAsJsonObject("output"));
            int time = json.get("time").getAsInt();
            return new WeldRecipe(recipeId, inputs, output, time);
        }

        @Nullable
        @Override
        public WeldRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ItemStack output = buffer.readItem();
            int time = buffer.readInt();
            LinkedList<IngredientStack> inputs = new LinkedList<>();
            while (buffer.isReadable()) {
                inputs.add(IngredientStack.ReadFromBuffer(buffer));
            }
            return new WeldRecipe(recipeId, inputs, output, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, WeldRecipe recipe) {
            buffer.writeItemStack(recipe.output,false);
            buffer.writeInt(recipe.time);
            for (IngredientStack input : recipe.inputs) {
                input.serializeToBuffer(buffer);
            }
        }
    }
}
