package mfrf.micro_machinery.item;

import mfrf.micro_machinery.events.RegistryThingsEvent;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMAxeBase extends AxeItem {

    /**
     * @param material_tier        材料等级，见枚举类EnumToolTier
     * @param damage_correct_value 伤害修正值，同MMSwordBase
     */
    public MMAxeBase(Tier material_tier, int damage_correct_value, float attack_speed, Properties properties) {
        super(material_tier, damage_correct_value, attack_speed, properties);
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(CreativeModeTabs.TOOLS_AND_UTILITIES).add(() -> this);
    }

}
