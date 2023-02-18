package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumCableState implements StringRepresentable {
    CONNECT("connect"),
    CABLE("cable"),
    EMPTY("empty");


    private final String name;

    EnumCableState(String name) {
    this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
