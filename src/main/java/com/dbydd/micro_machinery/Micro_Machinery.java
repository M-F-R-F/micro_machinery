package com.dbydd.micro_machinery;

import com.dbydd.micro_machinery.items.MMItemBase;
import com.dbydd.micro_machinery.registery_lists.RegisteryedItems;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

@Mod("micro_machinery")
public class Micro_Machinery {
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, "micro_machinery");

    static {
        InitListsNeedToRegister();

        Micro_Machinery.RegisteryItems(MMItemBase.registerys);
    }


    public Micro_Machinery() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void RegisteryItems(Map<String, Supplier<Item>> map) {
        for (Map.Entry entry : map.entrySet()) {
            ITEM_REGISTER.register((String) entry.getKey(), (Supplier<? extends Item>) entry.getValue());
        }
    }

    private static void InitListsNeedToRegister() {
        RegisteryedItems.Init();
    }
}
