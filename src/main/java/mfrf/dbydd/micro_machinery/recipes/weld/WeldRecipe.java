package mfrf.dbydd.micro_machinery.recipes.weld;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class WeldRecipe extends RecipeBase {

    public WeldRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return null;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<WeldRecipe> {

        @Override
        public WeldRecipe read(ResourceLocation recipeId, JsonObject json) {

        }

        @Nullable
        @Override
        public WeldRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

        }

        @Override
        public void write(PacketBuffer buffer, WeldRecipe recipe) {

        }
    }
}
