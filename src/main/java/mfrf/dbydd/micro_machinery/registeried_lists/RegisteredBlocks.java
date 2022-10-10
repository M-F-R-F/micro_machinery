package mfrf.dbydd.micro_machinery.registeried_lists;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.lathe.BlockLathe;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace.BlockBlastFurnace;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.huge_container.BlockHugeContainer;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockPlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.energy_interface.BlockHolderEnergyInterfaceInput;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.energy_interface.BlockHolderEnergyInterfaceOutput;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.lever.LeverPlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.pump.BlockPump;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io.BlockRedstoneInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.test.TestMainPart;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.atomization.BlockAtomization;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.centrifuge.BlockCentrifuge;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt.BlockConveyorBelt;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell.BlockCreativeEnergyCell;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.cutter.BlockCutter;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.electrolysis.BlockElectrolysis;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.energy_cable.BlockEnergyCable;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.fluidpipe.FluidPipeBlock;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.forge_anvil.BlockAnvil;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.generator.BlockGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator_Handler;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin.BlockKlin;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.weld.BlockWeld;
import mfrf.dbydd.micro_machinery.blocks.machines.ter_test.TestBlock;
import mfrf.dbydd.micro_machinery.enums.EnumAnvilType;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RegisteredBlocks {

    public static final Block.Properties HARD1 = Block.Properties.create(Material.ROCK).harvestLevel(1).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD2 = Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD3 = Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE);

    public static final Block STALINITE = new MMBlockBase(Block.Properties.create(Material.GLASS).hardnessAndResistance(8.0f).harvestTool(ToolType.PICKAXE).noDrops().notSolid(), "stalinite");
    public static final Block STEEL_SCAFFOLDING = new MMBlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).notSolid(), "steel_scaffolding");
    //Ores
    public static final Block ORECOPPER = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orecopper");
    public static final Block ORETIN = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "oretin");
    public static final Block OREILMENITE = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "oreilmenite");
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
    public static final Block MOLTEN_COPPER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_copper_discarded");
    public static final Block MOLTEN_TIN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_tin_discarded");
    public static final Block MOLTEN_BRONZE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_bronze_discarded");
    public static final Block MOLTEN_STEEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_steel_discarded");
    public static final Block MOLTEN_SS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_ss_discarded");
    public static final Block MOLTEN_TUNGSTEN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_tungsten_discarded");
    public static final Block MOLTEN_NICKEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_nickel_discarded");
    public static final Block MOLTEN_INVAR_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_invar_discarded");
    public static final Block MOLTEN_HSS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_hss_discarded");
    public static final Block MOLTEN_SILVER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_silver_discarded");
    public static final Block MOLTEN_GOLD_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_gold_discarded");
    public static final Block MOLTEN_MANGANESE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_manganese_discarded");
    public static final Block MOLTEN_CHROMIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_chromium_discarded");
    public static final Block MOLTEN_VANADIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_vanadium_discarded");
    public static final Block MOLTEN_COBALT_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_cobalt_discarded");
    public static final Block MOLTEN_TITANIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_titanium_discarded");
    public static final Block MOLTEN_ALUMINUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_aluminum_discarded");
    public static final Block MOLTEN_NCALLOY_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_ncalloy_discarded");
    public static final Block MOLTEN_FERROCHROME_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_ferrochrome_discarded");
    public static final Block MOLTEN_IRON_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "molten_iron_discarded");
    //modules
    public static final Block MODULE_GENERATOR = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_generator");
    public static final Block MODULE_HEAT_SINK = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_heat_sink");
    public static final Block MODULE_PRESSURE_BEARING = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_pressure_bearing");
    public static final Block MODULE_TRANSMISSION_1 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_transmission_1");
    public static final Block MODULE_TRANSMISSION_2 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_transmission_2");
    public static final Block MODULE_TRANSMISSION_3 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_transmission_3");
    public static final Block MODULE_TRANSMISSION_4 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_transmission_4");
    public static final Block MODULE_TRANSMISSION_5 = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "module_transmission_5");
    //other
    public static final Block PCM = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "pcm");
    public static final Block TANK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "tank_block");
    public static final Block GRAPHITE_CRUCIBLE = new MMBlockBase(HARD1.hardnessAndResistance(1.5f).notSolid(), "graphite_crucible");
    public static final Block SLAG_CONCRETE = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "slag_concrete");
    public static final Block CLAY_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "clay_brick_block");
    public static final Block FIRE_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "fire_brick_block");
    public static final Block BELLOW = new MMBlockBase(HARD1.hardnessAndResistance(2.0f).harvestTool(ToolType.AXE), "bellow");
    //machine
    public static final Block KLIN = new BlockKlin(Block.Properties.create(Material.IRON).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(2.0f));
    public static final Block GENERATOR = new BlockGenerator(Block.Properties.create(Material.IRON).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3.0f));
    public static final Block HAND_GENERATOR = new BlockHandGenerator(Block.Properties.create(Material.IRON).hardnessAndResistance(3).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(1));
    public static final Block TESTBLOCK = new TestBlock(Block.Properties.create(Material.ROCK), "testblock");
    public static final Block STONE_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1), "stone_anvil", EnumAnvilType.STONE, 16);
    public static final Block BRONZE_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2), "bronze_anvil", EnumAnvilType.BRONZE, 16);
    public static final Block PIGIRON_ANVIL = new BlockAnvil(Block.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(4.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3), "pigiron_anvil", EnumAnvilType.PIGIRON, 12);
    public static final Block CREATIVE_ENERGY_CELL = new BlockCreativeEnergyCell(Block.Properties.create(Material.IRON));
    //    public static final Block BLOCK_ETCHER = new BlockEtcher(Block.Properties.create(Material.IRON));
    public static final Block ELECTROLYSIS = new BlockElectrolysis(Block.Properties.create(Material.IRON));
    public static final Block CUTTER = new BlockCutter(Block.Properties.create(Material.IRON));
    public static final Block CENTRIFUGE = new BlockCentrifuge(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "centrifuge");
    public static final Block ATOMIZATION = new BlockAtomization(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "atomization");
    public static final Block WELD = new BlockWeld(Block.Properties.create(Material.IRON));
    //cable
    public static final Block TESTENERGY_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid(), "test_cable", EnumCableMaterial.TEST);
    public static final Block COPPER_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid().harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "copper_cable", EnumCableMaterial.COPPER);
    public static final Block NICKEL_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "nickel_cable", EnumCableMaterial.NICKEL);
    public static final Block ALUMINUM_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "aluminum_cable", EnumCableMaterial.ALUMINUM);
    public static final Block TUNGSTEN_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid().harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "tungsten_cable", EnumCableMaterial.TUNGSTEN);
    public static final Block COBALT_CABLE = new BlockEnergyCable(Block.Properties.create(Material.IRON).notSolid().harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f), "cobalt_cable", EnumCableMaterial.COBALT);
    //pipe
    //todo converyerBelt
    public static final Block CONVEYER_BELT_1 = new BlockConveyorBelt(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_1", TriFields.of(Config.CONVEY_BELT_1_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_1_TRANSMIT_SPEED::get, Config.CONVEY_BELT_1_TRANSMIT_STACK_SIZE::get));
    public static final Block CONVEYER_BELT_2 = new BlockConveyorBelt(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_2", TriFields.of(Config.CONVEY_BELT_2_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_2_TRANSMIT_SPEED::get, Config.CONVEY_BELT_2_TRANSMIT_STACK_SIZE::get));
    public static final Block CONVEYER_BELT_3 = new BlockConveyorBelt(Block.Properties.create(Material.IRON).notSolid().harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f), "conveyor_belt_3", TriFields.of(Config.CONVEY_BELT_3_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_3_TRANSMIT_SPEED::get, Config.CONVEY_BELT_3_TRANSMIT_STACK_SIZE::get));

    //fluid pipe
    public static final Block PIPE_INVAR = new FluidPipeBlock(Block.Properties.create(Material.IRON), "pipe_invar");
    public static final Block PIPE_STAINLESS_STEEL = new FluidPipeBlock(Block.Properties.create(Material.IRON), "pipe_stainless_steel");
    public static final Block PIPE_TUNGSTEN_STEEL = new FluidPipeBlock(Block.Properties.create(Material.IRON), "pipe_tungsten_steel");
    //multiBlock
    public static final Block LATHE = new BlockLathe(Block.Properties.create(Material.IRON).notSolid().harvestLevel(0).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f), "lathe");
    public static final Block PLACE_HOLDER = new BlockPlaceHolder("multi_block_place_holder");
    public static final Block LEVER_PLACEHOLDER = new LeverPlaceHolder();
    public static final Block ENERGY_INTERFACE_INPUT = new BlockHolderEnergyInterfaceInput();
    public static final Block ENERGY_INTERFACE_OUTPUT = new BlockHolderEnergyInterfaceOutput();

    public static final Block BELLOW_RIGHT = new MMBlockBase("bellow_right", true);
    public static final Block BELLOW_LEFT = new MMBlockBase("bellow_left", true);
    public static final Block HAND_GENERATOR_1 = new BlockHandGenerator_Handler();
    public static final Block ETCHER_1 = new MMBlockBase("etcher_1", true);
    public static final Block PUMP_1 = new MMBlockBase("pump_1", true);
    public static final Block FORGING_PRESS_1 = new MMBlockBase("forging_press_1", true);
    public static final Block CRUSHER_1 = new MMBlockBase("crusher_1", true);
    public static final Block CATING_MACHINE_1 = new MMBlockBase("cating_machine_1", true);
    public static final Block THICKENER_1 = new MMBlockBase("thickener_1", true);

    /**
     * this is machine with multi_block
     */
    public static final Block FORGING_PRESS = new MMBlockBase("forging_press", true);
    public static final Block REACTION_STILL = new MMBlockBase("reaction_still", true);
    public static final Block SPHERICAL_TANK = new MMBlockBase("spherical_tank", true);
    public static final Block THERMAL_POWER_PLANT = new MMBlockBase("thermal_power_plant", true);
    public static final Block FLYWHEEL = new MMBlockBase("flywheel", true);

    /**
     * Multi Block main parts
     */
    public static final Block BLAST_FURNACE = new BlockBlastFurnace(Block.Properties.create(Material.ROCK));
    public static final Block PUMP = new BlockPump(Block.Properties.create(Material.IRON));
    public static final Block HUGE_CONTAINER = new BlockHugeContainer(Block.Properties.create(Material.IRON));


    /**
     * new system of multi block
     * todo fill data
     */

    public static final MMBlockMultiBlockPart MULTIBLOCK_PART = new MMBlockMultiBlockPart(Block.Properties.create(Material.IRON).harvestLevel(-1).hardnessAndResistance(-1), "part", true);
    public static final BlockRedstoneInterface REDSTONE_INTERFACE = new BlockRedstoneInterface(Block.Properties.create(Material.IRON), "redstone_interface");

    public static final MMBlockMainPartBase TEST = new TestMainPart();

    private RegisteredBlocks() {
    }

    public static void Init() {
    }
}
