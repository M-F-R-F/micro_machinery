package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.fluids.MMFluidBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.HashMap;
import java.util.Map;

public class RegisteryedFluids {

    public static final MMFluidBase MOLTEN_COPPER = new MMFluidBase("molten_copper", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1356).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_TIN = new MMFluidBase("molten_tin", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(505).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_BRONZE = new MMFluidBase("molten_bronze", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1170).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_STEEL = new MMFluidBase("molten_steel", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1356).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_SS = new MMFluidBase("molten_ss", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1570).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_TUNGSTEN = new MMFluidBase("molten_tungsten", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(3683).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_NICKEL = new MMFluidBase("molten_nickel", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1726).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_INVAR = new MMFluidBase("molten_invar", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1723).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_HSS = new MMFluidBase("molten_hss", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1793).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_SILVER = new MMFluidBase("molten_silver", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1235).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_GOLD = new MMFluidBase("molten_gold", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1337).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_MANGANESE = new MMFluidBase("molten_manganese", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1517).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_CHROMIUM = new MMFluidBase("molten_chromium", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(2130).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_VANADIUM = new MMFluidBase("molten_vanadium", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(2163).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_COBALT = new MMFluidBase("molten_cobalt", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1768).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_TITANIUM = new MMFluidBase("molten_titanium", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1941).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_ALUMINUM = new MMFluidBase("molten_aluminum", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(933).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_NCALLOY = new MMFluidBase("molten_ncalloy", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1625).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_FERROCHROME = new MMFluidBase("molten_ferrochrome", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(2130).viscosity(5).density(2));
    public static final MMFluidBase MOLTEN_IRON = new MMFluidBase("molten_iron", Block.Properties.create(Material.LAVA), (fluid) -> fluid.luminosity(9).temperature(1808).viscosity(5).density(2));
    public static final MMFluidBase GOLDEN_APPLE_JUICE = new MMFluidBase("golden_apple_juice", Block.Properties.create(Material.WATER), (fluid) -> fluid.luminosity(4).temperature(293).viscosity(2).density(2));
    public static final MMFluidBase APPLE_JUICE = new MMFluidBase("apple_juice", Block.Properties.create(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(2).density(1));
    public static final MMFluidBase ETHENE = new MMFluidBase("ethene", Block.Properties.create(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(1).density(1).gaseous());

    public static void Init() {
    }
}
