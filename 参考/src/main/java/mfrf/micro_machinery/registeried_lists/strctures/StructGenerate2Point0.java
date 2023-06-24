package mfrf.micro_machinery.registeried_lists.strctures;

import com.mojang.serialization.Codec;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.worldgen.test.TestStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructGenerate2Point0 {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(Registry.f_122840_, MicroMachinery.MODID);
    public static final RegistryObject<StructureFeature<?>> PORTAL = STRUCTURES.register("test", () -> new TestStructure(NoneFeatureConfiguration.CODEC));
    //todo test register

}
