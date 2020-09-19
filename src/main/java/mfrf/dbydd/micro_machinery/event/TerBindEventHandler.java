package mfrf.dbydd.micro_machinery.event;

import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.special_renders.ter.AnvilTer;
import mfrf.dbydd.micro_machinery.special_renders.ter.HandGeneratorTer;
import mfrf.dbydd.micro_machinery.special_renders.ter.TestBlockTer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerBindEventHandler {

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(Registered_Tileentitie_Types.TEST_TILE_TYPE.get(), TestBlockTer::new);
        ClientRegistry.bindTileEntityRenderer(Registered_Tileentitie_Types.TILE_HAND_GENERATOR.get(), HandGeneratorTer::new);
        ClientRegistry.bindTileEntityRenderer(Registered_Tileentitie_Types.TILE_ANVIL_TYPE.get(), AnvilTer::new);
    }

}