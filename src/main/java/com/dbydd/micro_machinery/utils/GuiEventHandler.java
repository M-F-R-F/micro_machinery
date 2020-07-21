package com.dbydd.micro_machinery.utils;

import com.dbydd.micro_machinery.fluids.MMFluidBase;
import com.dbydd.micro_machinery.gui.klin.KlinContainer;
import com.dbydd.micro_machinery.gui.klin.KlinScreen;
import com.dbydd.micro_machinery.registery_lists.Registeryed_ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiEventHandler {
    @SubscribeEvent
    public static void onClineSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registeryed_ContainerTypes.KLINCONTAINER.get(), KlinScreen::new);
    }

}
