package com.dbydd.micro_machinery.recipes.Anvil;

import com.dbydd.micro_machinery.enums.EnumAnvilType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnvilRecipe {
    public static List<AnvilRecipe> RECIPES = new ArrayList<>();
    private final EnumAnvilType rankNeed;
    private final ItemStack output;
    private final ItemStack input;

    public AnvilRecipe(ItemStack input, ItemStack output, EnumAnvilType rankNeed) {
        this.input = input;
        this.output = output;
        this.rankNeed = rankNeed;
        RECIPES.add(this);
    }

    public ItemStack getInput() {
        return input;
    }

    public EnumAnvilType getRankNeed() {
        return rankNeed;
    }

    public ItemStack getOutput() {
        return output;
    }
}
