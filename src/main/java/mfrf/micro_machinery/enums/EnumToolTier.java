package mfrf.micro_machinery.enums;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum EnumToolTier implements Tier {
    BRONZE(400, 2, 5, 3, 2, Ingredient.EMPTY),
    HSS(900, 5, 7, 5, 3, Ingredient.EMPTY);

    private final int maxUses;
    private final int efficiency;
    private final int attackdamage;
    private final int harvestlevel;
    private final int enchantability;
    private final Ingredient ingredient;

    EnumToolTier(int maxUses, int efficiency, int attackdamage, int harvestlevel, int enchantability, Ingredient ingredient) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackdamage = attackdamage;
        this.harvestlevel = harvestlevel;
        this.enchantability = enchantability;
        this.ingredient = ingredient;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackdamage;
    }

    @Override
    public int getLevel() {
        return harvestlevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return ingredient;
    }

}
