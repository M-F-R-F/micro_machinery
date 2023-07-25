package mfrf.micro_machinery.registry_lists.worldgen;

import com.mojang.serialization.Codec;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.worldgen.VeinFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MicroMachinery.MODID);
    public static final RegistryObject<VeinFeature> VEIN_FEATURE = FEATURE_REGISTER.register("vein_feature", VeinFeature::new);

}
