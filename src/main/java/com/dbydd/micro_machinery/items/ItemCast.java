package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.enumtype.EnumCastType;

public class ItemCast extends ItemBase {
    EnumCastType type;
    int amount;

    public ItemCast(String itemname, int amount, EnumCastType type) {
        super(itemname);
        this.type = type;
        this.amount = amount;
    }

}
