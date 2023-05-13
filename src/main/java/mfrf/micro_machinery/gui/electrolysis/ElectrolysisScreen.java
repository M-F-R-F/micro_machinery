package mfrf.micro_machinery.gui.electrolysis;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisScreen extends ScreenBase<ElectrolysisContainer> {

    public ElectrolysisScreen(ElectrolysisContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/electrolysis.png"), 176, 167);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        TileElectrolysis electrolysis = menu.getElectrolysis();
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);

        if (electrolysis.isWorking())
            renderModule(pPoseStack, 70, 35, 16, 0, -calculateBarPixel(electrolysis.getProgress(), 24), 16);
        renderDefaultEnergyBarWithTip(pPoseStack, electrolysis.getEnergy(), 152, 78, p_render_1_, p_render_2_);
        renderTooltip(pPoseStack, p_render_1_, p_render_2_);
    }
}
