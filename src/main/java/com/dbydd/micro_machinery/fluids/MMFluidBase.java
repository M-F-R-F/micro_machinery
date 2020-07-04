package com.dbydd.micro_machinery.fluids;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.items.MMItemBase;
import com.dbydd.micro_machinery.registery_lists.RegisteryedFluids;
import com.mojang.datafixers.types.Func;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MMFluidBase {
    private static DeferredRegister<Fluid> FLUID_REGISTER = Micro_Machinery.FLUID_REGISTER;
    private static DeferredRegister<Block> BLOCK_REGISTER = Micro_Machinery.BLOCK_REGISTER;
    private static DeferredRegister<Item> ITEM_REGISTER = Micro_Machinery.ITEM_REGISTER;
    public final ResourceLocation fluid_resource_location;
    public final ResourceLocation fluid_flow_resource_location;
    public RegistryObject<FlowingFluid> fluid;
    public RegistryObject<FlowingFluid> fluid_flowing;
    public RegistryObject<FlowingFluidBlock> fluid_block;
    public RegistryObject<Item> fluid_bucket;
    public ForgeFlowingFluid.Properties fluid_properties;

    public MMFluidBase(String name, Block.Properties fluid_block_properties, Function<FluidAttributes.Builder, FluidAttributes.Builder> factory) {
        this.fluid_resource_location = new ResourceLocation("fluids/" + name + "_still");
        this.fluid_flow_resource_location = new ResourceLocation("fluids/" + name + "_flow");
        this.fluid_properties = new ForgeFlowingFluid.Properties(this.fluid, this.fluid_flowing, factory.apply(FluidAttributes.builder(fluid_resource_location, fluid_flow_resource_location))).bucket(this.fluid_bucket).block(this.fluid_block);
        this.fluid = FLUID_REGISTER.register(name, () -> new ForgeFlowingFluid.Source(this.fluid_properties));
        this.fluid_flowing = FLUID_REGISTER.register(name + "_flow", () -> new ForgeFlowingFluid.Flowing(this.fluid_properties));
        this.fluid_block = BLOCK_REGISTER.register(name, () -> new FlowingFluidBlock(this.fluid, fluid_block_properties));
        this.fluid_bucket = ITEM_REGISTER.register(name + "_bucket", () -> new BucketItem(this.fluid, MMItemBase.DEFAULT_PROPERTIES));
        RegisteryedFluids.registeries.put("name", this);
    }
}
