package com.dbydd.micro_machinery.recipes;

import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

public class KlinRecipes {
	public static final Set<IKlinRecipe> recipes = new HashSet<IKlinRecipe>();

	public static FluidStack getKlinToFluidResult(ItemStack input1, ItemStack input2) {
		for (IKlinRecipe recipe : recipes) {
			if (recipe.input1 == input1 || recipe.input1 == input2 && recipe.input2 == input2 || recipe.input1 == input1) {
				return recipe.outputfluidstack;
			}
		}
		return null;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getDualsmeltingrecipe() {
		//return this.smeltingrecipe;
		return null;
	}

}
