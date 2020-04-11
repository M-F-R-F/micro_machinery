package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EnergyModue extends BlockContainer implements IHasModel {
    protected EnergyModue(Material materialIn, String name, int level, int harvestlevel, float hardness) {
        super(materialIn);
        setHardness(hardness);
        setHarvestLevel("pickaxe", level);
        setUnlocalizedName(name + level);
        setRegistryName(name + level);
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
