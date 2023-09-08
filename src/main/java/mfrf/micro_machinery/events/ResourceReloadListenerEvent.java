package mfrf.micro_machinery.events;

import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ResourceReloadListenerEvent {
    @SubscribeEvent
    public static void onResourceReload(AddReloadListenerEvent event) {
        event.addListener(new MultiblockStructureMaps());
        event.addListener(new RecipeHelper());
    }
}
