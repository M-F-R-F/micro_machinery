package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel{
	
	float damage;

	public ToolSword(ToolMaterial material, int maxDamageIn, String name) {
		super(material);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
		setRegistryName(name);
		setUnlocalizedName(name);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public float getAttackDamage() {
		return 1999999.0f;
	}
	
	@Override
	public void registerModels() 
	{
        Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
