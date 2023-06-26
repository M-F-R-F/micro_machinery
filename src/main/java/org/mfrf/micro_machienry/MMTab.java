package org.mfrf.micro_machienry;

import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MMTab extends ItemGroup {
    public MMTab() {
        super("Micro_Machinery");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisteredItems.LOGO);
    }
}
