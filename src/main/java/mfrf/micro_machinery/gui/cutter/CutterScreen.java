package mfrf.micro_machinery.gui.cutter;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.cutter.TileCutter;
import mfrf.micro_machinery.gui.ScreenBase;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CutterScreen extends ScreenBase<CutterContainer> {
    public CutterScreen(CutterContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/cutter.png"), 176, 179);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float pTick) {
        initBase(guiGraphics);
        super.render(guiGraphics, mouse_x, mouse_y, pTick);
        TileCutter tileEntity = menu.getBlockEntity();
        if (tileEntity.working()) {
            IntegerContainer progress = tileEntity.getProgress();
            int i = (int) (((float) progress.getCurrent() / (float) progress.getMax()) * (float) 15);
            renderModule(guiGraphics, 72, 40, 92, 0, 16, i);
        }
        renderDefaultEnergyBarWithTip(guiGraphics, tileEntity.getEnergyContainer(), 157, 13, mouse_x, mouse_y);
        renderTooltip(guiGraphics, mouse_x, mouse_y);
    }
}
