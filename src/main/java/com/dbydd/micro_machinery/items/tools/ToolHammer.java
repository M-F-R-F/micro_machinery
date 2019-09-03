package com.dbydd.micro_machinery.items.tools;

import net.minecraft.item.ItemPickaxe;

public class ToolHammer extends ItemPickaxe{

	public ToolHammer(ToolMaterial material, int maxDamageIn, String name) {
		super(material);
		setMaxDamage(maxDamageIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		attackDamage = 4.0f;
	}

}
