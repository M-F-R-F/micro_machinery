package org.mfrf.micro_machienry.event;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(modid = Micro_Machinery.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitEventHandler {

    @SubscribeEvent
    public static void onServerSetup(FMLDedicatedServerSetupEvent event) {
        event.getServerSupplier().get().getResourceManager().addReloadListener(new MultiblockStructureMaps());
    }
}
