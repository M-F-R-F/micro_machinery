package com.dbydd.micro_machinery.EnumType;

public enum EnumCastType {
    INGOT, STICK, GEAR;

    public static String getString(EnumCastType type) {
        switch (type) {
            case GEAR:
                return "gear";
            case INGOT:
                return "ingot";
            case STICK:
                return "stick";
        }
        return null;
    }
}
