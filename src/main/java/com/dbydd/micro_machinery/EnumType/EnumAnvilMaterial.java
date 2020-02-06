package com.dbydd.micro_machinery.EnumType;

public enum EnumAnvilMaterial {
    STONE, BRONZE, PIGIRON, SUUUUUUUUUPER;

    public static EnumAnvilMaterial getMaterial(int level) {
        switch (level) {
            case 1:
                return STONE;
            case 2:
                return BRONZE;
            case 3:
                return PIGIRON;

        }
        return SUUUUUUUUUPER;
    }
}
