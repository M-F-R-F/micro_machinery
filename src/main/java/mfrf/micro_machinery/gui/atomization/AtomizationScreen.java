package mfrf.micro_machinery.gui.atomization;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AtomizationScreen extends ScreenBase<AtomizationContainer> {
    public AtomizationScreen(AtomizationContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/atomization.png"), 176, 180);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, p_render_1_, p_render_2_, p_render_3_);
        TileAtomization tileEntity = this.menu.getBlockEntity();
        renderTankWithGaugeAndToolTip(pPoseStack, tileEntity.getInput(), p_render_1_, p_render_2_, 26, 24, 16, 60);
        renderDefaultEnergyBarWithTip(pPoseStack, tileEntity.getFeContainer(), 157, 85, p_render_1_, p_render_2_);
        if (tileEntity.isWorking()) {
            renderModule(pPoseStack, 64, 31, 0, 152, calculateBarPixel(tileEntity.getProgress(), 44), 40);
        }
        renderTooltip(pPoseStack,p_render_1_,p_render_2_);
    }


}
