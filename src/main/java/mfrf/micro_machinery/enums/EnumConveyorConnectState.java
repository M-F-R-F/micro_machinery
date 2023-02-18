package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumConveyorConnectState implements StringRepresentable {
    CONNECTED("connected"), BLOCKED("blocked"), UP("up"), DOWN("down"), NULL("null");


    private final String name;

    EnumConveyorConnectState(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
