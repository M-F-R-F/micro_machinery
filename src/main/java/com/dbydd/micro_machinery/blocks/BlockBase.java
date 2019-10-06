package com.dbydd.micro_machinery.blocks;

import javax.annotation.Nonnull;

import com.dbydd.micro_machinery.Main;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.tabs.micro_machinery;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block implements IHasModel{

        public BlockBase(String name, Material material) {
                super(material);
                setUnlocalizedName(name);
                setRegistryName(name);
                setCreativeTab(Main.Micro_Machinery);
                ModBlocks.BLOCKS.add(this);
                ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        }

        @Override
        public void registerModels() {
                Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "invenroty");
        }

}
