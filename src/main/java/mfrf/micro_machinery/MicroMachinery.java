package mfrf.micro_machinery;

import com.mojang.logging.LogUtils;
import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.items.MMAxeBase;
import mfrf.micro_machinery.items.MMHammerBase;
import mfrf.micro_machinery.items.MMItemBase;
import mfrf.micro_machinery.items.MMSwordBase;
import mfrf.micro_machinery.registeried_lists.*;
import mfrf.micro_machinery.registeried_lists.strctures.StructGenerate2Point0;
import mfrf.micro_machinery.registeried_lists.strctures.Veins;
import mfrf.micro_machinery.worldgen.VeinFeature;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MicroMachinery.MODID)
public class MicroMachinery {
    public static final String MODID = "micro_machinery";
    public static final CreativeModeTab MMTAB = new MMTab();
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);


    /*
     * ugly ancient code :(
     * maybe someday I will fixit
     */
    static {
        InitListsNeedToRegister();

        MicroMachinery.RegisteryItems(MMItemBase.registeries);
        MicroMachinery.RegisteryItems(MMSwordBase.registeries);
        MicroMachinery.RegisteryItems(MMHammerBase.registeries);
        MicroMachinery.RegisteryItems(MMAxeBase.registeries);
        MicroMachinery.RegisteryBlocks(MMBlockBase.registeries);
        MicroMachinery.RegisteryBlocksWithoutItem(MMBlockBase.registeries_no_item);

//        ITEM_REGISTER.register("lathe", LatheBlockItem::new);
//        ITEM_REGISTER.register("pump", PumpBlockItem::new);
    }

    public MicroMachinery() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEM_REGISTER.register(modEventBus);
        BLOCK_REGISTER.register(modEventBus);
        FLUID_REGISTER.register(modEventBus);
        FEATURE_REGISTER.register(modEventBus);
        StructGenerate2Point0.STRUCTURES.register(modEventBus);
        RegisteredBlockEntityTypes.TILE_ENTITY_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredContainerTypes.CONTAINER_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredRecipeSerializers.RECIPE_SERIALIZERS_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info(MODID + " Loaded.");
        LOGGER.info("Mixin Version: " + MixinBootstrap.VERSION);
    }

    public static void RegisteryItems(Map<String, Supplier<Item>> map) {
        for (Map.Entry<String, Supplier<Item>> entry : map.entrySet()) {
            ITEM_REGISTER.register(entry.getKey(), entry.getValue());
        }
    }

    public static void RegisteryBlocks(Map<String, Supplier<Block>> map) {
        for (Map.Entry<String, Supplier<Block>> entry : map.entrySet()) {
            BLOCK_REGISTER.register(entry.getKey(), entry.getValue());
            ITEM_REGISTER.register(entry.getKey(),
                    () -> new BlockItem(entry.getValue().get(), new Item.Properties().m_41491_(MMTAB)));
        }
    }

    public static void RegisteryBlocksWithoutItem(Map<String, Supplier<Block>> map) {
        for (Map.Entry<String, Supplier<Block>> entry : map.entrySet()) {
            BLOCK_REGISTER.register(entry.getKey(), entry.getValue());
        }
    }

    public static void RegisterySingleBlock(String name, Block block, Item item) {
        BLOCK_REGISTER.register(name, () -> block);
        ITEM_REGISTER.register(name, () -> item);
    }

    private static void InitListsNeedToRegister() {
        RegisteredItems.Init();
        RegisteredBlocks.Init();
        RegisteredFluids.Init();
        Veins.Init();
        VeinFeature.Init();
    }

}
