package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.fluids.MMFluidBase;
import com.sun.deploy.net.MessageHeader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderTypes {
    public static Map<Block, RenderType> blockRenderTypeMap = new HashMap<>();
    public static List<MMFluidBase> fluidRenderTypeMap = new ArrayList<>();

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        for(Map.Entry<Block, RenderType> entry : blockRenderTypeMap.entrySet()){
            RenderTypeLookup.setRenderLayer(entry.getKey(), entry.getValue());
        }
    }

    @SubscribeEvent
    public static void onFluidRenderTypeSetup(FMLClientSetupEvent event) {
        for (MMFluidBase mmFluidBase : fluidRenderTypeMap) {
            RenderTypeLookup.setRenderLayer(mmFluidBase.fluid.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(mmFluidBase.fluid_flowing.get(), RenderType.getTranslucent());
        }
    }
}
