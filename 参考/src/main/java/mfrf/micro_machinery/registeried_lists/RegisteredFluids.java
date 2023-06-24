package mfrf.micro_machinery.registeried_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.fluids.MMFluidBase;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredFluids {
    public static final MMFluidBase MOLTEN_COPPER = new MMFluidBase("molten_copper", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356), 20);
    public static final MMFluidBase MOLTEN_TIN = new MMFluidBase("molten_tin", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(505), 20);
    public static final MMFluidBase MOLTEN_BRONZE = new MMFluidBase("molten_bronze", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1170), 20);
    public static final MMFluidBase MOLTEN_STEEL = new MMFluidBase("molten_steel", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356), 20);
    public static final MMFluidBase MOLTEN_SS = new MMFluidBase("molten_ss", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1570), 20);
    public static final MMFluidBase MOLTEN_TUNGSTEN = new MMFluidBase("molten_tungsten", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(3683), 20);
    public static final MMFluidBase MOLTEN_NICKEL = new MMFluidBase("molten_nickel", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1726), 20);
    public static final MMFluidBase MOLTEN_INVAR = new MMFluidBase("molten_invar", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1723), 20);
    public static final MMFluidBase MOLTEN_HSS = new MMFluidBase("molten_hss", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1893), 20);
    public static final MMFluidBase MOLTEN_SILVER = new MMFluidBase("molten_silver", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1235), 20);
    public static final MMFluidBase MOLTEN_GOLD = new MMFluidBase("molten_gold", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1337), 20);
    public static final MMFluidBase MOLTEN_MANGANESE = new MMFluidBase("molten_manganese", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1517), 20);
    public static final MMFluidBase MOLTEN_CHROMIUM = new MMFluidBase("molten_chromium", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130), 20);
    public static final MMFluidBase MOLTEN_VANADIUM = new MMFluidBase("molten_vanadium", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2163), 20);
    public static final MMFluidBase MOLTEN_COBALT = new MMFluidBase("molten_cobalt", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1768), 20);
    public static final MMFluidBase MOLTEN_TITANIUM = new MMFluidBase("molten_titanium", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1941), 20);
    public static final MMFluidBase MOLTEN_ALUMINUM = new MMFluidBase("molten_aluminum", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(933), 20);
    public static final MMFluidBase MOLTEN_NCALLOY = new MMFluidBase("molten_ncalloy", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1625), 20);
    public static final MMFluidBase MOLTEN_FERROCHROME = new MMFluidBase("molten_ferrochrome", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130), 20);
    public static final MMFluidBase MOLTEN_IRON = new MMFluidBase("molten_iron", Block.Properties.of(Material.LAVA).lightValue(15), (fluid) -> fluid.temperature(1800), 20);
    public static final MMFluidBase GOLDEN_APPLE_JUICE = new MMFluidBase("golden_apple_juice", Block.Properties.of(Material.WATER).lightValue(8), (fluid) -> fluid.temperature(293).viscosity(500), 5);
    public static final MMFluidBase APPLE_JUICE = new MMFluidBase("apple_juice", Block.Properties.of(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(500), 3);
    public static final MMFluidBase ETHENE = new MMFluidBase("ethene", Block.Properties.of(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(1000), 10);

    public static void Init() {
    }
}
