package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.energy_io.TileFEInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.redstone_io.TileRedstoneInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.test.MMTestTileMainMart;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.DelegateTile;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.ExpandMachineBase;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.pump.TilePump;
import mfrf.micro_machinery.block.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.block.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.block.machines.single_block_machines.conveyor_belt.TileConveyBelt;
import mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell.TileCreativeEnergyCell;
import mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell.TileTestcr;
import mfrf.micro_machinery.block.machines.single_block_machines.cutter.TileCutter;
import mfrf.micro_machinery.block.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.block.machines.single_block_machines.energy_cable.TileEnergyCable;
import mfrf.micro_machinery.block.machines.single_block_machines.fluidpipe.FluidPipeTile;
import mfrf.micro_machinery.block.machines.single_block_machines.forge_anvil.TileAnvil;
import mfrf.micro_machinery.block.machines.single_block_machines.generator.TileGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.block.machines.single_block_machines.weld.TileWeld;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBlockEntityTypes {
    public static DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MicroMachinery.MODID);


    public static final RegistryObject<BlockEntityType<TileKlin>> TILE_KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> BlockEntityType.Builder.of(TileKlin::new, MMBlocks.KLIN.getKey().get()).build(null));
    //    public static final RegistryObject<BlockEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> BlockEntityType.Builder.of(TerTestTile::new, MMBlocks.TESTBLOCK.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileGenerator>> TILE_GENERATOR_TYPE = TILE_ENTITY_TYPE_REGISTER.register("generator", () -> BlockEntityType.Builder.of(TileGenerator::new, MMBlocks.GENERATOR.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileHandGenerator>> TILE_HAND_GENERATOR = TILE_ENTITY_TYPE_REGISTER.register("hand_generator", () -> BlockEntityType.Builder.of(TileHandGenerator::new, MMBlocks.HAND_GENERATOR.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileAnvil>> TILE_ANVIL_TYPE = TILE_ENTITY_TYPE_REGISTER.register("anvil", () -> BlockEntityType.Builder.of(TileAnvil::new, MMBlocks.ANVIL_STONE.getKey().get(), MMBlocks.ANVIL_COPPER.getKey().get(), MMBlocks.ANVIL_STEEL.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEnergyCable>> TILE_ENERGY_CABLE = TILE_ENTITY_TYPE_REGISTER.register("energy_cable", () -> BlockEntityType.Builder.of(TileEnergyCable::new, MMBlocks.CABLE_1.getKey().get(), MMBlocks.CABLE_2.getKey().get(), MMBlocks.CABLE_3.getKey().get(), MMBlocks.CABLE_4.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileCreativeEnergyCell>> TILE_ENERGY_CELL = TILE_ENTITY_TYPE_REGISTER.register("energy_cell", () -> BlockEntityType.Builder.of(TileCreativeEnergyCell::new, MMBlocks.CREATIVE_ENERGY_CELL.getKey().get()).build(null));
    //    public static final RegistryObject<BlockEntityType<TileLathe>> TILE_LATHE = TILE_ENTITY_TYPE_REGISTER.register("lathe", () -> BlockEntityType.Builder.of(TileLathe::new, MMBlocks.LATHE.getKey().get()).build(null));
//    public static final RegistryObject<BlockEntityType<TilePlaceHolder>> TILE_PLACEHOLDER = TILE_ENTITY_TYPE_REGISTER.register("place_holder", () -> BlockEntityType.Builder.of(TilePlaceHolder::new, MMMultiBlockHolderBase.PLACE_HOLDER_LIST.toArray(new Block[MMMultiBlockHolderBase.PLACE_HOLDER_LIST.size()])).build(null));
//    public static final RegistryObject<BlockEntityType<TileBlastFurnace>> TILE_BLAST_FURNACE = TILE_ENTITY_TYPE_REGISTER.register("blast_furnace", () -> BlockEntityType.Builder.of(TileBlastFurnace::new, MMBlocks.BLAST_FURNACE.getKey().get()).build(null));
    //    public static final RegistryObject<BlockEntityType<TileEtcher>> TILE_ETCHER = TILE_ENTITY_TYPE_REGISTER.register("etcher", () -> BlockEntityType.Builder.of(TileEtcher::new, MMBlocks.BLOCK_ETCHER.getKey().get()).build(null));
//    public static final RegistryObject<BlockEntityType<TileEnergyInterface>> TILE_ENERGY_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("energy_interface", () -> BlockEntityType.Builder.of(TileEnergyInterface::new, MMBlocks.ENERGY_INTERFACE_INPUT.getKey().get(), MMBlocks.ENERGY_INTERFACE_OUTPUT.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileElectrolysis>> TILE_ELECTROLYSIS = TILE_ENTITY_TYPE_REGISTER.register("electrolysis", () -> BlockEntityType.Builder.of(TileElectrolysis::new, MMBlocks.ELECTROLYSIS.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileCutter>> TILE_CUTTER = TILE_ENTITY_TYPE_REGISTER.register("cutter", () -> BlockEntityType.Builder.of(TileCutter::new, MMBlocks.CUTTER.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileCentrifuge>> TILE_CENTRIFUGE = TILE_ENTITY_TYPE_REGISTER.register("centrifuge", () -> BlockEntityType.Builder.of(TileCentrifuge::new, MMBlocks.CENTRIFUGE.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<FluidPipeTile>> FLUID_PIPE = TILE_ENTITY_TYPE_REGISTER.register("fluid_pipe", () -> BlockEntityType.Builder.of(FluidPipeTile::new, MMBlocks.PIPE_1.getKey().get(), MMBlocks.PIPE_2.getKey().get(), MMBlocks.PIPE_3.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileAtomization>> TILE_ATOMIZATION = TILE_ENTITY_TYPE_REGISTER.register("atomization", () -> BlockEntityType.Builder.of(TileAtomization::new, MMBlocks.ATOMIZATION.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TilePump>> TILE_PUMP = TILE_ENTITY_TYPE_REGISTER.register("pump", () -> BlockEntityType.Builder.of(TilePump::new, MMBlocks.PUMP.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileWeld>> TILE_WELD = TILE_ENTITY_TYPE_REGISTER.register("weld", () -> BlockEntityType.Builder.of(TileWeld::new, MMBlocks.WELD.getKey().get()).build(null));
    //    public static final RegistryObject<BlockEntityType<TileHugeContainer>> TILE_HUGE_CONTAINER = TILE_ENTITY_TYPE_REGISTER.register("huge_container", () -> BlockEntityType.Builder.of(TileHugeContainer::new, MMBlocks.HUGE_CONTAINER.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileConveyBelt>> TILE_CONVEY_BELT = TILE_ENTITY_TYPE_REGISTER.register("convey_belt", () -> BlockEntityType.Builder.of(TileConveyBelt::new, MMBlocks.CONVEYOR_BELT_1.getKey().get(), MMBlocks.CONVEYOR_BELT_2.getKey().get(), MMBlocks.CONVEYOR_BELT_3.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileTestcr>> TESTCR = TILE_ENTITY_TYPE_REGISTER.register("testcr", () -> BlockEntityType.Builder.of(TileTestcr::new, MMBlocks.TESTCR.getKey().get()).build(null));
    //todo: fix this
    public static final RegistryObject<BlockEntityType<MMTileMultiBlockPart>> MULTI_BLOCK_PART = TILE_ENTITY_TYPE_REGISTER.register("part", () -> BlockEntityType.Builder.of(MMTileMultiBlockPart::new, MMBlocks.MULTIBLOCK_PART.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileRedstoneInterface>> REDSTONE_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("redstone_interface", () -> BlockEntityType.Builder.of(TileRedstoneInterface::new, MMBlocks.INTERFACE_DATA.getKey().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileFEInterface>> FE_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("fe_interface", () -> BlockEntityType.Builder.of(TileFEInterface::new, MMBlocks.INTERFACE_ENERGY.getKey().get()).build(null));
    //
    public static final RegistryObject<BlockEntityType<MMTestTileMainMart>> TEST = TILE_ENTITY_TYPE_REGISTER.register("test", () -> BlockEntityType.Builder.of(MMTestTileMainMart::new, MMBlocks.TEST_MAIN_MART.get()).build(null));
    public static final RegistryObject<BlockEntityType<DelegateTile>> DELEGATE_TILE = TILE_ENTITY_TYPE_REGISTER.register("delegate_tile", () -> BlockEntityType.Builder.of(DelegateTile::new, ExpandMachineBase.main_parts.toArray(new ExpandMachineBase[]{})).build(null));
}