package mfrf.micro_machinery;

import com.mojang.logging.LogUtils;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.registry_lists.MMFeatures;
import mfrf.micro_machinery.registry_lists.MMFluids;
import mfrf.micro_machinery.registry_lists.MMItems;
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
        LOGGER.info(MODID + " Loaded.");
        LOGGER.info("Mixin Version: " + MixinBootstrap.VERSION);
    }

}
