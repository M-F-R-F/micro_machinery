package mfrf.micro_machinery.gui.weld;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.weld.TileWeld;
import mfrf.micro_machinery.gui.ScreenBase;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WeldScreen extends ScreenBase<WeldContainer> {

    public WeldScreen(WeldContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/weld.png"), 176, 182);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float pTick) {
        initBase(guiGraphics);
        super.render(guiGraphics, mouse_x, mouse_y, pTick);
        TileWeld weld = menu.getWeld();

        if (weld.isWorking()) {
            IntegerContainer progress = weld.getProgress();
            renderModule(guiGraphics, 84, 42, 16, 0, calculateBarPixel(progress, 25), 16);
        }
        renderDefaultEnergyBarWithTip(guiGraphics, weld.getFeContainer(), 157, 82, mouse_x, mouse_y);
        renderTooltip(guiGraphics, mouse_x, mouse_y);
    }

}
