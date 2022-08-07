package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConveyorVerticalState implements IStringSerializable {
    UP,DOWN,NONE;

    @Override
    public String getName() {
        return name();
    }
}
