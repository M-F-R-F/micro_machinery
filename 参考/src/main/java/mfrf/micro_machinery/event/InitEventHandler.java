package mfrf.micro_machinery.event;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(modid = MicroMachinery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitEventHandler {

    @SubscribeEvent
    public static void onServerSetup(FMLDedicatedServerSetupEvent event) {
        //todo addReloadListener event
        event.enqueueWork(() -> {

            Minecraft.getInstance().getResourceManager().addReloadListener(new MultiblockStructureMaps());
        });
    }
}
