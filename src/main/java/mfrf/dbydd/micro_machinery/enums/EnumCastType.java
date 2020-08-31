package mfrf.dbydd.micro_machinery.enums;

public enum EnumCastType {
    INGOT, STICK, GEAR, SWORD;

    public static String getString(EnumCastType type) {
        switch (type) {
            case GEAR:
                return "gear";
            case INGOT:
                return "ingot";
            case STICK:
                return "stick";
            case SWORD:
                return "sword";
        }
        return "null";
    }

    public static EnumCastType fromString(String type) {
        switch (type) {
            case "gear":
                return GEAR;
            case "ingot":
                return INGOT;
            case "stick":
                return STICK;
            case "sword":
                return SWORD;
        }
        return null;
    }
}
