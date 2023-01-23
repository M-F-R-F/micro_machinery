package mfrf.micro_machinery.items;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMSwordBase extends SwordItem {
    public static Map<String, Supplier<Item>> registeries = new HashMap<>();
    public static Item.Properties DEFAULT_PROPERTIES = new Item.Properties().m_41491_(MicroMachinery.MMTAB);

    public MMSwordBase(Tier material_tier, int correct_value, float attack_speed, Item.Properties properties, String name) {
        super(material_tier, correct_value, attack_speed, properties);
        registeries.put(name, () -> this);
    }

    public MMSwordBase(Tier material_tier, int correct_value, float attack_speed, String name) {
        super(material_tier, correct_value, attack_speed, DEFAULT_PROPERTIES);
        registeries.put(name, () -> this);
    }

}
