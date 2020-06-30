package com.dbydd.micro_machinery.blocks;

import com.dbydd.micro_machinery.registery_lists.BlockRenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMBlockBase extends Block {
    public static final Properties DEFAULT_PROPERTIES = Properties.create(Material.ROCK).notSolid();
    public static Map<String, Supplier<Block>> registerys = new HashMap<>();
    private final String name;

    public MMBlockBase(Properties properties, String name) {
        super(properties);
        this.name = name;
        registerys.put(name, () -> this);
    }

    public MMBlockBase(String name) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registerys.put(name, () -> this);
    }

    public MMBlockBase(Properties properties, String name, RenderType renderType) {
        super(properties);
        this.name = name;
        registerys.put(name, () -> this);
        BlockRenderTypes.blockRenderTypeMap.put(this, renderType);
    }

    public MMBlockBase(String name, RenderType renderType) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registerys.put(name, () -> this);
        BlockRenderTypes.blockRenderTypeMap.put(this, renderType);
    }

    @Override
    public String getTranslationKey() {
        return "micro_machinery_" + name;
    }
}
