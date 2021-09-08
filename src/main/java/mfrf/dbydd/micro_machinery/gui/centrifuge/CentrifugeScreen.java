package mfrf.dbydd.micro_machinery.gui.centrifuge;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.gui.blast_furnace.BlastFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CentrifugeScreen extends ScreenBase<CentrifugeContainer> {

    public CentrifugeScreen(CentrifugeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.MOD_ID, "textures/gui/centrifuge.png"),176,188);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);

    }

    @Override
    protected void init() {
        super.init();
    }
}
