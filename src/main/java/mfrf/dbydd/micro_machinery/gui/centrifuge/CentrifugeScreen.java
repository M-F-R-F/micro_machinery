package mfrf.dbydd.micro_machinery.gui.centrifuge;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CentrifugeScreen extends ScreenBase<CentrifugeContainer> {

    public CentrifugeScreen(CentrifugeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/centrifuge.png"), 176, 188);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);

        int i = calculateBarPixel(container.tileCentrifuge.progress, 30);
        renderModule(73, 42, 176, 0, i, 5);
        renderDefaultEnergyBarWithTip(container.tileCentrifuge.feContainer, 157, 16, p_render_1_, p_render_2_);
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }

    @Override
    protected void init() {
        super.init();
    }
}
