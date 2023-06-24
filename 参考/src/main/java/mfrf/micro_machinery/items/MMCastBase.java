package mfrf.micro_machinery.items;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.enums.EnumCastType;

public class MMCastBase extends MMItemBase{
    public final EnumCastType type;

    public MMCastBase(String name, EnumCastType type) {
        super(new Properties().m_41491_(MicroMachinery.MMTAB).stacksTo(1), name);
        this.type = type;
    }
}
