package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConveyorConnectState implements IStringSerializable {
    CONNECTED("connected"), BLOCKED("blocked"), UP("up"), DOWN("down");


    private final String name;

    EnumConveyorConnectState(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
