package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.blocks.mathines.forge_anvil.BlockAnvil;
import com.dbydd.micro_machinery.blocks.mathines.generator.BlockGenerator;
import com.dbydd.micro_machinery.blocks.mathines.hand_generator.BlockHandGenerator;
import com.dbydd.micro_machinery.blocks.mathines.hand_generator.BlockHandGenerator_Handler;
import com.dbydd.micro_machinery.blocks.mathines.klin.BlockKlin;
import com.dbydd.micro_machinery.blocks.mathines.ter_test.TestBlock;
import com.dbydd.micro_machinery.enums.EnumAnvilType;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.ToolType;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

public class RegisteredBlocks {

    public static final Block.Properties HARD1 = Block.Properties.create(Material.ROCK).harvestLevel(1).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD2 = Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD3 = Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE);

    public static final Block STALINITE = new MMBlockBase(Block.Properties.create(Material.GLASS).hardnessAndResistance(8.0f).harvestTool(ToolType.PICKAXE).noDrops().notSolid(), "stalinite");
    public static final Block STEEL_SCAFFOLDING = new MMBlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).notSolid(), "steel_scaffolding");
    //Ores
    public static final Block ORECOPPER = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orecopper");
    public static final Block ORETIN = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "oretin");
    public static final Block OREILMENITE = new MMBlockBase(HARD1.hardnessAndResistance(5.0f), "oreilmenite");
    public static final Block ORESILVER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "oresilver");
    public static final Block OREPYROLUSITE = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orepyrolusite");
    public static final Block OREGRAPHITE = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "oregraphite");
    public static final Block ORECHROMITE = new MMBlockBase(HARD3.hardnessAndResistance(4.5f), "orechromite");
    public static final Block OREBAUXITE = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orebauxite");
    public static final Block OREFERROMANGANESE = new MMBlockBase(HARD3.hardnessAndResistance(3.0f), "oreferromanganese");
    public static final Block ORENICKEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "orenickel");
    public static final Block ORENOLANITE = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orenolanite");
    public static final Block OREPYROLUSITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orepyrolusite_nether");
    public static final Block OREGRAPHITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "oregraphite_nether");
    public static final Block OREBAUXITE_NETHER = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orebauxite_nether");
    public static final Block OREFERROMANGANESE_NETHER = new MMBlockBase(HARD3.hardnessAndResistance(3.0f), "oreferromanganese_nether");
    public static final Block ORENOLANITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orenolanite_nether");
    public static final Block ORETUNSTITE = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "oretunstite");
    //oreblock
    public static final Block BLOCKCOPPER = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockcopper");
    public static final Block BLOCKTIN = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "blocktin");
    public static final Block BLOCKBRONZE = new MMBlockBase(HARD2.hardnessAndResistance(4.5f), "blockbronze");
    public static final Block BLOCKSTEEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "blocksteel");
    public static final Block BLOCKSS = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "blockss");
    public static final Block BLOCKTUNGSTEN = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktungsten");
    public static final Block BLOCKNICKEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "blocknickel");
    public static final Block BLOCKTUNGSTEN_STEEL = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktungsten_steel");
    public static final Block BLOCKINVAR = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockinvar");
    public static final Block BLOCKHSS = new MMBlockBase(HARD3.hardnessAndResistance(6.0f), "blockhss");
    public static final Block BLOCKSILVER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "blocksilver");
    public static final Block BLOCKCHROMIUM = new MMBlockBase(HARD2.hardnessAndResistance(6.5f), "blockchromium");
    public static final Block BLOCKVANADIUM = new MMBlockBase(HARD2.hardnessAndResistance(5.0f), "blockvanadium");
    public static final Block BLOCKCOBALT = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "blockcobalt");
    public static final Block BLOCKTITANIUM = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktitanium");
    public static final Block BLOCKALUMINUM = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "blockaluminum");
    public static final Block BLOCKNCALLOY = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockncalloy");
    public static final Block BLOCKMANGANESE = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockmanganese");
    //coil
    public static final Block COILCOPPER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "coilcopper");
    public static final Block COILNICKEL = new MMBlockBase(HARD2.hardnessAndResistance(2.5f), "coilnickel");
    public static final Block COILALUMINUM = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "coilaluminum");
    public static final Block COILTUNGSTEN = new MMBlockBase(HARD3.hardnessAndResistance(6.0f), "coiltungsten");
    public static final Block COILCOBALT = new MMBlockBase(HARD3.hardnessAndResistance(4.0f), "coilcobalt");
    //machine casing
    public static final Block CASING_1 = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "casing_1");
    public static final Block CASING_2 = new MMBlockBase(HARD1.hardnessAndResistance(3.0f), "casing_2");
    public static final Block CASING_3 = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "casing_3");
    public static final Block CASING_4 = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "casing_4");
    //discarded metal
    public static final Block MOLTEN_COPPER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_copper_discarded");
    public static final Block MOLTEN_TIN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_tin_discarded");
    public static final Block MOLTEN_BRONZE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_bronze_discarded");
    public static final Block MOLTEN_STEEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_steel_discarded");
    public static final Block MOLTEN_SS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ss_discarded");
    public static final Block MOLTEN_TUNGSTEN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_tungsten_discarded");
    public static final Block MOLTEN_NICKEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_nickel_discarded");
    public static final Block MOLTEN_INVAR_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_invar_discarded");
    public static final Block MOLTEN_HSS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_hss_discarded");
    public static final Block MOLTEN_SILVER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_silver_discarded");
    public static final Block MOLTEN_GOLD_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_gold_discarded");
    public static final Block MOLTEN_MANGANESE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_manganese_discarded");
    public static final Block MOLTEN_CHROMIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_chromium_discarded");
    public static final Block MOLTEN_VANADIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_vanadium_discarded");
    public static final Block MOLTEN_COBALT_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_cobalt_discarded");
    public static final Block MOLTEN_TITANIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_titanium_discarded");
    public static final Block MOLTEN_ALUMINUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_aluminum_discarded");
    public static final Block MOLTEN_NCALLOY_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ncalloy_discarded");
    public static final Block MOLTEN_FERROCHROME_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ferrochrome_discarded");
    public static final Block MOLTEN_IRON_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_iron_discarded");
    //modules
    public static final Block MODULE_GENERATOR = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_generator");
    public static final Block MODULE_HEAT_SINK = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_heat_sink");
    public static final Block MODULE_PRESSURE_BEARING = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_pressure_bearing");
    public static final Block MODULE_TRANSMISSION_1 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_transmission_1");
    public static final Block MODULE_TRANSMISSION_2 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_transmission_2");
    public static final Block MODULE_TRANSMISSION_3 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_transmission_3");
    public static final Block MODULE_TRANSMISSION_4 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_transmission_4");
    public static final Block MODULE_TRANSMISSION_5 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f),"module_transmission_5");
    //other
    public static final Block PCM = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "pcm");
    public static final Block TANK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "tank_block");
    public static final Block GRAPHITE_CRUCIBLE = new MMBlockBase(HARD1.hardnessAndResistance(1.5f).notSolid(), "graphite_crucible");
    public static final Block SLAG_CONCRETE = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "slag_concrete");
    public static final Block CLAY_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "clay_brick_block");
    public static final Block FIRE_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "fire_brick_block");
    //tile entity
    //machine
    public static final Block KLIN = new BlockKlin();
    public static final Block GENERATOR = new BlockGenerator();
    public static final Block HAND_GENERATOR = new BlockHandGenerator();
    public static final Block TESTBLOCK = new TestBlock(Block.Properties.create(Material.ROCK), "testblock");
    public static final Block STONE_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1), "stone_anvil", EnumAnvilType.STONE, 16);
    public static final Block BRONZE_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2), "bronze_anvil", EnumAnvilType.BRONZE, 16);
    public static final Block PIGIRON_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(4.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3), "pigiron_anvil", EnumAnvilType.PIGIRON, 12);
    //cable

    //pipe

    //fluid pipe

    //indicator
    public static final Block INDICATOR = new MMBlockBase("indicator", true);
    public static final Block ENERGY_INDICATOR = new MMBlockBase("energy_indicator", true);
    public static final Block WATER_INDICATOR = new MMBlockBase("water_indicator", true);
    public static final Block TEMPERATURE_INDICATOR = new MMBlockBase("temperature_indicator", true);
    public static final Block FLUID_INDICATOR = new MMBlockBase("fluid_indicator", true);
    public static final Block REV_INDICATOR = new MMBlockBase("rev_indicator", true);

    public static final Block PUSHROD = new MMBlockBase("pushrod", true);

    public static final Block LIGHT_GREEN = new MMBlockBase("light_green", true);
    public static final Block LIGHT_RED = new MMBlockBase("light_red", true);
    public static final Block LIGHT_BLACK = new MMBlockBase("light_black", true);
    public static final Block LIGHT_FLICKER = new MMBlockBase("light_flicker", true);
    public static final Block HAND_GENERATOR_1 = new BlockHandGenerator_Handler();
    /** ↓this is machine with multi_block */
    public static final Block FORGING_PRESS = new MMBlockBase("forging_press", true);
    public static final Block REACTION_STILL = new MMBlockBase("reaction_still", true);
    public static final Block SPHERICAL_TANK = new MMBlockBase("spherical_tank", true);
    public static final Block THERMAL_POWER_PLANT = new MMBlockBase("thermal_power_plant", true);
    public static final Block FLYWHEEL = new MMBlockBase("flywheel", true);


    private RegisteredBlocks() {
    }

    public static void Init() {
    }
}
