package mfrf.micro_machinery.registry_lists;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Supplier;
import com.ibm.icu.impl.number.Properties;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.MMBlockBase;
import mfrf.micro_machinery.item.MMBlockItemBase;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBlocks {
        public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,MicroMachinery.MODID);
        //todo make data gen

        public static final Pair<RegistryObject<Block>, RegistryObject<Item>>
                STALINITE = makeBlockWithItem("stalinite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.GLASS).strength(8.0f).requiresCorrectToolForDrops().noOcclusion())),
//Deco
                STEEL_SCAFFOLDING = makeBlockWithItem("steel_scaffolding", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).strength(1.0f).requiresCorrectToolForDrops().noOcclusion())),
//Ores
            ORECOPPER = makeBlockWithItem("orecopper", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.5f))),
            ORETIN = makeBlockWithItem("oretin", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            OREILMENITE = makeBlockWithItem("oreilmenite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(5.0f))),
            ORESILVER = makeBlockWithItem("oresilver", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            OREPYROLUSITE = makeBlockWithItem("orepyrolusite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            OREGRAPHITE = makeBlockWithItem("oregraphite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f))),
            ORECHROMITE = makeBlockWithItem("orechromite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(4.5f))),
            OREBAUXITE = makeBlockWithItem("orebauxite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.5f))),
            OREFERROMANGANESE = makeBlockWithItem("oreferromanganese", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(3.0f))),
            ORENICKEL = makeBlockWithItem("orenickel", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.5f))),
            ORENOLANITE = makeBlockWithItem("orenolanite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            OREPYROLUSITE_NETHER = makeBlockWithItem("orepyrolusite_nether", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            OREGRAPHITE_NETHER = makeBlockWithItem("oregraphite_nether", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f))),
            OREBAUXITE_NETHER = makeBlockWithItem("orebauxite_nether", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.5f))),
            OREFERROMANGANESE_NETHER = makeBlockWithItem("oreferromanganese_nether", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(3.0f))),
            ORENOLANITE_NETHER = makeBlockWithItem("orenolanite_nether", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            ORETUNSTITE = makeBlockWithItem("oretunstite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(7.0f))),

    //oreblock
            BLOCKCOPPER = makeBlockWithItem("blockcopper", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.0f))),
            BLOCKTIN = makeBlockWithItem("blocktin", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.5f))),
            BLOCKBRONZE = makeBlockWithItem("blockbronze", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.5f))),
            BLOCKSTEEL = makeBlockWithItem("blocksteel", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.5f))),
            BLOCKSS = makeBlockWithItem("blockss", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKTUNGSTEN = makeBlockWithItem("blocktungsten", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(7.0f))),
            BLOCKNICKEL = makeBlockWithItem("blocknickel", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.5f))),
            BLOCKTUNGSTEN_STEEL = makeBlockWithItem("blocktungsten_steel", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(7.0f))),
            BLOCKINVAR = makeBlockWithItem("blockinvar", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.0f))),
            BLOCKHSS = makeBlockWithItem("blockhss", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(6.0f))),
            BLOCKSILVER = makeBlockWithItem("blocksilver", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            BLOCKCHROMIUM = makeBlockWithItem("blockchromium", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5f))),
            BLOCKVANADIUM = makeBlockWithItem("blockvanadium", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKCOBALT = makeBlockWithItem("blockcobalt", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(5.0f))),
            BLOCKTITANIUM = makeBlockWithItem("blocktitanium", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(7.0f))),
            BLOCKALUMINUM = makeBlockWithItem("blockaluminum", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            BLOCKNCALLOY = makeBlockWithItem("blockncalloy", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.0f))),
            BLOCKMANGANESE = makeBlockWithItem("blockmanganese", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.0f))),
    //coil
            COILCOPPER = makeBlockWithItem("coilcopper", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            COILNICKEL = makeBlockWithItem("coilnickel", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(2.5f))),
            COILALUMINUM = makeBlockWithItem("coilaluminum", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f))),
            COILTUNGSTEN = makeBlockWithItem("coiltungsten", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(6.0f))),
            COILCOBALT = makeBlockWithItem("coilcobalt", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(4.0f))),
    //machine casing
            CASING_1 = makeBlockWithItem("casing_1", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            CASING_2 = makeBlockWithItem("casing_2", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0f))),
            CASING_3 = makeBlockWithItem("casing_3", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(4.0f))),
            CASING_4 = makeBlockWithItem("casing_4", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(3).requiresCorrectToolForDrops().strength(5.0f))),
    //discarded metal
            MOLTEN_COPPER_DISCARDED = makeBlockWithItem("molten_copper_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_TIN_DISCARDED = makeBlockWithItem("molten_tin_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_BRONZE_DISCARDED = makeBlockWithItem("molten_bronze_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_STEEL_DISCARDED = makeBlockWithItem("molten_steel_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_SS_DISCARDED = makeBlockWithItem("molten_ss_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_TUNGSTEN_DISCARDED = makeBlockWithItem("molten_tungsten_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_NICKEL_DISCARDED = makeBlockWithItem("molten_nickel_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_INVAR_DISCARDED = makeBlockWithItem("molten_invar_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_HSS_DISCARDED = makeBlockWithItem("molten_hss_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_SILVER_DISCARDED = makeBlockWithItem("molten_silver_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_GOLD_DISCARDED = makeBlockWithItem("molten_gold_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_MANGANESE_DISCARDED = makeBlockWithItem("molten_manganese_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_CHROMIUM_DISCARDED = makeBlockWithItem("molten_chromium_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_VANADIUM_DISCARDED = makeBlockWithItem("molten_vanadium_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_COBALT_DISCARDED = makeBlockWithItem("molten_cobalt_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_TITANIUM_DISCARDED = makeBlockWithItem("molten_titanium_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_ALUMINUM_DISCARDED = makeBlockWithItem("molten_aluminum_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_NCALLOY_DISCARDED = makeBlockWithItem("molten_ncalloy_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_FERROCHROME_DISCARDED = makeBlockWithItem("molten_ferrochrome_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            MOLTEN_IRON_DISCARDED = makeBlockWithItem("molten_iron_discarded", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
    //modules
            MODULE_GENERATOR = makeBlockWithItem("module_generator", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_HEAT_SINK = makeBlockWithItem("module_heat_sink", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_PRESSURE_BEARING = makeBlockWithItem("module_pressure_bearing", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_TRANSMISSION_1 = makeBlockWithItem("module_transmission_1", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_TRANSMISSION_2 = makeBlockWithItem("module_transmission_2", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_TRANSMISSION_3 = makeBlockWithItem("module_transmission_3", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_TRANSMISSION_4 = makeBlockWithItem("module_transmission_4", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
            MODULE_TRANSMISSION_5 = makeBlockWithItem("module_transmission_5", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f))),
    //other
            PCM = makeBlockWithItem("pcm", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.5f))),
            TANK_BLOCK = makeBlockWithItem("tank_block", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(1.5f))),
            GRAPHITE_CRUCIBLE = makeBlockWithItem("graphite_crucible", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(1.5f).noOcclusion())),
            SLAG_CONCRETE = makeBlockWithItem("slag_concrete", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(1.5f))),
            CLAY_BRICK_BLOCK = makeBlockWithItem("clay_brick_block", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            FIRE_BRICK_BLOCK = makeBlockWithItem("fire_brick_block", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f))),
            BELLOW = makeBlockWithItem("bellow", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops().strength(2.0f).harvestTool(ToolType.AXE)));
//     //machine
//     public static final Block
//             KLIN = new BlockKlin(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().harvestLevel(1).strength(2.0f)),
//             GENERATOR = new BlockGenerator(Block.Properties.of().sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().harvestLevel(1).strength(3.0f)),
//             HAND_GENERATOR = new BlockHandGenerator(Block.Properties.of().sound(SoundType.METAL).strength(3).noOcclusion().requiresCorrectToolForDrops().harvestLevel(1)),
//             STONE_ANVIL = new BlockAnvil(Block.Properties.of().sound(SoundType.ANVIL).noOcclusion().strength(2.0f).requiresCorrectToolForDrops().harvestLevel(1), "stone_anvil", EnumAnvilType.STONE, 16),
//             BRONZE_ANVIL = new BlockAnvil(Block.Properties.of().sound(SoundType.ANVIL).noOcclusion().strength(3.0f).requiresCorrectToolForDrops().harvestLevel(2), "bronze_anvil", EnumAnvilType.BRONZE, 16),
//             PIGIRON_ANVIL = new BlockAnvil(Block.Properties.of().sound(SoundType.ANVIL).noOcclusion().strength(4.0f).requiresCorrectToolForDrops().harvestLevel(3), "pigiron_anvil", EnumAnvilType.PIGIRON, 12),
//             CREATIVE_ENERGY_CELL = new BlockCreativeEnergyCell(Block.Properties.of().sound(SoundType.METAL)),
//             ELECTROLYSIS = new BlockElectrolysis(Block.Properties.of().sound(SoundType.METAL)),
//             CUTTER = new BlockCutter(Block.Properties.of().sound(SoundType.METAL)),
//             CENTRIFUGE = new BlockCentrifuge(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f), "centrifuge"),
//             ATOMIZATION = new BlockAtomization(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f), "atomization"),
//             WELD = new BlockWeld(Block.Properties.of().sound(SoundType.METAL));
//     //    public static final Block BLOCK_ETCHER = new BlockEtcher(Block.Properties.of().sound(SoundType.METAL)),

//     //cable
//     public static final Block
//             COPPER_CABLE = new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(1).requiresCorrectToolForDrops().strength(3.0f), "copper_cable", EnumCableMaterial.COPPER),
//             NICKEL_CABLE = new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f), "nickel_cable", EnumCableMaterial.NICKEL),
//             ALUMINUM_CABLE = new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(3.0f), "aluminum_cable", EnumCableMaterial.ALUMINUM),
//             TUNGSTEN_CABLE = new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(3).requiresCorrectToolForDrops().strength(3.0f), "tungsten_cable", EnumCableMaterial.TUNGSTEN),
//             COBALT_CABLE = new BlockEnergyCable(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(3).requiresCorrectToolForDrops().strength(3.0f), "cobalt_cable", EnumCableMaterial.COBALT);
//     //convey belt
//     public static final Block
//             CONVEYOR_BELT_1 = new BlockConveyorBelt(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f), "conveyor_belt_1", TriFields.of(Config.CONVEY_BELT_1_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_1_TRANSMIT_SPEED::get, Config.CONVEY_BELT_1_TRANSMIT_STACK_SIZE::get)),
//             CONVEYOR_BELT_2 = new BlockConveyorBelt(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f), "conveyor_belt_2", TriFields.of(Config.CONVEY_BELT_2_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_2_TRANSMIT_SPEED::get, Config.CONVEY_BELT_2_TRANSMIT_STACK_SIZE::get)),
//             CONVEYOR_BELT_3 = new BlockConveyorBelt(Block.Properties.of().sound(SoundType.METAL).noOcclusion().harvestLevel(2).requiresCorrectToolForDrops().strength(2.0f), "conveyor_belt_3", TriFields.of(Config.CONVEY_BELT_3_EXTRACT_INTERVAL::get, Config.CONVEY_BELT_3_TRANSMIT_SPEED::get, Config.CONVEY_BELT_3_TRANSMIT_STACK_SIZE::get));

//     //fluid pipe
//     public static final Block
//             PIPE_INVAR = new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL), "pipe_invar"),
//             PIPE_STAINLESS_STEEL = new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL), "pipe_stainless_steel"),
//             PIPE_TUNGSTEN_STEEL = new FluidPipeBlock(Block.Properties.of().sound(SoundType.METAL), "pipe_tungsten_steel");
//     //multiBlock

//     //component
//     public static final Block
//             BELLOW_RIGHT = new MMBlockBase("bellow_right", true),
//             BELLOW_LEFT = new MMBlockBase("bellow_left", true),
//             HAND_GENERATOR_1 = new BlockHandGenerator_Handler(),
//             ETCHER_1 = new MMBlockBase("etcher_1", true),
//             PUMP_1 = new MMBlockBase("pump_1", true),
//             FORGING_PRESS_1 = new MMBlockBase("forging_press_1", true),
//             CRUSHER_1 = new MMBlockBase("crusher_1", true),
//             CATING_MACHINE_1 = new MMBlockBase("cating_machine_1", true),
//             THICKENER_1 = new MMBlockBase("thickener_1", true);

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


//     /**
//      * new system of multi block
//      */
//     //part
//     public static final MMBlockMultiBlockPart MULTIBLOCK_PART = new MMBlockMultiBlockPart(Block.Properties.of().sound(SoundType.METAL).harvestLevel(-1).strength(-1), "part", true);
//     //component
//     public static final BlockRedstoneInterface REDSTONE_INTERFACE = new BlockRedstoneInterface(Block.Properties.of().sound(SoundType.METAL), "redstone_interface");
//     //main part
//     public static final MMBlockMainPartBase TEST = new TestMainPart();

public static Pair<RegistryObject<Block>, RegistryObject<Item>> makeBlockWithItem(String name,Supplier<Block> block, Function<RegistryObject<Block>,Supplier<BlockItem>> item) {
        RegistryObject<Block> ret_block = BLOCK_REGISTER.register(name,block);
        RegistryObject<Item> ret_item = MMItems.ITEM_REGISTER.register(name,item.apply(ret_block));
        return Pair.of(ret_block,ret_item);
    }

public static Pair<RegistryObject<Block>, RegistryObject<Item>> makeBlockWithItem(String name,Supplier<Block> block) {
        RegistryObject<Block> ret_block = BLOCK_REGISTER.register(name,block);
        RegistryObject<Item> ret_item = MMItems.ITEM_REGISTER.register(name,()->new MMBlockItemBase(ret_block.get()));
        return Pair.of(ret_block,ret_item);
    }

}
