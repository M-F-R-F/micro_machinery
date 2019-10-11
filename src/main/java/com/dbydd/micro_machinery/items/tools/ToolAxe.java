package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel{

	public ToolAxe(ToolMaterial material, int maxDamageIn, String name, float damage, float eff) {
		super(material);
		setMaxDamage(maxDamageIn);
		setCreativeTab(Micro_Machinery.Micro_Machinery);
		setRegistryName(name);
		setUnlocalizedName(name);
		efficiency = eff;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
