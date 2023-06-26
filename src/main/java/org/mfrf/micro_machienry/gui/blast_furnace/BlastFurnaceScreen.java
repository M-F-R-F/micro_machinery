package org.mfrf.micro_machienry.gui.blast_furnace;

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

        if (container.getFurnace().burning()) {
            renderModule(56, 50, 40, 14, 16, calculateBarPixel(container.getFurnace().getHeatHandler(), 16));
        }
        if (container.getFurnace().isWorking()) {
            renderModule(79, 35, 16, 0, -calculateBarPixel(container.getFurnace().getProgressContainer(), 25), 16);
        }
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }

}
