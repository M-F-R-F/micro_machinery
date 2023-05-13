package mfrf.micro_machinery.gui.weld;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
import mfrf.micro_machinery.gui.ScreenBase;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WeldScreen extends ScreenBase<WeldContainer> {

    public WeldScreen(WeldContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/weld.png"), 176, 182);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);
        TileWeld weld = menu.getWeld();

        if (weld.isWorking()) {
            IntegerContainer progress = weld.getProgress();
            renderModule(pPoseStack, 84, 42, 16, 0, calculateBarPixel(progress, 25), 16);
        }
        renderDefaultEnergyBarWithTip(pPoseStack, weld.getFeContainer(), 157, 82, p_render_1_, p_render_2_);
        renderTooltip(pPoseStack, p_render_1_, p_render_2_);
    }

}
