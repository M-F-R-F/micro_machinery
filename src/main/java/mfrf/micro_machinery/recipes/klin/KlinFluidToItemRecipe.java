package mfrf.micro_machinery.recipes.klin;

import mfrf.dbydd.micro_machinery.enums.EnumCastType;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class KlinFluidToItemRecipe implements IRecipe<RecipeWrapper> {

    private final ItemStack output;
    private final EnumCastType cast;
    private final FluidStack inputfluid;
    private final int cooldown;
    private final ResourceLocation id;

    public KlinFluidToItemRecipe(ItemStack output, FluidStack inputfluid, EnumCastType cast, int cooldown, ResourceLocation id) {
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = cast;
        this.cooldown = cooldown;
        this.id = id;

    }

    public ItemStack getOutput() {
        return output;
    }

    public EnumCastType getCast() {
        return cast;
    }

    public FluidStack getInputfluid() {
        return inputfluid;
    }

    public int getCooldown() {
        return cooldown;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.KLIN_FLUID_TO_ITEM.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.KLIN_FLUID_TP_ITEM_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KlinFluidToItemRecipe> {
        @Override
        public KlinFluidToItemRecipe read(ResourceLocation recipeId, JsonObject json) {
            int coolDown = JSONUtils.getInt(json, "coolDown");
            EnumCastType castType = EnumCastType.fromString(JSONUtils.getString(json, "castType"));
            JsonObject input = JSONUtils.getJsonObject(json, "input");
            JsonObject output = JSONUtils.getJsonObject(json, "output");

            FluidStack inputFluidStack = new FluidStack(RecipeHelper.getFluidByName(JSONUtils.getString(input, "fluidName")), JSONUtils.getInt(input, "amount"));
            ItemStack outputItemStack = new ItemStack(JSONUtils.getItem(output, "itemName"), JSONUtils.getInt(output, "count"));

            return new KlinFluidToItemRecipe(outputItemStack, inputFluidStack, castType, coolDown, recipeId);
        }

        @Override
        public KlinFluidToItemRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int coolDown = buffer.readInt();
            EnumCastType enumCastType = EnumCastType.fromString(buffer.readString());
            ItemStack outPut = buffer.readItemStack();
            FluidStack inputFluid = buffer.readFluidStack();
            return new KlinFluidToItemRecipe(outPut, inputFluid, enumCastType, coolDown, recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, KlinFluidToItemRecipe recipe) {
            buffer.writeInt(recipe.cooldown);
            buffer.writeString(EnumCastType.getString(recipe.cast));
            buffer.writeItemStack(recipe.output);
            recipe.inputfluid.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a PacketBuffer and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}