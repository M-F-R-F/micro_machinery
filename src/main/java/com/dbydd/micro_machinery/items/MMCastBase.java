package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.enums.EnumCastType;

public class MMCastBase extends MMItemBase{
    public final int amount;
    public final EnumCastType type;

    public MMCastBase(String name, EnumCastType type, int amount) {
        super(new Properties().group(Micro_Machinery.MMTAB).maxStackSize(1), name);
        this.amount = amount;
        this.type = type;
    }
}
