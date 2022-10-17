package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum EnumConveyorConnectState implements IStringSerializable {
    CONNECTED("connected"), BLOCKED("blocked"), UP("up"), DOWN("down");


    private final String name;

    EnumConveyorConnectState(String name) {
        this.name = name;
    }

    public static EnumConveyorConnectState thonk(Direction vertical) {
        switch (vertical) {
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            default:
                return CONNECTED;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
