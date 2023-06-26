package org.mfrf.micro_machienry.gui.weld;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WeldScreen extends ScreenBase<WeldContainer> {

    public WeldScreen(WeldContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.NAME, "textures/gui/weld.png"), 176, 182);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        TileWeld weld = container.getWeld();

        if (weld.isWorking()) {
            IntegerContainer progress = weld.getProgress();
            renderModule(84, 42, 16, 0, calculateBarPixel(progress, 25), 16);
        }
        renderDefaultEnergyBarWithTip(weld.getFeContainer(), 157, 82, p_render_1_, p_render_2_);
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }

}
