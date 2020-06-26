package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.item.Item;

public class Laser_Drill extends Item implements IHasModel {
    public Laser_Drill() {
        setUnlocalizedName("laser_drill");
        setRegistryName("laser_drill");
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
