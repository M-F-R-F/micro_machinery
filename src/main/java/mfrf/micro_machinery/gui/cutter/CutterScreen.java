package mfrf.micro_machinery.gui.cutter;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.cutter.TileCutter;
import mfrf.micro_machinery.gui.ScreenBase;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CutterScreen extends ScreenBase<CutterContainer> {
    public CutterScreen(CutterContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/cutter.png"), 176, 179);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);
        TileCutter tileEntity = menu.getBlockEntity();
        if (tileEntity.working()) {
            IntegerContainer progress = tileEntity.getProgress();
            int i = (int) (((float) progress.getCurrent() / (float) progress.getMax()) * (float) 15);
            renderModule(pPoseStack, 72, 40, 92, 0, 16, i);
        }
        renderDefaultEnergyBarWithTip(pPoseStack, tileEntity.getEnergyContainer(), 157, 83, p_render_1_, p_render_2_);
        renderTooltip(pPoseStack, p_render_1_, p_render_2_);
    }
}
