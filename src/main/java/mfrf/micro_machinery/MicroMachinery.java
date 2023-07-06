package mfrf.micro_machinery;

import com.mojang.logging.LogUtils;
import mfrf.micro_machinery.events.MoltenMaterialToRockEventDenier;
import mfrf.micro_machinery.registry_lists.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MicroMachinery.MODID)
public class MicroMachinery {
    public static final String MODID = "micro_machinery";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MicroMachinery() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MMItems.ITEM_REGISTER.register(modEventBus);
        MMBlocks.BLOCK_REGISTER.register(modEventBus);
        MMFluids.FLUID_REGISTER.register(modEventBus);
        MMFeatures.FEATURE_REGISTER.register(modEventBus);
        MMRecipeSerializers.RECIPE_SERIALIZERS_REGISTER.register(modEventBus);
        MMContainerTypes.CONTAINER_TYPE_REGISTER.register(modEventBus);

        if (Config.FLUID_COLLIDE_GENERATE_DISCARDED_STONE.get()) {
            modEventBus.addListener(MoltenMaterialToRockEventDenier::onMoltenMaterialGenerateStone);
        }

        LOGGER.info(MODID + " Loaded.");
        LOGGER.info("Mixin Version: " + MixinBootstrap.VERSION);
    }

}
