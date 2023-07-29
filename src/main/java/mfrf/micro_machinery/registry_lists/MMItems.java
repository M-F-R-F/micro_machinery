package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.enums.EnumToolTier;
import mfrf.micro_machinery.item.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMItems {
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,
            MicroMachinery.MODID);

    public static final RegistryObject<Item> ICON = ITEM_REGISTER.register("micro_machinery_logo", MMItemBase::new),
    // hammer
    STONE_HAMMER = ITEM_REGISTER.register("stone_hammer", () -> new MMHammerBase(4.0f, -3.1f, Tiers.STONE, new Item.Properties().stacksTo(1))),
            BRONZE_HAMMER = ITEM_REGISTER.register("bronze_hammer", () -> new MMHammerBase(5.0f, -3.15f, EnumToolTier.BRONZE, new Item.Properties().stacksTo(1))),
            IRON_HAMMER = ITEM_REGISTER.register("iron_hammer", () -> new MMHammerBase(6.0f, -3.2f, Tiers.DIAMOND, new Item.Properties().stacksTo(1))),
            SUPER_HAMMER = ITEM_REGISTER.register("super_hammer", () -> new MMHammerBase(8.0f, 0.0f, EnumToolTier.CARBIDE, new Item.Properties().stacksTo(1))),
            DEBUG = ITEM_REGISTER.register("debug", () -> new DebugTool()),
    //sword
    BRONZE_SWORD = ITEM_REGISTER.register("bronze_sword", () -> new MMSwordBase(EnumToolTier.BRONZE, 2, -2.4f, new Item.Properties().stacksTo(1))),
            SMA_SWORD = ITEM_REGISTER.register("sma_sword", () -> new MMSwordBase(Tiers.DIAMOND, 4, -2.6f, new Item.Properties().stacksTo(1))),
            CARBIDE_SWORD = ITEM_REGISTER.register("carbide_sword", () -> new MMSwordBase(EnumToolTier.CARBIDE, 7, -2.2f, new Item.Properties().stacksTo(1))),
    //food
    GOLDEN_APPLE_DROPS = ITEM_REGISTER.register("golden_apple_drops", () ->
            new MMItemBase(new Item.Properties(), new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
                    1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0),
                    1.0F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0),
                    1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 3),
                    1.0F).nutrition(3).alwaysEat().saturationMod(1).build())),
            GOLDEN_APPLE_JAM_BUN = ITEM_REGISTER.register("golden_apple_jam_bun", () ->
                    new MMItemBase(new Item.Properties(), new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
                            1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0),
                            1.0F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0),
                            1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 3),
                            1.0F).nutrition(10).saturationMod(1).build())),
            APPLE_DROPS = ITEM_REGISTER.register("apple_drops", () ->
                    new MMItemBase(new Item.Properties(), new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 1),
                            1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0),
                            1.0F).nutrition(2).alwaysEat().saturationMod(1).build())),
            APPLE_JAM_BUN = ITEM_REGISTER.register("apple_jam_bun", () ->
                    new MMItemBase(new Item.Properties(), new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 1),
                            1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0),
                            1.0F).nutrition(8).saturationMod(1).build())),

    // //prospector
    // PROSPECTOR = new ToolProspector(),
    // //drill
    // LASER_DRILL = new ItemLaserDrill("laser_drill"),

    // raw
    RAW_TIN = ITEM_REGISTER.register("raw_tin", MMItemBase::new),
            RAW_SILVER = ITEM_REGISTER.register("raw_silver", MMItemBase::new),
            RAW_NICKEL = ITEM_REGISTER.register("raw_nickel", MMItemBase::new),
            RAW_ALUMINUM = ITEM_REGISTER.register("raw_aluminum", MMItemBase::new),
            RAW_MANGANESE = ITEM_REGISTER.register("raw_manganese", MMItemBase::new),
            RAW_CHROMITE = ITEM_REGISTER.register("raw_chromite", MMItemBase::new),
            RAW_NOLANITE = ITEM_REGISTER.register("raw_nolanite", MMItemBase::new),
            RAW_ILMENITE = ITEM_REGISTER.register("raw_ilmenite", MMItemBase::new),
            RAW_TUNSTITE = ITEM_REGISTER.register("raw_tunstite", MMItemBase::new),
    // oreconcentrate
    ORE_COPPER_CONCENTRATE = ITEM_REGISTER.register("ore_copper_concentrate", MMItemBase::new),
            ORE_TIN_CONCENTRATE = ITEM_REGISTER.register("ore_tin_concentrate", MMItemBase::new),
            ORE_ILMENITE_CONCENTRATE = ITEM_REGISTER.register("ore_ilmenite_concentrate", MMItemBase::new),
            ORE_SILVER_CONCENTRATE = ITEM_REGISTER.register("ore_silver_concentrate", MMItemBase::new),
            ORE_MANGANESE_CONCENTRATE = ITEM_REGISTER.register("ore_manganese_concentrate", MMItemBase::new),
            ORE_IRON_CONCENTRATE = ITEM_REGISTER.register("ore_iron_concentrate", MMItemBase::new),
            ORE_GOLD_CONCENTRATE = ITEM_REGISTER.register("ore_gold_concentrate", MMItemBase::new),
            ORE_CHROMITE_CONCENTRATE = ITEM_REGISTER.register("ore_chromite_concentrate", MMItemBase::new),
            ORE_BAUXITE_CONCENTRATE = ITEM_REGISTER.register("ore_bauxite_concentrate", MMItemBase::new),
            ORE_NICKEL_CONCENTRATE = ITEM_REGISTER.register("ore_nickel_concentrate", MMItemBase::new),
            ORE_NOLANITE_CONCENTRATE = ITEM_REGISTER.register("ore_nolanite_concentrate", MMItemBase::new),
            ORE_TUNSTITE_CONCENTRATE = ITEM_REGISTER.register("ore_tunstite_concentrate", MMItemBase::new),
    // dust
    DUST_TIN = ITEM_REGISTER.register("dust_tin", MMItemBase::new),
            DUST_SILVER = ITEM_REGISTER.register("dust_silver", MMItemBase::new),
            DUST_NICKEL = ITEM_REGISTER.register("dust_nickel", MMItemBase::new),
            DUST_ALUMINUM = ITEM_REGISTER.register("dust_aluminum", MMItemBase::new),
            DUST_MANGANESE = ITEM_REGISTER.register("dust_manganese", MMItemBase::new),
            DUST_STEEL = ITEM_REGISTER.register("dust_steel", MMItemBase::new),
            DUST_CHROMIUM = ITEM_REGISTER.register("dust_chromium", MMItemBase::new),
            DUST_VANADIUM = ITEM_REGISTER.register("dust_vanadium", MMItemBase::new),
            DUST_COBALT = ITEM_REGISTER.register("dust_cobalt", MMItemBase::new),
            DUST_TITANIUM = ITEM_REGISTER.register("dust_titanium", MMItemBase::new),
            DUST_TUNGSTEN = ITEM_REGISTER.register("dust_tungsten", MMItemBase::new),
            DUST_BRONZE = ITEM_REGISTER.register("dust_bronze", MMItemBase::new),
            DUST_INVAR = ITEM_REGISTER.register("dust_invar", MMItemBase::new),
            DUST_SS = ITEM_REGISTER.register("dust_ss", MMItemBase::new),
            DUST_HSS = ITEM_REGISTER.register("dust_hss", MMItemBase::new),
            DUST_TUNGSTEN_STEEL = ITEM_REGISTER.register("dust_tungsten_steel", MMItemBase::new),
            DUST_SMA = ITEM_REGISTER.register("dust_sma", MMItemBase::new),
            DUST_CARBIDE = ITEM_REGISTER.register("dust_carbide", MMItemBase::new),
            DUST_SILICON = ITEM_REGISTER.register("dust_silicon", MMItemBase::new),
            DUST_CARBON = ITEM_REGISTER.register("dust_carbon", MMItemBase::new),
            DUST_COPPER = ITEM_REGISTER.register("dust_copper", MMItemBase::new),
            DUST_IRON = ITEM_REGISTER.register("dust_iron", MMItemBase::new),
            DUST_GOLD = ITEM_REGISTER.register("dust_gold", MMItemBase::new),
    // ingot
    INGOT_TIN = ITEM_REGISTER.register("ingot_tin", MMItemBase::new),
            INGOT_SILVER = ITEM_REGISTER.register("ingot_silver", MMItemBase::new),
            INGOT_NICKEL = ITEM_REGISTER.register("ingot_nickel", MMItemBase::new),
            INGOT_ALUMINUM = ITEM_REGISTER.register("ingot_aluminum", MMItemBase::new),
            INGOT_MANGANESE = ITEM_REGISTER.register("ingot_manganese", MMItemBase::new),
            INGOT_STEEL = ITEM_REGISTER.register("ingot_steel", MMItemBase::new),
            INGOT_CHROMIUM = ITEM_REGISTER.register("ingot_chromium", MMItemBase::new),
            INGOT_VANADIUM = ITEM_REGISTER.register("ingot_vanadium", MMItemBase::new),
            INGOT_COBALT = ITEM_REGISTER.register("ingot_cobalt", MMItemBase::new),
            INGOT_TITANIUM = ITEM_REGISTER.register("ingot_titanium", MMItemBase::new),
            INGOT_TUNGSTEN = ITEM_REGISTER.register("ingot_tungsten", MMItemBase::new),
            INGOT_BRONZE = ITEM_REGISTER.register("ingot_bronze", MMItemBase::new),
            INGOT_INVAR = ITEM_REGISTER.register("ingot_invar", MMItemBase::new),
            INGOT_SS = ITEM_REGISTER.register("ingot_ss", MMItemBase::new),
            INGOT_HSS = ITEM_REGISTER.register("ingot_hss", MMItemBase::new),
            INGOT_TUNGSTEN_STEEL = ITEM_REGISTER.register("ingot_tungsten_steel", MMItemBase::new),
            INGOT_SMA = ITEM_REGISTER.register("ingot_sma", MMItemBase::new),
            INGOT_CARBIDE = ITEM_REGISTER.register("ingot_carbide", MMItemBase::new),
            INGOT_GRAPHITE = ITEM_REGISTER.register("ingot_graphite", MMItemBase::new),
            HOT_INGOT_IRON = ITEM_REGISTER.register("hot_ingot_iron", MMItemBase::new),
            HOT_INGOT_STEEL = ITEM_REGISTER.register("hot_ingot_steel", MMItemBase::new),
    // plate
    PLATE_TIN = ITEM_REGISTER.register("plate_tin", MMItemBase::new),
            PLATE_SILVER = ITEM_REGISTER.register("plate_silver", MMItemBase::new),
            PLATE_NICKEL = ITEM_REGISTER.register("plate_nickel", MMItemBase::new),
            PLATE_ALUMINUM = ITEM_REGISTER.register("plate_aluminum", MMItemBase::new),
            PLATE_STEEL = ITEM_REGISTER.register("plate_steel", MMItemBase::new),
            PLATE_CHROMIUM = ITEM_REGISTER.register("plate_chromium", MMItemBase::new),
            PLATE_TITANIUM = ITEM_REGISTER.register("plate_titanium", MMItemBase::new),
            PLATE_BRONZE = ITEM_REGISTER.register("plate_bronze", MMItemBase::new),
            PLATE_INVAR = ITEM_REGISTER.register("plate_invar", MMItemBase::new),
            PLATE_SS = ITEM_REGISTER.register("plate_ss", MMItemBase::new),
            PLATE_HSS = ITEM_REGISTER.register("plate_hss", MMItemBase::new),
            PLATE_TUNGSTEN_STEEL = ITEM_REGISTER.register("plate_tungsten_steel", MMItemBase::new),
            PLATE_SMA = ITEM_REGISTER.register("plate_sma", MMItemBase::new),
            PLATE_CARBIDE = ITEM_REGISTER.register("plate_carbide", MMItemBase::new),
            PLATE_PE = ITEM_REGISTER.register("plate_pe", MMItemBase::new),
            PLATE_COPPER = ITEM_REGISTER.register("plate_copper", MMItemBase::new),
            PLATE_IRON = ITEM_REGISTER.register("plate_iron", MMItemBase::new),
            PLATE_GOLD = ITEM_REGISTER.register("plate_gold", MMItemBase::new),
    // stick
    STICK_TIN = ITEM_REGISTER.register("stick_tin", MMItemBase::new),
            STICK_SILVER = ITEM_REGISTER.register("stick_silver", MMItemBase::new),
            STICK_ALUMINUM = ITEM_REGISTER.register("stick_aluminum", MMItemBase::new),
            STICK_STEEL = ITEM_REGISTER.register("stick_steel", MMItemBase::new),
            STICK_TITANIUM = ITEM_REGISTER.register("stick_titanium", MMItemBase::new),
            STICK_BRONZE = ITEM_REGISTER.register("stick_bronze", MMItemBase::new),
            STICK_INVAR = ITEM_REGISTER.register("stick_invar", MMItemBase::new),
            STICK_SS = ITEM_REGISTER.register("stick_ss", MMItemBase::new),
            STICK_HSS = ITEM_REGISTER.register("stick_hss", MMItemBase::new),
            STICK_TUNGSTEN_STEEL = ITEM_REGISTER.register("stick_tungsten_steel", MMItemBase::new),
            STICK_COPPER = ITEM_REGISTER.register("stick_copper", MMItemBase::new),
            STICK_IRON = ITEM_REGISTER.register("stick_iron", MMItemBase::new),
            STICK_GOLD = ITEM_REGISTER.register("stick_gold", MMItemBase::new),
    // wire
    WIRE_TIN = ITEM_REGISTER.register("wire_tin", MMItemBase::new),
            WIRE_SILVER = ITEM_REGISTER.register("wire_silver", MMItemBase::new),
            WIRE_ALUMINUM = ITEM_REGISTER.register("wire_aluminum", MMItemBase::new),
            WIRE_STEEL = ITEM_REGISTER.register("wire_steel", MMItemBase::new),
            WIRE_TITANIUM = ITEM_REGISTER.register("wire_titanium", MMItemBase::new),
            WIRE_COPPER = ITEM_REGISTER.register("wire_copper", MMItemBase::new),
            WIRE_IRON = ITEM_REGISTER.register("wire_iron", MMItemBase::new),
            WIRE_GOLD = ITEM_REGISTER.register("wire_gold", MMItemBase::new),

    // circle casting
    CIRCLE_CASTING_SILVER = ITEM_REGISTER.register("circle_casting_silver", MMItemBase::new),
            CIRCLE_CASTING_NICKEL = ITEM_REGISTER.register("circle_casting_nickel", MMItemBase::new),
            CIRCLE_CASTING_ALUMINUM = ITEM_REGISTER.register("circle_casting_aluminum", MMItemBase::new),
            CIRCLE_CASTING_STEEL = ITEM_REGISTER.register("circle_casting_steel", MMItemBase::new),
            CIRCLE_CASTING_TITANIUM = ITEM_REGISTER.register("circle_casting_titanium", MMItemBase::new),
            CIRCLE_CASTING_BRONZE = ITEM_REGISTER.register("circle_casting_bronze", MMItemBase::new),
            CIRCLE_CASTING_INVAR = ITEM_REGISTER.register("circle_casting_invar", MMItemBase::new),
            CIRCLE_CASTING_SS = ITEM_REGISTER.register("circle_casting_ss", MMItemBase::new),
            CIRCLE_CASTING_HSS = ITEM_REGISTER.register("circle_casting_hss", MMItemBase::new),
            CIRCLE_CASTING_TUNGSTEN_STEEL = ITEM_REGISTER.register("circle_casting_tungsten_steel", MMItemBase::new),
            CIRCLE_CASTING_IRON = ITEM_REGISTER.register("circle_casting_iron", MMItemBase::new),
            CIRCLE_CASTING_GOLD = ITEM_REGISTER.register("circle_casting_gold", MMItemBase::new),
    // axle
    AXLE_STEEL = ITEM_REGISTER.register("axle_steel", MMItemBase::new),
            AXLE_INVAR = ITEM_REGISTER.register("axle_invar", MMItemBase::new),
            AXLE_SS = ITEM_REGISTER.register("axle_ss", MMItemBase::new),
    // gear
    GEAR_SILVER = ITEM_REGISTER.register("gear_silver", MMItemBase::new),
            GEAR_NICKEL = ITEM_REGISTER.register("gear_nickel", MMItemBase::new),
            GEAR_ALUMINUM = ITEM_REGISTER.register("gear_aluminum", MMItemBase::new),
            GEAR_STEEL = ITEM_REGISTER.register("gear_steel", MMItemBase::new),
            GEAR_TITANIUM = ITEM_REGISTER.register("gear_titanium", MMItemBase::new),
            GEAR_BRONZE = ITEM_REGISTER.register("gear_bronze", MMItemBase::new),
            GEAR_INVAR = ITEM_REGISTER.register("gear_invar", MMItemBase::new),
            GEAR_SS = ITEM_REGISTER.register("gear_ss", MMItemBase::new),
            GEAR_HSS = ITEM_REGISTER.register("gear_hss", MMItemBase::new),
            GEAR_TUNGSTEN_STEEL = ITEM_REGISTER.register("gear_tungsten_steel", MMItemBase::new),
            GEAR_IRON = ITEM_REGISTER.register("gear_iron", MMItemBase::new),
            GEAR_GOLD = ITEM_REGISTER.register("gear_gold", MMItemBase::new),
    // gear blank
    GEAR_BLANK_SILVER = ITEM_REGISTER.register("gear_blank_silver", MMItemBase::new),
            GEAR_BLANK_NICKEL = ITEM_REGISTER.register("gear_blank_nickel", MMItemBase::new),
            GEAR_BLANK_ALUMINUM = ITEM_REGISTER.register("gear_blank_aluminum", MMItemBase::new),
            GEAR_BLANK_STEEL = ITEM_REGISTER.register("gear_blank_steel", MMItemBase::new),
            GEAR_BLANK_TITANIUM = ITEM_REGISTER.register("gear_blank_titanium", MMItemBase::new),
            GEAR_BLANK_BRONZE = ITEM_REGISTER.register("gear_blank_bronze", MMItemBase::new),
            GEAR_BLANK_INVAR = ITEM_REGISTER.register("gear_blank_invar", MMItemBase::new),
            GEAR_BLANK_SS = ITEM_REGISTER.register("gear_blank_ss", MMItemBase::new),
            GEAR_BLANK_HSS = ITEM_REGISTER.register("gear_blank_hss", MMItemBase::new),
            GEAR_BLANK_TUNGSTEN_STEEL = ITEM_REGISTER.register("gear_blank_tungsten_steel", MMItemBase::new),
            GEAR_BLANK_IRON = ITEM_REGISTER.register("gear_blank_iron", MMItemBase::new),
            GEAR_BLANK_GOLD = ITEM_REGISTER.register("gear_blank_gold", MMItemBase::new),

    // slag
    SLAG = ITEM_REGISTER.register("slag", MMItemBase::new),
            SLAG_VANADIUM = ITEM_REGISTER.register("slag_vanadium", MMItemBase::new),
            SLAG_COBALT = ITEM_REGISTER.register("slag_cobalt", MMItemBase::new),
            SLAG_TITANIUM = ITEM_REGISTER.register("slag_titanium", MMItemBase::new),
            SLAG_CHROMIUM = ITEM_REGISTER.register("slag_chromium", MMItemBase::new),
            REFINED_SLAG_VANADIUM = ITEM_REGISTER.register("refined_slag_vanadium", MMItemBase::new),
            REFINED_SLAG_COBALT = ITEM_REGISTER.register("refined_slag_cobalt", MMItemBase::new),
            REFINED_SLAG_TITANIUM = ITEM_REGISTER.register("refined_slag_titanium", MMItemBase::new),
            REFINED_SLAG_CHROMIUM = ITEM_REGISTER.register("refined_slag_chromium", MMItemBase::new),
    // motor
    MOTOR_1 = ITEM_REGISTER.register("motor_1", MMItemBase::new),
            MOTOR_2 = ITEM_REGISTER.register("motor_2", MMItemBase::new),
            MOTOR_3 = ITEM_REGISTER.register("motor_3", MMItemBase::new),
    // flying_wheel
    FLYING_WHEEL_1 = ITEM_REGISTER.register("flying_wheel_1", MMItemBase::new),
            FLYING_WHEEL_2 = ITEM_REGISTER.register("flying_wheel_2", MMItemBase::new),
            FLYING_WHEEL_3 = ITEM_REGISTER.register("flying_wheel_3", MMItemBase::new),
    //
    MONOCRYSTALLINE_SILCON = ITEM_REGISTER.register("monocrystalline_silicon", MMItemBase::new),
            WAFER = ITEM_REGISTER.register("wafer", MMItemBase::new),
            MICRO_CHIP = ITEM_REGISTER.register("micro_chip", MMItemBase::new),
            COMPONENT_BASIC = ITEM_REGISTER.register("component_basic", MMItemBase::new),
            COMPONENT_ADVANCED = ITEM_REGISTER.register("component_advanced", MMItemBase::new),
            LASER = ITEM_REGISTER.register("laser", MMItemBase::new),
            PLASMA = ITEM_REGISTER.register("plasma", MMItemBase::new),
    //rotor
    ROTOR_STEEL = ITEM_REGISTER.register("rotor_steel", MMItemBase::new),
            ROTOR_TITANIUM = ITEM_REGISTER.register("rotor_titanium", MMItemBase::new),
            ROTOR_INVAR = ITEM_REGISTER.register("rotor_invar", MMItemBase::new),
            ROTOR_SS = ITEM_REGISTER.register("rotor_ss", MMItemBase::new),
            ROTOR_HSS = ITEM_REGISTER.register("rotor_hss", MMItemBase::new),
            ROTOR_TUNGSTEN_STEEL = ITEM_REGISTER.register("rotor_tungsten_steel", MMItemBase::new),
            BLADE_STEEL = ITEM_REGISTER.register("blade_steel", MMItemBase::new),
            BLADE_TITANIUM = ITEM_REGISTER.register("blade_titanium", MMItemBase::new),
            BLADE_INVAR = ITEM_REGISTER.register("blade_invar", MMItemBase::new),
            BLADE_SS = ITEM_REGISTER.register("blade_ss", MMItemBase::new),
            BLADE_HSS = ITEM_REGISTER.register("blade_hss", MMItemBase::new),
            BLADE_TUNGSTEN_STEEL = ITEM_REGISTER.register("blade_tungsten_steel", MMItemBase::new),

    // other
    FIRE_BRICK = ITEM_REGISTER.register("fire_brick", MMItemBase::new),
            ADAPTER_CARD = ITEM_REGISTER.register("adapter_card", MMItemBase::new),
            FLYWHEEL_BATTERY = ITEM_REGISTER.register("flywheel_battery", MMItemBase::new),
            DSB = ITEM_REGISTER.register("dsb", () -> new SawBladeBase(Config.DSB_EFFICIENCY));

    // TEST_UTIL = new DebugTool;

}
