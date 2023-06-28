package mfrf.micro_machinery.item;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMSwordBase extends SwordItem {

    public MMSwordBase(Tier material_tier, int correct_value, float attack_speed, Properties properties) {
        super(material_tier, correct_value, attack_speed, properties);
    }

}
