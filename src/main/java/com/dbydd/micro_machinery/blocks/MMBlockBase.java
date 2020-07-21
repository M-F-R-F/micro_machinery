package com.dbydd.micro_machinery.blocks;

import com.dbydd.micro_machinery.registery_lists.RenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class MMBlockBase extends Block {
    public static final Properties DEFAULT_PROPERTIES = Properties.create(Material.ROCK).notSolid();
    public static Map<String, Supplier<Block>> registeries = new TreeMap<>();
    private final String name;

    public MMBlockBase(Properties properties, String name) {
        super(properties);
        this.name = name;
        registeries.put(name, () -> this);
    }

    public MMBlockBase(String name) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registeries.put(name, () -> this);
    }

    public MMBlockBase(Properties properties, String name, RenderType renderType) {
        super(properties);
        this.name = name;
        registeries.put(name, () -> this);
        RenderTypes.blockRenderTypeMap.put(this, renderType);
    }

    public MMBlockBase(String name, RenderType renderType) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registeries.put(name, () -> this);
        RenderTypes.blockRenderTypeMap.put(this, renderType);
    }

    @Override
    public String getTranslationKey() {
        return "micro_machinery_" + name;
    }
}
