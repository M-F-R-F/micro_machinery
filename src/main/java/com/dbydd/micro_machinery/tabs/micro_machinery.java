package com.dbydd.micro_machinery.tabs;

import net.minecraft.item.ItemStack;

import com.dbydd.micro_machinery.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;

public class micro_machinery extends CreativeTabs {

    public micro_machinery(String label) {
        super("Micro_Machinery");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.AXISHSS);
    }

}