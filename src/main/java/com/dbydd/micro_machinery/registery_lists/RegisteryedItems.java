package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.enums.EnumToolTier;
import com.dbydd.micro_machinery.items.MMHammerBase;
import com.dbydd.micro_machinery.items.MMItemBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class RegisteryedItems {

        public static final Item TESTITEM = new MMItemBase(new Item.Properties().group(ItemGroup.FOOD), "testitem");
        public static final Item TESTHAMMER = new MMHammerBase(90000.0f, 1.0f, EnumToolTier.EXAMPLE,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, EnumToolTier.EXAMPLE.getHarvestLevel()), "test_hammer");


    private RegisteryedItems() {

    }

    public static void Init() {

    }
}
