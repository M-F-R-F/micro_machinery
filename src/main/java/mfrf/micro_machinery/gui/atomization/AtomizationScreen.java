package mfrf.micro_machinery.gui.atomization;


import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AtomizationScreen extends ScreenBase<AtomizationContainer> {
    public AtomizationScreen(AtomizationContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/atomization.png"), 176, 180);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float pTick) {
        initBase(guiGraphics);
        super.render(guiGraphics, mouse_x, mouse_y, pTick);
        TileAtomization tileEntity = menu.getBlockEntity();
        renderTankWithGaugeAndToolTip(guiGraphics, tileEntity.getInput(), mouse_x, mouse_y, 26, 24, 16, 60);
        renderDefaultEnergyBarWithTip(guiGraphics, tileEntity.getFeContainer(), 157, 13, mouse_x, mouse_y);
        if (tileEntity.isWorking()) {
            renderModule(guiGraphics, 64, 31, 0, 152, calculateBarPixel(tileEntity.getProgress(), 44), 40);
        }
        renderTooltip(guiGraphics, mouse_x, mouse_y);
    }

}
