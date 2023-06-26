package org.mfrf.micro_machienry.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCableMaterial implements IStringSerializable {
    TEST(Integer.MAX_VALUE, "test_material"),
    COPPER(256, "copper"),
    NICKEL(1024, "nickel"),
    ALUMINUM(4096, "aluminum"),
    TUNGSTEN(16384, "tungsten"),
    COBALT(65536, "cobalt"),
    NULL(0, "null");


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

    public static EnumCableMaterial fromName(String name) {
        switch (name) {
            case "test_material":
                return TEST;
            case "copper":
                return COPPER;
            case "nickel":
                return NICKEL;
            case "aluminum":
                return ALUMINUM;
            case "tungsten":
                return TUNGSTEN;
            case "cobalt":
                return COBALT;
            default:
                return NULL;
        }
    }
}
