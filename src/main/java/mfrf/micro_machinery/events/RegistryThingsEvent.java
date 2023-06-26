package mfrf.micro_machinery.events;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MicroMachinery.MODID)
public class RegistryThingsEvent {

    @SubscribeEvent
    public static void onRegisterItemInToTab(BuildCreativeModeTabContentsEvent event) {
    }
}
