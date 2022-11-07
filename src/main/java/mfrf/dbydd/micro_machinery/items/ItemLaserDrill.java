package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import net.minecraftforge.energy.EnergyStorage;

public class ItemLaserDrill extends MMItemBase {
    private static final EnergyStorage TEMPLATE_ENERGY_STORAGE = new EnergyStorage(Config.LASER_DRILL_ENERGY_CAP.get());

    public ItemLaserDrill() {
        super(new Properties().group(Micro_Machinery.MMTAB).maxStackSize(1), "laser_drill");
    }

//    @Nullable
//    @Override
//    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
//        todo reg item energy
//    }


}
