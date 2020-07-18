package com.dbydd.micro_machinery.utils;

import com.dbydd.micro_machinery.gui.klin.KlinContainer;
import com.dbydd.micro_machinery.gui.klin.KlinScreen;
import com.dbydd.micro_machinery.registery_lists.Registeryed_ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registeryed_ContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
    }
}
