package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumFluidPipeState implements StringRepresentable {
    OPEN, AUTO_TRUE, AUTO_FALSE, CLOSE, AUTO_CONNECTED;

    @Override
    public String getSerializedName() {
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
