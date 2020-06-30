package com.dbydd.micro_machinery.groups;

import com.dbydd.micro_machinery.registery_lists.RegisteryedItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MMTAB extends ItemGroup {
    public MMTAB() {
        super("Micro_Machinery:Blocks");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisteryedItems.TESTITEM);
    }
}
