package mfrf.micro_machinery.registeried_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io.TileRedstoneInterface;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.test.TestTileMainPart;
import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.blocks.machines.single_block_machines.conveyor_belt.TileConveyBelt;
import mfrf.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell.TileCreativeEnergyCell;
import mfrf.micro_machinery.blocks.machines.single_block_machines.cutter.TileCutter;
import mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.blocks.machines.single_block_machines.energy_cable.TileEnergyCable;
import mfrf.micro_machinery.blocks.machines.single_block_machines.fluidpipe.FluidPipeTile;
import mfrf.micro_machinery.blocks.machines.single_block_machines.forge_anvil.TileAnvil;
import mfrf.micro_machinery.blocks.machines.single_block_machines.generator.TileGenerator;
import mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteredBlockEntityTypes {
    public static DeferredRegister<BlockEntityType<? extends BlockEntity>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MicroMachinery.MODID);


    public static final RegistryObject<BlockEntityType<TileKlin>> TILE_KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> BlockEntityType.Builder.of(TileKlin::new, RegisteredBlocks.KLIN).build(null));
    //    public static final RegistryObject<BlockEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> BlockEntityType.Builder.of(TerTestTile::new, RegisteredBlocks.TESTBLOCK).build(null));
    public static final RegistryObject<BlockEntityType<TileGenerator>> TILE_GENERATOR_TYPE = TILE_ENTITY_TYPE_REGISTER.register("generator", () -> BlockEntityType.Builder.of(TileGenerator::new, RegisteredBlocks.GENERATOR).build(null));
    public static final RegistryObject<BlockEntityType<TileHandGenerator>> TILE_HAND_GENERATOR = TILE_ENTITY_TYPE_REGISTER.register("hand_generator", () -> BlockEntityType.Builder.of(TileHandGenerator::new, RegisteredBlocks.HAND_GENERATOR).build(null));
    public static final RegistryObject<BlockEntityType<TileAnvil>> TILE_ANVIL_TYPE = TILE_ENTITY_TYPE_REGISTER.register("anvil", () -> BlockEntityType.Builder.of(TileAnvil::new, RegisteredBlocks.STONE_ANVIL, RegisteredBlocks.BRONZE_ANVIL, RegisteredBlocks.PIGIRON_ANVIL).build(null));
    public static final RegistryObject<BlockEntityType<TileEnergyCable>> TILE_ENERGY_CABLE = TILE_ENTITY_TYPE_REGISTER.register("energy_cable", () -> BlockEntityType.Builder.of(TileEnergyCable::new, RegisteredBlocks.ALUMINUM_CABLE, RegisteredBlocks.COBALT_CABLE, RegisteredBlocks.COPPER_CABLE, RegisteredBlocks.NICKEL_CABLE, RegisteredBlocks.TUNGSTEN_CABLE).build(null));
    public static final RegistryObject<BlockEntityType<TileCreativeEnergyCell>> TILE_ENERGY_CELL = TILE_ENTITY_TYPE_REGISTER.register("energy_cell", () -> BlockEntityType.Builder.of(TileCreativeEnergyCell::new, RegisteredBlocks.CREATIVE_ENERGY_CELL).build(null));
    //    public static final RegistryObject<BlockEntityType<TileLathe>> TILE_LATHE = TILE_ENTITY_TYPE_REGISTER.register("lathe", () -> BlockEntityType.Builder.of(TileLathe::new, RegisteredBlocks.LATHE).build(null));
//    public static final RegistryObject<BlockEntityType<TilePlaceHolder>> TILE_PLACEHOLDER = TILE_ENTITY_TYPE_REGISTER.register("place_holder", () -> BlockEntityType.Builder.of(TilePlaceHolder::new, MMMultiBlockHolderBase.PLACE_HOLDER_LIST.toArray(new Block[MMMultiBlockHolderBase.PLACE_HOLDER_LIST.size()])).build(null));
//    public static final RegistryObject<BlockEntityType<TileBlastFurnace>> TILE_BLAST_FURNACE = TILE_ENTITY_TYPE_REGISTER.register("blast_furnace", () -> BlockEntityType.Builder.of(TileBlastFurnace::new, RegisteredBlocks.BLAST_FURNACE).build(null));
    //    public static final RegistryObject<BlockEntityType<TileEtcher>> TILE_ETCHER = TILE_ENTITY_TYPE_REGISTER.register("etcher", () -> BlockEntityType.Builder.of(TileEtcher::new, RegisteredBlocks.BLOCK_ETCHER).build(null));
//    public static final RegistryObject<BlockEntityType<TileEnergyInterface>> TILE_ENERGY_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("energy_interface", () -> BlockEntityType.Builder.of(TileEnergyInterface::new, RegisteredBlocks.ENERGY_INTERFACE_INPUT, RegisteredBlocks.ENERGY_INTERFACE_OUTPUT).build(null));
    public static final RegistryObject<BlockEntityType<TileElectrolysis>> TILE_ELECTROLYSIS = TILE_ENTITY_TYPE_REGISTER.register("electrolysis", () -> BlockEntityType.Builder.of(TileElectrolysis::new, RegisteredBlocks.ELECTROLYSIS).build(null));
    public static final RegistryObject<BlockEntityType<TileCutter>> TILE_CUTTER = TILE_ENTITY_TYPE_REGISTER.register("cutter", () -> BlockEntityType.Builder.of(TileCutter::new, RegisteredBlocks.CUTTER).build(null));
    public static final RegistryObject<BlockEntityType<TileCentrifuge>> TILE_CENTRIFUGE = TILE_ENTITY_TYPE_REGISTER.register("centrifuge", () -> BlockEntityType.Builder.of(TileCentrifuge::new, RegisteredBlocks.CENTRIFUGE).build(null));
    public static final RegistryObject<BlockEntityType<FluidPipeTile>> TILE_FLUID_PIPE_DEMO = TILE_ENTITY_TYPE_REGISTER.register("fluid_pipe", () -> BlockEntityType.Builder.of(FluidPipeTile::new, RegisteredBlocks.PIPE_INVAR, RegisteredBlocks.PIPE_STAINLESS_STEEL, RegisteredBlocks.PIPE_TUNGSTEN_STEEL).build(null));
    public static final RegistryObject<BlockEntityType<TileAtomization>> TILE_ATOMIZATION = TILE_ENTITY_TYPE_REGISTER.register("atomization", () -> BlockEntityType.Builder.of(TileAtomization::new, RegisteredBlocks.ATOMIZATION).build(null));
    //    public static final RegistryObject<BlockEntityType<TilePump>> TILE_PUMP = TILE_ENTITY_TYPE_REGISTER.register("pump", () -> BlockEntityType.Builder.of(TilePump::new, RegisteredBlocks.PUMP).build(null));
    public static final RegistryObject<BlockEntityType<TileWeld>> TILE_WELD = TILE_ENTITY_TYPE_REGISTER.register("weld", () -> BlockEntityType.Builder.of(TileWeld::new, RegisteredBlocks.WELD).build(null));
    //    public static final RegistryObject<BlockEntityType<TileHugeContainer>> TILE_HUGE_CONTAINER = TILE_ENTITY_TYPE_REGISTER.register("huge_container", () -> BlockEntityType.Builder.of(TileHugeContainer::new, RegisteredBlocks.HUGE_CONTAINER).build(null));
    public static final RegistryObject<BlockEntityType<TileConveyBelt>> TILE_CONVEY_BELT = TILE_ENTITY_TYPE_REGISTER.register("convey_belt", () -> BlockEntityType.Builder.of(TileConveyBelt::new, RegisteredBlocks.CONVEYOR_BELT_1, RegisteredBlocks.CONVEYOR_BELT_2, RegisteredBlocks.CONVEYOR_BELT_3).build(null));
    public static final RegistryObject<BlockEntityType<MMTileMultiBlockPart>> MULTI_BLOCK_PART = TILE_ENTITY_TYPE_REGISTER.register("part", () -> BlockEntityType.Builder.of(MMTileMultiBlockPart::new, RegisteredBlocks.MULTIBLOCK_PART).build(null));
    public static final RegistryObject<BlockEntityType<TileRedstoneInterface>> REDSTONE_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("redstone_interface", () -> BlockEntityType.Builder.of(TileRedstoneInterface::new, RegisteredBlocks.REDSTONE_INTERFACE).build(null));

    public static final RegistryObject<BlockEntityType<TestTileMainPart>> TEST = TILE_ENTITY_TYPE_REGISTER.register("test", () -> BlockEntityType.Builder.of(TestTileMainPart::new, RegisteredBlocks.TEST).build(null));
}