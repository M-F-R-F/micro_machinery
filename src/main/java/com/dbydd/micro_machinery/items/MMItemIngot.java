package com.dbydd.micro_machinery.items;

import net.minecraft.util.ResourceLocation;

public class MMItemIngot extends MMItemBase {
    public MMItemIngot(String name) {
        super(name);
        setRegistryName(new ResourceLocation("micro_machinery","ingot"));
    }
}
