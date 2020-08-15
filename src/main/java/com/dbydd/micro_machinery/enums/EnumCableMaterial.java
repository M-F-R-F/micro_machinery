package com.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCableMaterial implements IStringSerializable {
    COPPER(100, "copper"),
    NICLEL(200, "nickel"),
    ALUMINUM(300, "aluminum"),
    TUNGSTEN(400, "tungsten"),
    COBALT(500, "cobalt");


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
