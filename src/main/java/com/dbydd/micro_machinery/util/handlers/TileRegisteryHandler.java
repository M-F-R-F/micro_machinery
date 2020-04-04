package com.dbydd.micro_machinery.util.handlers;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.singlemachinetileentity.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.blocks.tileentities.singlemachinetileentity.TileEntityKlin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegisteryHandler {
    public static void registerTileEntities() {

        GameRegistry.registerTileEntity(TileEntityKlin.class, new ResourceLocation(Reference.MODID + ":klin"));
        GameRegistry.registerTileEntity(TileEntityForgingAnvil.class, new ResourceLocation(Reference.MODID + ":anvil"));
    }

}
