package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum EnumToolTier implements IItemTier {
    BRONZE(400,2,5,3,2,Ingredient.EMPTY),
    HSS(900,5,7,5,3,Ingredient.EMPTY);

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
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackdamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestlevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return ingredient;
    }
}
