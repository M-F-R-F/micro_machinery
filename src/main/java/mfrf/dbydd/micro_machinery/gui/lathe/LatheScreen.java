package mfrf.dbydd.micro_machinery.gui.lathe;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LatheScreen extends ScreenBase<LatheContainer> {
    public LatheScreen(LatheContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/lathe.png"),176 , 182);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    protected void init() {
        super.init();

        //todo
    }
}
