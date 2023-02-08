package mfrf.micro_machinery.event;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.fluids.MMFluidBase;
import net.minecraft.world.level.block.Block
import net.minecraft.fluid.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MoltenMaterialToRockEventDenier {
    @SubscribeEvent
    public static void onMoltenMaterialGenerateStone(BlockEvent.FluidPlaceBlockEvent event) {
        Fluid fluid = event.getWorld().getFluidState(event.getLiquidPos()).getFluid();
        if (fluid.getRegistryName().getNamespace().equals(MicroMachinery.MODID) && fluid.getTags().contains(new ResourceLocation("minecraft", "lava"))) {
            String path = fluid.getRegistryName().getPath();

            for (MMFluidBase fluidBase : MMFluidBase.fluidBaseList) {
                if (path.contains(fluidBase.getName())) {
                    Block block = MMBlockBase.registeries.get(fluidBase.getName() + "_discarded").get();
                    event.setNewState(block.getDefaultState());
                }
            }
        }
        event.setResult(Event.Result.ALLOW);
    }

}
