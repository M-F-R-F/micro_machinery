package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConveyorConnectState implements IStringSerializable {
    CONNECTED, BLOCKED, EMPTY, VERTICAL_CONNECTED_UP, VERTICAL_CONNECTED_DOWN;

    @Override
    public String getName() {
        return this.name();
    }
}
