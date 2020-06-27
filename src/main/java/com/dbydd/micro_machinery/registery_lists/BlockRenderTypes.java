package com.dbydd.micro_machinery.registery_lists;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRenderTypes {
    public static Map<Block, RenderType> blockRenderTypeMap = new HashMap<>();

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        for(Map.Entry<Block, RenderType> entry : blockRenderTypeMap.entrySet()){
            RenderTypeLookup.setRenderLayer(entry.getKey(), entry.getValue());
        }
    }
}
