package mfrf.micro_machinery.registry_lists;

import com.google.common.base.Supplier;
import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.MMBlockBase;
import mfrf.micro_machinery.block.MMDirectionalBlock;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.energy_io.BlockFEInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.redstone_io.BlockRedstoneInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.test.MMTestBlockMainPart;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.pump.BlockPump;
import mfrf.micro_machinery.block.machines.single_block_machines.atomization.BlockAtomization;
import mfrf.micro_machinery.block.machines.single_block_machines.centrifuge.BlockCentrifuge;
import mfrf.micro_machinery.block.machines.single_block_machines.conveyor_belt.BlockConveyorBeltBase;
import mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell.BlockCreativeEnergyCell;
import mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell.testcr;
import mfrf.micro_machinery.block.machines.single_block_machines.cutter.BlockCutter;
import mfrf.micro_machinery.block.machines.single_block_machines.electrolysis.BlockElectrolysis;
import mfrf.micro_machinery.block.machines.single_block_machines.energy_cable.BlockEnergyCable;
import mfrf.micro_machinery.block.machines.single_block_machines.fluidpipe.FluidPipeBlock;
import mfrf.micro_machinery.block.machines.single_block_machines.forge_anvil.BlockAnvil;
import mfrf.micro_machinery.block.machines.single_block_machines.generator.BlockGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.klin.BlockKlin;
import mfrf.micro_machinery.block.machines.single_block_machines.weld.BlockWeld;
import mfrf.micro_machinery.enums.EnumAnvilType;
import mfrf.micro_machinery.enums.EnumCableMaterial;
import mfrf.micro_machinery.item.MMBlockItemBase;
import mfrf.micro_machinery.utils.TriFields;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;
import java.util.function.Function;

public class MMBlocks {
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MicroMachinery.MODID);
    //todo make data gen

    public static final Pair<RegistryObject<Block>, RegistryObject<Item>>
            //Deco
            //Ores
            ORETIN = makeBlockWithItem("ore_tin", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            OREILMENITE = makeBlockWithItem("ore_ilmenite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5.0f))),
            ORESILVER = makeBlockWithItem("ore_silver", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            OREMANGANESE = makeBlockWithItem("ore_manganese", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f))),
            OREGRAPHITE = makeBlockWithItem("ore_graphite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            ORECHROMITE = makeBlockWithItem("ore_chromite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(4.5f))),
            OREBAUXITE = makeBlockWithItem("ore_bauxite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.5f))),
            ORENICKEL = makeBlockWithItem("ore_nickel", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.5f))),
            ORENOLANITE = makeBlockWithItem("ore_nolanite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f))),
            ORETUNSTITE = makeBlockWithItem("ore_tunstite", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(7.0f))),
            OREMANGANESE_NETHER = makeBlockWithItem("ore_manganese_nether", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f))),
            OREGRAPHITE_NETHER = makeBlockWithItem("ore_graphite_nether", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            OREBAUXITE_NETHER = makeBlockWithItem("ore_bauxite_nether", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.5f))),
            ORENOLANITE_NETHER = makeBlockWithItem("ore_nolanite_nether", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f))),
            OREILMENITE_DEEPSLATE = makeBlockWithItem("ore_ilmenite_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5.5f))),
            ORECHROMITE_DEEPSLATE = makeBlockWithItem("ore_chromite_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5.0f))),
            ORENOLANITE_DEEPSLATE = makeBlockWithItem("ore_nolanite_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.5f))),
            ORETUNSTITE_DEEPSLATE = makeBlockWithItem("ore_tunstite_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(8.0f))),
            ORESILVER_DEEPSLATE = makeBlockWithItem("ore_silver_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.5f))),
            OREBAUXITE_DEEPSLATE = makeBlockWithItem("ore_bauxite_deepslate", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f))),
    //oreblock
    BLOCKTIN = makeBlockWithItem("block_tin", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.5f))),
            BLOCKSILVER = makeBlockWithItem("block_silver", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            BLOCKNICKEL = makeBlockWithItem("block_nickel", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.5f))),
            BLOCKALUMINUM = makeBlockWithItem("block_aluminum", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.0f))),
            BLOCKMANGANESE = makeBlockWithItem("block_manganese", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(4.0f))),

    BLOCKSTEEL = makeBlockWithItem("block_steel", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.5f))),
            BLOCKCHROMIUM = makeBlockWithItem("block_chromium", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(6.5f))),
            BLOCKVANADIUM = makeBlockWithItem("block_vanadium", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKCOBALT = makeBlockWithItem("block_cobalt", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKTITANIUM = makeBlockWithItem("block_titanium", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(7.0f))),
            BLOCKTUNGSTEN = makeBlockWithItem("block_tungsten", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(7.0f))),

    BLOCKBRONZE = makeBlockWithItem("block_bronze", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(4.5f))),
            BLOCKINVAR = makeBlockWithItem("block_invar", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(4.0f))),
            BLOCKSS = makeBlockWithItem("block_ss", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKHSS = makeBlockWithItem("block_hss", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(6.0f))),
            BLOCKTUNGSTEN_STEEL = makeBlockWithItem("block_tungsten_steel", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(7.0f))),
            BLOCKSMA = makeBlockWithItem("block_sma", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(4.0f))),
    //raw
    RAWTIN_BLOCK = makeBlockWithItem("raw_tin_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWSILVER_BLOCK = makeBlockWithItem("raw_silver_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWNICKEL_BLOCK = makeBlockWithItem("raw_nickel_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWALUMINUM_BLOCK = makeBlockWithItem("raw_aluminum_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWMANGANESE_BLOCK = makeBlockWithItem("raw_manganese_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWCHROMITE_BLOCK = makeBlockWithItem("raw_chromite_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWNOLANITE_BLOCK = makeBlockWithItem("raw_nolanite_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWILMENITE_BLOCK = makeBlockWithItem("raw_ilmenite_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            RAWTUNSTITE_BLOCK = makeBlockWithItem("raw_tunstite_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
    //discarded metal
    MOLTEN_TIN_DISCARDED = makeBlockWithItem("molten_tin_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_SILVER_DISCARDED = makeBlockWithItem("molten_silver_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_NICKEL_DISCARDED = makeBlockWithItem("molten_nickel_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_ALUMINUM_DISCARDED = makeBlockWithItem("molten_aluminum_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_MANGANESE_DISCARDED = makeBlockWithItem("molten_manganese_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_STEEL_DISCARDED = makeBlockWithItem("molten_steel_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_CHROMIUM_DISCARDED = makeBlockWithItem("molten_chromium_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_VANADIUM_DISCARDED = makeBlockWithItem("molten_vanadium_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_COBALT_DISCARDED = makeBlockWithItem("molten_cobalt_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_TITANIUM_DISCARDED = makeBlockWithItem("molten_titanium_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_TUNGSTEN_DISCARDED = makeBlockWithItem("molten_tungsten_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_BRONZE_DISCARDED = makeBlockWithItem("molten_bronze_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_INVAR_DISCARDED = makeBlockWithItem("molten_invar_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_SS_DISCARDED = makeBlockWithItem("molten_ss_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_HSS_DISCARDED = makeBlockWithItem("molten_hss_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_SMA_DISCARDED = makeBlockWithItem("molten_sma_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_COPPER_DISCARDED = makeBlockWithItem("molten_copper_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_IRON_DISCARDED = makeBlockWithItem("molten_iron_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_GOLD_DISCARDED = makeBlockWithItem("molten_gold_discarded", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(2.0f))),
    //machine casing
    CASING_1 = makeBlockWithItem("casing_1", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(2.0f))),
            CASING_2 = makeBlockWithItem("casing_2", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            CASING_3 = makeBlockWithItem("casing_3", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(4.0f))),
            CASING_4 = makeBlockWithItem("casing_4", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(5.0f))),
    //module
    STEEL_SCAFFOLDING = makeBlockWithItem("steel_scaffolding", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion())),
            MODULE_GENERATOR = makeBlockWithItem("module_generator", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_HEAT_SINK = makeBlockWithItem("module_heat_sink", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_PRESSURE_BEARING = makeBlockWithItem("module_pressure_bearing", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            INTERFACE_ENERGY = makeBlockWithItem("interface_energy", () -> new BlockFEInterface(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            INTERFACE_DATA = makeBlockWithItem("interface_data", () -> new BlockRedstoneInterface(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            INTERFACE_ITEM = makeBlockWithItem("interface_item", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            INTERFACE_FLUID = makeBlockWithItem("interface_fluid", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_DTE = makeBlockWithItem("module_dte", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_INTELLIGENT = makeBlockWithItem("module_intelligent", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
    //other
    FIRE_BRICK_BLOCK = makeBlockWithItem("fire_brick_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
            TANK_BLOCK = makeBlockWithItem("tank_block", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(1.5f))),
            BELLOW/*待删除*/ = makeBlockWithItem("bellow", () -> new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f))),
    //machine
    KLIN/*待更改为多方块*/ = makeBlockWithItem("klin", () -> new BlockKlin(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(2.0f))),
            GENERATOR = makeBlockWithItem("generator", () -> new BlockGenerator(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            HAND_GENERATOR/*待删除*/ = makeBlockWithItem("hand_generator", () -> new BlockHandGenerator(Block.Properties.of().sound(SoundType.METAL).strength(3).noOcclusion().requiresCorrectToolForDrops())),
            ANVIL_STONE = makeBlockWithItem("anvil_stone", () -> new BlockAnvil(Block.Properties.of().sound(SoundType.STONE).noOcclusion().strength(2.0f).requiresCorrectToolForDrops(), EnumAnvilType.STONE, 16)),
            ANVIL_COPPER = makeBlockWithItem("anvil_copper", () -> new BlockAnvil(Block.Properties.of().sound(SoundType.ANVIL).noOcclusion().strength(3.0f).requiresCorrectToolForDrops(), EnumAnvilType.BRONZE, 16)),
            ANVIL_STEEL = makeBlockWithItem("anvil_steel", () -> new BlockAnvil(Block.Properties.of().sound(SoundType.ANVIL).noOcclusion().strength(4.0f).requiresCorrectToolForDrops(), EnumAnvilType.PIGIRON, 12)),
            CREATIVE_ENERGY_CELL = makeBlockWithItem("creative_energy_cell", () -> new BlockCreativeEnergyCell(Block.Properties.of().sound(SoundType.METAL))),
            ELECTROLYSIS = makeBlockWithItem("electrolysis", () -> new BlockElectrolysis(Block.Properties.of().sound(SoundType.METAL))),
            CUTTER = makeBlockWithItem("cutter", () -> new BlockCutter(Block.Properties.of().sound(SoundType.METAL))),
            CENTRIFUGE = makeBlockWithItem("centrifuge", () -> new BlockCentrifuge(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            ATOMIZATION = makeBlockWithItem("atomization", () -> new BlockAtomization(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(3.0f))),
            WELD/*待删除*/ = makeBlockWithItem("weld", () -> new BlockWeld(Block.Properties.of().sound(SoundType.METAL))),
            LATHE = makeBlockWithItem("lathe", () -> new BlockWeld(Block.Properties.of().sound(SoundType.METAL))),
            WHEEL_GENERATOR = makeBlockWithItem("wheel_generator", () -> new BlockWeld(Block.Properties.of().sound(SoundType.WOOD))),
            PUMP = makeBlockWithItem("pump", () -> new BlockPump(Block.Properties.of().sound(SoundType.METAL).noOcclusion())),

    //    public static final Block BLOCK_ETCHER = new BlockEtcher(Block.Properties.of().sound(SoundType.METAL)),

    //cable
    CABLE_1 = makeBlockWithItem("cable_1", () -> new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(3.0f), EnumCableMaterial.LEVEL1)),
            CABLE_2 = makeBlockWithItem("cable_2", () -> new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(3.0f), EnumCableMaterial.LEVEL2)),
            CABLE_3 = makeBlockWithItem("cable_3", () -> new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(3.0f), EnumCableMaterial.LEVEL3)),
            CABLE_4 = makeBlockWithItem("cable_4", () -> new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(3.0f), EnumCableMaterial.LEVEL4)),
            TESTCR = makeBlockWithItem("testcr", testcr::new),
    //convey belt
    CONVEYOR_BELT_1 = makeBlockWithItem("conveyor_belt_1", () -> new BlockConveyorBeltBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(2.0f), TriFields.of(Config.CONVEY_BELT_1_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_1_TRANSMIT_SPEED::get, Config.CONVEY_BELT_1_TRANSMIT_STACK_SIZE::get))),
            CONVEYOR_BELT_2 = makeBlockWithItem("conveyor_belt_2", () -> new BlockConveyorBeltBase(Block.Properties.of().sound(SoundType.METAL).noOcclusion().dynamicShape().requiresCorrectToolForDrops().strength(2.0f), TriFields.of(Config.CONVEY_BELT_2_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_2_TRANSMIT_SPEED::get, Config.CONVEY_BELT_2_TRANSMIT_STACK_SIZE::get))),
            CONVEYOR_BELT_3 = makeBlockWithItem("conveyor_belt_3", () -> new BlockConveyorBeltBase(Block.Properties.of().sound(SoundType.METAL).dynamicShape().noOcclusion().requiresCorrectToolForDrops().strength(2.0f), TriFields.of(Config.CONVEY_BELT_3_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_3_TRANSMIT_SPEED::get, Config.CONVEY_BELT_3_TRANSMIT_STACK_SIZE::get))),
            SPLITTER = makeBlockWithItem("splitter", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(5.0f))),
            MECHANICAL_ARM = makeBlockWithItem("mechanical_arm", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(5.0f))),
    //fluid pipe
    PIPE_1 = makeBlockWithItem("pipe_1", () -> new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL).dynamicShape().noOcclusion())),
            PIPE_2 = makeBlockWithItem("pipe_2", () -> new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL).dynamicShape().noOcclusion())),
            PIPE_3 = makeBlockWithItem("pipe_3", () -> new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL).dynamicShape().noOcclusion())),
            CHECK_VALVE = makeBlockWithItem("check_valve", () -> new MMDirectionalBlock(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().strength(5.0f)));
    //multiBlock

    //ter_util
    public static final RegistryObject<MMBlockBase>
            BELLOW_RIGHT = BLOCK_REGISTER.register("bellow_right", MMBlockBase::new),
            BELLOW_LEFT = BLOCK_REGISTER.register("bellow_left", MMBlockBase::new),
            ETCHER_1 = BLOCK_REGISTER.register("etcher_1", MMBlockBase::new),
            FORGING_PRESS_1 = BLOCK_REGISTER.register("forging_press_1", MMBlockBase::new),
            CRUSHER_1 = BLOCK_REGISTER.register("crusher_1", MMBlockBase::new),
            CATING_MACHINE_1 = BLOCK_REGISTER.register("cating_machine_1", MMBlockBase::new),
            THICKENER_1 = BLOCK_REGISTER.register("thickener_1", MMBlockBase::new);
    public static final RegistryObject<MMDirectionalBlock>
            HAND_GENERATOR_1 = BLOCK_REGISTER.register("hand_generator_handle", MMDirectionalBlock::new);
//     /**
//      * this is machine with multi_block
//      */
//     public static final Block
//             FORGING_PRESS = new MMBlockBase("forging_press", true),
//             REACTION_STILL = new MMBlockBase("reaction_still", true),
//             SPHERICAL_TANK = new MMBlockBase("spherical_tank", true),
//             THERMAL_POWER_PLANT = new MMBlockBase("thermal_power_plant", true),
//             FLYWHEEL = new MMBlockBase("flywheel", true);

    //     /**
//      * Multi Block main parts
//      */
    public static final RegistryObject<MMTestBlockMainPart> TEST_MAIN_MART = BLOCK_REGISTER.register("test_main_mart", () -> new MMTestBlockMainPart(MMBlockBase.DEFAULT_PROPERTIES, "test"));


    //     /**
//      * new system of multi block
//      */
//     //part
    public static final RegistryObject<MMBlockMultiBlockPart> MULTIBLOCK_PART = BLOCK_REGISTER.register("part", () -> new MMBlockMultiBlockPart(Block.Properties.of().sound(SoundType.METAL).strength(-1)));

    public static Pair<RegistryObject<Block>, RegistryObject<Item>> makeBlockWithItem(String name, Supplier<Block> block, Function<RegistryObject<Block>, Supplier<BlockItem>> item) {
        RegistryObject<Block> ret_block = BLOCK_REGISTER.register(name, block);
        RegistryObject<Item> ret_item = MMItems.ITEM_REGISTER.register(name, item.apply(ret_block));
        return Pair.of(ret_block, ret_item);
    }

    public static Pair<RegistryObject<Block>, RegistryObject<Item>> makeBlockWithItem(String name, Lazy<Block> block) {
        RegistryObject<Block> ret_block = BLOCK_REGISTER.register(name, block);
        RegistryObject<Item> ret_item = MMItems.ITEM_REGISTER.register(name,ret_block.lazyMap(MMBlockItemBase::new) );
        return Pair.of(ret_block, ret_item);
    }


}
