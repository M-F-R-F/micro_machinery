package org.mfrf.micro_machienry;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.mfrf.micro_machienry.blocks.MMBlockBase;
import org.mfrf.micro_machienry.items.MMAxeBase;
import org.mfrf.micro_machienry.items.MMHammerBase;
import org.mfrf.micro_machienry.items.MMItemBase;
import org.mfrf.micro_machienry.items.MMSwordBase;
import org.mfrf.micro_machienry.registeried_lists.*;
import org.mfrf.micro_machienry.registeried_lists.strctures.Veins;
import org.mfrf.micro_machienry.worldgen.VeinFeature;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Micro_machienry.MOD_ID)
public class Micro_machienry {

    public static final String NAME = "micro_machinery";
    public static final String MOD_ID = "Micro Machinery";
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, NAME);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, NAME);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, NAME);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister
            .create(ForgeRegistries.FEATURES, NAME);
    public static final ItemGroup MMTAB = new MMTab();
    private static final Logger logger = LogManager.getLogger(MOD_ID);

    static {
        InitListsNeedToRegister();

        Micro_Machinery.RegisteryItems(MMItemBase.registeries);
        Micro_Machinery.RegisteryItems(MMSwordBase.registeries);
        Micro_Machinery.RegisteryItems(MMHammerBase.registeries);
        Micro_Machinery.RegisteryItems(MMAxeBase.registeries);
        Micro_Machinery.RegisteryBlocks(MMBlockBase.registeries);
        Micro_Machinery.RegisteryBlocksWithoutItem(MMBlockBase.registeries_no_item);

        ITEM_REGISTER.register("lathe", LatheBlockItem::new);
        ITEM_REGISTER.register("pump", PumpBlockItem::new);
    }

    public Micro_Machinery() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredTileEntityTypes.TILE_ENTITY_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredContainerTypes.CONTAINER_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredRecipeSerializers.RECIPE_SERIALIZERS_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        logger.info(MOD_ID + " Loaded.");
        logger.info("Mixin Version: " + MixinBootstrap.VERSION);
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
                    () -> new BlockItem(entry.getValue().get(), new Item.Properties().group(MMTAB)));
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
