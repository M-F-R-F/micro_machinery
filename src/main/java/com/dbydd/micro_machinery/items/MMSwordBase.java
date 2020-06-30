package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMSwordBase extends SwordItem {
    public static Map<String, Supplier<Item>> registerys = new HashMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().group(Micro_Machinery.MMTAB);

    /**
     * @param correct_value 修正值，会在材料的伤害值基础上对此项做加法作为最终攻击力
     */
    public MMSwordBase(IItemTier material_tier, int correct_value, float attack_speed, Properties properties, String name) {
        super(material_tier, correct_value, attack_speed, properties);
        registerys.put(name, () -> this);
    }

    /**
     * @param correct_value 修正值，会在材料的伤害值基础上对此项做加法作为最终攻击力
     */
    public MMSwordBase(IItemTier material_tier, int correct_value, float attack_speed, String name) {
        super(material_tier, correct_value, attack_speed, DEFAULT_PROPERTIES);
        registerys.put(name, () -> this);
    }

}
