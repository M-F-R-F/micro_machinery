package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.worldgen.VeinFeature;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MicroMachinery.MODID);
    public static final RegistryObject<VeinFeature> VEIN_FEATURE = FEATURE_REGISTER.register("vein_feature", VeinFeature::new);
}
