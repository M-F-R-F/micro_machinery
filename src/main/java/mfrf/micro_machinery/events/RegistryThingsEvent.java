package mfrf.micro_machinery.events;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MicroMachinery.MODID)
public class RegistryThingsEvent {

    public static Map<ResourceKey<CreativeModeTab>, List<Supplier<Item>>> TAB_ITEM_MAP = new TreeMap<>();

    public static List<Supplier<Item>> getOrCreateItemListToRegisterTab(ResourceKey<CreativeModeTab> tabResourceKey) {
        return TAB_ITEM_MAP.getOrDefault(tabResourceKey, new ArrayList<>());
    }

    @SubscribeEvent
    public static void onRegisterItemInToTab(BuildCreativeModeTabContentsEvent event) {
        TAB_ITEM_MAP.getOrDefault(event.getTabKey(), Collections.emptyList()).forEach(event::accept);
    }
}
