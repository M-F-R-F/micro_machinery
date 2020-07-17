package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.mathines.klin.BlockKlin;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registereyed_Tileentities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Micro_Machinery.NAME);


    public static RegistryObject<TileEntityType<TileKlin>> KLIN_TYPE = TILE_ENTITY_TYPE_REGISTER.register("klin", () -> TileEntityType.Builder.create(TileKlin::new, RegisteryedBlocks.KLIN).build(null));
}
