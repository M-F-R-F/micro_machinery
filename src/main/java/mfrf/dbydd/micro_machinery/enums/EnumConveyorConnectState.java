package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConveyorConnectState implements IStringSerializable {
    IN("in"), OUT("out"), BLOCKED("blocked"), UP_IN("up_in"), UP_OUT("up_out");


    private final String name;

    EnumConveyorConnectState(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static boolean isIn(EnumConveyorConnectState state) {
        return state == IN || state == UP_IN;
    }

    public static boolean isOut(EnumConveyorConnectState state) {
        return state == OUT || state == UP_OUT;
    }
}
