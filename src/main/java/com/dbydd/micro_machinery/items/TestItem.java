package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.item.Item;

public class TestItem extends Item implements IHasModel {
    public TestItem() {
        setUnlocalizedName("testitem");
        setRegistryName("testitem");
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
            Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
