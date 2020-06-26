package com.dbydd.micro_machinery.recipes.forginganvil;

import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;

public class ForgingAnvilRecipe {
    private int level = 0;
    private int forgetime = 4;
    private ItemStack output = ItemStack.EMPTY;
    private ItemStack input = ItemStack.EMPTY;

    public ForgingAnvilRecipe(int level, int forgetime, ItemStack output, ItemStack input) {
        if (forgetime <= 4)
            this.forgetime = forgetime;
        this.level = level;
        this.output = output;
        this.input = input;

        ModRecipes.forgingAnvilRecipes.add(this);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForgetime() {
        return forgetime;
    }

    public void setForgetime(int forgetime) {
        this.forgetime = forgetime;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

}
