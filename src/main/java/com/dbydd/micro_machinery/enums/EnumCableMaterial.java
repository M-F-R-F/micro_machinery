package com.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCableMaterial implements IStringSerializable {
    COPPER(256, "copper"),
    NICLEL(1024, "nickel"),
    ALUMINUM(4096, "aluminum"),
    TUNGSTEN(16384, "tungsten"),
    COBALT(65536, "cobalt");


    private final int transfer;
    private final String materialName;

    EnumCableMaterial(int transfer, String materialName) {
        this.transfer = transfer;
        this.materialName = materialName;
    }

    public int getTransfer() {
        return transfer;
    }

    @Override
    public String getName() {
        return materialName;
    }
}
