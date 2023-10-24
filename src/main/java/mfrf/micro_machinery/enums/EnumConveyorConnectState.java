package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumConveyorConnectState implements StringRepresentable {
    STRAIGHT,LEFT,RIGHT,UP,DOWN,MERGE,DIVIDE;

    private final String name;

    EnumConveyorConnectState() {
        this.name = this.name();
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
