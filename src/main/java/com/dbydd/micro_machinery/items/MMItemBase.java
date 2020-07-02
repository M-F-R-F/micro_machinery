package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Map<String, Supplier<Item>> registerys = new HashMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().group(Micro_Machinery.MMTAB);

    public MMItemBase(Properties properties, String name) {
        super(properties);
        registerys.put(name, () -> this);
    }

    public MMItemBase(String name) {
        super(DEFAULT_PROPERTIES);
        registerys.put(name, () -> this);
    }

    /**
     * registry food
     */
    public MMItemBase(Properties properties,String name, Food food) {
        super(properties.food(food));
        registerys.put(name, () -> this);
    }
}
