package mfrf.dbydd.micro_machinery.gui.blast_furnace;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BlastFurnaceScreen extends ScreenBase<BlastFurnaceContainer> {
    public BlastFurnaceScreen(BlastFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/blast_furnace.png"), 176, 166);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);

        int progressPixel = calculateBarPixel(container.getFurnace().getProgressContainer(), 22);
        int fuelPixel = calculateBarPixel(container.getFurnace().getHeatHandler(), 16);

        renderModule(57,36 - fuelPixel,41,16 + fuelPixel,15,16 + fuelPixel);
        renderModule(80,35,17,0,22 + progressPixel,16);

        renderHoveredToolTip(p_render_1_,p_render_2_);
    }

}
