package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.registry_lists.MMBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
                    MMBlocks.ORETIN,MMBlocks.ORESILVER,MMBlocks.ORESILVER_DEEPSLATE,
                    MMBlocks.BLOCKTIN,MMBlocks.BLOCKSILVER,
                    MMBlocks.RAWALUMINUM_BLOCK,MMBlocks.RAWCHROMITE_BLOCK,MMBlocks.RAWILMENITE_BLOCK,MMBlocks.RAWNICKEL_BLOCK,MMBlocks.RAWMANGANESE_BLOCK,MMBlocks.RAWNOLANITE_BLOCK,MMBlocks.RAWSILVER_BLOCK,MMBlocks.RAWTIN_BLOCK,MMBlocks.RAWTUNSTITE_BLOCK,
                    MMBlocks.MOLTEN_ALUMINUM_DISCARDED,MMBlocks.MOLTEN_BRONZE_DISCARDED,MMBlocks.MOLTEN_CHROMIUM_DISCARDED,MMBlocks.MOLTEN_COBALT_DISCARDED,MMBlocks.MOLTEN_COPPER_DISCARDED,MMBlocks.MOLTEN_GOLD_DISCARDED,MMBlocks.MOLTEN_SS_DISCARDED,MMBlocks.MOLTEN_HSS_DISCARDED,MMBlocks.MOLTEN_INVAR_DISCARDED,MMBlocks.MOLTEN_IRON_DISCARDED,MMBlocks.MOLTEN_MANGANESE_DISCARDED,MMBlocks.MOLTEN_NICKEL_DISCARDED,MMBlocks.MOLTEN_SILVER_DISCARDED,MMBlocks.MOLTEN_SMA_DISCARDED,MMBlocks.MOLTEN_STEEL_DISCARDED,MMBlocks.MOLTEN_TIN_DISCARDED,MMBlocks.MOLTEN_TITANIUM_DISCARDED,MMBlocks.MOLTEN_TUNGSTEN_DISCARDED,MMBlocks.MOLTEN_VANADIUM_DISCARDED,
                    MMBlocks.CONVEYOR_BELT_1,MMBlocks.CONVEYOR_BELT_2,MMBlocks.CONVEYOR_BELT_3,MMBlocks.PIPE_1,MMBlocks.PIPE_2,MMBlocks.PIPE_3,MMBlocks.CABLE_1,MMBlocks.CABLE_2,MMBlocks.CABLE_3,MMBlocks.CABLE_4,MMBlocks.SPLITTER,MMBlocks.MECHANICAL_ARM,MMBlocks.CHECK_VALVE,
                    MMBlocks.ANVIL_STONE
//锡、银、以及粗矿块、废金属块、物流块
            ).forEach(
                    registryObjectRegistryObjectPair -> {
                        ResourceKey<Block> key = registryObjectRegistryObjectPair.getKey().getKey();
                        tag(/*→*/BlockTags.NEEDS_STONE_TOOL/*←*/).add(key);//这个tag表示使用石质工具挖
                        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);//这个表示使用镐子挖掘
                    }
            );

            List.of(
                    MMBlocks.OREBAUXITE,MMBlocks.OREMANGANESE,MMBlocks.ORENICKEL,MMBlocks.ORECHROMITE,MMBlocks.OREGRAPHITE,MMBlocks.ORENOLANITE,MMBlocks.OREBAUXITE_NETHER,MMBlocks.OREMANGANESE_NETHER,MMBlocks.OREGRAPHITE_NETHER,MMBlocks.ORENOLANITE_NETHER,MMBlocks.ORECHROMITE_DEEPSLATE,MMBlocks.ORENOLANITE_DEEPSLATE,MMBlocks.OREBAUXITE_DEEPSLATE,
                    MMBlocks.BLOCKALUMINUM,MMBlocks.BLOCKMANGANESE,MMBlocks.BLOCKNICKEL,MMBlocks.BLOCKSTEEL,MMBlocks.BLOCKCHROMIUM,MMBlocks.BLOCKVANADIUM,MMBlocks.BLOCKCOBALT,MMBlocks.BLOCKBRONZE,MMBlocks.BLOCKINVAR,MMBlocks.BLOCKSS,MMBlocks.BLOCKHSS,MMBlocks.BLOCKSMA,
                    MMBlocks.CASING_1,MMBlocks.CASING_2,MMBlocks.CASING_3,MMBlocks.CASING_4,MMBlocks.MODULE_DTE,MMBlocks.MODULE_GENERATOR,MMBlocks.MODULE_HEAT_SINK,MMBlocks.MODULE_INTELLIGENT,MMBlocks.MODULE_PRESSURE_BEARING,MMBlocks.STEEL_SCAFFOLDING,MMBlocks.FIRE_BRICK_BLOCK,MMBlocks.TANK_BLOCK,MMBlocks.INTERFACE_DATA,MMBlocks.INTERFACE_ENERGY,MMBlocks.INTERFACE_FLUID,MMBlocks.INTERFACE_ITEM,
                    MMBlocks.ANVIL_COPPER,MMBlocks.ANVIL_STEEL,MMBlocks.GENERATOR,MMBlocks.LATHE,MMBlocks.WHEEL_GENERATOR,MMBlocks.CUTTER,MMBlocks.ELECTROLYSIS,MMBlocks.CENTRIFUGE,MMBlocks.ATOMIZATION,MMBlocks.PUMP
//其他金属、机械方块、模块、砖和储罐、大部分机器
            ).forEach(
                    registryObjectRegistryObjectPair -> {
                        ResourceKey<Block> key = registryObjectRegistryObjectPair.getKey().getKey();
                        tag(/*→*/BlockTags.NEEDS_IRON_TOOL/*←*/).add(key);//这个tag表示使用铁质工具挖
                        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);//这个表示使用镐子挖掘
                    }
            );

            List.of(
                    MMBlocks.ORETUNSTITE,MMBlocks.OREILMENITE,MMBlocks.OREILMENITE_DEEPSLATE,MMBlocks.ORETUNSTITE_DEEPSLATE,
                    MMBlocks.BLOCKTITANIUM,MMBlocks.BLOCKTUNGSTEN,MMBlocks.BLOCKTUNGSTEN_STEEL
//钛、钨
            ).forEach(
                    registryObjectRegistryObjectPair -> {
                        ResourceKey<Block> key = registryObjectRegistryObjectPair.getKey().getKey();
                        tag(/*→*/BlockTags.NEEDS_DIAMOND_TOOL/*←*/).add(key);//这个tag表示使用钻石工具挖
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
