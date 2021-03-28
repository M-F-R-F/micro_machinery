package mfrf.dbydd.micro_machinery;

import it.unimi.dsi.fastutil.booleans.BooleanList;
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

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("world settings","this part defines your ore generation").push("world");
        GENERATE_CHANCE_OF_COPPER_VEIN = builder.comment("generation chance of copper vein(0.0-0.05)").defineInRange("value",0.01d,0.0d,0.05d);
        GENERATE_CHANCE_OF_TIN_VEIN = builder.comment("generation chance of tin vein(0.0-0.05)").defineInRange("value",0.008d,0.0d,0.05d);
        GENERATE_CHANCE_OF_IRON_VEIN = builder.comment("generation chance of iron vein(0.0-0.05)").defineInRange("value",0.0075d,0.0d,0.05d);
        GENERATE_CHANCE_OF_GOLD_VEIN = builder.comment("generation chance of gold vein(0.0-0.05)").defineInRange("value",0.04d,0.0d,0.05d);
        GENERATE_CHANCE_OF_COAL_VEIN = builder.comment("generation chance of coal vein(0.0-0.05)").defineInRange("value",0.012d,0.0d,0.05d);
        GENERATE_CHANCE_OF_ILMENITE_VEIN = builder.comment("generation chance of ilmenite vein(0.0-0.05)").defineInRange("value",0.006d,0.0d,0.05d);
        GENERATE_CHANCE_OF_SILVER_VEIN = builder.comment("generation chance of silver vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_PYROLUSITE_VEIN = builder.comment("generation chance of pyrolusite vein(0.0-0.05)").defineInRange("value",0.004d,0.0d,0.05d);
        GENERATE_CHANCE_OF_CHROMITE_VEIN = builder.comment("generation chance of chromite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_BAUXITE_VEIN = builder.comment("generation chance of bauxite vein(0.0-0.05)").defineInRange("value",0.007d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NICKEL_VEIN = builder.comment("generation chance of nickel vein(0.0-0.05)").defineInRange("value",0.0055d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NOLANITE_VEIN = builder.comment("generation chance of nolanite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_FERROMANGANESE_VEIN = builder.comment("generation chance of ferromanganese vein(0.0-0.05)").defineInRange("value",0.0005d,0.0d,0.05d);

        GENERATE_CHANCE_OF_NETHERPYROLUSITE_VEIN = builder.comment("generation chance of netherpyrolusite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NETHERGRAPHITE_VEIN = builder.comment("generation chance of nethergraphite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NETHERBAUXITE_VEIN = builder.comment("generation chance of netherbauxite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NETHERFERROMANGANESE_VEIN = builder.comment("generation chance of netherferromanganese vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);
        GENERATE_CHANCE_OF_NETHERNOLANITE_VEIN = builder.comment("generation chance of nethernolanite vein(0.0-0.05)").defineInRange("value",0.005d,0.0d,0.05d);

        GENERATE_CHANCE_OF_TUNSTITE_VEIN = builder.comment("generation chance of tunstite vein").defineInRange("value",0.0075d,0.0d,0.05d);
        builder.pop();

        builder.comment("machine settings","this part defines your machine").push("machine");
        ENERGY_PRODUCTION_EFFICIENCY = builder.comment("energy production efficiency(0.1-10)").defineInRange("value",1.0d,0.1d,10.0d);
        FLYWHEEL_ENERGY_STORAGE_EFFICIENCY=builder.comment("flywheel energy storage efficiency(0.1-10)").defineInRange("value",1.0d,0.1d,10.0d);
        SPHERICAL_TANK_VOLUME=builder.comment("spherical tank volume(1600-1024000)").defineInRange("value",256000,1600,1024000);
        DSB_EFFICIENCY=builder.comment("Diamond Saw Blade efficiency(0.2-5)").defineInRange("value",1.0d,0.2d,5.0d);
        HSB_EFFICIENCY=builder.comment("flywheel energy storage efficiency(0.2-5)").defineInRange("value",1.0d,0.2d,5.0d);
        MINIMUM_WATER_LIMIT= builder.comment("open or stop thermal power plant's minimum water limit").define("bool", true);
        builder.pop();

        builder.comment("other settings","this part defines some other things").push("other");
        APPLE_JUICE_TIME=builder.comment("duration of apple juice(0.1-10)").defineInRange("value",1.0d,0.1d,10.0d);
        LASER_DRILL_DAMAGE=builder.comment("laser drill damage(0-#)").defineInRange("value",7,0,Integer.MAX_VALUE);
        builder.pop();

        CONFIG = builder.build();
    }
}
