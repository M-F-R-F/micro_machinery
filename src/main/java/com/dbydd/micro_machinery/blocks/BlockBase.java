package com.dbydd.micro_machinery.blocks;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

        public BlockBase(String name, Material material) {
                super(material);
                setUnlocalizedName(name);
                setRegistryName(name);
                setCreativeTab(Micro_Machinery.Micro_Machinery);
                ModBlocks.BLOCKS.add(this);
                ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        }

        @Override
        public void registerModels() {
            Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
        }

        public Item blockToItem(){
                return Item.getItemFromBlock(this);
        }

}
