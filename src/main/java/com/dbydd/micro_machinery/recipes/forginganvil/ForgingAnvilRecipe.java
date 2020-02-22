package com.dbydd.micro_machinery.recipes.forginganvil;

import com.dbydd.micro_machinery.init.ModRecipes;
import net.minecraft.item.ItemStack;

public class ForgingAnvilRecipe {
    int forgetime = 3;

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

    ItemStack output = ItemStack.EMPTY;
    ItemStack input = ItemStack.EMPTY;

    public ForgingAnvilRecipe(int forgetime, ItemStack output, ItemStack input) {
        this.forgetime = forgetime;
        this.output = output;
        this.input = input;

        ModRecipes.forginganvilrecipes.add(this);
    }

}
