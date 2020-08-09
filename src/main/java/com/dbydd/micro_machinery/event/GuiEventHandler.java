package com.dbydd.micro_machinery.event;

import com.dbydd.micro_machinery.gui.generator.GeneratorScreen;
import com.dbydd.micro_machinery.gui.klin.KlinScreen;
import com.dbydd.micro_machinery.registery_lists.Registered_ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiEventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registered_ContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
        ScreenManager.registerFactory(Registered_ContainerTypes.GENERATOR.get(), GeneratorScreen::new);
    }

}
