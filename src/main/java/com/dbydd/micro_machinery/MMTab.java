package com.dbydd.micro_machinery;

import com.dbydd.micro_machinery.registery_lists.RegisteryedItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MMTab extends ItemGroup {
    public MMTab() {
        super("Micro_Machinery");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisteryedItems.LOGO);
    }
}
