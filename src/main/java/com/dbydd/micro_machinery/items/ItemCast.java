package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.EnumType.EnumCastType;

public class ItemCast extends ItemBase {
    public int amount;
    public EnumCastType type;

    public ItemCast(String itemname, int amount, EnumCastType type) {
        super(itemname);
        this.type = type;
        this.amount = amount;
        setMaxStackSize(1);
    }

    public boolean canpouring(int amount) {
        return amount >= this.amount;
    }
}
