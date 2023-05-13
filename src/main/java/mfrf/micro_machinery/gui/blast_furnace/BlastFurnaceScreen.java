package mfrf.micro_machinery.gui.blast_furnace;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.Component;
import net.minecraft.world.entity.player.Inventory;

public class BlastFurnaceScreen extends ScreenBase<BlastFurnaceContainer> {
    public BlastFurnaceScreen(BlastFurnaceContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/blast_furnace.png"), 176, 166);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);

        if (menu.getFurnace().burning()) {
            renderModule(56, 50, 40, 14, 16, calculateBarPixel(container.getFurnace().getHeatHandler(), 16));
        }
        if (menu.getFurnace().isWorking()) {
            renderModule(79, 35, 16, 0, -calculateBarPixel(container.getFurnace().getProgressContainer(), 25), 16);
        }
        renderTooltip(pPoseStack, p_render_1_, p_render_2_);
    }

}
