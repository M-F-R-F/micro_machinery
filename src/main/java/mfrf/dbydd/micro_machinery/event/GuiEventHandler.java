package mfrf.dbydd.micro_machinery.event;

import mfrf.dbydd.micro_machinery.gui.atomization.AtomizationScreen;
import mfrf.dbydd.micro_machinery.gui.blast_furnace.BlastFurnaceScreen;
import mfrf.dbydd.micro_machinery.gui.centrifuge.CentrifugeScreen;
import mfrf.dbydd.micro_machinery.gui.cutter.CutterScreen;
import mfrf.dbydd.micro_machinery.gui.electrolysis.ElectrolysisScreen;
import mfrf.dbydd.micro_machinery.gui.generator.GeneratorScreen;
import mfrf.dbydd.micro_machinery.gui.klin.KlinScreen;
import mfrf.dbydd.micro_machinery.gui.lathe.LatheScreen;
import mfrf.dbydd.micro_machinery.network.tile_sync_to_server.TileClientToServerSyncChannel;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiEventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegisteredContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.GENERATOR.get(), GeneratorScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.LATHE_CONTAINER.get(), LatheScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.BLAST_FURNACE_CONTAINER.get(), BlastFurnaceScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.ELECTROLYSIS_CONTAINER.get(), ElectrolysisScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.CUTTER_CONTAINER.get(), CutterScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.CENTRIFUGE_CONTAINER.get(), CentrifugeScreen::new);
        ScreenManager.registerFactory(RegisteredContainerTypes.ATOMIZATION_CONTAINER.get(), AtomizationScreen::new);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        TileClientToServerSyncChannel.registerMessage();
    }


}
