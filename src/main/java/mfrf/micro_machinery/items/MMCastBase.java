package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.enums.EnumCastType;

public class MMCastBase extends MMItemBase{
    public final EnumCastType type;

    public MMCastBase(String name, EnumCastType type) {
        super(new Properties().group(Micro_Machinery.MMTAB).maxStackSize(1), name);
        this.type = type;
    }
}
