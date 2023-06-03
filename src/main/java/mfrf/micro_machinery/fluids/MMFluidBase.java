package mfrf.micro_machinery.fluids;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.items.MMItemBase;
import mfrf.micro_machinery.registeried_lists.FluidBucketDispenserRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MMFluidBase {
    public static List<MMFluidBase> fluidBaseList = new ArrayList<>();
    private static final DeferredRegister<Fluid> FLUID_REGISTER = MicroMachinery.FLUID_REGISTER;
    private static final DeferredRegister<Block> BLOCK_REGISTER = MicroMachinery.BLOCK_REGISTER;
    private static final DeferredRegister<Item> ITEM_REGISTER = MicroMachinery.ITEM_REGISTER;
    public final ResourceLocation fluid_resource_location;
    public final ResourceLocation fluid_flow_resource_location;
    private final String name;
    public RegistryObject<FlowingFluid> fluid;
    public RegistryObject<FlowingFluid> fluid_flowing;
    public RegistryObject<LiquidBlock> fluid_block;
    public RegistryObject<Item> fluid_bucket;
    public ForgeFlowingFluid.Properties fluid_properties;

    public MMFluidBase(String name, Block.Properties fluid_block_properties, Function<FluidAttributes.Builder, FluidAttributes.Builder> factory, int tickRate) {
        this.name = name;
        this.fluid_resource_location = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_still");
        this.fluid_flow_resource_location = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_flow");
        this.fluid = FLUID_REGISTER.register("fluids/" + name, () -> new ForgeFlowingFluid.Source(this.fluid_properties));
        this.fluid_flowing = FLUID_REGISTER.register("fluids/" + name + "_flow", () -> new ForgeFlowingFluid.Flowing(this.fluid_properties));
        this.fluid_block = BLOCK_REGISTER.register("fluids/" + name, () -> new LiquidBlock(this.fluid, fluid_block_properties));
        this.fluid_bucket = ITEM_REGISTER.register(name + "_bucket", () -> new BucketItem(this.fluid, MMItemBase.DEFAULT_PROPERTIES.craftRemainder(Items.BUCKET).stacksTo(1).m_41491_(CreativeModeTab.f_40753_)));
        this.fluid_properties = new ForgeFlowingFluid.Properties(this.fluid, this.fluid_flowing, factory.apply(FluidAttributes.builder(fluid_resource_location, fluid_flow_resource_location).density(10).viscosity(1500))).bucket(this.fluid_bucket).block(this.fluid_block).slopeFindDistance(3).explosionResistance(100F).tickRate(tickRate);
        fluidBaseList.add(this);
        FluidBucketDispenserRegister.fluids.add(this);
    }

    public String getName() {
        return name;
    }
}
