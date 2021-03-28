package mfrf.dbydd.micro_machinery.event;

import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.special_renders.ter.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerBindEventHandler {

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntityTypes.TEST_TILE_TYPE.get(), TestBlockTer::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntityTypes.TILE_BLAST_FURNACE.get(), BlastFurnaceTer::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntityTypes.TILE_HAND_GENERATOR.get(), HandGeneratorTer::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntityTypes.TILE_ANVIL_TYPE.get(), AnvilTer::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntityTypes.TILE_ETCHER.get(), EtcherTer::new);
    }

}
