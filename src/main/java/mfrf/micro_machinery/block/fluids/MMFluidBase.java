package mfrf.micro_machinery.block.fluids;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.events.RegistryThingsEvent;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.registry_lists.MMFluids;
import mfrf.micro_machinery.registry_lists.MMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import static mfrf.micro_machinery.registry_lists.MMBlocks.BLOCK_REGISTER;
import static mfrf.micro_machinery.registry_lists.MMBlocks.THICKENER_1;
import static mfrf.micro_machinery.registry_lists.MMFluids.FLUID_REGISTER;
import static mfrf.micro_machinery.registry_lists.MMItems.ITEM_REGISTER;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMFluidBase {
    public static final HashSet<MMFluidBase> MOLTEN_FLUIDS = new HashSet<>();
    private final String name;
    private final RegistryObject<FluidType> fluid_type;
    private final RegistryObject<Fluid> fluid;
    private final RegistryObject<Fluid> fluid_flow;
    private final RegistryObject<BucketItem> bucket;
    private RegistryObject<LiquidBlock> block;
    private final BlockBehaviour.Properties fluid_block_properties;
    private int tickrate;

    /**
     * this.fluid_properties = new ForgeFlowingFluid.Properties(this.fluid, this.fluid_flowing, factory.apply(FluidAttributes.builder(fluid_resource_location, fluid_flow_resource_location).density(10).viscosity(1500))).bucket(this.fluid_bucket).block(this.fluid_block).slopeFindDistance(3).explosionResistance(100F).tickRate(tickRate);
     */
    public MMFluidBase(String name, Block.Properties fluid_block_properties, FluidType.Properties fluid_properties, boolean is_molten_material, int tickRate) {
        this.name = name;
        this.tickrate = tickRate;
        this.fluid_type = MMFluids.FLUID_TYPE_REGISTER.register(name, () -> new FluidType(fluid_properties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private final ResourceLocation
                            STILL = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_still"),
                            FLOWING = new ResourceLocation(MicroMachinery.MODID, "fluids/" + name + "_flow");

                    @Override
                    public ResourceLocation getStillTexture() {
                        return STILL;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return FLOWING;
                    }
                });
            }
        });
        this.fluid = RegistryObject.create(new ResourceLocation(MicroMachinery.MODID, name), ForgeRegistries.Keys.FLUIDS, MicroMachinery.MODID);
        this.fluid_flow = RegistryObject.create(new ResourceLocation(MicroMachinery.MODID, name + "_flow"), ForgeRegistries.Keys.FLUIDS, MicroMachinery.MODID);
        this.fluid_block_properties = fluid_block_properties;
        this.bucket = ITEM_REGISTER.register(name + "_bucket", () -> new BucketItem(fluid::get, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
        RegistryThingsEvent.addItemToRegisterTab(bucket::get);

        if (is_molten_material) {
            MOLTEN_FLUIDS.add(this);
        }
    }

    public MMFluidBase(String name, Block.Properties fluid_block_properties, FluidType.Properties fluid_properties, boolean is_molten_material) {
        this(name, fluid_block_properties, fluid_properties, is_molten_material, 20);
    }

    @SubscribeEvent
    public void registerFluids(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.FLUIDS, helper -> {
            // set up properties
            ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(this.fluid_type, this.fluid, this.fluid_flow)
                    .tickRate(tickrate)
                    .block(block)
                    .bucket(bucket);

            helper.register(fluid.getId(), new ForgeFlowingFluid.Source(properties));
            helper.register(fluid_flow.getId(), new ForgeFlowingFluid.Flowing(properties));
            this.block = MMBlocks.BLOCK_REGISTER.register(name, () -> new LiquidBlock(() -> (FlowingFluid) fluid_flow.get(), fluid_block_properties));
        });
    }


    public String getName() {
        return name;
    }
}
