package mfrf.micro_machinery.recipes.fluid_crash;

import com.google.gson.JsonObject;
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

public class FluidCrashRecipe extends RecipeBase {
    public ResourceLocation fluidA;
    public int fluidAUsage;
    public ResourceLocation fluidB;
    public int fluidBUsage;
    public ItemStack generate;

    public FluidCrashRecipe(ResourceLocation id, ResourceLocation fluidA, int fluidAUsage, ResourceLocation fluidB, int fluidBUsage, ItemStack generate) {
        super(id);
        this.fluidA = fluidA;
        this.fluidAUsage = fluidAUsage;
        this.fluidB = fluidB;
        this.fluidBUsage = fluidBUsage;
        this.generate = generate;
    }

    public FluidCrashRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.FLUID_CRASH_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.FLUID_CRASH_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FluidCrashRecipe> {

        @Override
        public FluidCrashRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject fluidA = json.get("fluidA").getAsJsonObject();
            JsonObject fluidB = json.get("fluidB").getAsJsonObject();
            JsonObject result = json.get("result").getAsJsonObject();

            return new FluidCrashRecipe(recipeId,
                    ResourceLocation.tryParse(fluidA.get("fluid_name").getAsString()),
                    fluidA.get("amount").getAsInt(),
                    ResourceLocation.tryParse(fluidB.get("fluid_name").getAsString()),
                    fluidB.get("amount").getAsInt(),
                    RecipeHelper.getItemStackFormJsonObject(result)
            );
        }

        @Nullable
        @Override
        public FluidCrashRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ResourceLocation fluidA = ResourceLocation.tryParse(buffer.readUtf(32767));
            int fluidAmount = buffer.readInt();
            ResourceLocation fluidB = ResourceLocation.tryParse(buffer.readUtf(32767));
            int fluidBmount = buffer.readInt();
            ItemStack generate = buffer.readItem();
            return new FluidCrashRecipe(recipeId, fluidA, fluidAmount, fluidB, fluidBmount, generate);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidCrashRecipe recipe) {
            buffer.writeUtf(recipe.fluidA.toString());
            buffer.writeInt(recipe.fluidAUsage);
            buffer.writeUtf(recipe.fluidB.toString());
            buffer.writeInt(recipe.fluidBUsage);
            buffer.writeItemStack(recipe.generate, false);
        }
    }

}
