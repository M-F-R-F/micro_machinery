package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.fluids.MMFluidBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RegisteryedFluids {

    public static final MMFluidBase MOLTEN_COPPER = new MMFluidBase("molten_copper", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356));
    public static final MMFluidBase MOLTEN_TIN = new MMFluidBase("molten_tin", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(505));
    public static final MMFluidBase MOLTEN_BRONZE = new MMFluidBase("molten_bronze", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1170));
    public static final MMFluidBase MOLTEN_STEEL = new MMFluidBase("molten_steel", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356));
    public static final MMFluidBase MOLTEN_SS = new MMFluidBase("molten_ss", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1570));
    public static final MMFluidBase MOLTEN_TUNGSTEN = new MMFluidBase("molten_tungsten", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(3683));
    public static final MMFluidBase MOLTEN_NICKEL = new MMFluidBase("molten_nickel", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1726));
    public static final MMFluidBase MOLTEN_INVAR = new MMFluidBase("molten_invar", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1723));
    public static final MMFluidBase MOLTEN_HSS = new MMFluidBase("molten_hss", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1793));
    public static final MMFluidBase MOLTEN_SILVER = new MMFluidBase("molten_silver", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1235));
    public static final MMFluidBase MOLTEN_GOLD = new MMFluidBase("molten_gold", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1337));
    public static final MMFluidBase MOLTEN_MANGANESE = new MMFluidBase("molten_manganese", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1517));
    public static final MMFluidBase MOLTEN_CHROMIUM = new MMFluidBase("molten_chromium", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130));
    public static final MMFluidBase MOLTEN_VANADIUM = new MMFluidBase("molten_vanadium", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2163));
    public static final MMFluidBase MOLTEN_COBALT = new MMFluidBase("molten_cobalt", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1768));
    public static final MMFluidBase MOLTEN_TITANIUM = new MMFluidBase("molten_titanium", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1941));
    public static final MMFluidBase MOLTEN_ALUMINUM = new MMFluidBase("molten_aluminum", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(933));
    public static final MMFluidBase MOLTEN_NCALLOY = new MMFluidBase("molten_ncalloy", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1625));
    public static final MMFluidBase MOLTEN_FERROCHROME = new MMFluidBase("molten_ferrochrome", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130));
    public static final MMFluidBase MOLTEN_IRON = new MMFluidBase("molten_iron", Block.Properties.create(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1808));
    public static final MMFluidBase GOLDEN_APPLE_JUICE = new MMFluidBase("golden_apple_juice", Block.Properties.create(Material.WATER).lightValue(8), (fluid) -> fluid.temperature(293).viscosity(500));
    public static final MMFluidBase APPLE_JUICE = new MMFluidBase("apple_juice", Block.Properties.create(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(500));
    public static final MMFluidBase ETHENE = new MMFluidBase("ethene", Block.Properties.create(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(500));

    public static void Init() {
    }
}
