package mfrf.micro_machinery.gui.electrolysis;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisScreen extends ScreenBase<ElectrolysisContainer> {

    public ElectrolysisScreen(ElectrolysisContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/electrolysis.png"), 176, 167);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float pTick) {
        TileElectrolysis electrolysis = menu.getElectrolysis();
        initBase(guiGraphics);
        super.render(guiGraphics, mouse_x, mouse_y, pTick);

        if (electrolysis.isWorking())
            renderModule(guiGraphics, 70, 35, 16, 0, -calculateBarPixel(electrolysis.getProgress(), 24), 16);
        renderDefaultEnergyBarWithTip(guiGraphics, electrolysis.getEnergy(), 152, 8, mouse_x, mouse_y);
//        renderDefaultEnergyBarWithTip(guiGraphics, electrolysis.getEnergy(), -16, 128, mouse_x, mouse_y);
        renderTooltip(guiGraphics, mouse_x, mouse_y);
    }
}
