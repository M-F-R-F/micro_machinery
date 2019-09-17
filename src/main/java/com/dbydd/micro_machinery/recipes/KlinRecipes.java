package com.dbydd.micro_machinery.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.dbydd.micro_machinery.recipes.recipename.IKlinRecipe;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class KlinRecipes {
	private static final KlinRecipes INSTANCE = new KlinRecipes();
	private final Table<Object, FluidStack, Item> smeltingrecipe = HashBasedTable.<Object, FluidStack, Item>create();

	public static KlinRecipes getInstance() {
		return INSTANCE;
	}

	private KlinRecipes() {
		//addKlinRecipe(new ItemStack(Blocks.ACACIA_FENCE), new ItemStack(Blocks.ACACIA_FENCE_GATE), new ItemStack(BlockInit.COPPER_CHEST), 5.0F);
	}


	public void addKlinRecipe(IKlinRecipe recipe, FluidStack outputfluidstack, Item cast, float experience) {
		if (IKlinRecipe.outputitemstack != ItemStack.EMPTY) return;
		this.smeltingrecipe.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getKlinResult(ItemStack input1, ItemStack input2) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingrecipe.columnMap().entrySet()) {
			if (this.compareItemStacks(input1, (ItemStack) entry.getKey())) {
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if (this.compareItemStacks(input2, (ItemStack) ent.getKey())) {
						return (ItemStack) ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getDualsmeltingrecipe() {
		return this.smeltingrecipe;
	}

	public float getKlinExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return ((Float) entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}

}
