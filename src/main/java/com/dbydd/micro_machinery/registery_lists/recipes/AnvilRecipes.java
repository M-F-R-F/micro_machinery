package com.dbydd.micro_machinery.registery_lists.recipes;

import com.dbydd.micro_machinery.enums.EnumAnvilType;
import com.dbydd.micro_machinery.recipes.Anvil.AnvilRecipe;
import com.dbydd.micro_machinery.registery_lists.RegisteredBlocks;
import com.dbydd.micro_machinery.registery_lists.RegisteredItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AnvilRecipes {
    //stone
    public static final AnvilRecipe orecopper = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORECOPPER),new ItemStack(RegisteredItems.CRUSHED_COPPER,2), EnumAnvilType.STONE);
    public static final AnvilRecipe oretin = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORECOPPER),new ItemStack(RegisteredItems.CRUSHED_TIN,2), EnumAnvilType.STONE);
    public static final AnvilRecipe platecopper = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_COPPER),new ItemStack(RegisteredItems.PLATE_COPPER), EnumAnvilType.STONE);
    public static final AnvilRecipe platetin = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_TIN),new ItemStack(RegisteredItems.PLATE_TIN), EnumAnvilType.STONE);
    public static final AnvilRecipe gravel = new AnvilRecipe(new ItemStack(Blocks.COBBLESTONE),new ItemStack(Blocks.GRAVEL), EnumAnvilType.STONE);
    public static final AnvilRecipe sand = new AnvilRecipe(new ItemStack(Blocks.GRAVEL),new ItemStack(Blocks.SAND), EnumAnvilType.STONE);
    public static final AnvilRecipe sand2 = new AnvilRecipe(new ItemStack(Blocks.SANDSTONE),new ItemStack(Blocks.SAND), EnumAnvilType.STONE);
    public static final AnvilRecipe sand3 = new AnvilRecipe(new ItemStack(Blocks.RED_SANDSTONE),new ItemStack(Blocks.RED_SAND), EnumAnvilType.STONE);
    public static final AnvilRecipe dustcarbon = new AnvilRecipe(new ItemStack(Items.COAL),new ItemStack(RegisteredItems.DUST_CARBON), EnumAnvilType.STONE);

    //bronze
    public static final AnvilRecipe oreiron = new AnvilRecipe(new ItemStack(Blocks.IRON_ORE),new ItemStack(RegisteredItems.CRUSHED_IRON,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oregold = new AnvilRecipe(new ItemStack(Blocks.GOLD_ORE),new ItemStack(RegisteredItems.CRUSHED_GOLD,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orenickel = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORENICKEL),new ItemStack(RegisteredItems.CRUSHED_NICKEL,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oresilver = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORESILVER),new ItemStack(RegisteredItems.CRUSHED_SILVER,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe platebronze = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_BRONZE),new ItemStack(RegisteredItems.PLATE_BRONZE), EnumAnvilType.BRONZE);
    public static final AnvilRecipe platenickel = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_NICKEL),new ItemStack(RegisteredItems.PLATE_NICKEL), EnumAnvilType.BRONZE);
    public static final AnvilRecipe platesilver = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_SILVER),new ItemStack(RegisteredItems.PLATE_SILVER), EnumAnvilType.BRONZE);
    public static final AnvilRecipe plategold = new AnvilRecipe(new ItemStack(Items.GOLD_INGOT),new ItemStack(RegisteredItems.PLATE_GOLD), EnumAnvilType.BRONZE);
    public static final AnvilRecipe plateiron = new AnvilRecipe(new ItemStack(Items.IRON_INGOT),new ItemStack(RegisteredItems.PLATE_IRON), EnumAnvilType.BRONZE);
    public static final AnvilRecipe wirecopper = new AnvilRecipe(new ItemStack(RegisteredItems.STICK_COPPER),new ItemStack(RegisteredItems.STRING_COPPER), EnumAnvilType.BRONZE);
    public static final AnvilRecipe wiretin = new AnvilRecipe(new ItemStack(RegisteredItems.STICK_TIN),new ItemStack(RegisteredItems.STRING_TIN), EnumAnvilType.BRONZE);

    //iron
    public static final AnvilRecipe platesteel = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_STEEL),new ItemStack(RegisteredItems.PLATE_STEEL), EnumAnvilType.PIGIRON);
    public static final AnvilRecipe plateinvar = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_INVAR),new ItemStack(RegisteredItems.PLATE_INVAR), EnumAnvilType.PIGIRON);
    public static final AnvilRecipe platealuminum = new AnvilRecipe(new ItemStack(RegisteredItems.INGOT_ALUMINUM),new ItemStack(RegisteredItems.PLATE_ALUMINUM), EnumAnvilType.PIGIRON);
    public static final AnvilRecipe orepyrolusite = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREPYROLUSITE),new ItemStack(RegisteredItems.CRUSHED_PYROLUSITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orepyrolusite1 = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREPYROLUSITE_NETHER),new ItemStack(RegisteredItems.CRUSHED_PYROLUSITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orechromite = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORECHROMITE),new ItemStack(RegisteredItems.CRUSHED_CHROMITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orebauxite = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREBAUXITE),new ItemStack(RegisteredItems.CRUSHED_BAUXITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orebauxite1 = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREBAUXITE_NETHER),new ItemStack(RegisteredItems.CRUSHED_BAUXITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oreferromanganese = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREFERROMANGANESE),new ItemStack(RegisteredItems.CRUSHED_FERROMANGANESE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oreferromanganese1 = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREFERROMANGANESE_NETHER),new ItemStack(RegisteredItems.CRUSHED_FERROMANGANESE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orenolanite = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORENOLANITE),new ItemStack(RegisteredItems.CRUSHED_NOLANITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe orenolanite1 = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORENOLANITE_NETHER),new ItemStack(RegisteredItems.CRUSHED_NOLANITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oretunstite = new AnvilRecipe(new ItemStack(RegisteredBlocks.ORETUNSTITE),new ItemStack(RegisteredItems.CRUSHED_TUNSTITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe oreilmenite = new AnvilRecipe(new ItemStack(RegisteredBlocks.OREILMENITE),new ItemStack(RegisteredItems.CRUSHED_ILMENITE,2), EnumAnvilType.BRONZE);
    public static final AnvilRecipe wiregold = new AnvilRecipe(new ItemStack(RegisteredItems.STICK_GOLD),new ItemStack(RegisteredItems.STRING_GOLD), EnumAnvilType.BRONZE);
    public static final AnvilRecipe wireiron = new AnvilRecipe(new ItemStack(RegisteredItems.STICK_IRON),new ItemStack(RegisteredItems.STRING_IRON), EnumAnvilType.BRONZE);
    public static final AnvilRecipe wirealuminum = new AnvilRecipe(new ItemStack(RegisteredItems.STICK_ALUMINUM),new ItemStack(RegisteredItems.STRING_ALUMINUM), EnumAnvilType.BRONZE);


    public static void init(){}
}
