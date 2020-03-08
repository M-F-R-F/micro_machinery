package com.dbydd.micro_machinery.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class MaterialBlock extends BlockBase {

	public MaterialBlock(String name, int harvestlevel, float hardness) {
		super("Block" + name, Material.IRON);
		setHarvestLevel("pickaxe", harvestlevel);
		setHardness(hardness);
	}

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
