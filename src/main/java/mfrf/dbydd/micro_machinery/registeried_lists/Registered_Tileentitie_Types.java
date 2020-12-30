package mfrf.dbydd.micro_machinery.registeried_lists;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.creative_energy_cell.TileCreativeEnergyCell;
import mfrf.dbydd.micro_machinery.blocks.machines.energy_cable.TileEnergyCable;
import mfrf.dbydd.micro_machinery.blocks.machines.etcher.TileEtcher;
import mfrf.dbydd.micro_machinery.blocks.machines.forge_anvil.TileAnvil;
import mfrf.dbydd.micro_machinery.blocks.machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.hand_generator.TileHandGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace.TileBlastFurnace;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.energy_interface.TileEnergyInterface;
import mfrf.dbydd.micro_machinery.blocks.machines.ter_test.TerTestTile;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Tileentitie_Types {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Micro_Machinery.NAME);


    public static final RegistryObject<TileEntityType<TileKlin>> TILE_KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> TileEntityType.Builder.create(TileKlin::new, RegisteredBlocks.KLIN).build(null));
    public static final RegistryObject<TileEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> TileEntityType.Builder.create(TerTestTile::new, RegisteredBlocks.TESTBLOCK).build(null));
    public static final RegistryObject<TileEntityType<TileGenerator>> TILE_GENERATOR_TYPE = TILE_ENTITY_TYPE_REGISTER.register("generator", () -> TileEntityType.Builder.create(TileGenerator::new, RegisteredBlocks.GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileHandGenerator>> TILE_HAND_GENERATOR = TILE_ENTITY_TYPE_REGISTER.register("hand_generator", () -> TileEntityType.Builder.create(TileHandGenerator::new, RegisteredBlocks.HAND_GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileAnvil>> TILE_ANVIL_TYPE = TILE_ENTITY_TYPE_REGISTER.register("anvil", () -> TileEntityType.Builder.create(TileAnvil::new, RegisteredBlocks.STONE_ANVIL, RegisteredBlocks.BRONZE_ANVIL, RegisteredBlocks.PIGIRON_ANVIL).build(null));
    public static final RegistryObject<TileEntityType<TileEnergyCable>> TILE_ENERGY_CABLE = TILE_ENTITY_TYPE_REGISTER.register("energy_cable", () -> TileEntityType.Builder.create(TileEnergyCable::new, RegisteredBlocks.ALUMINUM_CABLE, RegisteredBlocks.COBALT_CABLE, RegisteredBlocks.COPPER_CABLE, RegisteredBlocks.NICKEL_CABLE, RegisteredBlocks.TUNGSTEN_CABLE).build(null));
    public static final RegistryObject<TileEntityType<TileCreativeEnergyCell>> TILE_ENERGY_CELL = TILE_ENTITY_TYPE_REGISTER.register("energy_cell", () -> TileEntityType.Builder.create(TileCreativeEnergyCell::new, RegisteredBlocks.CREATIVE_ENERGY_CELL).build(null));
    public static final RegistryObject<TileEntityType<TileLathe>> TILE_LATHE = TILE_ENTITY_TYPE_REGISTER.register("lathe", () -> TileEntityType.Builder.create(TileLathe::new, RegisteredBlocks.LATHE).build(null));
    public static final RegistryObject<TileEntityType<TilePlaceHolder>> TILE_PLACEHOLDER = TILE_ENTITY_TYPE_REGISTER.register("place_holder", () -> TileEntityType.Builder.create(TilePlaceHolder::new, MMMultiBlockBase.PLACE_HOLDER_LIST.toArray(new Block[MMMultiBlockBase.PLACE_HOLDER_LIST.size()])).build(null));
    public static final RegistryObject<TileEntityType<TileBlastFurnace>> TILE_BLAST_FURNACE = TILE_ENTITY_TYPE_REGISTER.register("blast_furnace", () -> TileEntityType.Builder.create(TileBlastFurnace::new, RegisteredBlocks.BLAST_FURNACE).build(null));
    public static final RegistryObject<TileEntityType<TileEtcher>> TILE_ETCHER = TILE_ENTITY_TYPE_REGISTER.register("etcher", () -> TileEntityType.Builder.create(TileEtcher::new, RegisteredBlocks.BLOCK_ETCHER).build(null));
    public static final RegistryObject<TileEntityType<TileEnergyInterface>> TILE_ENERGY_INTERFACE = TILE_ENTITY_TYPE_REGISTER.register("energy_interface", () -> TileEntityType.Builder.create(TileEnergyInterface::new, RegisteredBlocks.ENERGY_INTERFACE).build(null));
}