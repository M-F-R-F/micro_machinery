package com.dbydd.micro_machinery;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.items.MMAxeBase;
import com.dbydd.micro_machinery.items.MMHammerBase;
import com.dbydd.micro_machinery.items.MMItemBase;
import com.dbydd.micro_machinery.items.MMSwordBase;
import com.dbydd.micro_machinery.registery_lists.*;
import com.dbydd.micro_machinery.registery_lists.strctures.Veins;
import com.dbydd.micro_machinery.worldgen.VeinFeature;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

@Mod("micro_machinery")
public class Micro_Machinery {
    public static final String NAME = "micro_machinery";
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, NAME);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, NAME);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, NAME);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, NAME);
    public static final ItemGroup MMTAB = new MMTab();

    static {
        InitListsNeedToRegister();

        Micro_Machinery.RegisteryItems(MMItemBase.registeries);
        Micro_Machinery.RegisteryItems(MMSwordBase.registeries);
        Micro_Machinery.RegisteryItems(MMHammerBase.registeries);
        Micro_Machinery.RegisteryItems(MMAxeBase.registeries);
        Micro_Machinery.RegisteryBlocks(MMBlockBase.registeries);
        Micro_Machinery.RegisteryBlocksWithoutItem(MMBlockBase.registeries_no_item);
    }

    public Micro_Machinery() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registered_Tileentitie_Types.TILE_ENTITY_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registered_ContainerTypes.CONTAINER_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredRecipeSerializers.RECIPE_SERIALIZERS_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void RegisteryItems(Map<String, Supplier<Item>> map) {
        for (Map.Entry<String, Supplier<Item>> entry : map.entrySet()) {
            ITEM_REGISTER.register(entry.getKey(), entry.getValue());
        }
    }

    public static void RegisteryBlocks(Map<String, Supplier<Block>> map) {
        for (Map.Entry<String, Supplier<Block>> entry : map.entrySet()) {
            BLOCK_REGISTER.register(entry.getKey(), entry.getValue());
            ITEM_REGISTER.register(entry.getKey(), () -> new BlockItem(entry.getValue().get(), new Item.Properties().group(MMTAB)));
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
