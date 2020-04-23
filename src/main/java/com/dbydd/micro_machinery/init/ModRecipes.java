package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.recipes.firegenerator.FireGeneratorRecipe;
import com.dbydd.micro_machinery.recipes.forginganvil.ForgingAnvilRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinFluidRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static final List<KlinFluidRecipe> klinFluidRecipes = new ArrayList<KlinFluidRecipe>();
    public static final List<KlinRecipe> klinToFluidRecipes = new ArrayList<KlinRecipe>();
    public static final List<ForgingAnvilRecipe> forgingAnvilRecipes = new ArrayList<ForgingAnvilRecipe>();
    public static final List<FireGeneratorRecipe> fireGenerateRecipes = new ArrayList<FireGeneratorRecipe>();

    //klin to fluid
    public static final KlinRecipe molten_copper1 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORECOPPER, 1), 120);
    public static final KlinRecipe molten_copper2 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.CRUSHEDCOPPER, 1), 120);
    public static final KlinRecipe molten_copper3 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_COPPER, 160), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.ORECOPPERCONCENTRATE, 1), 120);
    public static final KlinRecipe molten_tin1 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_TIN, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModBlocks.ORETIN, 1), 60);
    public static final KlinRecipe molten_tin2 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_TIN, 144), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.CRUSHEDTIN, 1), 60);
    public static final KlinRecipe molten_tin3 = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_TIN, 160), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.ORETINCONCENTRATE, 1), 60);
    public static final KlinRecipe molten_bronze = new KlinRecipe(new FluidStack(ModFluids.MOLTEN_BRONZE, 576), new ItemStack(ModItems.INGOTCOPPER, 3), new ItemStack(ModItems.INGOTTIN, 1), 100);

    //klin fluid to material
    public static final KlinFluidRecipe ingot_copper = new KlinFluidRecipe(new ItemStack(ModItems.INGOTCOPPER), ModFluids.MOLTEN_COPPER, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe ingot_tin = new KlinFluidRecipe(new ItemStack(ModItems.INGOTTIN), ModFluids.MOLTEN_TIN, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe ingot_bronze = new KlinFluidRecipe(new ItemStack(ModItems.INGOTBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_INGOT, 60);
    public static final KlinFluidRecipe stick_copper = new KlinFluidRecipe(new ItemStack(ModItems.STICKCOPPER), ModFluids.MOLTEN_COPPER, ModItems.CAST_STICK, 30);
    public static final KlinFluidRecipe stick_tin = new KlinFluidRecipe(new ItemStack(ModItems.STICKTIN), ModFluids.MOLTEN_TIN, ModItems.CAST_STICK, 20);
    public static final KlinFluidRecipe stick_bronze = new KlinFluidRecipe(new ItemStack(ModItems.STICKBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_STICK, 30);
    public static final KlinFluidRecipe gear_bronze = new KlinFluidRecipe(new ItemStack(ModItems.GEARBRONZE), ModFluids.MOLTEN_BRONZE, ModItems.CAST_GEAR, 160);
    public static final KlinFluidRecipe bronze_sword0 = new KlinFluidRecipe(new ItemStack(ModItems.BRONZE_SWORD0), ModFluids.MOLTEN_BRONZE, ModItems.CAST_SWORD, 360);

    //forge recipe
    public static final ForgingAnvilRecipe plate_copper = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.PLATECOPPER), new ItemStack(ModItems.INGOTCOPPER));
    public static final ForgingAnvilRecipe plate_tin = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.PLATETIN), new ItemStack(ModItems.INGOTTIN));
    public static final ForgingAnvilRecipe plate_bronze = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.PLATEBRONZE), new ItemStack(ModItems.INGOTBRONZE));
    public static final ForgingAnvilRecipe screw_bronze = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.SCREWBRONZE), new ItemStack(ModItems.STICKBRONZE));
    public static final ForgingAnvilRecipe string_copper = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.STRINGCOPPER), new ItemStack(ModItems.STICKCOPPER));
    public static final ForgingAnvilRecipe string_tin = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.STRINGTIN), new ItemStack(ModItems.STICKTIN));
    public static final ForgingAnvilRecipe plate_iron = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.PLATEIRON), new ItemStack(Items.IRON_INGOT));
    public static final ForgingAnvilRecipe plate_nickel = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.PLATENICKEL), new ItemStack(ModItems.INGOTNICKEL));
    public static final ForgingAnvilRecipe plate_gold = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.PLATEGOLD), new ItemStack(Items.GOLD_INGOT));
    public static final ForgingAnvilRecipe plate_invar = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.PLATEINVAR), new ItemStack(ModItems.INGOTINVAR));
    public static final ForgingAnvilRecipe plate_silver = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.PLATESILVER), new ItemStack(ModItems.INGOTSILVER));
    public static final ForgingAnvilRecipe string_gold = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.STRINGGOLD), new ItemStack(ModItems.STICKGOLD));
    public static final ForgingAnvilRecipe screw_iron = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.SCREWIRON), new ItemStack(ModItems.STICKIRON));
    public static final ForgingAnvilRecipe screw_invar = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.SCREWINVAR), new ItemStack(ModItems.STICKINVAR));
    public static final ForgingAnvilRecipe crushed_copper = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.CRUSHEDCOPPER, 2), new ItemStack(ModBlocks.ORECOPPER));
    public static final ForgingAnvilRecipe crushed_tin = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.CRUSHEDTIN, 2), new ItemStack(ModBlocks.ORETIN));
    public static final ForgingAnvilRecipe crushed_gold = new ForgingAnvilRecipe(2, 4, new ItemStack(ModItems.CRUSHEDGOLD, 2), new ItemStack(Blocks.GOLD_ORE));
    public static final ForgingAnvilRecipe plate_steel = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.PLATESTEEL), new ItemStack(ModItems.INGOTSTEEL));
    public static final ForgingAnvilRecipe plate_aluminum = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.PLATEALUMINUM), new ItemStack(ModItems.INGOTALUMINUM));
    public static final ForgingAnvilRecipe plate_Stainless_Steel = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.PLATESS), new ItemStack(ModItems.INGOTSS));
    public static final ForgingAnvilRecipe screw_Stainless_Steel = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.SCREWSS), new ItemStack(ModItems.STICKSS));
    public static final ForgingAnvilRecipe string_aluminum = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.STRINGALUMINUM), new ItemStack(ModItems.STICKALUMINUM));
    public static final ForgingAnvilRecipe crushed_iron = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDIRON, 2), new ItemStack(Blocks.IRON_ORE));
    public static final ForgingAnvilRecipe crushed_silver = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDSILVER, 2), new ItemStack(ModBlocks.ORESILVER));
    public static final ForgingAnvilRecipe crushed_pyrolusite1 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDPYROLUSITE, 2), new ItemStack(ModBlocks.OREPYROLUSITE));
    public static final ForgingAnvilRecipe crushed_pyrolusite2 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDPYROLUSITE, 2), new ItemStack(ModBlocks.OREPYROLUSITE_NETHER));
    public static final ForgingAnvilRecipe crushed_chromite = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDCHROMITE, 2), new ItemStack(ModBlocks.ORECHROMITE));
    public static final ForgingAnvilRecipe crushed_bauxite1 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDBAUXITE, 2), new ItemStack(ModBlocks.OREBAUXITE));
    public static final ForgingAnvilRecipe crushed_bauxite2 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDBAUXITE, 2), new ItemStack(ModBlocks.OREBAUXITE_NETHER));
    public static final ForgingAnvilRecipe crushed_ferromanganese1 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDFERROMANGANESE, 2), new ItemStack(ModBlocks.OREFERROMANGANESE));
    public static final ForgingAnvilRecipe crushed_ferromanganese2 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDFERROMANGANESE, 2), new ItemStack(ModBlocks.OREFERROMANGANESE_NETHER));
    public static final ForgingAnvilRecipe crushed_nickel = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDNICKEL, 2), new ItemStack(ModBlocks.ORENICKEL));
    public static final ForgingAnvilRecipe crushed_nolanite1 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDNOLANITE, 2), new ItemStack(ModBlocks.ORENOLANITE));
    public static final ForgingAnvilRecipe crushed_nolanite2 = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDNOLANITE, 2), new ItemStack(ModBlocks.ORENOLANITE_NETHER));
    public static final ForgingAnvilRecipe crushed_tunstite = new ForgingAnvilRecipe(3, 4, new ItemStack(ModItems.CRUSHEDTUNSTITE, 2), new ItemStack(ModBlocks.ORETUNSTITE));
    public static final ForgingAnvilRecipe dust_carbon = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.DUSTCARBON), new ItemStack(Items.COAL));
    public static final ForgingAnvilRecipe dust_carbon1 = new ForgingAnvilRecipe(1, 4, new ItemStack(ModItems.DUSTCARBON), new ItemStack(Items.COAL, 1, 1));

    //fire generator
    public static final FireGeneratorRecipe coal = new FireGeneratorRecipe(new ItemStack(Items.COAL), 128, 500, 1);
    public static final FireGeneratorRecipe coal1 = new FireGeneratorRecipe(new ItemStack(Items.COAL,1,1), 128, 500, 1);
    public static final FireGeneratorRecipe log = new FireGeneratorRecipe(new ItemStack(Blocks.LOG), 64, 50, 2);
    public static final FireGeneratorRecipe log1 = new FireGeneratorRecipe(new ItemStack(Blocks.LOG,1,1), 64, 50, 2);
    public static final FireGeneratorRecipe log2 = new FireGeneratorRecipe(new ItemStack(Blocks.LOG,1,2), 64, 50, 2);
    public static final FireGeneratorRecipe log3 = new FireGeneratorRecipe(new ItemStack(Blocks.LOG,1,3), 64, 50, 2);
    public static final FireGeneratorRecipe log4 = new FireGeneratorRecipe(new ItemStack(Blocks.LOG2), 64, 50, 2);

}
