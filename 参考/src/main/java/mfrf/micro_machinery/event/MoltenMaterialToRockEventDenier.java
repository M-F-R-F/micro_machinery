package mfrf.micro_machinery.event;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.fluids.MMFluidBase;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MoltenMaterialToRockEventDenier {
    @SubscribeEvent
    public static void onMoltenMaterialGenerateStone(BlockEvent.FluidPlaceBlockEvent event) {
        FluidState fluid = event.getWorld().getFluidState(event.getLiquidPos());
        if (fluid.getType().getRegistryName().getNamespace().equals(MicroMachinery.MODID) && fluid.getTags().anyMatch(fluidTagKey -> fluidTagKey.equals(FluidTags.LAVA))) {
            String path = fluid.getType().getRegistryName().getPath();

            for (MMFluidBase fluidBase : MMFluidBase.fluidBaseList) {
                if (path.contains(fluidBase.getName())) {
                    Block block = MMBlockBase.registeries.get(fluidBase.getName() + "_discarded").get();
                    event.setNewState(block.defaultBlockState());
                }
            }
        }
        event.setResult(Event.Result.ALLOW);
    }

}
