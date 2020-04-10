package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.energy.IEnergyStorage;

public class Lazer_Drill extends Item implements IHasModel {
    public Lazer_Drill() {
        setUnlocalizedName("lazer_drill");
        setRegistryName("lazer_drill");
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
            Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
