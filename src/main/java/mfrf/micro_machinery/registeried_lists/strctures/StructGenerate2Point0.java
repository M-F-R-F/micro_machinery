package mfrf.micro_machinery.registeried_lists.strctures;

import com.mojang.serialization.Codec;
import com.sun.jna.Structure;
import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

public class StructGenerate2Point0 {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(Registry.f_122840_, MicroMachinery.MODID);
//    public static final RegistryObject<StructureType<?>> PORTAL = STRUCTURES.register("portal", () -> typeConvert(PortalStructure.CODEC));
    //todo test register

    private static <S extends FeatureConfiguration> StructureFeature<S> typeConvert(Codec<S> codec) {
        return (StructureFeature<S>) codec;
    }
}
