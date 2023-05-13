package mfrf.micro_machinery.gui.centrifuge;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CentrifugeScreen extends ScreenBase<CentrifugeContainer> {

    public CentrifugeScreen(CentrifugeContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/centrifuge.png"), 176, 188);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);
        TileCentrifuge tileCentrifuge = menu.getTileCentrifuge();

        if (tileCentrifuge.isWorking()) {
            renderModule(pPoseStack, 73, 42, 89, 16, calculateBarPixel(tileCentrifuge.getProgress(), 30), 5);
        }
        renderDefaultEnergyBarWithTip(pPoseStack, tileCentrifuge.getFeContainer(), 157, 85, p_render_1_, p_render_2_);
        renderTooltip(pPoseStack, p_render_1_, p_render_2_);
    }

}
