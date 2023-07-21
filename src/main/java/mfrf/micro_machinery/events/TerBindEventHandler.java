package mfrf.micro_machinery.events;

import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.special_renders.ter.AnvilTer;
import mfrf.micro_machinery.special_renders.ter.HandGeneratorTer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class TerBindEventHandler {

    @SubscribeEvent
    public static void onClientEvent(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerBlockEntityRenderer(MMBlockEntityTypes.TEST_TILE_TYPE.get(), TestBlockTer::new);
//        event.registerBlockEntityRenderer(MMBlockEntityTypes.TILE_BLAST_FURNACE.get(), BlastFurnaceTer::new);
        event.registerBlockEntityRenderer(MMBlockEntityTypes.TILE_HAND_GENERATOR.get(), HandGeneratorTer::new);
        event.registerBlockEntityRenderer(MMBlockEntityTypes.TILE_ANVIL_TYPE.get(), AnvilTer::new);
//        ClientRegistry.bindBlockEntityRenderer(RegisteredBlockEntityTypes.TILE_ETCHER.get(), EtcherTer::new);
    }

}
