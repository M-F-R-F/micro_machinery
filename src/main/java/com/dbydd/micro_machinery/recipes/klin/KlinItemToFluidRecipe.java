package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.recipes.RecipeHelper;
import com.dbydd.micro_machinery.registery_lists.RegisteredRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

import javax.annotation.Nullable;

public class KlinItemToFluidRecipe implements IRecipe<RecipeWrapper> {

    private final boolean issingle;
    private final int melttime;
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack input;
    private final FluidStack outputfluidstack;
    private final ResourceLocation id;
    private final boolean isInputTag;
    private final ResourceLocation tag;
    private final int count;
    private final boolean isInput1Tag;
    private final ResourceLocation tag1;
    private final int count1;
    private final boolean isInput2Tag;
    private final ResourceLocation tag2;
    private final int count2;

    public KlinItemToFluidRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2, int melttime, ResourceLocation id) {
        this.input1 = input1;
        this.input2 = input2;
        this.id = id;
        this.input = ItemStack.EMPTY;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;
        this.issingle = false;
        this.isInput1Tag = false;
        this.isInput2Tag = false;
        this.tag1 = null;
        this.tag2 = null;
        this.isInputTag = false;
        this.tag = null;
        this.count = 0;
        this.count1 = 0;
        this.count2 = 0;

    }

    public KlinItemToFluidRecipe(FluidStack outputfluidstack, ItemStack input, int melttime, ResourceLocation id) {
        this.id = id;
        this.input1 = ItemStack.EMPTY;
        this.input2 = ItemStack.EMPTY;
        this.input = input;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;
        this.issingle = true;
        this.isInput1Tag = false;
        this.isInput2Tag = false;
        this.tag1 = null;
        this.tag2 = null;
        this.isInputTag = false;
        this.tag = null;
        this.count = 0;
        this.count1 = 0;
        this.count2 = 0;
    }

    public KlinItemToFluidRecipe(int melttime, FluidStack outputfluidstack, ResourceLocation id, ResourceLocation tag1, ResourceLocation tag2, int count1, int count2) {
        this.issingle = true;
        this.melttime = melttime;
        this.outputfluidstack = outputfluidstack;
        this.id = id;
        this.input = ItemStack.EMPTY;
        this.input1 = ItemStack.EMPTY;
        this.input2 = ItemStack.EMPTY;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.isInput1Tag = true;
        this.isInput2Tag = true;
        this.tag = null;
        this.isInputTag = false;
        this.count = 0;
        this.count1 = count1;
        this.count2 = count2;
    }

    public KlinItemToFluidRecipe(int melttime, FluidStack outputfluidstack, ResourceLocation id, String tag, int count) {
        this.issingle = false;
        this.melttime = melttime;
        this.outputfluidstack = outputfluidstack;
        this.id = id;
        this.input = ItemStack.EMPTY;
        this.input1 = ItemStack.EMPTY;
        this.input2 = ItemStack.EMPTY;
        this.tag1 = null;
        this.tag2 = null;
        this.isInput1Tag = true;
        this.isInput2Tag = true;
        this.tag = null;
        this.isInputTag = false;
        this.count = count;
        this.count2 = 0;
        this.count1 = 0;
    }

    public KlinItemToFluidRecipe(int melttime, ItemStack input1, ItemStack input2, ItemStack input, FluidStack outputfluidstack, ResourceLocation id, boolean isInputTag, ResourceLocation tag, boolean isInput1Tag, ResourceLocation tag1, boolean isInput2Tag, ResourceLocation tag2, int count, int count1, int count2) {
        this.issingle = false;
        this.melttime = melttime;
        this.input1 = input1;
        this.input2 = input2;
        this.input = input;
        this.outputfluidstack = outputfluidstack;
        this.id = id;
        this.isInputTag = isInputTag;
        this.tag = tag;
        this.isInput1Tag = isInput1Tag;
        this.tag1 = tag1;
        this.isInput2Tag = isInput2Tag;
        this.tag2 = tag2;
        this.count1 = count1;
        this.count2 = count2;
        this.count = count;
    }

    public ItemStack getInput1() {
        return issingle ? input : input1;
    }

    public ItemStack getInput2() {
        return issingle ? input : input2;
    }

    public ItemStack getInput() {
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

    public boolean isInputTag() {
        return isInputTag;
    }

    public ResourceLocation getTag() {
        return tag;
    }

    public int getCount() {
        return count;
    }

    public boolean isInput1Tag() {
        return isInput1Tag;
    }

    public ResourceLocation getTag1() {
        return tag1;
    }

    public int getCount1() {
        return count1;
    }

    public boolean isInput2Tag() {
        return isInput2Tag;
    }

    public ResourceLocation getTag2() {
        return tag2;
    }

    public int getCount2() {
        return count2;
    }

    @Override
    public String toString() {
        return "KlinItemToFluidRecipe{" +
                "issingle=" + issingle +
                ", melttime=" + melttime +
                ", input1=" + input1 +
                ", input2=" + input2 +
                ", input=" + input +
                ", outputfluidstack=" + outputfluidstack +
                '}';
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KlinItemToFluidRecipe> {
        @Override
        public KlinItemToFluidRecipe read(ResourceLocation recipeId, JsonObject json) {
            boolean isSingle = JSONUtils.getBoolean(json, "isSingle");
            int meltTime = JSONUtils.getInt(json, "meltTime");
            JsonObject output = JSONUtils.getJsonObject(json, "output");
            FluidStack result = new FluidStack(RecipeHelper.getFluidByName(JSONUtils.getString(output, "fluidName")), JSONUtils.getInt(output, "amount"));
            if (isSingle) {
                JsonObject inputIfSingle = JSONUtils.getJsonObject(json, "inputIfSingle");
                int count = JSONUtils.getInt(inputIfSingle, "count");
                boolean isTag = JSONUtils.getBoolean(inputIfSingle, "isTag");

                if (isTag) {
                    String tag = JSONUtils.getString(inputIfSingle, "tag");
                    return new KlinItemToFluidRecipe(meltTime, result, recipeId, tag, count);
                }

                Item item = JSONUtils.getItem(inputIfSingle, "item");
                return new KlinItemToFluidRecipe(result, new ItemStack(item, count), meltTime, recipeId);

            } else {

                JsonObject input = JSONUtils.getJsonObject(json, "input");
                JsonObject input1 = JSONUtils.getJsonObject(input, "input1");
                int count1 = JSONUtils.getInt(input1, "count");
                JsonObject input2 = JSONUtils.getJsonObject(input, "input2");
                int count2 = JSONUtils.getInt(input2, "count");
                boolean isInput2Tag = JSONUtils.getBoolean(input2, "isTag");
                boolean isInput1Tag = JSONUtils.getBoolean(input1, "isTag");

                if (isInput1Tag) {
                    String input1Tag = JSONUtils.getString(input1, "tag");
                    if (isInput2Tag) {
                        String input2Tag = JSONUtils.getString(input2, "tag");
                        return new KlinItemToFluidRecipe(meltTime, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, result, recipeId, false, null, isInput1Tag, new ResourceLocation(input1Tag), isInput2Tag, new ResourceLocation(input2Tag), 0, count1, count2);
                    }
                    Item item2 = JSONUtils.getItem(input2, "item");
                    return new KlinItemToFluidRecipe(meltTime, ItemStack.EMPTY, new ItemStack(item2, count2), ItemStack.EMPTY, result, recipeId, false, null, isInput1Tag, new ResourceLocation(input1Tag), isInput2Tag, null, 0, count1, 0);
                }

                Item item1 = JSONUtils.getItem(input1, "item");
                if (isInput2Tag) {
                    String input2Tag = JSONUtils.getString(input2, "tag");
                    return new KlinItemToFluidRecipe(meltTime, new ItemStack(item1, count1), ItemStack.EMPTY, ItemStack.EMPTY, result, recipeId, false, null, false, null, isInput2Tag, new ResourceLocation(input2Tag), 0, 0, count2);
                }

                Item item2 = JSONUtils.getItem(input2, "item");
                return new KlinItemToFluidRecipe(result, new ItemStack(item2, count2), meltTime, recipeId);
            }
        }

        @Nullable
        @Override
        public KlinItemToFluidRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            boolean isSingle = buffer.readBoolean();
            int meltTime = buffer.readVarInt();
            ItemStack input = buffer.readItemStack();
            ItemStack input1 = buffer.readItemStack();
            ItemStack input2 = buffer.readItemStack();
            FluidStack result = FluidStack.readFromPacket(buffer);
            if (isSingle) {
                return new KlinItemToFluidRecipe(result, input, meltTime, recipeId);
            } else {
                return new KlinItemToFluidRecipe(result, input1, input2, meltTime, recipeId);
            }
        }

        @Override
        public void write(PacketBuffer buffer, KlinItemToFluidRecipe recipe) {
            buffer.writeBoolean(recipe.issingle);
            buffer.writeInt(recipe.melttime);
            buffer.writeItemStack(recipe.input);
            buffer.writeItemStack(recipe.input1);
            buffer.writeItemStack(recipe.input2);
            recipe.outputfluidstack.writeToPacket(buffer);
        }

        //- read: reads the data from a JsonObject and returns an instance of your recipe (json file stored in datapack)
        //- read: reads the data from a PacketBuffer and returns an instance of your recipe (server/client packet sending)
        //- write: reads the data from your instance to a packet buffer (server/client packet sending)

    }
}
