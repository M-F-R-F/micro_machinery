package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.EnumType.EnumCastType;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.items.ItemBase;
import com.dbydd.micro_machinery.items.ItemCast;
import com.dbydd.micro_machinery.items.TestItem;
import com.dbydd.micro_machinery.items.tools.Laser_Drill;
import com.dbydd.micro_machinery.items.foods.FoodBase;
import com.dbydd.micro_machinery.items.foods.Golden_Apple_Food;
import com.dbydd.micro_machinery.items.materialbase.*;
import com.dbydd.micro_machinery.items.tools.MyMaterial;
import com.dbydd.micro_machinery.items.tools.ToolAxe;
import com.dbydd.micro_machinery.items.tools.ToolHammer;
import com.dbydd.micro_machinery.items.tools.ToolSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    private static String[] materiallist = {"Copper", "Tin", "Bronze", "Steel", "Invar", "SS", "Tungsten",
			"Tungsten_Steel", "HSS", "Gold", "Silver", "Manganese", "Chromium", "Nickel", "Vanadium", "Cobalt",
			"Titanium", "Aluminum", "NCAlloy", "Silicon", "Graphite", "Iron", "Carbon", "Ferrochrome"};

	public static final List<Item> ITEMS = new ArrayList<Item>();

	//ingot
	public static final Item INGOTCOPPER = new MaterialIngot(materiallist[0]);
	public static final Item INGOTTIN = new MaterialIngot(materiallist[1]);
	public static final Item INGOTBRONZE = new MaterialIngot(materiallist[2]);
	public static final Item INGOTSTEEL = new MaterialIngot(materiallist[3]);
	public static final Item INGOTINVAR = new MaterialIngot(materiallist[4]);
	public static final Item INGOTSS = new MaterialIngot(materiallist[5]);
	public static final Item INGOTTUNGSTEN = new MaterialIngot(materiallist[6]);
	public static final Item INGOTTUNGSTEM_STEEL = new MaterialIngot(materiallist[7]);
	public static final Item INGOTHSS = new MaterialIngot(materiallist[8]);
	public static final Item INGOTSILVER = new MaterialIngot(materiallist[10]);
	public static final Item INGOTMANGANESE = new MaterialIngot(materiallist[11]);
	public static final Item INGOTCHROMIUM = new MaterialIngot(materiallist[12]);
	public static final Item INGOTNICKEL = new MaterialIngot(materiallist[13]);
	public static final Item INGOTVANADIUM = new MaterialIngot(materiallist[14]);
	public static final Item INGOTCOBALT = new MaterialIngot(materiallist[15]);
	public static final Item INGOTTITANIUM = new MaterialIngot(materiallist[16]);
	public static final Item INGOTALUMINUM = new MaterialIngot(materiallist[17]);
	public static final Item INGOTNCALLOY = new MaterialIngot(materiallist[18]);
	public static final Item INGOTSILICON = new MaterialIngot(materiallist[19]);
	public static final Item INGOTGRAPHITE = new MaterialIngot(materiallist[20]);

	//powder
	public static final Item DUSTCOPPER = new MaterialDust(materiallist[0]);
	public static final Item DUSTTIN = new MaterialDust(materiallist[1]);
	public static final Item DUSTBRONZE = new MaterialDust(materiallist[2]);
	public static final Item DUSTSTEEL = new MaterialDust(materiallist[3]);
	public static final Item DUSTINVAR = new MaterialDust(materiallist[4]);
	public static final Item DUSTSS = new MaterialDust(materiallist[5]);
	public static final Item DUSTTUNGSTEN = new MaterialDust(materiallist[6]);
	public static final Item DUSTTUNGSTEM_STEEL = new MaterialDust(materiallist[7]);
	public static final Item DUSTHSS = new MaterialDust(materiallist[8]);
	public static final Item DUSTGOLD = new MaterialDust(materiallist[9]);
	public static final Item DUSTSILVER = new MaterialDust(materiallist[10]);
	public static final Item DUSTMANGANESE = new MaterialDust(materiallist[11]);
	public static final Item DUSTCHROMIUM = new MaterialDust(materiallist[12]);
	public static final Item DUSTNICKEL = new MaterialDust(materiallist[13]);
	public static final Item DUSTVANADIUM = new MaterialDust(materiallist[14]);
	public static final Item DUSTCOBALT = new MaterialDust(materiallist[15]);
	public static final Item DUSTTITANIUM = new MaterialDust(materiallist[16]);
	public static final Item DUSTALUMINUM = new MaterialDust(materiallist[17]);
	public static final Item DUSTNCALLOY = new MaterialDust(materiallist[18]);
	public static final Item DUSTSILICON = new MaterialDust(materiallist[19]);
	public static final Item DUSTIRON = new MaterialDust(materiallist[21]);
	public static final Item DUSTCARBON = new MaterialDust(materiallist[22]);
	public static final Item DUSTFERRCHROME = new MaterialDust(materiallist[23]);

	//stick
	public static final Item STICKCOPPER = new MaterialStick(materiallist[0]);
	public static final Item STICKTIN = new MaterialStick(materiallist[1]);
	public static final Item STICKBRONZE = new MaterialStick(materiallist[2]);
	public static final Item STICKSTEEL = new MaterialStick(materiallist[3]);
	public static final Item STICKINVAR = new MaterialStick(materiallist[4]);
	public static final Item STICKSS = new MaterialStick(materiallist[5]);
	public static final Item STICKTUNGSTEM_STEEL = new MaterialStick(materiallist[7]);
	public static final Item STICKHSS = new MaterialStick(materiallist[8]);
	public static final Item STICKGOLD = new MaterialStick(materiallist[9]);
	public static final Item STICKTITANIUM = new MaterialStick(materiallist[16]);
	public static final Item STICKALUMINUM = new MaterialStick(materiallist[17]);
	public static final Item STICKIRON = new MaterialStick(materiallist[21]);

	//circlecasting
	public static final Item CIRCLECASTINGBRONZE = new MaterialCircleCasting(materiallist[2]);
	public static final Item CIRCLECASTINGSTEEL = new MaterialCircleCasting(materiallist[3]);
	public static final Item CIRCLECASTINGIRON = new MaterialCircleCasting(materiallist[21]);
	public static final Item CIRCLECASTINGSILVER = new MaterialCircleCasting(materiallist[10]);
	public static final Item CIRCLECASTINGINVAR = new MaterialCircleCasting(materiallist[4]);
	public static final Item CIRCLECASTINGGOLD = new MaterialCircleCasting(materiallist[9]);
	public static final Item CIRCLECASTINGALUMINUM = new MaterialCircleCasting(materiallist[17]);
	public static final Item CIRCLECASTINGNICKEL = new MaterialCircleCasting(materiallist[13]);
	public static final Item CIRCLECASTINGSS = new MaterialCircleCasting(materiallist[5]);
	public static final Item CIRCLECASTINGTUNGSTEM_STEEL = new MaterialCircleCasting(materiallist[7]);
	public static final Item CIRCLECASTINGTITANIUM = new MaterialCircleCasting(materiallist[16]);


	//plate
	public static final Item PLATECOPPER = new MaterialPlate(materiallist[0]);
	public static final Item PLATETIN = new MaterialPlate(materiallist[1]);
	public static final Item PLATEBRONZE = new MaterialPlate(materiallist[2]);
	public static final Item PLATESTEEL = new MaterialPlate(materiallist[3]);
	public static final Item PLATEINVAR = new MaterialPlate(materiallist[4]);
	public static final Item PLATESS = new MaterialPlate(materiallist[5]);
	public static final Item PLATETUNGSTEM_STEEL = new MaterialPlate(materiallist[7]);
	public static final Item PLATEHSS = new MaterialPlate(materiallist[8]);
	public static final Item PLATEGOLD = new MaterialPlate(materiallist[9]);
	public static final Item PLATESILVER = new MaterialPlate(materiallist[10]);
	public static final Item PLATECHROMIUM = new MaterialPlate(materiallist[12]);
	public static final Item PLATENICKEL = new MaterialPlate(materiallist[13]);
	public static final Item PLATETITANIUM = new MaterialPlate(materiallist[16]);
	public static final Item PLATEALUMINUM = new MaterialPlate(materiallist[17]);
	public static final Item PLATENCALLOY = new MaterialPlate(materiallist[18]);
	public static final Item PLATESILICON = new MaterialPlate(materiallist[19]);
	public static final Item PLATEIRON = new MaterialPlate(materiallist[21]);

	//screw
	public static final Item SCREWBRONZE = new MaterialScrew(materiallist[2]);
	public static final Item SCREWINVAR = new MaterialScrew(materiallist[4]);
	public static final Item SCREWSS = new MaterialScrew(materiallist[5]);
	public static final Item SCREWTITANIUM = new MaterialScrew(materiallist[16]);
	public static final Item SCREWIRON = new MaterialScrew(materiallist[21]);

	//String
	public static final Item STRINGCOPPER = new MaterialString(materiallist[0]);
	public static final Item STRINGTIN = new MaterialString(materiallist[1]);
	public static final Item STRINGTUNGSTEN = new MaterialString(materiallist[6]);
	public static final Item STRINGSILVER = new MaterialString(materiallist[10]);
	public static final Item STRINGNICKEL = new MaterialString(materiallist[13]);
	public static final Item STRINGCOBALT = new MaterialString(materiallist[15]);
	public static final Item STRINGALUMINUM = new MaterialString(materiallist[17]);
	public static final Item STRINGNCALLOY = new MaterialString(materiallist[18]);
	public static final Item STRINGGOLD = new MaterialString(materiallist[9]);
	public static final Item STRINGIRON = new MaterialString(materiallist[21]);

	//roll
	public static final Item ROLLINVAR = new MaterialRoll(materiallist[4]);
	public static final Item ROLLSS = new MaterialRoll(materiallist[5]);
	public static final Item ROLLTUNGSTEM_STEEL = new MaterialRoll(materiallist[7]);
	public static final Item ROLLHSS = new MaterialRoll(materiallist[8]);
	public static final Item ROLLIRON = new MaterialRoll(materiallist[21]);

	//slag
	public static final Item SLAG = new MaterialSlag("Mixture");
	public static final Item SLAGMANGANESE = new MaterialSlag(materiallist[11]);
	public static final Item SLAGVANADIUM = new MaterialSlag(materiallist[14]);
	public static final Item SLAGCOBALT = new MaterialSlag(materiallist[15]);
	public static final Item SLAGTITANIUM = new MaterialSlag(materiallist[16]);
	public static final Item REFINEDSLAGMANGANESE = new MaterialSlag(materiallist[11] + "_Refined");
	public static final Item REFINEDSLAGVANADIUM = new MaterialSlag(materiallist[14] + "_Refined");
	public static final Item REFINEDSLAGCOBALT = new MaterialSlag(materiallist[15] + "_Refined");
	public static final Item REFINEDSLAGTITANIUM = new MaterialSlag(materiallist[16] + "_Refined");

	//axis
	public static final Item AXISBRONZE = new MaterialAxis(materiallist[2]);
	public static final Item AXISINVAR = new MaterialAxis(materiallist[4]);
	public static final Item AXISSS = new MaterialAxis(materiallist[5]);
	public static final Item AXISTUNGSTEM_STEEL = new MaterialAxis(materiallist[7]);
	public static final Item AXISHSS = new MaterialAxis(materiallist[8]);
	public static final Item AXISSTEEL = new MaterialAxis(materiallist[3]);
	public static final Item AXISTITANIUM = new MaterialAxis(materiallist[16]);
	public static final Item AXISIRON = new MaterialAxis(materiallist[21]);

	//gearblank
	public static final Item GEARBLANKBRONZE = new GearBlank(materiallist[2]);
	public static final Item GEARBLANKINVAR = new GearBlank(materiallist[4]);
	public static final Item GEARBLANKSS = new GearBlank(materiallist[5]);
	public static final Item GEARBLANKTUNGSTEM_STEEL = new GearBlank(materiallist[7]);
	public static final Item GEARBLANKSILVER = new GearBlank(materiallist[10]);
	public static final Item GEARBLANKNICKEL = new GearBlank(materiallist[13]);
	public static final Item GEARBLANKTITANIUM = new GearBlank(materiallist[16]);
	public static final Item GEARBLANKALUMINUM = new GearBlank(materiallist[17]);
	public static final Item GEARBLANKIRON = new GearBlank(materiallist[21]);

	//gear
	public static final Item GEARBRONZE = new MaterialGear(materiallist[2]);
	public static final Item GEARINVAR = new MaterialGear(materiallist[4]);
	public static final Item GEARSS = new MaterialGear(materiallist[5]);
	public static final Item GEARTUNGSTEM_STEEL = new MaterialGear(materiallist[7]);
	public static final Item GEARSILVER = new MaterialGear(materiallist[10]);
	public static final Item GEARNICKEL = new MaterialGear(materiallist[13]);
	public static final Item GEARTITANIUM = new MaterialGear(materiallist[16]);
	public static final Item GEARALUMINUM = new MaterialGear(materiallist[17]);
	public static final Item GEARIRON = new MaterialGear(materiallist[21]);

	//items
	public static final Item HSSADDITIVE = new ItemBase("HSSAdditive");
	public static final Item SSADDITIVE = new ItemBase("SSAdditive");

	//crushed ore
	public static final Item CRUSHEDCOPPER = new CrushedOre("Copper");
	public static final Item CRUSHEDTIN = new CrushedOre("Tin");
	public static final Item CRUSHEDILMENITE = new CrushedOre("Ilmenite");
	public static final Item CRUSHEDSILVER = new CrushedOre("Silver");
	public static final Item CRUSHEDPYROLUSITE = new CrushedOre("Pyrolusite");
	public static final Item CRUSHEDGOLD = new CrushedOre("Gold");
	public static final Item CRUSHEDCHROMITE = new CrushedOre("Chromite");
	public static final Item CRUSHEDBAUXITE = new CrushedOre("Bauxite");
	public static final Item CRUSHEDFERROMANGANESE = new CrushedOre("FerroManganese");
	public static final Item CRUSHEDNICKEL = new CrushedOre("Nickel");
	public static final Item CRUSHEDNOLANITE = new CrushedOre("Nolanite");
	public static final Item CRUSHEDTUNSTITE = new CrushedOre("Tunstite");
	public static final Item CRUSHEDIRON = new CrushedOre("Iron");

	//motor
	public static final Item MOTORLV1 = new MotorBase("lv1");
	public static final Item MOTORLV2 = new MotorBase("lv2");
	public static final Item MOTORLV3 = new MotorBase("lv3");
	public static final Item MOTORLV4 = new MotorBase("lv4");
	public static final Item MOTORLV5 = new MotorBase("lv5");

	//oreconcentrate
	public static final Item ORECOPPERCONCENTRATE = new OreConcentrate("Copper");
	public static final Item ORETINCONCENTRATE = new OreConcentrate("Tin");
	public static final Item OREILMENITECONCENTRATE = new OreConcentrate("Ilmenite");
	public static final Item ORESILVERCONCENTRATE = new OreConcentrate("Silver");
	public static final Item OREPYROLUSITECONCENTRATE = new OreConcentrate("Pyrolusite");
	public static final Item OREIRONCONCENTRATE = new OreConcentrate("Iron");
	public static final Item OREGOLDCONCENTRATE = new OreConcentrate("Gold");
	public static final Item ORECHROMITECONCENTRATE = new OreConcentrate("Chromite");
	public static final Item OREBAUXITECONCENTRATE = new OreConcentrate("Bauxite");
	public static final Item OREFERROMANGANESECONCENTRATE = new OreConcentrate("FerroManganese");
	public static final Item ORENICKELCONCENTRATE = new OreConcentrate("Nickel");
	public static final Item ORENOLANITECONCENTRATE = new OreConcentrate("Nolanite");
	public static final Item ORETUNSTITECONCENTRATE = new OreConcentrate("Tunstite");
	//foods
	//efficfoods
	public static final Item GOLDEN_APPLE_DROPS = new Golden_Apple_Food(5, 5.0f, "golden_apple_drops", true);
	public static final Item GOLDEN_APPLE_JAM_BUN = new Golden_Apple_Food(10, 10.0f, "golden_apple_jam_bun", false);
	//nomalfood
	public static final Item APPLE_DROPS = new FoodBase(5, 5.0f, "apple_drops");
	public static final Item APPLE_JAM_BUN = new FoodBase(10, 10.0f, "apple_jam_bun");

	//tools
    //axe
	public static final Item AXEBRONZE = new ToolAxe(ToolMaterial.IRON, 300, "bronze_axe", 6.0f, 7.0f);
	public static final Item AXETUNGSTEN_STEEL = new ToolAxe(ToolMaterial.DIAMOND, 1200, "tungsten_steel_axe", 7.0f, 12.0f);
	public static final Item AXEHSS = new ToolAxe(ToolMaterial.DIAMOND, 800, "hss_axe", 12.0f, 15.0f);
    //sword
	public static final Item BRONZE_SWORD = new ToolSword(MyMaterial.BRONZE, 250, "bronze_sword");
	public static final Item TUNGSTEN_STEEL_SWORD = new ToolSword(MyMaterial.TUNGSTEN_STEEL, 1000, "tungsten_steel_sword");
	public static final Item HSS_SWORD = new ToolSword(MyMaterial.HSS, 750, "hss_sword");
    //hammer
	public static final Item STONE_HAMMER = new ToolHammer(ToolMaterial.STONE, 100, "stone_hammer");
	public static final Item IRON_HAMMER = new ToolHammer(ToolMaterial.IRON, 700, "iron_hammer");
	public static final Item BRONZE_HAMMER = new ToolHammer(MyMaterial.BRONZE, 400, "bronze_hammer");

	//others
	public static final Item MONOCRYSTALLINE_SILICON = new ItemBase("monocrystalline_silicon");
	public static final Item WAFER = new ItemBase("wafer");
	public static final Item ETCHED_WAFER = new ItemBase("etched_wafer");
	public static final Item I7_8700K = new ItemBase("i7_8700k");
	public static final Item PLATE_PE = new ItemBase("plate_pe");
	public static final Item PCB = new ItemBase("pcb");
	public static final Item CAPACITOR = new ItemBase("capacitor");
	public static final Item RESISTOR = new ItemBase("resistor");
	public static final Item LASER = new ItemBase("laser");
	public static final Item CLAY_BRICK = new ItemBase("clay_brick");
	public static final Item FIRE_BRICK = new ItemBase("fire_brick");
	public static final Item PLASMA_GENERATOR = new ItemBase("plasma_generator");
	public static final Item DSB = new ItemBase("dsb");
	public static final Item HSB = new ItemBase("hsb");
	public static final Item BRICK_POWDER = new ItemBase("brick_powder");
	public static final Item CLAY_MIXED_WITH_CLINKER = new ItemBase("clay_mixed_with_clinker");
	public static final Item BRONZE_SWORD0 = new ItemBase("bronze_sword0");

	//casts
	public static final Item CAST_INGOT = new ItemCast("cast_ingot", 160, EnumCastType.INGOT);
	public static final Item CAST_STICK = new ItemCast("cast_stick", 72, EnumCastType.STICK);
	public static final Item CAST_GEAR = new ItemCast("cast_gear", 584, EnumCastType.GEAR);
	public static final Item CAST_SWORD = new ItemCast("cast_sword", 288, EnumCastType.SWORD);

	//testtool
	public static final Item LASER_DRILL = new Laser_Drill();
	public static final Item TESTStick = new TestItem("testItem");

	/*==========================================================================================================================================================================*/
	public static void registerRenderItem(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
}
