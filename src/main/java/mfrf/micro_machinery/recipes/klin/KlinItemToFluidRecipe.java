package mfrf.micro_machinery.recipes.klin;

import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class KlinItemToFluidRecipe implements IRecipe<RecipeWrapper> {

    private final boolean issingle;
    private final int melttime;
    private final Ingredient input1;
    private final int count1;
    private final Ingredient input2;
    private final int count2;
    private final Ingredient input;
    private final int count;
    private final FluidStack outputfluidstack;
    private final ResourceLocation id;

    public KlinItemToFluidRecipe(boolean issingle, int melttime, Ingredient input1, int count1, Ingredient input2, int count2, Ingredient input, int count, FluidStack outputfluidstack, ResourceLocation id) {
        this.issingle = issingle;
        this.melttime = melttime;
        this.input1 = input1;
        this.count1 = count1;
        this.input2 = input2;
        this.count2 = count2;
        this.input = input;
        this.count = count;
        this.outputfluidstack = outputfluidstack;
        this.id = id;
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    public int getCount() {
        return count;
    }

    public Ingredient getInput1() {
        return issingle ? input : input1;
    }

    public Ingredient getInput2() {
        return issingle ? input : input2;
    }

    public Ingredient getInput() {
        return input;
    }

    public FluidStack getOutputfluidstack() {
        return outputfluidstack;
    }

    public int getMelttime() {
        return melttime;
    }

    public boolean isIssingle() {
        return issingle;
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
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.KLIN_ITEM_TO_FLUID.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE;
    }


    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KlinItemToFluidRecipe> {
        @Override
        public KlinItemToFluidRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = JSONUtils.getJsonObject(json, "output");
            boolean isSingle = JSONUtils.getBoolean(json, "isSingle");
            int meltTime = JSONUtils.getInt(json, "meltTime");
            FluidStack result = new FluidStack(RecipeHelper.getFluidByName(JSONUtils.getString(output, "fluidName")), JSONUtils.getInt(output, "amount"));
            if (isSingle) {
                JsonObject inputIfSingle = JSONUtils.getJsonObject(json, "inputIfSingle");
                Ingredient input = Ingredient.deserialize(inputIfSingle);
                return new KlinItemToFluidRecipe(true, meltTime, Ingredient.EMPTY, 0, Ingredient.EMPTY, 0, input, JSONUtils.getInt(inputIfSingle, "count"), result, recipeId);
            } else {
                JsonObject input = JSONUtils.getJsonObject(json, "input");
                JsonObject input1 = JSONUtils.getJsonObject(input, "input1");
                JsonObject input2 = JSONUtils.getJsonObject(input, "input2");
                Ingredient input1Ingredient = Ingredient.deserialize(input1);
                Ingredient input2Ingredient = Ingredient.deserialize(input2);

                return new KlinItemToFluidRecipe(false, meltTime, input1Ingredient, JSONUtils.getInt(input1, "count"), input2Ingredient, JSONUtils.getInt(input2, "count"), Ingredient.EMPTY, 0, result, recipeId);
            }

        }

        @Nullable
        @Override
        public KlinItemToFluidRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int meltTime = buffer.readInt();
            boolean isSingle = buffer.readBoolean();
            Ingredient input = Ingredient.read(buffer);
            int count = buffer.readInt();
            Ingredient input1 = Ingredient.read(buffer);
            int count1 = buffer.readInt();
            Ingredient input2 = Ingredient.read(buffer);
            int count2 = buffer.readInt();
            FluidStack fluidStack = FluidStack.readFromPacket(buffer);
            return new KlinItemToFluidRecipe(isSingle, meltTime, input1, count1, input2, count2, input, count, fluidStack, recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, KlinItemToFluidRecipe recipe) {
            buffer.writeInt(recipe.melttime);
            buffer.writeBoolean(recipe.issingle);
            recipe.input.write(buffer);
            buffer.writeInt(recipe.count);
            recipe.input1.write(buffer);
            buffer.writeInt(recipe.count1);
            recipe.input2.write(buffer);
            buffer.writeInt(recipe.count2);
            recipe.outputfluidstack.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a PacketBuffer and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}
