package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Map<String, Supplier<Item>> registeries = new HashMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().group(Micro_Machinery.MMTAB);

    public MMItemBase(Properties properties, String name) {
        super(properties);
        registeries.put(name, () -> this);
    }

    public MMItemBase(String name) {
        super(DEFAULT_PROPERTIES);
        registeries.put(name, () -> this);
    }

    /**
     * registry food
     */
    public MMItemBase(Properties properties,String name, Food food) {
        super(properties.food(food));
        registeries.put(name, () -> this);
    }
}
