package mfrf.dbydd.micro_machinery.registeried_lists;

import mfrf.dbydd.micro_machinery.fluids.MMFluidBase;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderTypes {

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.KLIN, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.STALINITE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.STEEL_SCAFFOLDING, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.ENERGY_INDICATOR,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WATER_INDICATOR,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.FLUID_INDICATOR,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.TEMPERATURE_INDICATOR,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.REV_INDICATOR,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.BRONZE_ANVIL, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.STONE_ANVIL, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.PIGIRON_ANVIL, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.GRAPHITE_CRUCIBLE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.LATHE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.PLACE_HOLDER,RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.LEVER_PLACEHOLDER,RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onFluidRenderTypeSetup(FMLClientSetupEvent event) {
        for (MMFluidBase mmFluidBase : MMFluidBase.fluidBaseList) {
            RenderTypeLookup.setRenderLayer(mmFluidBase.fluid.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(mmFluidBase.fluid_flowing.get(), RenderType.getTranslucent());
        }
    }
}
