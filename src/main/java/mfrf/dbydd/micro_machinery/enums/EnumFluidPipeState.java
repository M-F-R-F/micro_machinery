package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumFluidPipeState implements IStringSerializable {
    OPEN, AUTO_TRUE, AUTO_FALSE, CLOSE;

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
        }
        return "close";
    }
}
