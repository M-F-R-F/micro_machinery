package mfrf.micro_machinery.registeried_lists;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io.BlockRedstoneInterface;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.test.TestMainPart;
import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.BlockAtomization;
import mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge.BlockCentrifuge;
import mfrf.micro_machinery.blocks.machines.single_block_machines.conveyor_belt.BlockConveyorBelt;
import mfrf.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell.BlockCreativeEnergyCell;
import mfrf.micro_machinery.blocks.machines.single_block_machines.cutter.BlockCutter;
import mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis.BlockElectrolysis;
import mfrf.micro_machinery.blocks.machines.single_block_machines.energy_cable.BlockEnergyCable;
import mfrf.micro_machinery.blocks.machines.single_block_machines.fluidpipe.FluidPipeBlock;
import mfrf.micro_machinery.blocks.machines.single_block_machines.forge_anvil.BlockAnvil;
import mfrf.micro_machinery.blocks.machines.single_block_machines.generator.BlockGenerator;
import mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator_Handler;
import mfrf.micro_machinery.blocks.machines.single_block_machines.klin.BlockKlin;
import mfrf.micro_machinery.blocks.machines.single_block_machines.weld.BlockWeld;
import mfrf.micro_machinery.enums.EnumAnvilType;
import mfrf.micro_machinery.enums.EnumCableMaterial;
import mfrf.micro_machinery.utils.TriFields;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class RegisteredBlocks {

    public static final Block STALINITE = new MMBlockBase(Block.Properties.of(Material.GLASS).hardnessAndResistance(8.0f).harvestTool(ToolType.PICKAXE).noDrops().noOcclusion(), "stalinite");
    public static final Block STEEL_SCAFFOLDING = new MMBlockBase(Block.Properties.of(Material.STONE).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).noOcclusion(), "steel_scaffolding");
    //Ores
    public static final Block
            ORECOPPER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "orecopper"),
            ORETIN = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "oretin"),
            OREILMENITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "oreilmenite"),
            ORESILVER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "oresilver"),
            OREPYROLUSITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "orepyrolusite"),
            OREGRAPHITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "oregraphite"),
            ORECHROMITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.5f), "orechromite"),
            OREBAUXITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "orebauxite"),
            OREFERROMANGANESE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "oreferromanganese"),
            ORENICKEL = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.5f), "orenickel"),
            ORENOLANITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "orenolanite"),
            OREPYROLUSITE_NETHER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "orepyrolusite_nether"),
            OREGRAPHITE_NETHER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "oregraphite_nether"),
            OREBAUXITE_NETHER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "orebauxite_nether"),
            OREFERROMANGANESE_NETHER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "oreferromanganese_nether"),
            ORENOLANITE_NETHER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "orenolanite_nether"),
            ORETUNSTITE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(7.0f), "oretunstite");

    //oreblock
    public static final Block
            BLOCKCOPPER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "blockcopper"),
            BLOCKTIN = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "blocktin"),
            BLOCKBRONZE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.5f), "blockbronze"),
            BLOCKSTEEL = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.5f), "blocksteel"),
            BLOCKSS = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "blockss"),
            BLOCKTUNGSTEN = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(7.0f), "blocktungsten"),
            BLOCKNICKEL = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.5f), "blocknickel"),
            BLOCKTUNGSTEN_STEEL = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(7.0f), "blocktungsten_steel"),
            BLOCKINVAR = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "blockinvar"),
            BLOCKHSS = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(6.0f), "blockhss"),
            BLOCKSILVER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "blocksilver"),
            BLOCKCHROMIUM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(6.5f), "blockchromium"),
            BLOCKVANADIUM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "blockvanadium"),
            BLOCKCOBALT = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "blockcobalt"),
            BLOCKTITANIUM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(7.0f), "blocktitanium"),
            BLOCKALUMINUM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "blockaluminum"),
            BLOCKNCALLOY = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "blockncalloy"),
            BLOCKMANGANESE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "blockmanganese");
    //coil
    public static final Block
            COILCOPPER = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "coilcopper"),
            COILNICKEL = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "coilnickel"),
            COILALUMINUM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "coilaluminum"),
            COILTUNGSTEN = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(6.0f), "coiltungsten"),
            COILCOBALT = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "coilcobalt");

    //machine casing
    public static final Block
            CASING_1 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "casing_1"),
            CASING_2 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "casing_2"),
            CASING_3 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0f), "casing_3"),
            CASING_4 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "casing_4");

    //discarded metal
    public static final Block
            MOLTEN_COPPER_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_copper_discarded"),
            MOLTEN_TIN_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_tin_discarded"),
            MOLTEN_BRONZE_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_bronze_discarded"),
            MOLTEN_STEEL_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_steel_discarded"),
            MOLTEN_SS_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_ss_discarded"),
            MOLTEN_TUNGSTEN_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_tungsten_discarded"),
            MOLTEN_NICKEL_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_nickel_discarded"),
            MOLTEN_INVAR_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_invar_discarded"),
            MOLTEN_HSS_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_hss_discarded"),
            MOLTEN_SILVER_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_silver_discarded"),
            MOLTEN_GOLD_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_gold_discarded"),
            MOLTEN_MANGANESE_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_manganese_discarded"),
            MOLTEN_CHROMIUM_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_chromium_discarded"),
            MOLTEN_VANADIUM_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_vanadium_discarded"),
            MOLTEN_COBALT_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_cobalt_discarded"),
            MOLTEN_TITANIUM_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_titanium_discarded"),
            MOLTEN_ALUMINUM_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_aluminum_discarded"),
            MOLTEN_NCALLOY_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_ncalloy_discarded"),
            MOLTEN_FERROCHROME_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_ferrochrome_discarded"),
            MOLTEN_IRON_DISCARDED = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "molten_iron_discarded");

    //modules
    public static final Block
            MODULE_GENERATOR = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_generator"),
            MODULE_HEAT_SINK = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_heat_sink"),
            MODULE_PRESSURE_BEARING = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_pressure_bearing"),
            MODULE_TRANSMISSION_1 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_transmission_1"),
            MODULE_TRANSMISSION_2 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_transmission_2"),
            MODULE_TRANSMISSION_3 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_transmission_3"),
            MODULE_TRANSMISSION_4 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_transmission_4"),
            MODULE_TRANSMISSION_5 = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "module_transmission_5");
    //other
    public static final Block
            PCM = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.5f), "pcm"),
            TANK_BLOCK = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5f), "tank_block"),
            GRAPHITE_CRUCIBLE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5f).noOcclusion(), "graphite_crucible"),
            SLAG_CONCRETE = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5f), "slag_concrete"),
            CLAY_BRICK_BLOCK = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "clay_brick_block"),
            FIRE_BRICK_BLOCK = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "fire_brick_block"),
            BELLOW = new MMBlockBase(Block.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f).harvestTool(ToolType.AXE), "bellow");
    //machine
    public static final Block
            KLIN = new BlockKlin(Block.Properties.of(Material.METAL).noOcclusion().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(2.0f)),
            GENERATOR = new BlockGenerator(Block.Properties.of(Material.METAL).noOcclusion().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3.0f)),
            HAND_GENERATOR = new BlockHandGenerator(Block.Properties.of(Material.METAL).hardnessAndResistance(3).noOcclusion().harvestTool(ToolType.PICKAXE).harvestLevel(1)),
            STONE_ANVIL = new BlockAnvil(Block.Properties.of(Material.ANVIL).noOcclusion().hardnessAndResistance(2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1), "stone_anvil", EnumAnvilType.STONE, 16),
            BRONZE_ANVIL = new BlockAnvil(Block.Properties.of(Material.ANVIL).noOcclusion().hardnessAndResistance(3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2), "bronze_anvil", EnumAnvilType.BRONZE, 16),
            PIGIRON_ANVIL = new BlockAnvil(Block.Properties.of(Material.ANVIL).noOcclusion().hardnessAndResistance(4.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3), "pigiron_anvil", EnumAnvilType.PIGIRON, 12),
            CREATIVE_ENERGY_CELL = new BlockCreativeEnergyCell(Block.Properties.of(Material.METAL)),
            ELECTROLYSIS = new BlockElectrolysis(Block.Properties.of(Material.METAL)),
            CUTTER = new BlockCutter(Block.Properties.of(Material.METAL)),
            CENTRIFUGE = new BlockCentrifuge(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "centrifuge"),
            ATOMIZATION = new BlockAtomization(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "atomization"),
            WELD = new BlockWeld(Block.Properties.of(Material.METAL));
    //    public static final Block BLOCK_ETCHER = new BlockEtcher(Block.Properties.of(Material.METAL)),

    //cable
    public static final Block
            COPPER_CABLE = new BlockEnergyCable(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "copper_cable", EnumCableMaterial.COPPER),
            NICKEL_CABLE = new BlockEnergyCable(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "nickel_cable", EnumCableMaterial.NICKEL),
            ALUMINUM_CABLE = new BlockEnergyCable(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "aluminum_cable", EnumCableMaterial.ALUMINUM),
            TUNGSTEN_CABLE = new BlockEnergyCable(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "tungsten_cable", EnumCableMaterial.TUNGSTEN),
            COBALT_CABLE = new BlockEnergyCable(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "cobalt_cable", EnumCableMaterial.COBALT);
    //convey belt
    public static final Block
            CONVEYOR_BELT_1 = new BlockConveyorBelt(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_1", TriFields.of(Config.CONVEY_BELT_1_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_1_TRANSMIT_SPEED::get, Config.CONVEY_BELT_1_TRANSMIT_STACK_SIZE::get)),
            CONVEYOR_BELT_2 = new BlockConveyorBelt(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_2", TriFields.of(Config.CONVEY_BELT_2_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_2_TRANSMIT_SPEED::get, Config.CONVEY_BELT_2_TRANSMIT_STACK_SIZE::get)),
            CONVEYOR_BELT_3 = new BlockConveyorBelt(Block.Properties.of(Material.METAL).noOcclusion().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_3", TriFields.of(Config.CONVEY_BELT_3_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_3_TRANSMIT_SPEED::get, Config.CONVEY_BELT_3_TRANSMIT_STACK_SIZE::get));

    //fluid pipe
    public static final Block
            PIPE_INVAR = new FluidPipeBlock(Block.Properties.of(Material.METAL), "pipe_invar"),
            PIPE_STAINLESS_STEEL = new FluidPipeBlock(Block.Properties.of(Material.METAL), "pipe_stainless_steel"),
            PIPE_TUNGSTEN_STEEL = new FluidPipeBlock(Block.Properties.of(Material.METAL), "pipe_tungsten_steel");
    //multiBlock

    //component
    public static final Block
            BELLOW_RIGHT = new MMBlockBase("bellow_right", true),
            BELLOW_LEFT = new MMBlockBase("bellow_left", true),
            HAND_GENERATOR_1 = new BlockHandGenerator_Handler(),
            ETCHER_1 = new MMBlockBase("etcher_1", true),
            PUMP_1 = new MMBlockBase("pump_1", true),
            FORGING_PRESS_1 = new MMBlockBase("forging_press_1", true),
            CRUSHER_1 = new MMBlockBase("crusher_1", true),
            CATING_MACHINE_1 = new MMBlockBase("cating_machine_1", true),
            THICKENER_1 = new MMBlockBase("thickener_1", true);

    /**
     * this is machine with multi_block
     */
    public static final Block
            FORGING_PRESS = new MMBlockBase("forging_press", true),
            REACTION_STILL = new MMBlockBase("reaction_still", true),
            SPHERICAL_TANK = new MMBlockBase("spherical_tank", true),
            THERMAL_POWER_PLANT = new MMBlockBase("thermal_power_plant", true),
            FLYWHEEL = new MMBlockBase("flywheel", true);

    /**
     * Multi Block main parts
     */


    /**
     * new system of multi block
     * todo fill data
     */
    //part
    public static final MMBlockMultiBlockPart MULTIBLOCK_PART = new MMBlockMultiBlockPart(Block.Properties.of(Material.METAL).harvestLevel(-1).hardnessAndResistance(-1), "part", true);
    //component
    public static final BlockRedstoneInterface REDSTONE_INTERFACE = new BlockRedstoneInterface(Block.Properties.of(Material.METAL), "redstone_interface");
    //main part
    public static final MMBlockMainPartBase TEST = new TestMainPart();

    private RegisteredBlocks() {
    }

    public static void Init() {
    }
}
