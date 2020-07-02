package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.enums.EnumToolTier;
import com.dbydd.micro_machinery.items.MMAxeBase;
import com.dbydd.micro_machinery.items.MMHammerBase;
import com.dbydd.micro_machinery.items.MMItemBase;
import com.dbydd.micro_machinery.items.MMSwordBase;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;

public class RegisteryedItems {

    public static final Item LOGO = new MMItemBase(new Item.Properties(), "micro_machinery_logo");
    //hammer
    public static final Item STONE_HAMMER = new MMHammerBase(4.0f, 1.0f, ItemTier.STONE, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, ItemTier.STONE.getHarvestLevel()), "stone_hammer");
    public static final Item BRONZE_HAMMER = new MMHammerBase(5.0f, 1.0f, ItemTier.IRON, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, ItemTier.IRON.getHarvestLevel()), "bronze_hammer");
    public static final Item STEEL_HAMMER = new MMHammerBase(6.0f, 1.0f, ItemTier.DIAMOND, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.PICKAXE, ItemTier.DIAMOND.getHarvestLevel()), "steel_hammer");
    //axe
    public static final Item BRONZE_AXE = new MMAxeBase(EnumToolTier.BRONZE, 6, 1.0f, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE, EnumToolTier.BRONZE.getHarvestLevel()), "bronze_axe");
    //sword
    public static final Item BRONZE_SWORD = new MMSwordBase(EnumToolTier.BRONZE, 2, 1.5f, new Item.Properties().group(Micro_Machinery.MMTAB), "bronze_sword");

    public static final Item TEST_FOOD = new MMItemBase(new Item.Properties(), "test_food", new Food(new Food.Builder().effect(() -> new EffectInstance(Effects.JUMP_BOOST, 2000, 50), 1.0f).hunger(20).setAlwaysEdible()));

    private RegisteryedItems() {

    }

    public static void Init() {

    }
}
