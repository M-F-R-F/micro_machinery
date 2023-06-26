package org.mfrf.micro_machienry.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumFluidPipeState implements IStringSerializable {
    OPEN, AUTO_TRUE, AUTO_FALSE, CLOSE, AUTO_CONNECTED;

    @Override
    public String getName() {
        switch (this) {
            case OPEN:
                return "open";
            case AUTO_TRUE:
                return "auto_true";
            case CLOSE:
                return "close";
            case AUTO_FALSE:
                return "auto_false";
            case AUTO_CONNECTED:
                return "auto_connected";
        }
        return "close";
    }
}
