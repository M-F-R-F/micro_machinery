package com.dbydd.micro_machinery.blocks;

import com.dbydd.micro_machinery.Main;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluid extends BlockFluidClassic implements IHasModel{

	String name = null;
	
	public BlockFluid(String name, Fluid fluid, Material material, float lightlevel, int tickRate) {
		super(fluid, material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.Micro_Machinery);   
        this.name = name;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		final String location = Reference.MODID + ":fluids/" + name;
        final Item itemFluid = Item.getItemFromBlock(this);
        ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(location, "fluid");
            }
        });
        ModelLoader.setCustomStateMapper(this, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(location, "fluid");
            }
        });
		
	}
	
	
}
