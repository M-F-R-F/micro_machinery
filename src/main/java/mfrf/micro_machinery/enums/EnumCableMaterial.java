package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumCableMaterial implements StringRepresentable {
    TEST(Integer.MAX_VALUE, "test_material"),
    LEVEL1(1024, "level1"),
    LEVEL2(4096, "level2"),
    LEVEL3(16384, "level3"),
    LEVEL4(65536, "level4"),
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
    public String getSerializedName() {
        return materialName;
    }

    public static EnumCableMaterial fromName(String name) {
        switch (name) {
            case "test_material":
                return TEST;
            case "level1":
                return LEVEL1;
            case "level2":
                return LEVEL2;
            case "level3":
                return LEVEL3;
            case "level4":
                return LEVEL4;
            default:
                return NULL;
        }
    }
}
