package com.dbydd.micro_machinery.util;

import net.minecraft.item.ItemStack;

public class ItemStackHelper {


    public static boolean areItemStackEqual(ItemStack stackA, ItemStack stackB) {
        return stackA.getItem() == stackB.getItem() && stackA.getMetadata() == stackB.getMetadata();
    }

    public static boolean compareCont(ItemStack stackA, ItemStack stackB) {
        return stackA.getCount() >= stackB.getCount();
    }

}
