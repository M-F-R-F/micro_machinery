package com.dbydd.micro_machinery.recipes.klin;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class KlinItemToFluidRecipe {

    public static List<KlinItemToFluidRecipe> RECIPES = new ArrayList<>();

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

        RECIPES.add(this);
    }

    public KlinItemToFluidRecipe(FluidStack outputfluidstack, ItemStack input, int melttime) {
        this.input1 = ItemStack.EMPTY;
        this.input2 = ItemStack.EMPTY;
        this.input = input;
        this.outputfluidstack = outputfluidstack;
        this.melttime = melttime;
        this.issingle = true;

        RECIPES.add(this);
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
}
