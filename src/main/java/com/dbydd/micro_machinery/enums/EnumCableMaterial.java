package com.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCableMaterial implements IStringSerializable {
    COPPER(1024, "copper"),
    NICLEL(4096, "nickel"),
    ALUMINUM(16384, "aluminum"),
    TUNGSTEN(65536, "tungsten"),
    COBALT(262144, "cobalt");


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
