package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumConveyorConnectState implements StringRepresentable {
    STRAIGHT,LEFT,RIGHT,UP,DOWN,MERGE,DIVIDE;

    @Override
    public String getSerializedName() {
        return this.name();
    }
}
