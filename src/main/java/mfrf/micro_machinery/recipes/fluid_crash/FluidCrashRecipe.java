package mfrf.micro_machinery.recipes.fluid_crash;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
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
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.FLUID_CRASH_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.FLUID_CRASH_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FluidCrashRecipe> {

        @Override
        public FluidCrashRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject fluidA = json.get("fluidA").getAsJsonObject();
            JsonObject fluidB = json.get("fluidB").getAsJsonObject();
            JsonObject result = json.get("result").getAsJsonObject();

            return new FluidCrashRecipe(recipeId,
                    ResourceLocation.tryCreate(fluidA.get("fluid_name").getAsString()),
                    fluidA.get("amount").getAsInt(),
                    ResourceLocation.tryCreate(fluidB.get("fluid_name").getAsString()),
                    fluidB.get("amount").getAsInt(),
                    RecipeHelper.getItemStackFormJsonObject(result)
            );
        }

        @Nullable
        @Override
        public FluidCrashRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            ResourceLocation fluidA = ResourceLocation.tryCreate(buffer.readString(32767));
            int fluidAmount = buffer.readInt();
            ResourceLocation fluidB = ResourceLocation.tryCreate(buffer.readString(32767));
            int fluidBmount = buffer.readInt();
            ItemStack generate = buffer.readItemStack();
            return new FluidCrashRecipe(recipeId, fluidA, fluidAmount, fluidB, fluidBmount, generate);
        }

        @Override
        public void write(PacketBuffer buffer, FluidCrashRecipe recipe) {
            buffer.writeString(recipe.fluidA.toString());
            buffer.writeInt(recipe.fluidAUsage);
            buffer.writeString(recipe.fluidB.toString());
            buffer.writeInt(recipe.fluidBUsage);
            buffer.writeItemStack(recipe.generate);
        }
    }

}
