package mfrf.micro_machinery.gui.centrifuge;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CentrifugeScreen extends ScreenBase<CentrifugeContainer> {

    public CentrifugeScreen(CentrifugeContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/centrifuge.png"), 176, 188);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float pTick) {
        initBase(guiGraphics);
        super.render(guiGraphics, mouse_x, mouse_y, pTick);
        TileCentrifuge tileCentrifuge = menu.getTileCentrifuge();

        if (tileCentrifuge.isWorking()) {
            renderModule(guiGraphics, 73, 42, 89, 16, calculateBarPixel(tileCentrifuge.getProgress(), 30), 5);
        }
        renderDefaultEnergyBarWithTip(guiGraphics, tileCentrifuge.getFeContainer(), 157, 13, mouse_x, mouse_y);
        renderTooltip(guiGraphics, mouse_x, mouse_y);
    }

}
