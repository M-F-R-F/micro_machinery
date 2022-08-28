package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConveyorConnectState implements IStringSerializable {
    CONNECTED, BLOCKED, UP, DOWN;

    @Override
    public String getName() {
        return this.name();
    }
}
