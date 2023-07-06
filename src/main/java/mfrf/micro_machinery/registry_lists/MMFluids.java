package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.fluids.MMFluidBase;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MMFluids {
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, MicroMachinery.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.FLUID_TYPES.get(), MicroMachinery.MODID);
    public static final MMFluidBase
            MOLTEN_COPPER = new MMFluidBase(
            "molten_copper",
            Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
            FluidType.Properties.create()
                    .lightLevel(15)
                    .adjacentPathType(null)
                    .pathType(BlockPathTypes.LAVA)
                    .canConvertToSource(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .density(8960)
                    .temperature(1356)
                    .canSwim(true)
                    .canPushEntity(true)
                    .fallDistanceModifier(0.5f)
                    .supportsBoating(true)
                    .viscosity(8960)
                    .canDrown(true)
                    .rarity(Rarity.EPIC)
    ),
            MOLTEN_TIN = new MMFluidBase("molten_tin", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(505), 20),
            MOLTEN_BRONZE = new MMFluidBase("molten_bronze", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1170), 20),
            MOLTEN_STEEL = new MMFluidBase("molten_steel", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1356), 20),
            MOLTEN_SS = new MMFluidBase("molten_ss", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1570), 20),
            MOLTEN_TUNGSTEN = new MMFluidBase("molten_tungsten", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(3683), 20),
            MOLTEN_NICKEL = new MMFluidBase("molten_nickel", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1726), 20),
            MOLTEN_INVAR = new MMFluidBase("molten_invar", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1723), 20),
            MOLTEN_HSS = new MMFluidBase("molten_hss", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1893), 20),
            MOLTEN_SILVER = new MMFluidBase("molten_silver", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1235), 20),
            MOLTEN_GOLD = new MMFluidBase("molten_gold", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1337), 20),
            MOLTEN_MANGANESE = new MMFluidBase("molten_manganese", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1517), 20),
            MOLTEN_CHROMIUM = new MMFluidBase("molten_chromium", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(2130), 20),
            MOLTEN_VANADIUM = new MMFluidBase("molten_vanadium", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(2163), 20),
            MOLTEN_COBALT = new MMFluidBase("molten_cobalt", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1768), 20),
            MOLTEN_TITANIUM = new MMFluidBase("molten_titanium", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1941), 20),
            MOLTEN_ALUMINUM = new MMFluidBase("molten_aluminum", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(933), 20),
            MOLTEN_NCALLOY = new MMFluidBase("molten_ncalloy", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1625), 20),
            MOLTEN_FERROCHROME = new MMFluidBase("molten_ferrochrome", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(2130), 20),
            MOLTEN_IRON = new MMFluidBase("molten_iron", Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15), (fluid) -> fluid.temperature(1800), 20),
            GOLDEN_APPLE_JUICE = new MMFluidBase("golden_apple_juice", Block.Properties.of().noCollission().replaceable().lightLevel(s -> 8), (fluid) -> fluid.temperature(293).viscosity(500), 5),
            APPLE_JUICE = new MMFluidBase("apple_juice", Block.Properties.of().noCollission().replaceable(), (fluid) -> fluid.temperature(293).viscosity(500), 3),
            ETHENE = new MMFluidBase("ethene", Block.Properties.of().noCollission().replaceable(), (fluid) -> fluid.temperature(293).viscosity(1000), 10);


}
