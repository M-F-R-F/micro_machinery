package mfrf.micro_machinery.items;

import mfrf.micro_machinery.registry_lists.MMItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Map<ResourceKey<CreativeModeTab>, Supplier<Item>> registeries = new TreeMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().stacksTo(64);

    public MMItemBase(Properties properties, ResourceKey<CreativeModeTab> tab) {
        super(properties);
        registeries.put(tab, () -> this);
    }

    public MMItemBase() {
        super(DEFAULT_PROPERTIES);
        registeries.put(MMItems.TAB.ICON_TAB.getKey(), () -> this);
    }

    public MMItemBase(Properties properties) {
        super(properties);
        registeries.put(MMItems.TAB.ICON_TAB.getKey(), () -> this);
    }

    /**
     * registry food
     */
    public MMItemBase(Properties properties, FoodProperties food) {
        super(properties.food(food));
        registeries.put(CreativeModeTabs.FOOD_AND_DRINKS, () -> this);
    }
}
