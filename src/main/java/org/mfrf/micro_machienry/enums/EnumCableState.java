package org.mfrf.micro_machienry.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCableState implements IStringSerializable {
    CONNECT("connect"),
    CABLE("cable"),
    EMPTY("empty");


    private final String name;

    EnumCableState(String name) {
    this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
