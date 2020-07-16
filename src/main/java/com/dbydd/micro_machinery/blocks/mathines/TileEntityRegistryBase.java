package com.dbydd.micro_machinery.blocks.mathines;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TileEntityRegistryBase {

    public static List<TileEntityRegistryBase> registerys = new ArrayList<>();
    private final String name;
    private final TileEntityType type;


    public TileEntityRegistryBase(String name, Function<TileEntityType, TileEntity> function, Block block) {
        this.name = name;
        this.type = TileEntityType.Builder.create(()->function.apply(this.getType()), block).build(null);
    }

    public TileEntityType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

