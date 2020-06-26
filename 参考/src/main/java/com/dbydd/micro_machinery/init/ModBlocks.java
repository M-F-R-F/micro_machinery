package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.blocks.*;
import com.dbydd.micro_machinery.blocks.machine.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //ores
    //stone = 1; iron = 2; diamond = 3
    public static final Block ORECOPPER = new BlockOres("Copper", 1, 2.5f);
    public static final Block ORETIN = new BlockOres("Tin", 1, 2.0f);
    public static final Block OREILMENITE = new BlockOres("Ilmenite", 1, 5.0f);
    public static final Block ORESILVER = new BlockOres("Silver", 1, 2.0f);
    public static final Block OREPYROLUSITE = new BlockOres("Pyrolusite", 2, 3.0f);
    public static final Block OREGRAPHITE = new BlockOres("Graphite", 2, 2.0f);
    public static final Block ORECHROMITE = new BlockOres("Chromite", 3, 4.5f);
    public static final Block OREBAUXITE = new BlockOres("Bauxite", 1, 2.5f);
    public static final Block OREFERROMANGANESE = new BlockOres("FerroManganese", 3, 3.0f);
    public static final Block ORENICKEL = new BlockOres("Nickel", 2, 3.5f);
    public static final Block ORENOLANITE = new BlockOres("Nolanite", 2, 3.0f);

    //netherore
    public static final Block OREPYROLUSITE_NETHER = new OreNether("Pyrolusite", 2, 3.0f);
    public static final Block OREGRAPHITE_NETHER = new OreNether("Graphite", 2, 2.0f);
    public static final Block OREBAUXITE_NETHER = new OreNether("Bauxite", 1, 2.5f);
    public static final Block OREFERROMANGANESE_NETHER = new OreNether("FerroManganese", 3, 3.0f);
    public static final Block ORENOLANITE_NETHER = new OreNether("Nolanite", 2, 3.0f);

    //endore
    public static final Block ORETUNSTITE = new OreEnd("Tunstite", 3, 7.0f);

    //oreblock
    public static final Block BLOCKCOPPER = new MaterialBlock("Copper", 2, 4.0f);
    public static final Block BLOCKTIN = new MaterialBlock("Tin", 1, 2.5f);
    public static final Block BLOCKBRONZE = new MaterialBlock("Bronze", 2, 4.5f);
    public static final Block BLOCKSTEEL = new MaterialBlock("Steel", 2, 3.5f);
    public static final Block BLOCKSS = new MaterialBlock("SS", 3, 5.0f);
    public static final Block BLOCKTUNGSTEN = new MaterialBlock("Tungsten", 3, 7.0f);
    public static final Block BLOCKNICKEL = new MaterialBlock("Nickel", 2, 3.5f);
    public static final Block BLOCKTUNGSTEN_STEEL = new MaterialBlock("Tungsten_Steel", 3, 7.0f);
    public static final Block BLOCKINVAR = new MaterialBlock("Invar", 2, 4.0f);
    public static final Block BLOCKHSS = new MaterialBlock("HSS", 3, 6.0f);
    public static final Block BLOCKSILVER = new MaterialBlock("Silver", 1, 2.0f);
    public static final Block BLOCKCHROMIUM = new MaterialBlock("Chromium", 2, 6.5f);
    public static final Block BLOCKVANADIUM = new MaterialBlock("Vanadium", 2, 5.0f);
    public static final Block BLOCKCOBALT = new MaterialBlock("Cobalt", 3, 5.0f);
    public static final Block BLOCKTITANIUM = new MaterialBlock("Titanium", 3, 7.0f);
    public static final Block BLOCKALUMINUM = new MaterialBlock("Aluminum", 2, 3.0f);
    public static final Block BLOCKNCALLOY = new MaterialBlock("NCAlloy", 2, 4.0f);

    //block
    public static final Block BLOCKPCM = new MaterialBlock("pcm", 2, 4.0f);
    public static final Block BLOCKSTALINITE = new MaterialBlock("stalinite", 2, 4.0f);
    public static final Block BLOCKTANK = new MaterialBlock("tank", 2, 4.0f);
    public static final Block BLOCKCONTAINER = new MaterialBlock("container", 2, 4.0f);
    public static final Block BLOCKSTEEL_SCAFFOLD = new MaterialBlock("steel_scaffold", 2, 4.0f);
    public static final Block BLOCKVACUUM_FACILITIES = new MaterialBlock("vacuum_facilities", 2, 4.0f);
    public static final Block BLOCKSLAG_CONCRETE = new MaterialBlock("slag_concrete", 2, 4.0f);
    public static final Block BLOCKCLAYBRICK = new MaterialBlock("claybrick", 1, 4.0f);
    public static final Block BLOCKFIREBRICK = new MaterialBlock("firebrick", 1, 4.0f);

    //fluids
    public static final Block MOLTEN_COPPER = new BlockFluid("molten_copper", ModFluids.MOLTEN_COPPER, Material.LAVA, 15, 3);
    public static final Block MOLTEN_TIN = new BlockFluid("molten_tin", ModFluids.MOLTEN_TIN, Material.LAVA, 15, 3);
    public static final Block MOLTEN_BRONZE = new BlockFluid("molten_bronze", ModFluids.MOLTEN_BRONZE, Material.LAVA, 15, 3);
    public static final Block MOLTEN_STEEL = new BlockFluid("molten_steel", ModFluids.MOLTEN_STEEL, Material.LAVA, 15, 3);
    public static final Block MOLTEN_SS = new BlockFluid("molten_ss", ModFluids.MOLTEN_SS, Material.LAVA, 15, 3);
    public static final Block MOLTEN_TUNGSTEN = new BlockFluid("molten_tungsten", ModFluids.MOLTEN_TUNGSTEN, Material.LAVA, 15, 3);
    public static final Block MOLTEN_NICKEL = new BlockFluid("molten_nickel", ModFluids.MOLTEN_NICKEL, Material.LAVA, 15, 3);
    public static final Block MOLTEN_INVAR = new BlockFluid("molten_invar", ModFluids.MOLTEN_INVAR, Material.LAVA, 15, 3);
    public static final Block MOLTEN_HSS = new BlockFluid("molten_hss", ModFluids.MOLTEN_HSS, Material.LAVA, 15, 3);
    public static final Block MOLTEN_SILVER = new BlockFluid("molten_silver", ModFluids.MOLTEN_SILVER, Material.LAVA, 15, 3);
    public static final Block MOLTEN_GOLD = new BlockFluid("molten_gold", ModFluids.MOLTEN_GOLD, Material.LAVA, 15, 3);
    public static final Block MOLTEN_MANGANESE = new BlockFluid("molten_manganese", ModFluids.MOLTEN_MANGANESE, Material.LAVA, 15, 3);
    public static final Block MOLTEN_CHROMIUM = new BlockFluid("molten_chromium", ModFluids.MOLTEN_CHROMIUM, Material.LAVA, 15, 3);
    public static final Block MOLTEN_VANADIUM = new BlockFluid("molten_vanadium", ModFluids.MOLTEN_VANADIUM, Material.LAVA, 15, 3);
    public static final Block MOLTEN_COBALT = new BlockFluid("molten_cobalt", ModFluids.MOLTEN_COBALT, Material.LAVA, 15, 3);
    public static final Block MOLTEN_TITANIUM = new BlockFluid("molten_titanium", ModFluids.MOLTEN_TITANIUM, Material.LAVA, 15, 3);
    public static final Block MOLTEN_ALUMINUM = new BlockFluid("molten_aluminum", ModFluids.MOLTEN_ALUMINUM, Material.LAVA, 15, 3);
    public static final Block MOLTEN_NCALLOY = new BlockFluid("molten_ncalloy", ModFluids.MOLTEN_NCALLOY, Material.LAVA, 15, 3);
    public static final Block MOLTEN_FERROCHROME = new BlockFluid("molten_ferrochrome", ModFluids.MOLTEN_FERROCHROME, Material.LAVA, 15, 3);
    public static final Block MOLTEN_IRON = new BlockFluid("molten_iron", ModFluids.MOLTEN_IRON, Material.LAVA, 15, 3);
    public static final Block GOLDEN_APPLE_JUICE = new BlockFluid("golden_apple_juice", ModFluids.GOLDEN_APPLE_JUICE, Material.WATER, 15, 3);
    public static final Block APPLE_JUICE = new BlockFluid("apple_juice", ModFluids.APPLE_JUICE, Material.WATER, 0, 3);
    public static final Block ETHENE = new BlockFluid("ethene", ModFluids.ETHENE, Material.LAVA, 0, 3);

    //tileentities
    //machine
    public static final Block KLIN = new BlockKlin("klin", Material.IRON);
    public static final Block STONE_ANVIL = new BlockForgingAnvil("stone_anvil", 1);
    public static final Block BRONZE_ANVIL = new BlockForgingAnvil("bronze_anvil", 2);
    public static final Block PIGIRON_ANVIL = new BlockForgingAnvil("pigiron_anvil", 3);
    public static final Block FIREGENERATOR = new FireGenerator();

    public static final Block TICKABLEENERGYCABLEWITHOUTGENERATEFORCE = new BlockTickableEnergyCableWithoutGenerateForce("tickableenergycablewithoutgenerateforce",Material.IRON, 25600);
    public static final Block ENERGYCABLEWITHOUTGENERATEFORCE = new BlockEnergyCableWithoutGenerateForce("energycablewithoutgenerateforce",Material.IRON, 25600);
    public static final Block CABLE_HEAD = new Cable_Heads("cable_head");
    public static final Block HAND_GENERATOR = new BlockHandGenerator();
    public static final Block HAND_GENERATOR_HANDLE = new BlockWithFacing("hand_generator_handle", Material.AIR).setCreativeTab(null);
}
