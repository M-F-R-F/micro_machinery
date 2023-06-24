package mfrf.micro_machinery.items;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMAxeBase extends AxeItem {
    public static Map<String, Supplier<Item>> registeries = new HashMap<>();

    /**
     * @param material_tier        材料等级，见枚举类EnumToolTier
     * @param damage_correct_value 伤害修正值，同MMSwordBase
     */
    public MMAxeBase(Tier material_tier, int damage_correct_value, float attack_speed, Properties properties, String name) {
        super(material_tier, damage_correct_value, attack_speed, properties);
        registeries.put(name, () -> this);
    }

}
