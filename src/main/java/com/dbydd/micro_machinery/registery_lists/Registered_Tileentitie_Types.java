package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.blocks.mathines.ter_test.TerTestTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Tileentitie_Types {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Micro_Machinery.NAME);


    public static RegistryObject<TileEntityType<TileKlin>> KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> TileEntityType.Builder.create(TileKlin::new, RegisteredBlocks.KLIN).build(null));
    public static RegistryObject<TileEntityType<TerTestTile>> TEST_TILE_TYPE = TILE_ENTITY_TYPE_REGISTER.register("test_tile", () -> TileEntityType.Builder.create(TerTestTile::new, RegisteredBlocks.TESTBLOCK).build(null));

}
