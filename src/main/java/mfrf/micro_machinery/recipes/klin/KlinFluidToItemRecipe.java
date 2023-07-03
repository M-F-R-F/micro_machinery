package mfrf.micro_machinery.recipes.klin;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.enums.EnumCastType;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class KlinFluidToItemRecipe implements Recipe<RecipeWrapper> {

    private final ItemStack output;
    private final String cast;
    private final FluidStack inputfluid;
    private final int cooldown;
    private final ResourceLocation id;

    public KlinFluidToItemRecipe(ItemStack output, FluidStack inputfluid, String cast, int cooldown, ResourceLocation id) {
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = cast;
        this.cooldown = cooldown;
        this.id = id;

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
    public ItemStack assemble(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.KLIN_FLUID_TO_ITEM.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.KLIN_FLUID_TP_ITEM_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<KlinFluidToItemRecipe> {
        @Override
        public KlinFluidToItemRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            int coolDown = json.get("coolDown").getAsInt();
            EnumCastType castType = EnumCastType.fromString(json.get("castType").getAsString());
            JsonObject input = json.getAsJsonObject("input");
            JsonObject output = json.getAsJsonObject("output");

            FluidStack inputFluidStack = new FluidStack(RecipeHelper.getFluidByName(input.get("fluidName").getAsString()), input.get("amount").getAsInt());
            ItemStack outputItemStack = new ItemStack(ShapedRecipe.itemFromJson(output.getAsJsonObject("itemName")), output.getAsJsonObject("count").getAsInt());

            return new KlinFluidToItemRecipe(outputItemStack, inputFluidStack, castType, coolDown, recipeId);
        }

        @Override
        public KlinFluidToItemRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int coolDown = buffer.readInt();
            EnumCastType enumCastType = EnumCastType.fromString(buffer.readUtf());
            ItemStack outPut = buffer.readItem();
            FluidStack inputFluid = buffer.readFluidStack();
            return new KlinFluidToItemRecipe(outPut, inputFluid, enumCastType, coolDown, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, KlinFluidToItemRecipe recipe) {
            buffer.writeInt(recipe.cooldown);
            buffer.writeUtf(EnumCastType.getString(recipe.cast));
            buffer.writeItemStack(recipe.output, false);
            recipe.inputfluid.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a FriendlyByteBuf and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}