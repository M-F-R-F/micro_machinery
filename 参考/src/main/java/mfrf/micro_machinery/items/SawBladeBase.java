package mfrf.micro_machinery.items;

import net.minecraftforge.common.ForgeConfigSpec;

public class SawBladeBase extends MMItemBase {

    private final ForgeConfigSpec.DoubleValue combinedSawEfficiency;

    public ForgeConfigSpec.DoubleValue getCombinedSawEfficiency() {
        return combinedSawEfficiency;
    }

    public SawBladeBase(String name, ForgeConfigSpec.DoubleValue combinedSawEfficiency) {
        super(DEFAULT_PROPERTIES.stacksTo(1), name);
        this.combinedSawEfficiency = combinedSawEfficiency;

    }

}
