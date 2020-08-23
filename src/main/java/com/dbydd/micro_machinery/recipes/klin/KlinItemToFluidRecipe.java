package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.recipes.RecipeHelper;
import com.dbydd.micro_machinery.registery_lists.RegisteredRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlinItemToFluidRecipe implements IRecipe<RecipeWrapper> {
    public static List<KlinItemToFluidRecipe> RECIPES = new ArrayList<>();

    private final boolean issingle;
    private final int melttime;
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack input;
    private final FluidStack outputfluidstack;


    public KlinItemToFluidRecipe(FluidStack outputfluidstack, ItemStack input1, ItemStack input2, int melttime) {
        this.input1 = input1;
        this.input2 = input2;
        this.input = ItemStack.EMPTY;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;
        this.issingle = false;
    }

    public KlinItemToFluidRecipe(FluidStack outputfluidstack, ItemStack input, int melttime) {
        this.input1 = ItemStack.EMPTY;
        this.input2 = ItemStack.EMPTY;
        this.input = input;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;
        this.issingle = true;
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
        int sizeInventory = inv.getSizeInventory();
        KlinItemToFluidRecipe recipe = RecipeHelper.GetKlinItemToFluidRecipe(inv.getStackInSlot(0), inv.getStackInSlot(sizeInventory));
        return recipe != null;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE;
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
                Item item = JSONUtils.getItem(inputIfSingle, "item");
                int count = JSONUtils.getInt(inputIfSingle, "count");
                return new KlinItemToFluidRecipe(result, new ItemStack(item, count), meltTime);
            } else {
                JsonObject input1 = JSONUtils.getJsonObject(json, "input1");
                Item item1 = JSONUtils.getItem(input1, "item");
                int count1 = JSONUtils.getInt(input1, "count");
                JsonObject input2 = JSONUtils.getJsonObject(json, "input2");
                Item item2 = JSONUtils.getItem(input2, "item");
                int count2 = JSONUtils.getInt(input2, "count");
                return new KlinItemToFluidRecipe(result, new ItemStack(item2, count2), meltTime);
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
            return new KlinItemToFluidRecipe(result, input, meltTime);
            } else {
                return new KlinItemToFluidRecipe(result, input1, input2, meltTime);
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
