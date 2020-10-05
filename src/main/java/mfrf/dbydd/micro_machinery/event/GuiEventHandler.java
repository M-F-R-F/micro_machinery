package mfrf.dbydd.micro_machinery.event;

import mfrf.dbydd.micro_machinery.gui.generator.GeneratorScreen;
import mfrf.dbydd.micro_machinery.gui.klin.KlinScreen;
import mfrf.dbydd.micro_machinery.gui.lathe.LatheScreen;
import mfrf.dbydd.micro_machinery.network.tile_sync_to_server.TileClientToServerSyncChannel;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiEventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registered_ContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
        ScreenManager.registerFactory(Registered_ContainerTypes.GENERATOR.get(), GeneratorScreen::new);
        ScreenManager.registerFactory(Registered_ContainerTypes.LATHE_CONTAINER.get(), LatheScreen::new);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event){
        TileClientToServerSyncChannel.registerMessage();
    }


}
