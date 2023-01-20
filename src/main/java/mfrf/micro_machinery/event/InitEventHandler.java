package mfrf.micro_machinery.event;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(modid = MicroMachinery.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitEventHandler {

    @SubscribeEvent
    public static void onServerSetup(FMLDedicatedServerSetupEvent event) {
        event.getServerSupplier().get().getResourceManager().addReloadListener(new MultiblockStructureMaps());
    }
}
