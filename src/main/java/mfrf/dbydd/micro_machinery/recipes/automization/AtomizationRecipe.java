package mfrf.dbydd.micro_machinery.recipes.automization;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class AtomizationRecipe extends RecipeBase {
    public ResourceLocation fluid;
    public int amount;
    public ItemStack result;

    public AtomizationRecipe(ResourceLocation id) {
        super(id);
    }

    public AtomizationRecipe(ResourceLocation id, ResourceLocation fluid, int amount, ItemStack result) {
        super(id);
        this.fluid = fluid;
        this.amount = amount;
        this.result = result;
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
            JsonObject fluid = json.get("fluid").getAsJsonObject();
            JsonObject result = json.get("result").getAsJsonObject();
            return new AtomizationRecipe(recipeId, RecipeHelper.getFluidNameFromJsonObject(fluid), RecipeHelper.getFluidAmountFromJsonObject(fluid), RecipeHelper.getItemStackFormJsonObject(result));
        }

        @Nullable
        @Override
        public AtomizationRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            ResourceLocation fluid = ResourceLocation.tryCreate(buffer.readString(32767));
            int amount = buffer.readInt();
            ItemStack itemStack = buffer.readItemStack();
            return new AtomizationRecipe(recipeId, fluid, amount, itemStack);
        }

        @Override
        public void write(PacketBuffer buffer, AtomizationRecipe recipe) {
            buffer.writeString(recipe.fluid.toString());
            buffer.writeInt(recipe.amount);
            buffer.writeItemStack(recipe.result);
        }
    }
}
