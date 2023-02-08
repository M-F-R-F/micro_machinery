package mfrf.micro_machinery.gui.atomization;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AtomizationScreen extends ScreenBase<AtomizationContainer> {
    public AtomizationScreen(AtomizationContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/atomization.png"), 176, 180);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        TileAtomization tileEntity = container.getBlockEntity();
        renderTankWithGaugeAndToolTip(tileEntity.getInput(), p_render_1_, p_render_2_, 26, 24, 16, 60);
        renderDefaultEnergyBarWithTip(tileEntity.getFeContainer(), 157, 85, p_render_1_, p_render_2_);
        if (tileEntity.isWorking()) {
            renderModule(64, 31, 0, 152, calculateBarPixel(tileEntity.getProgress(), 44), 40);
        }
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }

}
