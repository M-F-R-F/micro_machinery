package org.mfrf.micro_machienry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class MMBlockBase extends Block {
    public static final Properties DEFAULT_PROPERTIES = Properties.create(Material.ROCK).notSolid();
    public static Map<String, Supplier<Block>> registeries = new TreeMap<>();
    public static Map<String, Supplier<Block>> registeries_no_item = new TreeMap<>();
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

    public MMBlockBase(String name, boolean noitem) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registeries_no_item.put(name, () -> this);
    }

    public MMBlockBase(Properties properties, String name, boolean noitem) {
        super(DEFAULT_PROPERTIES);
        this.name = name;
        registeries_no_item.put(name, () -> this);
    }
}
