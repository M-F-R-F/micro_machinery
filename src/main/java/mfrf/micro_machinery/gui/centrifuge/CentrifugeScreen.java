package mfrf.micro_machinery.gui.centrifuge;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CentrifugeScreen extends ScreenBase<CentrifugeContainer> {

    public CentrifugeScreen(CentrifugeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/centrifuge.png"), 176, 188);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        TileCentrifuge tileCentrifuge = container.getTileCentrifuge();

        if (tileCentrifuge.isWorking()) {
            renderModule(73, 42, 89, 16, calculateBarPixel(tileCentrifuge.getProgress(), 30), 5);
        }
        renderDefaultEnergyBarWithTip(tileCentrifuge.getFeContainer(), 157, 85, p_render_1_, p_render_2_);
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }

}
