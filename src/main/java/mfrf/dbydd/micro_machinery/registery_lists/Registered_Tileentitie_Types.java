package mfrf.dbydd.micro_machinery.registery_lists;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.forge_anvil.TileAnvil;
import mfrf.dbydd.micro_machinery.blocks.machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.hand_generator.TileHandGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.blocks.machines.ter_test.TerTestTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Tileentitie_Types {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Micro_Machinery.NAME);


    public static RegistryObject<TileEntityType<TileKlin>> KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> TileEntityType.Builder.create(TileKlin::new, RegisteredBlocks.KLIN).build(null));
    public static RegistryObject<TileEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> TileEntityType.Builder.create(TerTestTile::new, RegisteredBlocks.TESTBLOCK).build(null));
    public static final RegistryObject<TileEntityType<TileGenerator>> TILE_GENERATOR_TYPE = TILE_ENTITY_TYPE_REGISTER.register("generator", ()->TileEntityType.Builder.create(TileGenerator::new, RegisteredBlocks.GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileHandGenerator>> TILE_HAND_GENERATOR = TILE_ENTITY_TYPE_REGISTER.register("hand_generator", ()->TileEntityType.Builder.create(TileHandGenerator::new, RegisteredBlocks.HAND_GENERATOR).build(null));
    public static final RegistryObject<TileEntityType<TileAnvil>> TILE_ANVIL_TYPE = TILE_ENTITY_TYPE_REGISTER.register("anvil", ()->TileEntityType.Builder.create(TileAnvil::new, RegisteredBlocks.STONE_ANVIL, RegisteredBlocks.BRONZE_ANVIL, RegisteredBlocks.PIGIRON_ANVIL).build(null));

}
