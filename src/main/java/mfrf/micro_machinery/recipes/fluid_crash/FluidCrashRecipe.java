package mfrf.micro_machinery.recipes.fluid_crash;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
        return MMRecipeSerializers.FLUID_CRASH_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.FLUID_CRASH_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<FluidCrashRecipe> {

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
                    RecipeHelper.getItemStackOutPutFormJsonObject(result)
            );
        }

        @Nullable
        @Override
        public FluidCrashRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int A = buffer.readInt();
            int B = buffer.readInt();
            ResourceLocation fluidA = ResourceLocation.tryParse(buffer.readUtf(A));
            ResourceLocation fluidB = ResourceLocation.tryParse(buffer.readUtf(B));
            int fluidAmount = buffer.readInt();
            int fluidBmount = buffer.readInt();
            ItemStack generate = buffer.readItem();
            return new FluidCrashRecipe(recipeId, fluidA, fluidAmount, fluidB, fluidBmount, generate);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FluidCrashRecipe recipe) {
            String A = recipe.fluidA.toString();
            String B = recipe.fluidB.toString();
            buffer.writeInt(A.length());
            buffer.writeInt(B.length());
            buffer.writeUtf(A, A.length());
            buffer.writeUtf(B, B.length());
            buffer.writeInt(recipe.fluidAUsage);
            buffer.writeInt(recipe.fluidBUsage);
            buffer.writeItemStack(recipe.generate, false);
        }
    }

}
