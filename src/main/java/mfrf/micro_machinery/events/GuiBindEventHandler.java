package mfrf.micro_machinery.events;

import mfrf.micro_machinery.gui.atomization.AtomizationScreen;
import mfrf.micro_machinery.gui.centrifuge.CentrifugeScreen;
import mfrf.micro_machinery.gui.cutter.CutterScreen;
import mfrf.micro_machinery.gui.electrolysis.ElectrolysisScreen;
import mfrf.micro_machinery.gui.generator.GeneratorScreen;
import mfrf.micro_machinery.gui.klin.KlinScreen;
import mfrf.micro_machinery.gui.weld.WeldScreen;
import mfrf.micro_machinery.network.tile_sync_to_server.TileClientToServerSyncChannel;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GuiBindEventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MMContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
            MenuScreens.register(MMContainerTypes.GENERATOR.get(), GeneratorScreen::new);
//        MenuScreens.register(MMContainerTypes.LATHE_CONTAINER.get(), LatheScreen::new);
//        MenuScreens.register(MMContainerTypes.BLAST_FURNACE_CONTAINER.get(), BlastFurnaceScreen::new);
            MenuScreens.register(MMContainerTypes.ELECTROLYSIS_CONTAINER.get(), ElectrolysisScreen::new);
            MenuScreens.register(MMContainerTypes.CUTTER_CONTAINER.get(), CutterScreen::new);
            MenuScreens.register(MMContainerTypes.CENTRIFUGE_CONTAINER.get(), CentrifugeScreen::new);
            MenuScreens.register(MMContainerTypes.ATOMIZATION_CONTAINER.get(), AtomizationScreen::new);
            MenuScreens.register(MMContainerTypes.WELD_CONTAINER.get(), WeldScreen::new);
        });
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        TileClientToServerSyncChannel.registerMessage();
    }


}
