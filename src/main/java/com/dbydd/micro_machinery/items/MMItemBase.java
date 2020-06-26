package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Map<String, Supplier<Item>> registerys = new HashMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties();

    public MMItemBase(Properties properties, String name) {
        super(properties);
        registerys.put(name, () -> this);
    }

    public MMItemBase(Properties properties, String name, Supplier<Item> supplier) {
        super(properties);
        registerys.put(name, supplier);
    }

    public MMItemBase(String name) {
        super(DEFAULT_PROPERTIES);
        registerys.put(name, () -> this);
    }

    public MMItemBase(String name, Supplier<Item> supplier) {
        super(DEFAULT_PROPERTIES);
        registerys.put(name, () -> this);
    }
}
