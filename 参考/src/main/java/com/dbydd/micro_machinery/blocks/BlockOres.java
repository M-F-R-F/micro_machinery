package com.dbydd.micro_machinery.blocks;

import net.minecraft.block.material.Material;

public class BlockOres extends BlockBase {

	public BlockOres(String name, int harvestlevel, float hardness) {
		super("Ore" + name, Material.IRON);
		setHarvestLevel("pickaxe", harvestlevel);
		setHardness(hardness);
	}

}
