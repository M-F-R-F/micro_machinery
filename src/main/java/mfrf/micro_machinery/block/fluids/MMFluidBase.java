package mfrf.micro_machinery.block.fluids;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.events.RegistryThingsEvent;
import mfrf.micro_machinery.registry_lists.MMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static mfrf.micro_machinery.registry_lists.MMBlocks.BLOCK_REGISTER;
import static mfrf.micro_machinery.registry_lists.MMFluids.FLUID_REGISTER;
import static mfrf.micro_machinery.registry_lists.MMFluids.FLUID_TYPE_REGISTER;
import static mfrf.micro_machinery.registry_lists.MMItems.ITEM_REGISTER;

public class MMFluidBase {
    public static List<MMFluidBase> fluidBaseList = new ArrayList<>();
    public final ResourceLocation fluid_resource_location;
    public final ResourceLocation fluid_flow_resource_location;
    private final String name;
    private RegistryObject<FluidType> fluid_type;
    public RegistryObject<FlowingFluid> fluid;
    public RegistryObject<FlowingFluid> fluid_flowing;
    public RegistryObject<LiquidBlock> fluid_block;
    public RegistryObject<Item> fluid_bucket;
    public ForgeFlowingFluid.Properties fluid_properties;


    /**
     * this.fluid_properties = new ForgeFlowingFluid.Properties(this.fluid, this.fluid_flowing, factory.apply(FluidAttributes.builder(fluid_resource_location, fluid_flow_resource_location).density(10).viscosity(1500))).bucket(this.fluid_bucket).block(this.fluid_block).slopeFindDistance(3).explosionResistance(100F).tickRate(tickRate);
     */
    public MMFluidBase(String name, Block.Properties fluid_block_properties, FluidType.Properties fluid_properties, int tickRate) {
        this.name = name;
        this.fluid_resource_location = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_still");
        this.fluid_flow_resource_location = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_flow");
        this.fluid = FLUID_REGISTER.register("fluids/" + name, () -> new ForgeFlowingFluid.Source(this.fluid_properties));
        this.fluid_type = FLUID_TYPE_REGISTER.register("fluids/" + name, () -> new FluidType(fluid_properties));
        this.fluid_flowing = FLUID_REGISTER.register("fluids/" + name + "_flow", () -> new ForgeFlowingFluid.Flowing(this.fluid_properties));
        this.fluid_block = BLOCK_REGISTER.register("fluids/" + name, () -> new LiquidBlock(this.fluid, fluid_block_properties));
        this.fluid_bucket = ITEM_REGISTER.register(name + "_bucket", () -> new BucketItem(this.fluid, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
        RegistryThingsEvent.getOrCreateItemListToRegisterTab(MMItems.TAB.ICON_TAB.getKey()).add(fluid_bucket);
        fluidBaseList.add(this);
//        FluidBucketDispenserRegister.fluids.add(this);//todo check bucket wrapper
    }

    public String getName() {
        return name;
    }
}
