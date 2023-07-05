package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.fluids.MMFluidBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMFluids {
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, MicroMachinery.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.FLUID_TYPES.get(), MicroMachinery.MODID);
    public static final RegistryObject<Fluid>
            MOLTEN_COPPER = FLUID_REGISTER.register("molten_copper", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356), 20)),
            MOLTEN_TIN = FLUID_REGISTER.register("molten_tin", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(505), 20)),
            MOLTEN_BRONZE = FLUID_REGISTER.register("molten_bronze", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1170), 20)),
            MOLTEN_STEEL = FLUID_REGISTER.register("molten_steel", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1356), 20)),
            MOLTEN_SS = FLUID_REGISTER.register("molten_ss", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1570), 20)),
            MOLTEN_TUNGSTEN = FLUID_REGISTER.register("molten_tungsten", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(3683), 20)),
            MOLTEN_NICKEL = FLUID_REGISTER.register("molten_nickel", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1726), 20)),
            MOLTEN_INVAR = FLUID_REGISTER.register("molten_invar", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1723), 20)),
            MOLTEN_HSS = FLUID_REGISTER.register("molten_hss", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1893), 20)),
            MOLTEN_SILVER = FLUID_REGISTER.register("molten_silver", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1235), 20)),
            MOLTEN_GOLD = FLUID_REGISTER.register("molten_gold", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1337), 20)),
            MOLTEN_MANGANESE = FLUID_REGISTER.register("molten_manganese", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1517), 20)),
            MOLTEN_CHROMIUM = FLUID_REGISTER.register("molten_chromium", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130), 20)),
            MOLTEN_VANADIUM = FLUID_REGISTER.register("molten_vanadium", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(2163), 20)),
            MOLTEN_COBALT = FLUID_REGISTER.register("molten_cobalt", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1768), 20)),
            MOLTEN_TITANIUM = FLUID_REGISTER.register("molten_titanium", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1941), 20)),
            MOLTEN_ALUMINUM = FLUID_REGISTER.register("molten_aluminum", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(933), 20)),
            MOLTEN_NCALLOY = FLUID_REGISTER.register("molten_ncalloy", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1625), 20)),
            MOLTEN_FERROCHROME = FLUID_REGISTER.register("molten_ferrochrome", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(2130), 20)),
            MOLTEN_IRON = FLUID_REGISTER.register("molten_iron", () -> new MMFluidBase(Block.Properties.of().sound(SoundType.LAVA).lightValue(15), (fluid) -> fluid.temperature(1800), 20)),
            GOLDEN_APPLE_JUICE = FLUID_REGISTER.register("golden_apple_juice", () -> new MMFluidBase(Block.Properties.of(Material.WATER).lightValue(8), (fluid) -> fluid.temperature(293).viscosity(500), 5)),
            APPLE_JUICE = FLUID_REGISTER.register("apple_juice", () -> new MMFluidBase(Block.Properties.of(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(500), 3)),
            ETHENE = FLUID_REGISTER.register("ethene", () -> new MMFluidBase(Block.Properties.of(Material.WATER), (fluid) -> fluid.temperature(293).viscosity(1000), 10));
}
