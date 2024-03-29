package mfrf.micro_machinery.item;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.events.RegistryThingsEvent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MMItemBase extends Item {
    public static Properties DEFAULT_PROPERTIES = new Properties().stacksTo(64);
    public static List<Item> simpleItems = new ArrayList<>();

    public MMItemBase(Properties properties, ResourceKey<CreativeModeTab> tab) {
        super(properties);
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(tab).add(() -> this);
    }

    public MMItemBase() {
        this(DEFAULT_PROPERTIES);
    }

    public MMItemBase(Properties properties) {
        super(properties);
        RegistryThingsEvent.TAB_ITEMS.add(() -> this);
        simpleItems.add(this);
    }

    /**
     * registry food
     */
    public MMItemBase(Properties properties, FoodProperties food) {
        this(properties.food(food));
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(CreativeModeTabs.FOOD_AND_DRINKS).add(() -> this);
    }

    @Override
    public @Nullable String getCreatorModId(ItemStack itemStack) {
        return MicroMachinery.MODID;
    }

    private String getLoc() {
        return "";
    }
}
