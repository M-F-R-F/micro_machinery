package mfrf.micro_machinery.events;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.items.MMItemBase;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MicroMachinery.MODID)
public class RegistryThingsEvent {

    @SubscribeEvent
    public static void onRegisterItemInToTab(BuildCreativeModeTabContentsEvent event) {
        MMItemBase.registeries.computeIfPresent(event.getTabKey(), (tab,list)->list.forEach(event::accept));
    }
}
