package mfrf.dbydd.micro_machinery.event;

import mfrf.dbydd.micro_machinery.gui.generator.GeneratorScreen;
import mfrf.dbydd.micro_machinery.gui.klin.KlinScreen;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
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
