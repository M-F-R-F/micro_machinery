package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.blocks.mathines.klin.BlockKlin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.ToolType;

public class RegisteryedBlocks {

    public static final Block.Properties HARD1 = Block.Properties.create(Material.ROCK).harvestLevel(1).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD2 = Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE);
    public static final Block.Properties HARD3 = Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE);

    public static final Block STALINITE = new MMBlockBase(Block.Properties.create(Material.GLASS).hardnessAndResistance(8.0f).harvestTool(ToolType.PICKAXE).noDrops().notSolid(), "stalinite");
    public static final Block STEEL_SCAFFOLDING = new MMBlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).notSolid(), "steel_scaffolding");
    //Ores
    public static final Block ORECOPPER = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orecopper");
    public static final Block ORETIN = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "oretin");
    public static final Block OREILMENITE = new MMBlockBase(HARD1.hardnessAndResistance(5.0f), "oreilmenite");
    public static final Block ORESILVER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "oresilver");
    public static final Block OREPYROLUSITE = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orepyrolusite");
    public static final Block OREGRAPHITE = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "oregraphite");
    public static final Block ORECHROMITE = new MMBlockBase(HARD3.hardnessAndResistance(4.5f), "orechromite");
    public static final Block OREBAUXITE = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orebauxite");
    public static final Block OREFERROMANGANESE = new MMBlockBase(HARD3.hardnessAndResistance(3.0f), "oreferromanganese");
    public static final Block ORENICKEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "orenickel");
    public static final Block ORENOLANITE = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orenolanite");
    public static final Block OREPYROLUSITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orepyrolusite_nether");
    public static final Block OREGRAPHITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "oregraphite_nether");
    public static final Block OREBAUXITE_NETHER = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "orebauxite_nether");
    public static final Block OREFERROMANGANESE_NETHER = new MMBlockBase(HARD3.hardnessAndResistance(3.0f), "oreferromanganese_nether");
    public static final Block ORENOLANITE_NETHER = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "orenolanite_nether");
    public static final Block ORETUNSTITE = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "oretunstite");
    //oreblock
    public static final Block BLOCKCOPPER = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockcopper");
    public static final Block BLOCKTIN = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "blocktin");
    public static final Block BLOCKBRONZE = new MMBlockBase(HARD2.hardnessAndResistance(4.5f), "blockbronze");
    public static final Block BLOCKSTEEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "blocksteel");
    public static final Block BLOCKSS = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "blockss");
    public static final Block BLOCKTUNGSTEN = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktungsten");
    public static final Block BLOCKNICKEL = new MMBlockBase(HARD2.hardnessAndResistance(3.5f), "blocknickel");
    public static final Block BLOCKTUNGSTEN_STEEL = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktungsten_steel");
    public static final Block BLOCKINVAR = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockinvar");
    public static final Block BLOCKHSS = new MMBlockBase(HARD3.hardnessAndResistance(6.0f), "blockhss");
    public static final Block BLOCKSILVER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "blocksilver");
    public static final Block BLOCKCHROMIUM = new MMBlockBase(HARD2.hardnessAndResistance(6.5f), "blockchromium");
    public static final Block BLOCKVANADIUM = new MMBlockBase(HARD2.hardnessAndResistance(5.0f), "blockvanadium");
    public static final Block BLOCKCOBALT = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "blockcobalt");
    public static final Block BLOCKTITANIUM = new MMBlockBase(HARD3.hardnessAndResistance(7.0f), "blocktitanium");
    public static final Block BLOCKALUMINUM = new MMBlockBase(HARD2.hardnessAndResistance(3.0f), "blockaluminum");
    public static final Block BLOCKNCALLOY = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "blockncalloy");
    //coil
    public static final Block COILCOPPER = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "coilcopper");
    public static final Block COILNICKEL = new MMBlockBase(HARD2.hardnessAndResistance(2.5f), "coilnickel");
    public static final Block COILALUMINUM = new MMBlockBase(HARD2.hardnessAndResistance(2.0f), "coilaluminum");
    public static final Block COILTUNGSTEN = new MMBlockBase(HARD3.hardnessAndResistance(6.0f), "coiltungsten");
    public static final Block COILCOBALT = new MMBlockBase(HARD3.hardnessAndResistance(4.0f), "coilcobalt");
    //machine casing
    public static final Block CASING_1 = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "casing_1");
    public static final Block CASING_2 = new MMBlockBase(HARD1.hardnessAndResistance(3.0f), "casing_2");
    public static final Block CASING_3 = new MMBlockBase(HARD2.hardnessAndResistance(4.0f), "casing_3");
    public static final Block CASING_4 = new MMBlockBase(HARD3.hardnessAndResistance(5.0f), "casing_4");
    //discarded metal
    public static final Block MOLTEN_COPPER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_copper_discarded");
    public static final Block MOLTEN_TIN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_tin_discarded");
    public static final Block MOLTEN_BRONZE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_bronze_discarded");
    public static final Block MOLTEN_STEEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_steel_discarded");
    public static final Block MOLTEN_SS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ss_discarded");
    public static final Block MOLTEN_TUNGSTEN_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_tungsten_discarded");
    public static final Block MOLTEN_NICKEL_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_nickel_discarded");
    public static final Block MOLTEN_INVAR_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_invar_discarded");
    public static final Block MOLTEN_HSS_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_hss_discarded");
    public static final Block MOLTEN_SILVER_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_silver_discarded");
    public static final Block MOLTEN_GOLD_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_gold_discarded");
    public static final Block MOLTEN_MANGANESE_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_manganese_discarded");
    public static final Block MOLTEN_CHROMIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_chromium_discarded");
    public static final Block MOLTEN_VANADIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_vanadium_discarded");
    public static final Block MOLTEN_COBALT_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_cobalt_discarded");
    public static final Block MOLTEN_TITANIUM_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_titanium_discarded");
    public static final Block MOLTEN_NCALLOY_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ncalloy_discarded");
    public static final Block MOLTEN_FERROCHROME_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_ferrochrome_discarded");
    public static final Block MOLTEN_IRON_DISCARDED = new MMBlockBase(HARD1.hardnessAndResistance(2.0f),"molten_iron_discarded");
    //other
    public static final Block PCM = new MMBlockBase(HARD1.hardnessAndResistance(2.5f), "pcm");
    public static final Block TANK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "tank_block");
    public static final Block CONTAINER_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "container_block");
    public static final Block SLAG_CONCRETE = new MMBlockBase(HARD1.hardnessAndResistance(1.5f), "slag_concrete");
    public static final Block CLAY_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "clay_brick_block");
    public static final Block FIRE_BRICK_BLOCK = new MMBlockBase(HARD1.hardnessAndResistance(2.0f), "fire_brick_block");
    //tile entity
    //machine
    public static final Block KLIN = new BlockKlin();
    //cable

    //pipe

    //fluid pipe


    private RegisteryedBlocks() {
    }

    public static void Init() {
    }
}
