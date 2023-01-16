package mfrf.dbydd.micro_machinery.mixins;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class Connector implements IMixinConnector {
    @Override
    public void connect() {
        Mixins.addConfiguration("assets/micro_machinery/mixins.micro_machinery.json");
    }
}
