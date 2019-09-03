package com.dbydd.micro_machinery.items.MaterialBase;

import com.dbydd.micro_machinery.Main;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.item.Item;

public class MaterialAxis extends Item implements IHasModel {

	public MaterialAxis(String material) {
		String itemname = "Axis"+material;
		setUnlocalizedName(itemname);
		setRegistryName(itemname);
        setCreativeTab(Main.Micro_Machinery);		
		ModItems.ITEMS.add(this);
}

@Override
public void registerModels() 
{
	Main.proxy.registerItemRenderer(this, 0, "inventory");
}
	
}
