package mfrf.micro_machinery.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumFluidPipeState implements StringRepresentable {
    OPEN(true), AUTO_TRUE(true), AUTO_FALSE(false), CLOSE(false), AUTO_CONNECTED(true);
    public final boolean judge;

    EnumFluidPipeState(boolean judge) {
        this.judge = judge;
    }

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
