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
    public static final Item BRONZE_AXE = new MMAxeBase(EnumToolTier.BRONZE, 1, 1.0f, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE, EnumToolTier.BRONZE.getHarvestLevel()), "bronze_axe");
    public static final Item TUNGSTEN_STEEL_AXE = new MMAxeBase(ItemTier.DIAMOND, 2, 1.0f, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE, ItemTier.DIAMOND.getHarvestLevel()), "tungsten_steel_axe");
    public static final Item HSS_AXE = new MMAxeBase(EnumToolTier.HSS, 4, 1.0f, new Item.Properties().group(Micro_Machinery.MMTAB).addToolType(ToolType.AXE, EnumToolTier.HSS.getHarvestLevel()), "hss_axe");
    //sword
    public static final Item BRONZE_SWORD = new MMSwordBase(EnumToolTier.BRONZE, 2, 1.5f, new Item.Properties().group(Micro_Machinery.MMTAB), "bronze_sword");
    public static final Item TUNGSTEN_STEEL_SWORD = new MMSwordBase(ItemTier.DIAMOND, 4, 1.2f, new Item.Properties().group(Micro_Machinery.MMTAB), "tungsten_steel_sword");
    public static final Item HSS_SWORD = new MMSwordBase(EnumToolTier.HSS, 7, 2.5f, new Item.Properties().group(Micro_Machinery.MMTAB), "hss_sword");
    //food
    public static final Item TEST_FOOD = new MMItemBase(new Item.Properties(), "test_food", new Food.Builder().effect(() -> new EffectInstance(Effects.JUMP_BOOST, 2000, 50), 1.0f).hunger(20).setAlwaysEdible().build());


    //drill
    public static final Item LASER_DRILL = new MMItemBase("laser_drill");
    //ingot
    public static final Item INGOT_COPPER = new MMItemBase("ingot_copper");
    public static final Item INGOT_TIN = new MMItemBase("ingot_tin");
    public static final Item INGOT_BRONZE = new MMItemBase("ingot_bronze");
    public static final Item INGOT_STEEL = new MMItemBase("ingot_steel");
    public static final Item INGOT_INVAR = new MMItemBase("ingot_invar");
    public static final Item INGOT_SS = new MMItemBase("ingot_ss");
    public static final Item INGOT_TUNGSTEN = new MMItemBase("ingot_tungsten");
    public static final Item INGOT_TUNGSTEN_STEEL = new MMItemBase("ingot_tungsten_steel");
    public static final Item INGOT_HSS = new MMItemBase("ingot_hss");
    public static final Item INGOT_SILVER = new MMItemBase("ingot_silver");
    public static final Item INGOT_MANGANESE = new MMItemBase("ingot_manganese");
    public static final Item INGOT_CHROMIUM = new MMItemBase("ingot_chromium");
    public static final Item INGOT_NICKEL = new MMItemBase("ingot_nickel");
    public static final Item INGOT_VANADIUM = new MMItemBase("ingot_vanadium");
    public static final Item INGOT_COBALT = new MMItemBase("ingot_cobalt");
    public static final Item INGOT_TITANIUM = new MMItemBase("ingot_titanium");
    public static final Item INGOT_ALUMINUM = new MMItemBase("ingot_aluminum");
    public static final Item INGOT_NCALLOY = new MMItemBase("ingot_ncalloy");
    public static final Item INGOT_SILICON = new MMItemBase("ingot_silicon");
    public static final Item INGOT_GRAPHITE = new MMItemBase("ingot_graphite");
    //dust
    public static final Item DUST_COPPER = new MMItemBase("dust_copper");
    public static final Item DUST_TIN = new MMItemBase("dust_tin");
    public static final Item DUST_BRONZE = new MMItemBase("dust_bronze");
    public static final Item DUST_STEEL = new MMItemBase("dust_steel");
    public static final Item DUST_INVAR = new MMItemBase("dust_invar");
    public static final Item DUST_SS = new MMItemBase("dust_ss");
    public static final Item DUST_TUNGSTEN = new MMItemBase("dust_tungsten");
    public static final Item DUST_TUNGSTEN_STEEL = new MMItemBase("dust_tungsten_steel");
    public static final Item DUST_HSS = new MMItemBase("dust_hss");
    public static final Item DUST_GOLD = new MMItemBase("dust_gold");
    public static final Item DUST_SILVER = new MMItemBase("dust_silver");
    public static final Item DUST_MANGANESE = new MMItemBase("dust_manganese");
    public static final Item DUST_CHROMIUM = new MMItemBase("dust_chromium");
    public static final Item DUST_NICKEL = new MMItemBase("dust_nickel");
    public static final Item DUST_VANADIUM = new MMItemBase("dust_vanadium");
    public static final Item DUST_COBALT = new MMItemBase("dust_cobalt");
    public static final Item DUST_TITANIUM = new MMItemBase("dust_titanium");
    public static final Item DUST_ALUMINUM = new MMItemBase("dust_aluminum");
    public static final Item DUST_NCALLOY = new MMItemBase("dust_ncalloy");
    public static final Item DUST_SILICON = new MMItemBase("dust_silicon");
    public static final Item DUST_IRON = new MMItemBase("dust_iron");
    public static final Item DUST_CARBON = new MMItemBase("dust_carbon");
    public static final Item DUST_FERROCHROME = new MMItemBase("dust_ferrochrome");
    //stick
    public static final Item STICK_COPPER = new MMItemBase("stick_copper");
    public static final Item STICK_TIN = new MMItemBase("stick_tin");
    public static final Item STICK_BRONZE = new MMItemBase("stick_bronze");
    public static final Item STICK_STEEL = new MMItemBase("stick_steel");
    public static final Item STICK_INVAR = new MMItemBase("stick_invar");
    public static final Item STICK_SS = new MMItemBase("stick_ss");
    public static final Item STICK_TUNGSTEN_STEEL = new MMItemBase("stick_tungsten_steel");
    public static final Item STICK_HSS = new MMItemBase("stick_hss");
    public static final Item STICK_GOLD = new MMItemBase("stick_gold");
    public static final Item STICK_TITANIUM = new MMItemBase("stick_titanium");
    public static final Item STICK_ALUMINUM = new MMItemBase("stick_aluminum");
    public static final Item STICK_IRON = new MMItemBase("stick_iron");
    //circle casting
    public static final Item CIRCLE_CASTING_BRONZE = new MMItemBase("circle_casting_bronze");
    public static final Item CIRCLE_CASTING_STEEL = new MMItemBase("circle_casting_steel");
    public static final Item CIRCLE_CASTING_IRON = new MMItemBase("circle_casting_iron");
    public static final Item CIRCLE_CASTING_SILVER = new MMItemBase("circle_casting_silver");
    public static final Item CIRCLE_CASTING_INVAR = new MMItemBase("circle_casting_invar");
    public static final Item CIRCLE_CASTING_GOLD = new MMItemBase("circle_casting_gold");
    public static final Item CIRCLE_CASTING_ALUMINUM = new MMItemBase("circle_casting_aluminum");
    public static final Item CIRCLE_CASTING_NICKEL = new MMItemBase("circle_casting_nickel");
    public static final Item CIRCLE_CASTING_SS = new MMItemBase("circle_casting_ss");
    public static final Item CIRCLE_CASTING_TUNGSTEN_STEEL = new MMItemBase("circle_casting_tungsten_steel");
    public static final Item CIRCLE_CASTING_TITANIUM = new MMItemBase("circle_casting_titanium");
    //plate
    public static final Item PLATE_COPPER = new MMItemBase("plate_copper");
    public static final Item PLATE_TIN = new MMItemBase("plate_tin");
    public static final Item PLATE_BRONZE = new MMItemBase("plate_bronze");
    public static final Item PLATE_STEEL = new MMItemBase("plate_steel");
    public static final Item PLATE_INVAR = new MMItemBase("plate_invar");
    public static final Item PLATE_SS = new MMItemBase("plate_ss");
    public static final Item PLATE_TUNGSTEN_STEEL = new MMItemBase("plate_tungsten_steel");
    public static final Item PLATE_HSS = new MMItemBase("plate_hss");
    public static final Item PLATE_GOLD = new MMItemBase("plate_gold");
    public static final Item PLATE_SILVER = new MMItemBase("plate_silver");
    public static final Item PLATE_CHROMIUM = new MMItemBase("plate_chromium");
    public static final Item PLATE_NICKEL = new MMItemBase("plate_nickel");
    public static final Item PLATE_TITANIUM = new MMItemBase("plate_titanium");
    public static final Item PLATE_ALUMINUM = new MMItemBase("plate_aluminum");
    public static final Item PLATE_NCALLOY = new MMItemBase("plate_ncalloy");
    public static final Item PLATE_SILICON = new MMItemBase("plate_silicon");
    public static final Item PLATE_IRON = new MMItemBase("plate_iron");
    //screw
    public static final Item SCREW_BRONZE = new MMItemBase("screw_bronze");
    public static final Item SCREW_INVAR = new MMItemBase("screw_invar");
    public static final Item SCREW_SS = new MMItemBase("screw_ss");
    public static final Item SCREW_TITANIUM = new MMItemBase("screw_titanium");
    public static final Item SCREW_IRON = new MMItemBase("screw_iron");
    //string
    public static final Item STRING_COPPER = new MMItemBase("string_copper");
    public static final Item STRING_TIN = new MMItemBase("string_tin");
    public static final Item STRING_TUNGSTEN = new MMItemBase("string_tungsten");
    public static final Item STRING_SILVER = new MMItemBase("string_silver");
    public static final Item STRING_NICKEL = new MMItemBase("string_nickel");
    public static final Item STRING_COBALT = new MMItemBase("string_cobalt");
    public static final Item STRING_ALUMINUM = new MMItemBase("string_aluminum");
    public static final Item STRING_NCALLOY = new MMItemBase("string_ncalloy");
    public static final Item STRING_GOLD = new MMItemBase("string_gold");
    public static final Item STRING_IRON = new MMItemBase("string_iron");
    //roll
    public static final Item ROLL_INVAR = new MMItemBase("roll_invar");
    public static final Item ROLL_SS = new MMItemBase("roll_ss");
    public static final Item ROLL_TUNGSTEN_STEEL = new MMItemBase("roll_tungsten_steel");
    public static final Item ROLL_HSS = new MMItemBase("roll_hss");
    public static final Item ROLL_IRON = new MMItemBase("roll_iron");
    //slag
    public static final Item SLAG = new MMItemBase("slag");
    public static final Item SLAG_MANGANESE = new MMItemBase("slag_manganese");
    public static final Item SLAG_VANADIUM = new MMItemBase("slag_vanadium");
    public static final Item SLAG_COBALT = new MMItemBase("slag_cobalt");
    public static final Item SLAG_TITANIUM = new MMItemBase("slag_titanium");
    public static final Item REFINED_SLAG_MANGANESE = new MMItemBase("refined_slag_manganese");
    public static final Item REFINED_SLAG_VANADIUM = new MMItemBase("refined_slag_vanadium");
    public static final Item REFINED_SLAG_COBALT = new MMItemBase("refined_slag_cobalt");
    public static final Item REFINED_SLAG_TITANIUM = new MMItemBase("refined_slag_titanium");
    //axis
    public static final Item AXIS_BRONZE = new MMItemBase("axis_bronze");
    public static final Item AXIS_INVAR = new MMItemBase("axis_invar");
    public static final Item AXIS_SS = new MMItemBase("axis_ss");
    public static final Item AXIS_TUNGSTEN_STEEL = new MMItemBase("axis_tungsten_steel");
    public static final Item AXIS_HSS = new MMItemBase("axis_hss");
    public static final Item AXIS_STEEL = new MMItemBase("axis_steel");
    public static final Item AXIS_TITANIUM = new MMItemBase("axis_titanium");
    public static final Item AXIS_IRON = new MMItemBase("axis_iron");
    //gear blank
    public static final Item GEAR_BLANK_BRONZE = new MMItemBase("gear_blank_bronze");
    public static final Item GEAR_BLANK_INVAR = new MMItemBase("gear_blank_invar");
    public static final Item GEAR_BLANK_SS = new MMItemBase("gear_blank_ss");
    public static final Item GEAR_BLANK_TUNGSTEN_STEEL = new MMItemBase("gear_blank_tungsten_steel");
    public static final Item GEAR_BLANK_SILVER = new MMItemBase("gear_blank_silver");
    public static final Item GEAR_BLANK_NICKEL = new MMItemBase("gear_blank_nickel");
    public static final Item GEAR_BLANK_TITANIUM = new MMItemBase("gear_blank_titanium");
    public static final Item GEAR_BLANK_ALUMINUM = new MMItemBase("gear_blank_aluminum");
    public static final Item GEAR_BLANK_IRON = new MMItemBase("gear_blank_iron");
    //gear
    public static final Item GEAR_BRONZE = new MMItemBase("gear_bronze");
    public static final Item GEAR_INVAR = new MMItemBase("gear_invar");
    public static final Item GEAR_SS = new MMItemBase("gear_ss");
    public static final Item GEAR_TUNGSTEN_STEEL = new MMItemBase("gear_tungsten_steel");
    public static final Item GEAR_SILVER = new MMItemBase("gear_silver");
    public static final Item GEAR_NICKEL = new MMItemBase("gear_nickel");
    public static final Item GEAR_TITANIUM = new MMItemBase("gear_titanium");
    public static final Item GEAR_ALUMINUM = new MMItemBase("gear_aluminum");
    public static final Item GEAR_IRON = new MMItemBase("gear_iron");
    //crushed ore
    public static final Item CRUSHED_COPPER = new MMItemBase("crushed_copper");
    public static final Item CRUSHED_TIN = new MMItemBase("crushed_tin");
    public static final Item CRUSHED_ILMENITE = new MMItemBase("crushed_ilmenite");
    public static final Item CRUSHED_SILVER = new MMItemBase("crushed_silver");
    public static final Item CRUSHED_PYROLUSITE = new MMItemBase("crushed_pyrolusite");
    public static final Item CRUSHED_GOLD = new MMItemBase("crushed_gold");
    public static final Item CRUSHED_CHROMITE = new MMItemBase("crushed_chromite");
    public static final Item CRUSHED_BAUXITE = new MMItemBase("crushed_bauxite");
    public static final Item CRUSHED_FERROMANGANESE = new MMItemBase("crushed_ferromanganese");
    public static final Item CRUSHED_NICKEL = new MMItemBase("crushed_nickel");
    public static final Item CRUSHED_NOLANITE = new MMItemBase("crushed_nolanite");
    public static final Item CRUSHED_TUNSTITE = new MMItemBase("crushed_tunstite");
    public static final Item CRUSHED_IRON = new MMItemBase("crushed_iron");
    //motor
    public static final Item MOTOR1 = new MMItemBase("motor1");
    public static final Item MOTOR2 = new MMItemBase("motor2");
    public static final Item MOTOR3 = new MMItemBase("motor3");
    public static final Item MOTOR4 = new MMItemBase("motor4");
    public static final Item MOTOR5 = new MMItemBase("motor5");
    //oreconcentrate
    public static final Item ORE_COPPER_CONCENTRATE = new MMItemBase("ore_copper_concentrate");
    public static final Item ORE_TIN_CONCENTRATE = new MMItemBase("ore_tin_concentrate");
    public static final Item ORE_ILMENITE_CONCENTRATE = new MMItemBase("ore_ilmenite_concentrate");
    public static final Item ORE_SILVER_CONCENTRATE = new MMItemBase("ore_silver_concentrate");
    public static final Item ORE_PYROLUSITE_CONCENTRATE = new MMItemBase("ore_pyrolusite_concentrate");
    public static final Item ORE_IRON_CONCENTRATE = new MMItemBase("ore_iron_concentrate");
    public static final Item ORE_GOLD_CONCENTRATE = new MMItemBase("ore_gold_concentrate");
    public static final Item ORE_CHROMITE_CONCENTRATE = new MMItemBase("ore_chromite_concentrate");
    public static final Item ORE_BAUXITE_CONCENTRATE = new MMItemBase("ore_bauxite_concentrate");
    public static final Item ORE_FERROMANGANESE_CONCENTRATE = new MMItemBase("ore_ferromanganese_concentrate");
    public static final Item ORE_NICKEL_CONCENTRATE = new MMItemBase("ore_nickel_concentrate");
    public static final Item ORE_NOLANITE_CONCENTRATE = new MMItemBase("ore_nolanite_concentrate");
    public static final Item ORE_TUNSTITE_CONCENTRATE = new MMItemBase("ore_tunstite_concentrate");
    //casts
    public static final Item CAST_INGOT = new MMItemBase("cast_ingot");
    public static final Item CAST_STICK = new MMItemBase("cast_stick");
    public static final Item CAST_GEAR = new MMItemBase("cast_gear");
    public static final Item CAST_SWORD = new MMItemBase("cast_sword");
    //other
    public static final Item MONOCRYSTALLINE_SILICON = new MMItemBase("monocrystalline_silicon");
    public static final Item WAFER = new MMItemBase("wafer");
    public static final Item ETCHED_WAFER = new MMItemBase("etched_wafer");
    public static final Item I7_8700K = new MMItemBase("i7_8700k");
    public static final Item PLATE_PE = new MMItemBase("plate_pe");
    public static final Item PCB = new MMItemBase("pcb");
    public static final Item CAPACITOR = new MMItemBase("capacitor");
    public static final Item RESISTOR = new MMItemBase("resistor");
    public static final Item LASER = new MMItemBase("laser");
    public static final Item CLAY_BRICK = new MMItemBase("clay_brick");
    public static final Item FIRE_BRICK = new MMItemBase("fire_brick");
    public static final Item PLASMA_GENERATOR = new MMItemBase("plasma_generator");
    public static final Item DSB = new MMItemBase("dsb");
    public static final Item HSB = new MMItemBase("hsb");
    public static final Item BRICK_POWDER = new MMItemBase("brick_powder");
    public static final Item CLAY_MIXED_WITH_CLINKER = new MMItemBase("clay_mixed_with_clinker");
    public static final Item BRONZE_SWORD0 = new MMItemBase("bronze_sword0");
    public static final Item HSS_ADDITIVE = new MMItemBase("HSS_Additive");
    public static final Item SS_ADDITIVE = new MMItemBase("SS_Additive");

    private RegisteryedItems() {
    }

    public static void Init() {
    }
}
