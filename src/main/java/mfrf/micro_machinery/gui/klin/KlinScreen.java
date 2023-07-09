package mfrf.micro_machinery.gui.klin;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KlinScreen extends ScreenBase<KlinContainer> {

    public KlinScreen(KlinContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/klin.png"), 176, 166);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        TileKlin klin = menu.getKlin();
        initBase(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        if (klin.issmelting()) {
            renderModule(guiGraphics, 78, 31, 70, 0, -calculateBarPixel(klin.getMeltTime(), 22f), 16);
        }
        if (klin.isBurning()) {
            renderModule(guiGraphics, 82, 29, 56, 12, 14, -calculateBarPixel(klin.getBurnTime(), 13f) - 13);
        }
        renderFluidTank(guiGraphics, klin.getFluidHandler(), 152, 63, 16, 60);
        renderTankGauge(guiGraphics, 152, 3, 16, 60);
        renderFluidTankTooltip(guiGraphics, klin.getFluidHandler(), mouseX, mouseY, 152, 3, 16, 60);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
