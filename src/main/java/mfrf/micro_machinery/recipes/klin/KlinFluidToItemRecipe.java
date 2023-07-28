package mfrf.micro_machinery.recipes.klin;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registry_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class KlinFluidToItemRecipe extends RecipeBase {

    private final ItemStack output;
    private final String cast;
    private final FluidStack inputfluid;
    private final int cooldown;

    public KlinFluidToItemRecipe(ItemStack output, FluidStack inputfluid, String cast, int cooldown, ResourceLocation id) {
        super(id);
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = cast;
        this.cooldown = cooldown;

    }

    public ItemStack getOutput() {
        return output;
    }

    public String getCast() {
        return cast;
    }

    public FluidStack getInputfluid() {
        return inputfluid;
    }

    public int getCoolbelow() {
        return cooldown;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        return false;
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.KLIN_FLUID_TO_ITEM.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.KLIN_FLUID_TP_ITEM_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<KlinFluidToItemRecipe> {
        @Override
        public KlinFluidToItemRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            int coolDown = json.get("coolDown").getAsInt();
            String castType = json.get("castType").getAsString();
            JsonObject input = json.getAsJsonObject("input");
            JsonObject output = json.getAsJsonObject("output");

            FluidStack inputFluidStack = new FluidStack(RecipeHelper.getFluidByName(input.get("fluidName").getAsString()), input.get("amount").getAsInt());
            ItemStack itemStackOutPutFormJsonObject = RecipeHelper.getItemStackOutPutFormJsonObject(output);

            return new KlinFluidToItemRecipe(itemStackOutPutFormJsonObject, inputFluidStack, castType, coolDown, recipeId);
        }

        @Override
        public KlinFluidToItemRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int coolDown = buffer.readInt();
            String enumCastType = buffer.readUtf();
            ItemStack outPut = buffer.readItem();
            FluidStack inputFluid = buffer.readFluidStack();
            return new KlinFluidToItemRecipe(outPut, inputFluid, enumCastType, coolDown, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, KlinFluidToItemRecipe recipe) {
            buffer.writeInt(recipe.cooldown);
            buffer.writeUtf(recipe.cast);
            buffer.writeItemStack(recipe.output, false);
            recipe.inputfluid.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a FriendlyByteBuf and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}