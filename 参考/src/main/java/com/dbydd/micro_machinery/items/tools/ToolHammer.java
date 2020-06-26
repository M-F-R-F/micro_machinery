package com.dbydd.micro_machinery.items.tools;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.item.ItemPickaxe;

public class ToolHammer extends ItemPickaxe implements IHasModel {

    public ToolHammer(ToolMaterial material, int maxDamageIn, String name) {
        super(material);
        setMaxDamage(maxDamageIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        attackDamage = 4.0f;
        setCreativeTab(Micro_Machinery.Micro_Machinery);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
