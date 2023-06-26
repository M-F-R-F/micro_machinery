package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MicroMachinery.MODID);
}
