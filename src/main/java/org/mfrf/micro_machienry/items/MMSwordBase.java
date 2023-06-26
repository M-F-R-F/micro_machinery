package org.mfrf.micro_machienry.items;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMSwordBase extends SwordItem {
    public static Map<String, Supplier<Item>> registeries = new HashMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().group(MicroMachinery.MMTAB);

    public MMSwordBase(IItemTier material_tier, int correct_value, float attack_speed, Properties properties, String name) {
        super(material_tier, correct_value, attack_speed, properties);
        registeries.put(name, () -> this);
    }

    public MMSwordBase(IItemTier material_tier, int correct_value, float attack_speed, String name) {
        super(material_tier, correct_value, attack_speed, DEFAULT_PROPERTIES);
        registeries.put(name, () -> this);
    }

}
