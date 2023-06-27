package mfrf.micro_machinery.items;

import mfrf.micro_machinery.events.RegistryThingsEvent;
import mfrf.micro_machinery.registry_lists.MMItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Properties DEFAULT_PROPERTIES = new Properties().stacksTo(64);

    public MMItemBase(Properties properties, ResourceKey<CreativeModeTab> tab) {
        super(properties);
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(tab).add(() -> this);
    }

    public MMItemBase() {
        this(DEFAULT_PROPERTIES);
    }

    public MMItemBase(Properties properties) {
        this(properties, MMItems.TAB.ICON_TAB.getKey());
    }

    /**
     * registry food
     */
    public MMItemBase(Properties properties, FoodProperties food) {
        this(properties.food(food));
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(CreativeModeTabs.FOOD_AND_DRINKS).add(() -> this);
    }
}
