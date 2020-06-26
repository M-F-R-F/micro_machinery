package com.dbydd.micro_machinery.init;

import com.dbydd.micro_machinery.fluid.MoltenMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModFluids {

	public static final List<Fluid> FLUIDS = new ArrayList<Fluid>();

	public static final Fluid MOLTEN_COPPER = new MoltenMaterial("molten_copper", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_copper" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_copper" + "_flow"), 1356);
	public static final Fluid MOLTEN_TIN = new MoltenMaterial("molten_tin", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_tin" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_tin" + "_flow"), 504);
	public static final Fluid MOLTEN_BRONZE = new MoltenMaterial("molten_bronze", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_bronze" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_bronze" + "_flow"), 1073);
	public static final Fluid MOLTEN_STEEL = new MoltenMaterial("molten_steel", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_steel" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_steel" + "_flow"), 1773);
	public static final Fluid MOLTEN_SS = new MoltenMaterial("molten_ss", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ss" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ss" + "_flow"), 1802);
	public static final Fluid MOLTEN_TUNGSTEN = new MoltenMaterial("molten_tungsten", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_tungsten" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_tungsten" + "_flow"), 3683);
	public static final Fluid MOLTEN_NICKEL = new MoltenMaterial("molten_nickel", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_nickel" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_nickel" + "_flow"), 1726);
	public static final Fluid MOLTEN_INVAR = new MoltenMaterial("molten_invar", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_invar" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_invar" + "_flow"), 1703);
	public static final Fluid MOLTEN_HSS = new MoltenMaterial("molten_hss", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_hss" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_hss" + "_flow"), 1573);
	public static final Fluid MOLTEN_SILVER = new MoltenMaterial("molten_silver", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_silver" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_silver" + "_flow"), 2234);
	public static final Fluid MOLTEN_GOLD = new MoltenMaterial("molten_gold", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_gold" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_gold" + "_flow"), 1337);
	public static final Fluid MOLTEN_MANGANESE = new MoltenMaterial("molten_manganese", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_manganese" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_manganese" + "_flow"), 1517);
	public static final Fluid MOLTEN_CHROMIUM = new MoltenMaterial("molten_chromium", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_chromium" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_chromium" + "_flow"), 2130);
	public static final Fluid MOLTEN_VANADIUM = new MoltenMaterial("molten_vanadium", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_vanadium" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_vanadium" + "_flow"), 2173);
	public static final Fluid MOLTEN_COBALT = new MoltenMaterial("molten_cobalt", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_cobalt" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_cobalt" + "_flow"), 1768);
	public static final Fluid MOLTEN_TITANIUM = new MoltenMaterial("molten_titanium", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_titanium" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_titanium" + "_flow"), 1933);
	public static final Fluid MOLTEN_ALUMINUM = new MoltenMaterial("molten_aluminum", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_aluminum" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_aluminum" + "_flow"), 933);
	public static final Fluid MOLTEN_NCALLOY = new MoltenMaterial("molten_ncalloy", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ncalloy" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ncalloy" + "_flow"), 1773);
	public static final Fluid MOLTEN_FERROCHROME = new MoltenMaterial("molten_ferrochrome", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ferrochrome" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_ferrochrome" + "_flow"), 2130);
	public static final Fluid MOLTEN_IRON = new MoltenMaterial("molten_iron", new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_iron" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "molten_iron" + "_flow"), 1517);
	public static final Fluid GOLDEN_APPLE_JUICE = new MoltenMaterial("golden_apple_juice", new ResourceLocation("micro_machinery:blocks/fluids/" + "golden_apple_juice" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "golden_apple_juice" + "_flow"), 300);
	public static final Fluid APPLE_JUICE = new MoltenMaterial("apple_juice", new ResourceLocation("micro_machinery:blocks/fluids/" + "apple_juice" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "apple_juice" + "_flow"), 300);
	public static final Fluid ETHENE = new MoltenMaterial("ethene", new ResourceLocation("micro_machinery:blocks/fluids/" + "ethene" + "_still"), new ResourceLocation("micro_machinery:blocks/fluids/" + "ethene" + "_flow"), 300);

	public static void registerFluids(Fluid fluid) {
		registerFluid(fluid);
	}

	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

	}

}
