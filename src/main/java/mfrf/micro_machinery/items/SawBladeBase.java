package mfrf.micro_machinery.items;

import net.minecraftforge.common.ForgeConfigSpec;

public class SawBladeBase extends MMItemBase {

    private final ForgeConfigSpec.DoubleValue combinedSawEfficiency;

    public ForgeConfigSpec.DoubleValue getCombinedSawEfficiency() {
        return combinedSawEfficiency;
    }

    public SawBladeBase(ForgeConfigSpec.DoubleValue combinedSawEfficiency) {
        super(new Properties().stacksTo(1));
        this.combinedSawEfficiency = combinedSawEfficiency;

    }

}
