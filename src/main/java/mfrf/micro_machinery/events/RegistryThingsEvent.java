package mfrf.micro_machinery.events;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.registry_lists.MMTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MicroMachinery.MODID)
public class RegistryThingsEvent {

    public static final Map<ResourceKey<CreativeModeTab>, List<Supplier<Item>>> TAB_ITEM_MAP = new HashMap<>();
    public static final List<Supplier<Item>> TAB_ITEMS = new ArrayList<>();

    public static void addItemToRegisterTab(Supplier<Item> item) {
        TAB_ITEMS.add(item);
    }

    public static List<Supplier<Item>> getOrCreateItemListToRegisterTab(ResourceKey<CreativeModeTab> tabResourceKey) {
        return TAB_ITEM_MAP.getOrDefault(tabResourceKey, new ArrayList<>());
    }

    @SubscribeEvent
    public static void onRegisterItemInToTab(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (tabKey == MMTab.ICON_TAB.getKey()) {
            TAB_ITEMS.forEach(event::accept);
        } else {
            TAB_ITEM_MAP.getOrDefault(tabKey, Collections.emptyList()).forEach(event::accept);
        }
    }
}
