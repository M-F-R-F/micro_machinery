package com.dbydd.micro_machinery.items.foods;

import com.dbydd.micro_machinery.Main;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel{

	public FoodBase(int amount, float saturation, String name) {
		super(amount, saturation, false);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.Micro_Machinery);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
