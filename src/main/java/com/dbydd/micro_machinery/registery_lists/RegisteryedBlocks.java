package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.ToolType;

public class RegisteryedBlocks {


    /**
     * 空心，即.notSolid()
     */
    public static final Block.Properties HOLLOW_PROPERTY = Block.Properties.create(Material.ROCK).notSolid();
    public static final Block.Properties HARD1 = Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(2.0f).harvestLevel(1).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD2 = Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(2.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD3 = Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(2.0f).harvestLevel(3).harvestTool(ToolType.PICKAXE);

    public static final Block EXAMPLE_BLOCK = new MMBlockBase(HOLLOW_PROPERTY, "example_block");

    /**
     * 可以半透明
     */
    public static final Block EXAMPLE_TRANSPARTMENT_BLOCK_1 = new MMBlockBase(HOLLOW_PROPERTY,"example_block_transpartment_1", RenderType.getTranslucent());

    /**
     * 要么不透明，要么完全透明
     */
    //Ores
    public static final Block ORECOPPER = new MMBlockBase(HARD1,"orecopper");
    public static final Block ORETIN = new MMBlockBase(HARD1,"oretin");
    public static final Block OREILMENITE = new MMBlockBase(HARD1,"oreilmenite");
    public static final Block ORESILVER = new MMBlockBase(HARD1,"oresilver");
    public static final Block OREPYROLUSITE = new MMBlockBase(HARD2,"orepyrolusite");
    public static final Block OREGRAPHITE = new MMBlockBase(HARD2,"oregraphite");
    public static final Block ORECHROMITE = new MMBlockBase(HARD3,"orechromite");
    public static final Block OREBAUXITE = new MMBlockBase(HARD1,"orebauxite");
    public static final Block OREFERROMANGANESE = new MMBlockBase(HARD3,"oreferromanganese");
    public static final Block ORENICKEL = new MMBlockBase(HARD2,"orenickel");
    public static final Block ORENOLANITE = new MMBlockBase(HARD2,"orenolanite");
    public static final Block OREPYROLUSITE_NETHER = new MMBlockBase(HARD2,"orepyrolusite_nether");
    public static final Block OREGRAPHITE_NETHER = new MMBlockBase(HARD2,"oregraphite_nether");
    public static final Block OREBAUXITE_NETHER = new MMBlockBase(HARD1,"orebauxite_nether");
    public static final Block OREFERROMANGANESE_NETHER = new MMBlockBase(HARD3,"oreferromanganese_nether");
    public static final Block ORENOLANITE_NETHER = new MMBlockBase(HARD2,"orenolanite_nether");
    public static final Block ORETUNSTITE = new MMBlockBase(HARD3,"oretunstite");
    //oreblock
    public static final Block BLOCKCOPPER = new MMBlockBase(HARD2,"blockcopper");
    public static final Block BLOCKTIN = new MMBlockBase(HARD1,"blocktin");
    public static final Block BLOCKBRONZE = new MMBlockBase(HARD2,"blockbronze");
    public static final Block BLOCKSTEEL = new MMBlockBase(HARD2,"blocksteel");
    public static final Block BLOCKSS = new MMBlockBase(HARD3,"blockss");
    public static final Block BLOCKTUNGSTEN = new MMBlockBase(HARD3,"blocktungsten");
    public static final Block BLOCKNICKEL = new MMBlockBase(HARD2,"blocknickel");
    public static final Block BLOCKTUNGSTEN_STEEL = new MMBlockBase(HARD3,"blocktungsten_steel");
    public static final Block BLOCKINVAR = new MMBlockBase(HARD2,"blockinvar");
    public static final Block BLOCKHSS = new MMBlockBase(HARD3,"blockhss");
    public static final Block BLOCKSILVER = new MMBlockBase(HARD1,"blocksilver");
    public static final Block BLOCKCHROMIUM = new MMBlockBase(HARD2,"blockchromium");
    public static final Block BLOCKVANADIUM = new MMBlockBase(HARD2,"blockvanadium");
    public static final Block BLOCKCOBALT = new MMBlockBase(HARD3,"blockcobalt");
    public static final Block BLOCKTITANIUM = new MMBlockBase(HARD3,"blocktitanium");
    public static final Block BLOCKALUMINUM = new MMBlockBase(HARD2,"blockaluminum");
    public static final Block BLOCKCALLOY = new MMBlockBase(HARD2,"blockcalloy");





    private RegisteryedBlocks() {
    }

    public static void Init() {
    }
}
