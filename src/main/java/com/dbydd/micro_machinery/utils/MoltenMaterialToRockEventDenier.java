package com.dbydd.micro_machinery.utils;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.fluids.MMFluidBase;
import com.sun.org.apache.bcel.internal.generic.FSUB;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
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
            String path = fluid.getRegistryName().getPath();

            for (MMFluidBase fluidBase : MMFluidBase.fluidBaseList) {
                if (path.contains(fluidBase.getName())) {
                    Block block = MMBlockBase.registeries.get(fluidBase.getName()+"_discarded").get();
                    event.setNewState(block.getDefaultState());
                }
            }
        }
        event.setResult(Event.Result.ALLOW);
    }

}
