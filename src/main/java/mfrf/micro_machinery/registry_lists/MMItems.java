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
            SUPERHAMMER = ITEM_REGISTER.register("superhammer", () -> new MMHammerBase(8.0f, 0.0f, EnumToolTier.HSS, new Item.Properties().stacksTo(1))),
            DEBUG = ITEM_REGISTER.register("debug",() -> new DebugTool()),
    //axe
    BRONZE_AXE = ITEM_REGISTER.register("bronze_axe", () -> new MMAxeBase(EnumToolTier.BRONZE, 1, -3.1f, new Item.Properties().stacksTo(1))),
            TUNGSTEN_STEEL_AXE = ITEM_REGISTER.register("tungsten_steel_axe", () -> new MMAxeBase(Tiers.DIAMOND, 2, -3.1f, new Item.Properties().stacksTo(1))),
            HSS_AXE = ITEM_REGISTER.register("hss_axe", () -> new MMAxeBase(EnumToolTier.HSS, 4, -3.0f, new Item.Properties().stacksTo(1))),
    //sword
    BRONZE_SWORD = ITEM_REGISTER.register("bronze_sword", () -> new MMSwordBase(EnumToolTier.BRONZE, 2, -2.4f, new Item.Properties().stacksTo(1))),
            TUNGSTEN_STEEL_SWORD = ITEM_REGISTER.register("tungsten_steel_sword", () -> new MMSwordBase(Tiers.DIAMOND, 4, -2.6f, new Item.Properties().stacksTo(1))),
            HSS_SWORD = ITEM_REGISTER.register("hss_sword", () -> new MMSwordBase(EnumToolTier.HSS, 7, -2.2f, new Item.Properties().stacksTo(1))),
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

    // ingot
    INGOT_COPPER = ITEM_REGISTER.register("ingot_copper", MMItemBase::new),
            INGOT_TIN = ITEM_REGISTER.register("ingot_tin", MMItemBase::new),
            INGOT_BRONZE = ITEM_REGISTER.register("ingot_bronze", MMItemBase::new),
            INGOT_STEEL = ITEM_REGISTER.register("ingot_steel", MMItemBase::new),
            INGOT_INVAR = ITEM_REGISTER.register("ingot_invar", MMItemBase::new),
            INGOT_SS = ITEM_REGISTER.register("ingot_ss", MMItemBase::new),
            INGOT_TUNGSTEN = ITEM_REGISTER.register("ingot_tungsten", MMItemBase::new),
            INGOT_TUNGSTEN_STEEL = ITEM_REGISTER.register("ingot_tungsten_steel", MMItemBase::new),
            INGOT_HSS = ITEM_REGISTER.register("ingot_hss", MMItemBase::new),
            INGOT_SILVER = ITEM_REGISTER.register("ingot_silver", MMItemBase::new),
            INGOT_MANGANESE = ITEM_REGISTER.register("ingot_manganese", MMItemBase::new),
            INGOT_CHROMIUM = ITEM_REGISTER.register("ingot_chromium", MMItemBase::new),
            INGOT_NICKEL = ITEM_REGISTER.register("ingot_nickel", MMItemBase::new),
            INGOT_VANADIUM = ITEM_REGISTER.register("ingot_vanadium", MMItemBase::new),
            INGOT_COBALT = ITEM_REGISTER.register("ingot_cobalt", MMItemBase::new),
            INGOT_TITANIUM = ITEM_REGISTER.register("ingot_titanium", MMItemBase::new),
            INGOT_ALUMINUM = ITEM_REGISTER.register("ingot_aluminum", MMItemBase::new),
            INGOT_NCALLOY = ITEM_REGISTER.register("ingot_ncalloy", MMItemBase::new),
            INGOT_SILICON = ITEM_REGISTER.register("ingot_silicon", MMItemBase::new),
            INGOT_GRAPHITE = ITEM_REGISTER.register("ingot_graphite", MMItemBase::new),
    // dust
    DUST_COPPER = ITEM_REGISTER.register("dust_copper", MMItemBase::new),
            DUST_TIN = ITEM_REGISTER.register("dust_tin", MMItemBase::new),
            DUST_BRONZE = ITEM_REGISTER.register("dust_bronze", MMItemBase::new),
            DUST_STEEL = ITEM_REGISTER.register("dust_steel", MMItemBase::new),
            DUST_INVAR = ITEM_REGISTER.register("dust_invar", MMItemBase::new),
            DUST_SS = ITEM_REGISTER.register("dust_ss", MMItemBase::new),
            DUST_TUNGSTEN = ITEM_REGISTER.register("dust_tungsten", MMItemBase::new),
            DUST_TUNGSTEN_STEEL = ITEM_REGISTER.register("dust_tungsten_steel", MMItemBase::new),
            DUST_HSS = ITEM_REGISTER.register("dust_hss", MMItemBase::new),
            DUST_GOLD = ITEM_REGISTER.register("dust_gold", MMItemBase::new),
            DUST_SILVER = ITEM_REGISTER.register("dust_silver", MMItemBase::new),
            DUST_MANGANESE = ITEM_REGISTER.register("dust_manganese", MMItemBase::new),
            DUST_CHROMIUM = ITEM_REGISTER.register("dust_chromium", MMItemBase::new),
            DUST_NICKEL = ITEM_REGISTER.register("dust_nickel", MMItemBase::new),
            DUST_VANADIUM = ITEM_REGISTER.register("dust_vanadium", MMItemBase::new),
            DUST_COBALT = ITEM_REGISTER.register("dust_cobalt", MMItemBase::new),
            DUST_TITANIUM = ITEM_REGISTER.register("dust_titanium", MMItemBase::new),
            DUST_ALUMINUM = ITEM_REGISTER.register("dust_aluminum", MMItemBase::new),
            DUST_NCALLOY = ITEM_REGISTER.register("dust_ncalloy", MMItemBase::new),
            DUST_SILICON = ITEM_REGISTER.register("dust_silicon", MMItemBase::new),
            DUST_IRON = ITEM_REGISTER.register("dust_iron", MMItemBase::new),
            DUST_CARBON = ITEM_REGISTER.register("dust_carbon", MMItemBase::new),
            DUST_FERROCHROME = ITEM_REGISTER.register("dust_ferrochrome", MMItemBase::new),
    // stick
    STICK_COPPER = ITEM_REGISTER.register("stick_copper", MMItemBase::new),
            STICK_TIN = ITEM_REGISTER.register("stick_tin", MMItemBase::new),
            STICK_BRONZE = ITEM_REGISTER.register("stick_bronze", MMItemBase::new),
            STICK_STEEL = ITEM_REGISTER.register("stick_steel", MMItemBase::new),
            STICK_INVAR = ITEM_REGISTER.register("stick_invar", MMItemBase::new),
            STICK_SS = ITEM_REGISTER.register("stick_ss", MMItemBase::new),
            STICK_TUNGSTEN_STEEL = ITEM_REGISTER.register("stick_tungsten_steel", MMItemBase::new),
            STICK_HSS = ITEM_REGISTER.register("stick_hss", MMItemBase::new),
            STICK_GOLD = ITEM_REGISTER.register("stick_gold", MMItemBase::new),
            STICK_TITANIUM = ITEM_REGISTER.register("stick_titanium", MMItemBase::new),
            STICK_ALUMINUM = ITEM_REGISTER.register("stick_aluminum", MMItemBase::new),
            STICK_IRON = ITEM_REGISTER.register("stick_iron", MMItemBase::new),
    // circle casting
    CIRCLE_CASTING_BRONZE = ITEM_REGISTER.register("circle_casting_bronze", MMItemBase::new),
            CIRCLE_CASTING_STEEL = ITEM_REGISTER.register("circle_casting_steel", MMItemBase::new),
            CIRCLE_CASTING_IRON = ITEM_REGISTER.register("circle_casting_iron", MMItemBase::new),
            CIRCLE_CASTING_SILVER = ITEM_REGISTER.register("circle_casting_silver", MMItemBase::new),
            CIRCLE_CASTING_INVAR = ITEM_REGISTER.register("circle_casting_invar", MMItemBase::new),
            CIRCLE_CASTING_GOLD = ITEM_REGISTER.register("circle_casting_gold", MMItemBase::new),
            CIRCLE_CASTING_ALUMINUM = ITEM_REGISTER.register("circle_casting_aluminum", MMItemBase::new),
            CIRCLE_CASTING_NICKEL = ITEM_REGISTER.register("circle_casting_nickel", MMItemBase::new),
            CIRCLE_CASTING_SS = ITEM_REGISTER.register("circle_casting_ss", MMItemBase::new),
            CIRCLE_CASTING_TUNGSTEN_STEEL = ITEM_REGISTER.register("circle_casting_tungsten_steel",
                    MMItemBase::new),
            CIRCLE_CASTING_TITANIUM = ITEM_REGISTER.register("circle_casting_titanium", MMItemBase::new),
            CIRCLE_CASTING_HSS = ITEM_REGISTER.register("circle_casting_hss", MMItemBase::new),
    // plate
    PLATE_COPPER = ITEM_REGISTER.register("plate_copper", MMItemBase::new),
            PLATE_TIN = ITEM_REGISTER.register("plate_tin", MMItemBase::new),
            PLATE_BRONZE = ITEM_REGISTER.register("plate_bronze", MMItemBase::new),
            PLATE_STEEL = ITEM_REGISTER.register("plate_steel", MMItemBase::new),
            PLATE_INVAR = ITEM_REGISTER.register("plate_invar", MMItemBase::new),
            PLATE_SS = ITEM_REGISTER.register("plate_ss", MMItemBase::new),
            PLATE_TUNGSTEN_STEEL = ITEM_REGISTER.register("plate_tungsten_steel", MMItemBase::new),
            PLATE_HSS = ITEM_REGISTER.register("plate_hss", MMItemBase::new),
            PLATE_GOLD = ITEM_REGISTER.register("plate_gold", MMItemBase::new),
            PLATE_SILVER = ITEM_REGISTER.register("plate_silver", MMItemBase::new),
            PLATE_CHROMIUM = ITEM_REGISTER.register("plate_chromium", MMItemBase::new),
            PLATE_NICKEL = ITEM_REGISTER.register("plate_nickel", MMItemBase::new),
            PLATE_TITANIUM = ITEM_REGISTER.register("plate_titanium", MMItemBase::new),
            PLATE_ALUMINUM = ITEM_REGISTER.register("plate_aluminum", MMItemBase::new),
            PLATE_NCALLOY = ITEM_REGISTER.register("plate_ncalloy", MMItemBase::new),
            PLATE_SILICON = ITEM_REGISTER.register("plate_silicon", MMItemBase::new),
            PLATE_IRON = ITEM_REGISTER.register("plate_iron", MMItemBase::new),
            PLATE_PE = ITEM_REGISTER.register("plate_pe", MMItemBase::new),
    // screw
    SCREW_BRONZE = ITEM_REGISTER.register("screw_bronze", MMItemBase::new),
            SCREW_INVAR = ITEM_REGISTER.register("screw_invar", MMItemBase::new),
            SCREW_SS = ITEM_REGISTER.register("screw_ss", MMItemBase::new),
            SCREW_TITANIUM = ITEM_REGISTER.register("screw_titanium", MMItemBase::new),
            SCREW_IRON = ITEM_REGISTER.register("screw_iron", MMItemBase::new),
    // string
    STRING_COPPER = ITEM_REGISTER.register("string_copper", MMItemBase::new),
            STRING_TIN = ITEM_REGISTER.register("string_tin", MMItemBase::new),
            STRING_TUNGSTEN = ITEM_REGISTER.register("string_tungsten", MMItemBase::new),
            STRING_SILVER = ITEM_REGISTER.register("string_silver", MMItemBase::new),
            STRING_NICKEL = ITEM_REGISTER.register("string_nickel", MMItemBase::new),
            STRING_COBALT = ITEM_REGISTER.register("string_cobalt", MMItemBase::new),
            STRING_ALUMINUM = ITEM_REGISTER.register("string_aluminum", MMItemBase::new),
            STRING_NCALLOY = ITEM_REGISTER.register("string_ncalloy", MMItemBase::new),
            STRING_GOLD = ITEM_REGISTER.register("string_gold", MMItemBase::new),
            STRING_IRON = ITEM_REGISTER.register("string_iron", MMItemBase::new),
    // roll
    ROLL_INVAR = ITEM_REGISTER.register("roll_invar", MMItemBase::new),
            ROLL_SS = ITEM_REGISTER.register("roll_ss", MMItemBase::new),
            ROLL_TUNGSTEN_STEEL = ITEM_REGISTER.register("roll_tungsten_steel", MMItemBase::new),
            ROLL_HSS = ITEM_REGISTER.register("roll_hss", MMItemBase::new),
            ROLL_STEEL = ITEM_REGISTER.register("roll_steel", MMItemBase::new),
    // slag
    SLAG = ITEM_REGISTER.register("slag", MMItemBase::new),
            SLAG_MANGANESE = ITEM_REGISTER.register("slag_manganese", MMItemBase::new),
            SLAG_VANADIUM = ITEM_REGISTER.register("slag_vanadium", MMItemBase::new),
            SLAG_COBALT = ITEM_REGISTER.register("slag_cobalt", MMItemBase::new),
            SLAG_TITANIUM = ITEM_REGISTER.register("slag_titanium", MMItemBase::new),
            REFINED_SLAG_MANGANESE = ITEM_REGISTER.register("refined_slag_manganese", MMItemBase::new),
            REFINED_SLAG_VANADIUM = ITEM_REGISTER.register("refined_slag_vanadium", MMItemBase::new),
            REFINED_SLAG_COBALT = ITEM_REGISTER.register("refined_slag_cobalt", MMItemBase::new),
            REFINED_SLAG_TITANIUM = ITEM_REGISTER.register("refined_slag_titanium", MMItemBase::new),
    // axis
    AXIS_BRONZE = ITEM_REGISTER.register("axis_bronze", MMItemBase::new),
            AXIS_INVAR = ITEM_REGISTER.register("axis_invar", MMItemBase::new),
            AXIS_SS = ITEM_REGISTER.register("axis_ss", MMItemBase::new),
            AXIS_TUNGSTEN_STEEL = ITEM_REGISTER.register("axis_tungsten_steel", MMItemBase::new),
            AXIS_HSS = ITEM_REGISTER.register("axis_hss", MMItemBase::new),
            AXIS_STEEL = ITEM_REGISTER.register("axis_steel", MMItemBase::new),
            AXIS_TITANIUM = ITEM_REGISTER.register("axis_titanium", MMItemBase::new),
            AXIS_IRON = ITEM_REGISTER.register("axis_iron", MMItemBase::new),
            AXIS_COBALT = ITEM_REGISTER.register("axis_cobalt", MMItemBase::new),
    // gear blank
    GEAR_BLANK_BRONZE = ITEM_REGISTER.register("gear_blank_bronze", MMItemBase::new),
            GEAR_BLANK_INVAR = ITEM_REGISTER.register("gear_blank_invar", MMItemBase::new),
            GEAR_BLANK_SS = ITEM_REGISTER.register("gear_blank_ss", MMItemBase::new),
            GEAR_BLANK_TUNGSTEN_STEEL = ITEM_REGISTER.register("gear_blank_tungsten_steel",
                    MMItemBase::new),
            GEAR_BLANK_SILVER = ITEM_REGISTER.register("gear_blank_silver", MMItemBase::new),
            GEAR_BLANK_NICKEL = ITEM_REGISTER.register("gear_blank_nickel", MMItemBase::new),
            GEAR_BLANK_TITANIUM = ITEM_REGISTER.register("gear_blank_titanium", MMItemBase::new),
            GEAR_BLANK_ALUMINUM = ITEM_REGISTER.register("gear_blank_aluminum", MMItemBase::new),
            GEAR_BLANK_IRON = ITEM_REGISTER.register("gear_blank_iron", MMItemBase::new),
            GEAR_BLANK_GOLD = ITEM_REGISTER.register("gear_blank_gold", MMItemBase::new),
            GEAR_BLANK_STEEL = ITEM_REGISTER.register("gear_blank_steel", MMItemBase::new),
    // gear
    GEAR_BRONZE = ITEM_REGISTER.register("gear_bronze", MMItemBase::new),
            GEAR_INVAR = ITEM_REGISTER.register("gear_invar", MMItemBase::new),
            GEAR_SS = ITEM_REGISTER.register("gear_ss", MMItemBase::new),
            GEAR_TUNGSTEN_STEEL = ITEM_REGISTER.register("gear_tungsten_steel", MMItemBase::new),
            GEAR_SILVER = ITEM_REGISTER.register("gear_silver", MMItemBase::new),
            GEAR_NICKEL = ITEM_REGISTER.register("gear_nickel", MMItemBase::new),
            GEAR_TITANIUM = ITEM_REGISTER.register("gear_titanium", MMItemBase::new),
            GEAR_ALUMINUM = ITEM_REGISTER.register("gear_aluminum", MMItemBase::new),
            GEAR_IRON = ITEM_REGISTER.register("gear_iron", MMItemBase::new),
            GEAR_GOLD = ITEM_REGISTER.register("gear_gold", MMItemBase::new),
            GEAR_STEEL = ITEM_REGISTER.register("gear_steel", MMItemBase::new),
    // crushed ore
    CRUSHED_COPPER = ITEM_REGISTER.register("crushed_copper", MMItemBase::new),
            CRUSHED_TIN = ITEM_REGISTER.register("crushed_tin", MMItemBase::new),
            CRUSHED_ILMENITE = ITEM_REGISTER.register("crushed_ilmenite", MMItemBase::new),
            CRUSHED_SILVER = ITEM_REGISTER.register("crushed_silver", MMItemBase::new),
            CRUSHED_PYROLUSITE = ITEM_REGISTER.register("crushed_pyrolusite", MMItemBase::new),
            CRUSHED_GOLD = ITEM_REGISTER.register("crushed_gold", MMItemBase::new),
            CRUSHED_CHROMITE = ITEM_REGISTER.register("crushed_chromite", MMItemBase::new),
            CRUSHED_BAUXITE = ITEM_REGISTER.register("crushed_bauxite", MMItemBase::new),
            CRUSHED_FERROMANGANESE = ITEM_REGISTER.register("crushed_ferromanganese", MMItemBase::new),
            CRUSHED_NICKEL = ITEM_REGISTER.register("crushed_nickel", MMItemBase::new),
            CRUSHED_NOLANITE = ITEM_REGISTER.register("crushed_nolanite", MMItemBase::new),
            CRUSHED_TUNSTITE = ITEM_REGISTER.register("crushed_tunstite", MMItemBase::new),
            CRUSHED_IRON = ITEM_REGISTER.register("crushed_iron", MMItemBase::new),
    // motor
    MOTOR_1 = ITEM_REGISTER.register("motor_1", MMItemBase::new),
            MOTOR_2 = ITEM_REGISTER.register("motor_2", MMItemBase::new),
            MOTOR_3 = ITEM_REGISTER.register("motor_3", MMItemBase::new),
            MOTOR_4 = ITEM_REGISTER.register("motor_4", MMItemBase::new),
            MOTOR_5 = ITEM_REGISTER.register("motor_5", MMItemBase::new),
    // oreconcentrate
    ORE_COPPER_CONCENTRATE = ITEM_REGISTER.register("ore_copper_concentrate", MMItemBase::new),
            ORE_TIN_CONCENTRATE = ITEM_REGISTER.register("ore_tin_concentrate", MMItemBase::new),
            ORE_ILMENITE_CONCENTRATE = ITEM_REGISTER.register("ore_ilmenite_concentrate", MMItemBase::new),
            ORE_SILVER_CONCENTRATE = ITEM_REGISTER.register("ore_silver_concentrate", MMItemBase::new),
            ORE_PYROLUSITE_CONCENTRATE = ITEM_REGISTER.register("ore_pyrolusite_concentrate",
                    MMItemBase::new),
            ORE_IRON_CONCENTRATE = ITEM_REGISTER.register("ore_iron_concentrate", MMItemBase::new),
            ORE_GOLD_CONCENTRATE = ITEM_REGISTER.register("ore_gold_concentrate", MMItemBase::new),
            ORE_CHROMITE_CONCENTRATE = ITEM_REGISTER.register("ore_chromite_concentrate", MMItemBase::new),
            ORE_BAUXITE_CONCENTRATE = ITEM_REGISTER.register("ore_bauxite_concentrate", MMItemBase::new),
            ORE_FERROMANGANESE_CONCENTRATE = ITEM_REGISTER.register("ore_ferromanganese_concentrate",
                    MMItemBase::new),
            ORE_NICKEL_CONCENTRATE = ITEM_REGISTER.register("ore_nickel_concentrate", MMItemBase::new),
            ORE_NOLANITE_CONCENTRATE = ITEM_REGISTER.register("ore_nolanite_concentrate", MMItemBase::new),
            ORE_TUNSTITE_CONCENTRATE = ITEM_REGISTER.register("ore_tunstite_concentrate", MMItemBase::new),
    // casts
    CAST_INGOT = ITEM_REGISTER.register("cast_ingot", () -> new MMCastBase("cast_ingot")),
            CAST_STICK = ITEM_REGISTER.register("cast_stick", () -> new MMCastBase("cast_stick")),
            CAST_GEAR_BLANK = ITEM_REGISTER.register("cast_gear_blank", () -> new MMCastBase("cast_gear_blank")),
            CAST_SWORD = ITEM_REGISTER.register("cast_sword", () -> new MMCastBase("cast_sword")),
    // other
    MONOCRYSTALLINE_SILICON = ITEM_REGISTER.register("monocrystalline_silicon", MMItemBase::new),
            WAFER = ITEM_REGISTER.register("wafer", MMItemBase::new),
            I7_8700K = ITEM_REGISTER.register("i7_8700k", MMItemBase::new),
            COMPONENT_BASIC = ITEM_REGISTER.register("component_basic", MMItemBase::new),
            COMPONENT_ADVANCED = ITEM_REGISTER.register("component_advanced", MMItemBase::new),
            LASER = ITEM_REGISTER.register("laser", MMItemBase::new),
            CLAY_BRICK = ITEM_REGISTER.register("clay_brick", MMItemBase::new),
            FIRE_BRICK = ITEM_REGISTER.register("fire_brick", MMItemBase::new),
            PLASMA_GENERATOR = ITEM_REGISTER.register("plasma_generator", MMItemBase::new),
            DSB = ITEM_REGISTER.register("dsb", () -> new SawBladeBase(Config.DSB_EFFICIENCY)),
            HSB = ITEM_REGISTER.register("hsb", () -> new SawBladeBase(Config.HSB_EFFICIENCY)),
            BRICK_POWDER = ITEM_REGISTER.register("brick_powder", MMItemBase::new),
            CLAY_MIXED_WITH_CLINKER = ITEM_REGISTER.register("clay_mixed_with_clinker", MMItemBase::new),
            BRONZE_SWORD0 = ITEM_REGISTER.register("bronze_sword0", MMItemBase::new),
            HSS_ADDITIVE = ITEM_REGISTER.register("hss_additive", MMItemBase::new),
            SS_ADDITIVE = ITEM_REGISTER.register("ss_additive", MMItemBase::new),
            SPONGE_IRON = ITEM_REGISTER.register("sponge_iron", MMItemBase::new),
            HOT_IRON_INGOT = ITEM_REGISTER.register("hot_iron_ingot", MMItemBase::new),
            HOT_STEEL_INGOT = ITEM_REGISTER.register("hot_steel_ingot", MMItemBase::new),
            FLYING_WHEEL_1 = ITEM_REGISTER.register("flying_wheel_1", MMItemBase::new),
            FLYING_WHEEL_2 = ITEM_REGISTER.register("flying_wheel_2", MMItemBase::new),
            FLYING_WHEEL_3 = ITEM_REGISTER.register("flying_wheel_3", MMItemBase::new),
            ADAPTER_CARD = ITEM_REGISTER.register("adapter_card", MMItemBase::new),
            PIPE_BLOCKAGE = ITEM_REGISTER.register("pipe_blockage", MMItemBase::new),
    // battery

    FLYWHEEL_BATTERY = ITEM_REGISTER.register("flywheel_battery", MMItemBase::new);
    // TEST_UTIL = new DebugTool;

}
