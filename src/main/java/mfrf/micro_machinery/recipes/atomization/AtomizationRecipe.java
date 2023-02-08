package mfrf.micro_machinery.recipes.atomization;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import mfrf.micro_machinery.utils.RecipeFluidStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistryEntry;

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
        return RegisteredRecipeSerializers.ATOMIZATION_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.ATOMIZATION_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<AtomizationRecipe> {

        @Override
        public AtomizationRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecipeFluidStack input = RecipeHelper.getFluidStackFromJsonObject(json.getAsJsonObject("input"));
            ItemStack output = RecipeHelper.getItemStackFormJsonObject(json.getAsJsonObject("output"));
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
            buffer.writeItemStack(recipe.result, false);
            buffer.writeInt(recipe.time);
        }
    }
}
