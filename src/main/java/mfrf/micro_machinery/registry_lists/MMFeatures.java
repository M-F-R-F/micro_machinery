package mfrf.micro_machinery.registry_lists;

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
    public static final DeferredRegister<StructureType<?>> FEATURE_REGISTER = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE.key(), MicroMachinery.MODID);
    public static final RegistryObject<StructureType<VeinFeature>> VEIN_FEATURE = FEATURE_REGISTER.register("vein_feature", () -> explicitStructureTypeTyping(VeinFeature.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}
