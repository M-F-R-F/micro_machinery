package mfrf.micro_machinery.recipes.klin;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.item.crafting.RecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.JSONUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class KlinItemToFluidRecipe implements Recipe<RecipeWrapper> {

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
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.KLIN_ITEM_TO_FLUID.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE;
    }


    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<KlinItemToFluidRecipe> {
        @Override
        public KlinItemToFluidRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject output = json.getAsJsonObject("output");
            boolean isSingle = GsonHelper.getAsBoolean(json, "isSingle");
            int meltTime = json.get("meltTime").getAsInt();
            FluidStack result = new FluidStack(RecipeHelper.getFluidByName(GsonHelper.getAsString(output, "fluidName")), output.get("amount").getAsInt());
            if (isSingle) {
                JsonObject inputIfSingle = json.getAsJsonObject("inputIfSingle");
                Ingredient input = Ingredient.fromJson(inputIfSingle);
                result.get(recipeId).getAsInt();
                return new KlinItemToFluidRecipe(true, meltTime, Ingredient.EMPTY, 0, Ingredient.EMPTY, 0, input, inputIfSingle, "count"),
                //todo fixit
            } else {
                JsonObject input = json.getAsJsonObject("input");
                JsonObject input1 = GsonHelper.getAsJsonObject(input, "input1");
                JsonObject input2 = GsonHelper.getAsJsonObject(input, "input2");
                Ingredient input1Ingredient = Ingredient.fromJson(input1);
                Ingredient input2Ingredient = Ingredient.fromJson(input2);

                return new KlinItemToFluidRecipe(false, meltTime, input1Ingredient, input1, "count"),
                input2Ingredient, GsonHelper.getAsInt(input2, "count"), Ingredient.EMPTY, 0, result.get(recipeId).getAsInt();
            }

        }

        @Nullable
        @Override
        public KlinItemToFluidRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int meltTime = buffer.readInt();
            boolean isSingle = buffer.readBoolean();
            Ingredient input = Ingredient.fromNetwork(buffer);
            int count = buffer.readInt();
            Ingredient input1 = Ingredient.fromNetwork(buffer);
            int count1 = buffer.readInt();
            Ingredient input2 = Ingredient.fromNetwork(buffer);
            int count2 = buffer.readInt();
            FluidStack fluidStack = FluidStack.readFromPacket(buffer);
            return new KlinItemToFluidRecipe(isSingle, meltTime, input1, count1, input2, count2, input, count, fluidStack, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, KlinItemToFluidRecipe recipe) {
            buffer.writeInt(recipe.melttime);
            buffer.writeBoolean(recipe.issingle);
            recipe.input.toNetwork(buffer);
            buffer.writeInt(recipe.count);
            recipe.input1.toNetwork(buffer);
            buffer.writeInt(recipe.count1);
            recipe.input2.toNetwork(buffer);
            buffer.writeInt(recipe.count2);
            recipe.outputfluidstack.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a FriendlyByteBuf and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}
