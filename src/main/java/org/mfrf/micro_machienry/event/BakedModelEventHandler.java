package org.mfrf.micro_machienry.event;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BakedModelEventHandler {

    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
    }

}
