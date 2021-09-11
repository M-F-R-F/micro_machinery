package mfrf.dbydd.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumFluidPipeState implements IStringSerializable {
    OPEN, AUTO_TRUE, AUTO_FALSE, CLOSE;

    @Override
    public String getName() {
        switch (this) {
            case OPEN:
            case AUTO_TRUE:
                return "open";
            case CLOSE:
            case AUTO_FALSE:
                return "close";
        }
        return "close";
    }
}
