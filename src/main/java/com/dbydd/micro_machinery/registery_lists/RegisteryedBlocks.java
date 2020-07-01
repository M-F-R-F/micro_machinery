package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;

public class RegisteryedBlocks {


    /**
     * 空心，即.notSolid()
     */
    public static final Block.Properties HOLLOW_PROPERTY = Block.Properties.create(Material.ROCK).notSolid();

    public static final Block EXAMPLE_BLOCK = new MMBlockBase(HOLLOW_PROPERTY, "example_block");

    /**
     * 可以半透明
     */
    public static final Block EXAMPLE_TRANSPARTMENT_BLOCK_1 = new MMBlockBase(HOLLOW_PROPERTY,"example_block_transpartment_1", RenderType.getTranslucent());

    /**
     * 要么不透明，要么完全透明
     */
    public static final Block EXAMPLE_TRANSPARTMENT_BLOCK_2 = new MMBlockBase(HOLLOW_PROPERTY,"example_block_transpartment_1", RenderType.getCutoutMipped());

    private RegisteryedBlocks() {
    }

    public static void Init() {
    }
}
