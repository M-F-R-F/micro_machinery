package com.dbydd.micro_machinery.items.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MyMaterial {

	public static final ToolMaterial TOOL_MATERIAL_TEST = EnumHelper.addToolMaterial("test", 4, 32767, 16.0F, 199999.0f, 128);
	public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial("bronze", 3, 300, 7.0f, 2.0f, 15);
	public static final ToolMaterial TUNGSTEN_STEEL = EnumHelper.addToolMaterial("tungsten_steel", 4, 1200, 12.0f, 6.0f, 11);
	public static final ToolMaterial HSS = EnumHelper.addToolMaterial("hss", 4, 800, 15.0f, 14.0f, 17);
	
}
