package mfrf.micro_machinery;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_COPPER_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_TIN_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_IRON_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_GOLD_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_COAL_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_ILMENITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_SILVER_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_PYROLUSITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_CHROMITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_BAUXITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NICKEL_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NOLANITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_FERROMANGANESE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NETHERPYROLUSITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NETHERGRAPHITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NETHERBAUXITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NETHERFERROMANGANESE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_NETHERNOLANITE_VEIN;
    public static ForgeConfigSpec.DoubleValue GENERATE_CHANCE_OF_TUNSTITE_VEIN;
    public static ForgeConfigSpec.DoubleValue ENERGY_PRODUCTION_EFFICIENCY;
    public static ForgeConfigSpec.DoubleValue FLYWHEEL_ENERGY_STORAGE_EFFICIENCY;
    public static ForgeConfigSpec.IntValue SPHERICAL_TANK_VOLUME;
    public static ForgeConfigSpec.DoubleValue APPLE_JUICE_TIME;
    public static ForgeConfigSpec.IntValue LASER_DRILL_DAMAGE;
    public static ForgeConfigSpec.DoubleValue DSB_EFFICIENCY;
    public static ForgeConfigSpec.DoubleValue HSB_EFFICIENCY;
    public static ForgeConfigSpec.BooleanValue MINIMUM_WATER_LIMIT;
    public static ForgeConfigSpec.IntValue HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE;
    public static ForgeConfigSpec.IntValue ATOMIZATION_FLUID_CONTAINER;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_STACK_SIZE_LIMIT;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_1_TRANSMIT_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_1_EXTRACT_INTERVAL;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_1_TRANSMIT_SPEED;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_2_TRANSMIT_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_2_EXTRACT_INTERVAL;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_2_TRANSMIT_SPEED;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_3_TRANSMIT_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_3_EXTRACT_INTERVAL;
    public static ForgeConfigSpec.IntValue CONVEY_BELT_3_TRANSMIT_SPEED;
    public static ForgeConfigSpec.IntValue HUGE_CONTAINER_SLOT;
    public static ForgeConfigSpec.IntValue HUGE_CONTAINER_SLOT_STACK;
    public static ForgeConfigSpec.IntValue LASER_DRILL_ENERGY_CAP;
    public static ForgeConfigSpec.IntValue LASER_DRILL_ENERGY_CONSUME;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("world settings", "this part defines your ore generation").push("world");
        GENERATE_CHANCE_OF_COPPER_VEIN = builder.comment("generation chance of copper vein(0.0-0.05)").defineInRange("value_copper", 0.01d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_TIN_VEIN = builder.comment("generation chance of tin vein(0.0-0.05)").defineInRange("value_tin", 0.008d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_IRON_VEIN = builder.comment("generation chance of iron vein(0.0-0.05)").defineInRange("value_iron", 0.0075d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_GOLD_VEIN = builder.comment("generation chance of gold vein(0.0-0.05)").defineInRange("value_gold", 0.04d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_COAL_VEIN = builder.comment("generation chance of coal vein(0.0-0.05)").defineInRange("value_coal", 0.012d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_ILMENITE_VEIN = builder.comment("generation chance of ilmenite vein(0.0-0.05)").defineInRange("value_ilmenite", 0.006d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_SILVER_VEIN = builder.comment("generation chance of silver vein(0.0-0.05)").defineInRange("value_silver", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_PYROLUSITE_VEIN = builder.comment("generation chance of pyrolusite vein(0.0-0.05)").defineInRange("value_pyrolusite", 0.004d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_CHROMITE_VEIN = builder.comment("generation chance of chromite vein(0.0-0.05)").defineInRange("value_chromite", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_BAUXITE_VEIN = builder.comment("generation chance of bauxite vein(0.0-0.05)").defineInRange("value_bauxite", 0.007d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NICKEL_VEIN = builder.comment("generation chance of nickel vein(0.0-0.05)").defineInRange("value_nickel", 0.0055d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NOLANITE_VEIN = builder.comment("generation chance of nolanite vein(0.0-0.05)").defineInRange("value_nolanite", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_FERROMANGANESE_VEIN = builder.comment("generation chance of ferromanganese vein(0.0-0.05)").defineInRange("value_ferromanganese", 0.0005d, 0.0d, 0.05d);

        GENERATE_CHANCE_OF_NETHERPYROLUSITE_VEIN = builder.comment("generation chance of netherpyrolusite vein(0.0-0.05)").defineInRange("value_nether_pyrolusite", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NETHERGRAPHITE_VEIN = builder.comment("generation chance of nethergraphite vein(0.0-0.05)").defineInRange("value_nether_graphite", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NETHERBAUXITE_VEIN = builder.comment("generation chance of netherbauxite vein(0.0-0.05)").defineInRange("value_nether_bauxite", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NETHERFERROMANGANESE_VEIN = builder.comment("generation chance of netherferromanganese vein(0.0-0.05)").defineInRange("value_nether_ferromanganese", 0.005d, 0.0d, 0.05d);
        GENERATE_CHANCE_OF_NETHERNOLANITE_VEIN = builder.comment("generation chance of nethernolanite vein(0.0-0.05)").defineInRange("value_nether_nolanite", 0.005d, 0.0d, 0.05d);

        GENERATE_CHANCE_OF_TUNSTITE_VEIN = builder.comment("generation chance of tunstite vein").defineInRange("value_tunstite", 0.0075d, 0.0d, 0.05d);
        builder.pop();

        builder.comment("machine settings", "this part defines your machine").push("machine");
        ENERGY_PRODUCTION_EFFICIENCY = builder.comment("energy production efficiency(0.1-10)").defineInRange("energy", 1.0d, 0.1d, 10.0d);
        FLYWHEEL_ENERGY_STORAGE_EFFICIENCY = builder.comment("flywheel energy storage efficiency(0.1-10)").defineInRange("flywheel", 1.0d, 0.1d, 10.0d);
        SPHERICAL_TANK_VOLUME = builder.comment("spherical tank volume(1600-1024000)").defineInRange("tank", 256000, 1600, 1024000);
        DSB_EFFICIENCY = builder.comment("Diamond Saw Blade efficiency(0.2-5)").defineInRange("dsb", 1.0d, 0.2d, 5.0d);
        HSB_EFFICIENCY = builder.comment("flywheel energy storage efficiency(0.2-5)").defineInRange("hsb", 1.0d, 0.2d, 5.0d);
        MINIMUM_WATER_LIMIT = builder.comment("open or stop thermal power plant's minimum water limit").define("minimum_water_limit", true);
        ATOMIZATION_FLUID_CONTAINER = builder.comment("max fluid amount that atomization could contain").defineInRange("atomization_fluid_amount", 144, 144, 129600);
        CONVEY_BELT_1_TRANSMIT_SPEED = builder.comment("how many ticks that a stack will be transfered from one side to other side by conveyor belt (tier 1)").defineInRange("conveytor_belt_transmit_speed_tier_1", 40, 1, Integer.MAX_VALUE);
        CONVEY_BELT_1_TRANSMIT_STACK_SIZE = builder.comment("how many items can be moved in one extract progress on conveyor belt (tier 1)").defineInRange("conveyor_belt_transmit_stack_size_tier_1", 1, 1, Integer.MAX_VALUE);
        CONVEY_BELT_1_EXTRACT_INTERVAL = builder.comment("how many ticks that the conveyor belt will try to extract item (tier 1)").defineInRange("conveyor_belt_extract_interval", 20, 1, Integer.MAX_VALUE);
        CONVEY_BELT_2_TRANSMIT_SPEED = builder.comment("how many ticks that a stack will be transfered from one side to other side by conveyor belt (tier 2)").defineInRange("conveytor_belt_transmit_speed_tier_2", 20, 1, Integer.MAX_VALUE);
        CONVEY_BELT_2_TRANSMIT_STACK_SIZE = builder.comment("how many items can be moved in one extract progress on conveyor belt (tier 2)").defineInRange("conveyor_belt_transmit_stack_size_tier_2", 4, 1, Integer.MAX_VALUE);
        CONVEY_BELT_2_EXTRACT_INTERVAL = builder.comment("how many ticks that the conveyor belt will try to extract item (tier 2)").defineInRange("conveyor_belt_extract_interval", 10, 1, Integer.MAX_VALUE);
        CONVEY_BELT_3_TRANSMIT_SPEED = builder.comment("how many ticks that a stack will be transfered from one side to other side by conveyor belt (tier 3)").defineInRange("conveytor_belt_transmit_speed_tier_3", 10, 1, Integer.MAX_VALUE);
        CONVEY_BELT_3_TRANSMIT_STACK_SIZE = builder.comment("how many items can be moved in one extract progress on conveyor belt (tier 3)").defineInRange("conveyor_belt_transmit_stack_size_tier_3", 16, 1, Integer.MAX_VALUE);
        CONVEY_BELT_3_EXTRACT_INTERVAL = builder.comment("how many ticks that the conveyor belt will try to extract item (tier 3)").defineInRange("conveyor_belt_extract_interval", 10, 1, Integer.MAX_VALUE);
        HUGE_CONTAINER_SLOT = builder.comment("how many slots in huge container").defineInRange("huge_container_slot", 8, 1, Integer.MAX_VALUE);
        HUGE_CONTAINER_SLOT_STACK = builder.comment("how many item can stack in one slot of huge container").defineInRange("huge_container_slot_stack", 4096, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("technical", "this part is some technical options").push("technical");
        HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE = builder.comment("Some block require update active and periodically, which may cause high performance use.", "This argument controls the cycle tick of update", "include: fluid pipe").defineInRange("high_frequency_block_update_cycle", 35, 1, Integer.MAX_VALUE);
        CONVEY_BELT_STACK_SIZE_LIMIT = builder.comment("this entry defines how many stacks can hold by single ConveyBelt").defineInRange("convey_belt_stack_size_limit", 64, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("other settings", "this part defines some other things").push("other");
        APPLE_JUICE_TIME = builder.comment("duration of apple juice(0.1-10)").defineInRange("apple_juice", 1.0d, 0.1d, 10.0d);
        LASER_DRILL_DAMAGE = builder.comment("laser drill damage(0-#)").defineInRange("laser_drill_damage", 7, 0, Integer.MAX_VALUE);
        LASER_DRILL_ENERGY_CAP = builder.comment("laser drill energy cap(0-#)").defineInRange("laser_drill_energy", 131072, 0, Integer.MAX_VALUE);
        LASER_DRILL_ENERGY_CONSUME = builder.comment("laser drill energy consume(0-#)").defineInRange("laser_drill_energy_consume", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        CONFIG = builder.build();
    }
}
