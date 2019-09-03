package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Main;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraft.world.storage.loot.functions.SetNBT;

public class ToolAxe extends ItemAxe implements IHasModel{

	public ToolAxe(ToolMaterial material, int maxDamageIn, String name, float damage, float eff) {
		super(material);
		setMaxDamage(maxDamageIn);
		setCreativeTab(Main.Micro_Machinery);
		setRegistryName(name);
		setUnlocalizedName(name);
		efficiency = eff;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
