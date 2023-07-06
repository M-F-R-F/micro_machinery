package mfrf.micro_machinery.events;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.block.fluids.MMFluidBase;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class MoltenMaterialToRockEventDenier {
    public static void onMoltenMaterialGenerateStone(BlockEvent.FluidPlaceBlockEvent event) {
        FluidState fluid = event.getLevel().getFluidState(event.getLiquidPos());
        if (fluid.getFluidType().getTemperature() > 1000) {
//            String path = fluid.getType().getRegistryName().getPath();
//
//            for (MMFluidBase fluidBase : MMFluidBase.FLUID_BASE_LIST) {
//                if (path.contains(fluidBase.getName())) {
//                    Block block = MMBlockBase.registeries.get(fluidBase.getName() + "_discarded").get();
//                    event.setNewState(block.defaultBlockState());
//                }
//            }
        MMFluidBase mmFluidBase = MMFluidBase.getFluidMap().get(fluid.getType().getFluidType());
        if(mmFluidBase != null){

        }
        }


        event.setResult(Event.Result.ALLOW);
    }

}
