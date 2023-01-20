package mfrf.micro_machinery.recipes.atomization;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import mfrf.micro_machinery.utils.RecipeFluidStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

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
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.ATOMIZATION_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.ATOMIZATION_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AtomizationRecipe> {

        @Override
        public AtomizationRecipe read(ResourceLocation recipeId, JsonObject json) {
            RecipeFluidStack input = RecipeHelper.getFluidStackFromJsonObject(json.getAsJsonObject("input"));
            ItemStack output = RecipeHelper.getItemStackFormJsonObject(json.getAsJsonObject("output"));
            int time = json.get("time").getAsInt();
            return new AtomizationRecipe(recipeId, input, output, time);
        }

        @Nullable
        @Override
        public AtomizationRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            RecipeFluidStack recipeFluidStack = RecipeFluidStack.read(buffer);
            ItemStack itemStack = buffer.readItemStack();
            int time = buffer.readInt();
            return new AtomizationRecipe(recipeId, recipeFluidStack, itemStack, time);
        }

        @Override
        public void write(PacketBuffer buffer, AtomizationRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeInt(recipe.time);
        }
    }
}
