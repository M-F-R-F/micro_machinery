package com.dbydd.micro_machinery.utils;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.fluids.MMFluidBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Collections;

@Mod.EventBusSubscriber
public class MoltenMaterialToRockEventDenier {
    @SubscribeEvent
    public static void onMoltenMaterialGenerateStone(BlockEvent.FluidPlaceBlockEvent event) {
        Fluid fluid = event.getWorld().getFluidState(event.getLiquidPos()).getFluid();
        if (fluid.getRegistryName().getNamespace().equals(Micro_Machinery.NAME) && fluid.getTags().contains(new ResourceLocation("minecraft", "lava"))) {
            //todo
            event.setNewState(Blocks.DIAMOND_BLOCK.getDefaultState());
        }
            event.setResult(Event.Result.ALLOW);
    }
}
