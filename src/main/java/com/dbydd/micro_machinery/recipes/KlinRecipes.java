package com.dbydd.micro_machinery.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.dbydd.micro_machinery.recipes.CustomRecipe.IFluidRecipe;
import com.dbydd.micro_machinery.recipes.CustomRecipe.IKlinRecipe;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class KlinRecipes {
	private static final KlinRecipes INSTANCE = new KlinRecipes();
	private final Table<IKlinRecipe, IFluidRecipe, Item> smeltingrecipe = HashBasedTable.<IKlinRecipe, IFluidRecipe, Item>create();

	public static KlinRecipes getInstance() {
		return INSTANCE;
	}

	public void addKlinRecipe(IKlinRecipe itemrecipe, IFluidRecipe fluidrecipe, Item item) {
		if (itemrecipe.outputfluidstack != null || fluidrecipe.output != ItemStack.EMPTY) return;
		this.smeltingrecipe.put(itemrecipe, fluidrecipe, item);
	}

	public ItemStack getKlinResult(ItemStack input1, ItemStack input2, IKlinRecipe klinRecipe) {
		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getDualsmeltingrecipe() {
		//return this.smeltingrecipe;
		return null;
	}

}
