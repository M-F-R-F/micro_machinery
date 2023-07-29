package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.registry_lists.MMBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBiomeTagsProvider;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockPropertys extends BlockTagsProvider{

        public BlockPropertys(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, modId, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            List.of(
                    MMBlocks.ORETIN,MMBlocks.ORESILVER,MMBlocks.OREBAUXITE,MMBlocks.OREBAUXITE_NETHER,MMBlocks.BLOCKTIN,MMBlocks.BLOCKSILVER
                    //这玩意里面填对应的方块
                    //记得加逗号
            ).forEach(
                    registryObjectRegistryObjectPair -> {
                        ResourceKey<Block> key = registryObjectRegistryObjectPair.getKey().getKey();
                        //这里填对应的TAG
                        tag(/*→*/BlockTags.NEEDS_IRON_TOOL/*←*/).add(key);//这个tag表示使用铁质工具挖
                        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);//这个表示使用镐子挖掘
                    }
            );

            List.of(
                    MMBlocks.BELLOW
            ).forEach(registryObjectRegistryObjectPair -> {
                ResourceKey<Block> key = registryObjectRegistryObjectPair.getKey().getKey();
                tag(BlockTags.NEEDS_IRON_TOOL).add(key);
                tag(BlockTags.MINEABLE_WITH_AXE).add(key);//同样的，这里表示使用斧头
            });

//                STALINITE = makeBlockWithItem("stalinite", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.GLASS).strength(8.0f).requiresCorrectToolForDrops().noOcclusion())),
//                STEEL_SCAFFOLDING = makeBlockWithItem("steel_scaffolding", ()->new MMBlockBase(Block.Properties.of().sound(SoundType.STONE).strength(1.0f).requiresCorrectToolForDrops().noOcclusion())),
//                // ORECOPPER : harvestLevel(1)
//                // ORETIN : harvestLevel(1)
//                // OREILMENITE : harvestLevel(3)
//                // ORESILVER : harvestLevel(1)
//                // OREPYROLUSITE : harvestLevel(2)
//                // OREGRAPHITE : harvestLevel(2)
//                // ORECHROMITE : harvestLevel(3)
//                // OREBAUXITE : harvestLevel(1)
//                // OREFERROMANGANESE : harvestLevel(3)
//                // ORENICKEL : harvestLevel(2)
//                // ORENOLANITE : harvestLevel(2)
//                // OREPYROLUSITE_NETHER : harvestLevel(2)
//                // OREGRAPHITE_NETHER : harvestLevel(2)
//                // OREBAUXITE_NETHER : harvestLevel(1)
//                // OREFERROMANGANESE_NETHER : harvestLevel(3)
//                // ORENOLANITE_NETHER : harvestLevel(2)
//                // ORETUNSTITE : harvestLevel(3)
//                // BLOCKCOPPER : harvestLevel(2)
//                // BLOCKTIN : harvestLevel(1)
//                // BLOCKBRONZE : harvestLevel(2)
//                // BLOCKSTEEL : harvestLevel(2)
//                // BLOCKSS : harvestLevel(3)
//                // BLOCKTUNGSTEN : harvestLevel(3)
//                // BLOCKNICKEL : harvestLevel(2)
//                // BLOCKTUNGSTEN_STEEL : harvestLevel(3)
//                // BLOCKINVAR : harvestLevel(2)
//                // BLOCKHSS : harvestLevel(3)
//                // BLOCKSILVER : harvestLevel(1)
//                // BLOCKCHROMIUM : harvestLevel(2)
//                // BLOCKVANADIUM : harvestLevel(2)
//                // BLOCKCOBALT : harvestLevel(3)
//                // BLOCKTITANIUM : harvestLevel(3)
//                // BLOCKALUMINUM : harvestLevel(2)
//                // BLOCKNCALLOY : harvestLevel(2)
//                // BLOCKMANGANESE : harvestLevel(2)
//                // COILCOPPER : harvestLevel(1)
//                // COILNICKEL : harvestLevel(2)
//                // COILALUMINUM : harvestLevel(2)
//                // COILTUNGSTEN : harvestLevel(3)
//                // COILCOBALT : harvestLevel(3)
//                // CASING_1 : harvestLevel(1)
//                // CASING_2 : harvestLevel(1)
//                // CASING_3 : harvestLevel(2)
//                // CASING_4 : harvestLevel(3)
//                // MOLTEN_COPPER_DISCARDED : harvestLevel(1)
//                // MOLTEN_TIN_DISCARDED : harvestLevel(1)
//                // MOLTEN_BRONZE_DISCARDED : harvestLevel(1)
//                // MOLTEN_STEEL_DISCARDED : harvestLevel(1)
//                // MOLTEN_SS_DISCARDED : harvestLevel(1)
//                // MOLTEN_TUNGSTEN_DISCARDED : harvestLevel(1)
//                // MOLTEN_NICKEL_DISCARDED : harvestLevel(1)
//                // MOLTEN_INVAR_DISCARDED : harvestLevel(1)
//                // MOLTEN_HSS_DISCARDED : harvestLevel(1)
//                // MOLTEN_SILVER_DISCARDED : harvestLevel(1)
//                // MOLTEN_GOLD_DISCARDED : harvestLevel(1)
//                // MOLTEN_MANGANESE_DISCARDED : harvestLevel(1)
//                // MOLTEN_CHROMIUM_DISCARDED : harvestLevel(1)
//                // MOLTEN_VANADIUM_DISCARDED : harvestLevel(1)
//                // MOLTEN_COBALT_DISCARDED : harvestLevel(1)
//                // MOLTEN_TITANIUM_DISCARDED : harvestLevel(1)
//                // MOLTEN_ALUMINUM_DISCARDED : harvestLevel(1)
//                // MOLTEN_NCALLOY_DISCARDED : harvestLevel(1)
//                // MOLTEN_FERROCHROME_DISCARDED : harvestLevel(1)
//                // MOLTEN_IRON_DISCARDED : harvestLevel(1)
//                // MODULE_GENERATOR : harvestLevel(2)
//                // MODULE_HEAT_SINK : harvestLevel(2)
//                // MODULE_PRESSURE_BEARING : harvestLevel(2)
//                // MODULE_TRANSMISSION_1 : harvestLevel(2)
//                // MODULE_TRANSMISSION_2 : harvestLevel(2)
//                // MODULE_TRANSMISSION_3 : harvestLevel(2)
//                // MODULE_TRANSMISSION_4 : harvestLevel(2)
//                // MODULE_TRANSMISSION_5 : harvestLevel(2)
//                // PCM : harvestLevel(1)
//                // TANK_BLOCK : harvestLevel(1)
//                // GRAPHITE_CRUCIBLE : harvestLevel(1)
//                // SLAG_CONCRETE : harvestLevel(1)
//                // CLAY_BRICK_BLOCK : harvestLevel(1)
//                // FIRE_BRICK_BLOCK : harvestLevel(1)
//                // BELLOW : harvestLevel(1)
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
        }
}
