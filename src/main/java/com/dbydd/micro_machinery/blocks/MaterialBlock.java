package com.dbydd.micro_machinery.blocks;

import net.minecraft.block.material.Material;

public class MaterialBlock extends BlockBase {

	public MaterialBlock(String name, int harvestlevel, float hardness) {
		super("Block" + name, Material.IRON);
		setHarvestLevel("pickaxe", harvestlevel);
		setHardness(hardness);
	}

}
