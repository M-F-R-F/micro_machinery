package mfrf.micro_machinery.recipes.atomization;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import mfrf.micro_machinery.utils.RecipeFluidStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class AtomizationRecipe extends RecipeBase {
    public RecipeFluidStack input;
    public ItemStack result;
    public int time;

    public AtomizationRecipe(ResourceLocation id) {
        super(id);
    }

    public AtomizationRecipe(ResourceLocation id, RecipeFluidStack input, ItemStack result, int time) {
        super(id);
        this.input = input;
        this.result = result;
        this.time = time;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.ATOMIZATION_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.ATOMIZATION_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AtomizationRecipe> {

        @Override
        public AtomizationRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecipeFluidStack input = RecipeHelper.getFluidStackFromJsonObject(json.getAsJsonObject("input"));
            ItemStack output = RecipeHelper.getItemStackOutPutFormJsonObject(json.getAsJsonObject("output"));
            int time = json.get("time").getAsInt();
            return new AtomizationRecipe(recipeId, input, output, time);
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public AtomizationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            RecipeFluidStack recipeFluidStack = RecipeFluidStack.read(buffer);
            ItemStack itemStack = buffer.readItem();
            int time = buffer.readInt();
            return new AtomizationRecipe(recipeId, recipeFluidStack, itemStack, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AtomizationRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.result.copy(), false);
            buffer.writeInt(recipe.time);
        }
    }
}
