package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.items.MMItemBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class RegisteryedItems {

    static {
        new MMItemBase(new Item.Properties().group(ItemGroup.FOOD), "testitem");
    }

    private RegisteryedItems() {

    }

    public static void Init() {

    }
}
