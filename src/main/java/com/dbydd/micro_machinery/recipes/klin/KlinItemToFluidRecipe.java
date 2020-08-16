package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.recipes.MMRecipeBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlinItemToFluidRecipe extends MMRecipeBase {

    public static IRecipeType<KlinItemToFluidRecipe> TYPE = IRecipeType.register(Micro_Machinery.NAME+":klin_item_to_fluid");
    public static Map<ResourceLocation, KlinItemToFluidRecipe> RECIPES = new HashMap<>();

    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack input;
    private final FluidStack outputfluidstack;
    private final int melttime;
    private final boolean issingle;


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
        return issingle?input:input1;
    }

    public ItemStack getInput2() {
        return issingle?input:input2;
    }

    public ItemStack getInput(){
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
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
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
        return null;
    }
}
