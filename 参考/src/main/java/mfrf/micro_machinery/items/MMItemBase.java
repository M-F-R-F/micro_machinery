package mfrf.micro_machinery.items;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class MMItemBase extends Item {
    public static Map<String, Supplier<Item>> registeries = new TreeMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().m_41491_(MicroMachinery.MMTAB);

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
    public MMItemBase(Properties properties,String name, FoodProperties food) {
        super(properties.food(food));
        registeries.put(name, () -> this);
    }
}
