package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.fluids.MMFluidBase;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class MMFluids {
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, MicroMachinery.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MicroMachinery.MODID);
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
                    .rarity(Rarity.EPIC),
            true
    ),
            MOLTEN_TIN = new MMFluidBase(
                    "molten_tin",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(505)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_BRONZE = new MMFluidBase(
                    "molten_bronze",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1170)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_STEEL = new MMFluidBase(
                    "molten_steel",
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
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_SS = new MMFluidBase(
                    "molten_ss",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1570)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_TUNGSTEN = new MMFluidBase(
                    "molten_tungsten",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(3683)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_NICKEL = new MMFluidBase(
                    "molten_nickel",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1726)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_INVAR = new MMFluidBase(
                    "molten_invar",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1723)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_HSS = new MMFluidBase(
                    "molten_hss",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1893)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_SILVER = new MMFluidBase(
                    "molten_silver",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1235)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_GOLD = new MMFluidBase(
                    "molten_gold",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1337)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_MANGANESE = new MMFluidBase(
                    "molten_manganese",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1517)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_CHROMIUM = new MMFluidBase(
                    "molten_chromium",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(2130)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_VANADIUM = new MMFluidBase(
                    "molten_vanadium",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(2163)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_COBALT = new MMFluidBase(
                    "molten_cobalt",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1768)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_TITANIUM = new MMFluidBase(
                    "molten_titanium",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1941)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_ALUMINUM = new MMFluidBase(
                    "molten_aluminum",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(933)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_SMA = new MMFluidBase(
                    "molten_sma",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1941)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),
            MOLTEN_IRON = new MMFluidBase(
                    "molten_iron",
                    Block.Properties.of().noCollission().replaceable().randomTicks().lightLevel((state) -> 15),
                    FluidType.Properties.create()
                            .lightLevel(15)
                            .adjacentPathType(null)
                            .pathType(BlockPathTypes.LAVA)
                            .canConvertToSource(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                            .density(8960)
                            .temperature(1800)
                            .canSwim(true)
                            .canPushEntity(true)
                            .fallDistanceModifier(0.5f)
                            .supportsBoating(true)
                            .viscosity(8960)
                            .canDrown(true)
                            .rarity(Rarity.EPIC),
                    true
            ),

    GOLDEN_APPLE_JUICE = new MMFluidBase(
            "golden_apple_juice",
            Block.Properties.of().noCollission().replaceable().lightLevel(s -> 8),
            FluidType.Properties.create()
                    .temperature(293).
                    viscosity(500),
            false, 5
    ),

    APPLE_JUICE = new MMFluidBase(
            "apple_juice",
            Block.Properties.of().noCollission().replaceable(),
            FluidType.Properties.create()
                    .temperature(293)
                    .viscosity(500),
            false, 3
    ),
            ETHENE = new MMFluidBase(
                    "ethene", Block.Properties.of().noCollission().replaceable(),
                    FluidType.Properties.create()
                            .temperature(293).viscosity(1000), false, 10
            ),
            STEAM = new MMFluidBase(
                    "steam", Block.Properties.of().noCollission().replaceable(),
                    FluidType.Properties.create()
                            .temperature(600).viscosity(1000), false, 10
            ),
            STEAM_H = new MMFluidBase(
                    "steam_h", Block.Properties.of().noCollission().replaceable(),
                    FluidType.Properties.create()
                            .temperature(700).viscosity(1000), false, 10
            ),
            STEAM_S = new MMFluidBase(
                    "steam_s", Block.Properties.of().noCollission().replaceable(),
                    FluidType.Properties.create()
                            .temperature(800).viscosity(1000), false, 10
            ),
            STEAM_US = new MMFluidBase(
                    "steam_us", Block.Properties.of().noCollission().replaceable(),
                    FluidType.Properties.create()
                            .temperature(900).viscosity(1000), false, 10
            );

        public static void init(){
            MMFluidBase.init();
        }
}
