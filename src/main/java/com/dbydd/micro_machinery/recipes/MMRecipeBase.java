package com.dbydd.micro_machinery.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class MMRecipeBase implements IRecipe<IInventory> {


    @Override
    public abstract boolean matches(IInventory inv, World worldIn);

    @Override
    public abstract ItemStack getCraftingResult(IInventory inv);

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public abstract ResourceLocation getId();

    @Override
    public abstract IRecipeSerializer<?> getSerializer();

    @Override
    public abstract IRecipeType<?> getType();
}
