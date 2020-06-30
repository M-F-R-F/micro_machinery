package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.enums.EnumToolTier;
import com.dbydd.micro_machinery.items.MMAxeBase;
import com.dbydd.micro_machinery.items.MMHammerBase;
import com.dbydd.micro_machinery.items.MMItemBase;
import com.dbydd.micro_machinery.items.MMSwordBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ToolType;

public class RegisteryedItems {

        public static final Item TESTITEM = new MMItemBase(new Item.Properties().group(ItemGroup.FOOD), "testitem");
//hammer
        public static final Item STONE_HAMMER = new MMHammerBase(4.0f,1.0f,EnumToolTier.STONE,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, EnumToolTier.STONE.getHarvestLevel()),"stone_hammer");
        public static final Item BRONZE_HAMMER = new MMHammerBase(5.0f,1.0f,EnumToolTier.BRONZE,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, EnumToolTier.BRONZE.getHarvestLevel()),"bronze_hammer");
        public static final Item IRON_HAMMER = new MMHammerBase(6.0f,1.0f,EnumToolTier.IRON,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, EnumToolTier.IRON.getHarvestLevel()),"iron_hammer");
//axe
        public static final Item BRONZE_AXE = new MMAxeBase(EnumToolTier.BRONZE,6,1.0f,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE,EnumToolTier.BRONZE.getHarvestLevel()),"bronze_axe");
//sword
        public static final Item BRONZE_SWORD = new MMSwordBase(EnumToolTier.BRONZE,2,1.5f,new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE,EnumToolTier.BRONZE.getHarvestLevel()),"bronze_sword");

    private RegisteryedItems() {

    }

    public static void Init() {

    }
}
