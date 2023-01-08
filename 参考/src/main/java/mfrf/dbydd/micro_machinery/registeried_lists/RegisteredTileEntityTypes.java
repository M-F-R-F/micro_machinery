package mfrf.dbydd.micro_machinery.registeried_lists;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace.TileBlastFurnace;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.huge_container.TileHugeContainer;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.energy_interface.TileEnergyInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.pump.TilePump;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io.TileRedstoneInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.test.TestTileMainPart;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt.TileConveyBelt;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell.TileCreativeEnergyCell;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.cutter.TileCutter;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.energy_cable.TileEnergyCable;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.fluidpipe.FluidPipeTile;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.forge_anvil.TileAnvil;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
import mfrf.dbydd.micro_machinery.blocks.machines.ter_test.TerTestTile;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredTileEntityTypes {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Micro_Machinery.NAME);


    public static final RegistryObject<TileEntityType<TileKlin>> TILE_KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> TileEntityType.Builder.create(TileKlin::new, RegisteredBlocks.KLIN).build(null));
    public static final RegistryObject<TileEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> TileEntityType.Builder.create(TerTestTile::new, RegisteredBlocks.TESTBLOCK).build(null));
    public static final RegistryObject<TileEntityType<TileGenerator>> TILE_GENERATOR_TYPE = TILE_ENTITY_TYPE_REGISTER.register("generator", () -> TileEntityType.Builder.create(TileGenerator::new, RegisteredBlocks.GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileHandGenerator>> TILE_HAND_GENERATOR = TILE_ENTITY_TYPE_REGISTER.register("hand_generator", () -> TileEntityType.Builder.create(TileHandGenerator::new, RegisteredBlocks.HAND_GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileAnvil>> TILE_ANVIL_TYPE = TILE_ENTITY_TYPE_REGISTER.register("anvil", () -> TileEntityType.Builder.create(TileAnvil::new, RegisteredBlocks.STONE_ANVIL, RegisteredBlocks.BRONZE_ANVIL, RegisteredBlocks.PIGIRON_ANVIL).build(null));
    public static final RegistryObject<TileEntityType<TileEnergyCable>> TILE_ENERGY_CABLE = TILE_ENTITY_TYPE_REGISTER.register("energy_cable", () -> TileEntityType.Builder.create(TileEnergyCable::new, RegisteredBlocks.ALUMINUM_CABLE, RegisteredBlocks.COBALT_CABLE, RegisteredBlocks.COPPER_CABLE, RegisteredBlocks.NICKEL_CABLE, RegisteredBlocks.TUNGSTEN_CABLE).build(null));
    public static final RegistryObject<TileEntityType<TileCreativeEnergyCell>> TILE_ENERGY_CELL = TILE_ENTITY_TYPE_REGISTER.register("energy_cell", () -> TileEntityType.Builder.create(TileCreativeEnergyCell::new, RegisteredBlocks.CREATIVE_ENERGY_CELL).build(null));
    public static final RegistryObject<TileEntityType<TileLathe>> TILE_LATHE = TILE_ENTITY_TYPE_REGISTER.register("lathe", () -> TileEntityType.Builder.create(TileLathe::new, RegisteredBlocks.LATHE).build(null));
    public static final RegistryObject<TileEntityType<TilePlaceHolder>> TILE_PLACEHOLDER = TILE_ENTITY_TYPE_REGISTER.register("place_holder", () -> TileEntityType.Builder.create(TilePlaceHolder::new, MMMultiBlockHolderBase.PLACE_HOLDER_LIST.toArray(new Block[MMMultiBlockHolderBase.PLACE_HOLDER_LIST.size()])).build(null));
    public static final RegistryObject<TileEntityType<TileBlastFurnace>> TILE_BLAST_FURNACE = TILE_ENTITY_TYPE_REGISTER.register("blast_furnace", () -> TileEntityType.Builder.create(TileBlastFurnace::new, RegisteredBlocks.BLAST_FURNACE).build(null));
    //    public static final RegistryObject<TileEntityType<TileEtcher>> TILE_ETCHER = TILE_ENTITY_TYPE_REGISTER.register("etcher", () -> TileEntityType.Builder.create(TileEtcher::new, RegisteredBlocks.BLOCK_ETCHER).build(null));
    public static final RegistryObject<TileEntityType<TileEnergyInterface>> TILE_ENERGY_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("energy_interface", () -> TileEntityType.Builder.create(TileEnergyInterface::new, RegisteredBlocks.ENERGY_INTERFACE_INPUT, RegisteredBlocks.ENERGY_INTERFACE_OUTPUT).build(null));
    public static final RegistryObject<TileEntityType<TileElectrolysis>> TILE_ELECTROLYSIS = TILE_ENTITY_TYPE_REGISTER.register("electrolysis", () -> TileEntityType.Builder.create(TileElectrolysis::new, RegisteredBlocks.ELECTROLYSIS).build(null));
    public static final RegistryObject<TileEntityType<TileCutter>> TILE_CUTTER = TILE_ENTITY_TYPE_REGISTER.register("cutter", () -> TileEntityType.Builder.create(TileCutter::new, RegisteredBlocks.CUTTER).build(null));
    public static final RegistryObject<TileEntityType<TileCentrifuge>> TILE_CENTRIFUGE = TILE_ENTITY_TYPE_REGISTER.register("centrifuge", () -> TileEntityType.Builder.create(TileCentrifuge::new, RegisteredBlocks.CENTRIFUGE).build(null));
    public static final RegistryObject<TileEntityType<FluidPipeTile>> TILE_FLUID_PIPE_DEMO = TILE_ENTITY_TYPE_REGISTER.register("fluid_pipe", () -> TileEntityType.Builder.create(FluidPipeTile::new, RegisteredBlocks.PIPE_INVAR, RegisteredBlocks.PIPE_STAINLESS_STEEL, RegisteredBlocks.PIPE_TUNGSTEN_STEEL).build(null));
    public static final RegistryObject<TileEntityType<TileAtomization>> TILE_ATOMIZATION = TILE_ENTITY_TYPE_REGISTER.register("atomization", () -> TileEntityType.Builder.create(TileAtomization::new, RegisteredBlocks.ATOMIZATION).build(null));
    public static final RegistryObject<TileEntityType<TilePump>> TILE_PUMP = TILE_ENTITY_TYPE_REGISTER.register("pump", () -> TileEntityType.Builder.create(TilePump::new, RegisteredBlocks.PUMP).build(null));
    public static final RegistryObject<TileEntityType<TileWeld>> TILE_WELD = TILE_ENTITY_TYPE_REGISTER.register("weld", () -> TileEntityType.Builder.create(TileWeld::new, RegisteredBlocks.WELD).build(null));
    public static final RegistryObject<TileEntityType<TileHugeContainer>> TILE_HUGE_CONTAINER = TILE_ENTITY_TYPE_REGISTER.register("huge_container", () -> TileEntityType.Builder.create(TileHugeContainer::new, RegisteredBlocks.HUGE_CONTAINER).build(null));
    public static final RegistryObject<TileEntityType<TileConveyBelt>> TILE_CONVEY_BELT = TILE_ENTITY_TYPE_REGISTER.register("convey_belt", () -> TileEntityType.Builder.create(TileConveyBelt::new, RegisteredBlocks.CONVEYOR_BELT_1, RegisteredBlocks.CONVEYOR_BELT_2, RegisteredBlocks.CONVEYOR_BELT_3).build(null));
    public static final RegistryObject<TileEntityType<MMTileMultiBlockPart>> MULTI_BLOCK_PART = TILE_ENTITY_TYPE_REGISTER.register("part", () -> TileEntityType.Builder.create(MMTileMultiBlockPart::new, RegisteredBlocks.MULTIBLOCK_PART).build(null));
    public static final RegistryObject<TileEntityType<TileRedstoneInterface>> REDSTONE_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("redstone_interface", () -> TileEntityType.Builder.create(TileRedstoneInterface::new, RegisteredBlocks.REDSTONE_INTERFACE).build(null));

    public static final RegistryObject<TileEntityType<TestTileMainPart>> TEST = TILE_ENTITY_TYPE_REGISTER.register("test", () -> TileEntityType.Builder.create(TestTileMainPart::new, RegisteredBlocks.TEST).build(null));
}