package mfrf.micro_machinery.enums;

public enum EnumCastType {
    INGOT, STICK, GEAR_BLANK, SWORD;

    public static String getString(EnumCastType type) {
        switch (type) {
            case GEAR_BLANK:
                return "gear_blank";
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
            case "gear_blank":
                return GEAR_BLANK;
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
