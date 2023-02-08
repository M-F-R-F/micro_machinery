package mfrf.micro_machinery.gui.electrolysis;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectrolysisScreen extends ScreenBase<ElectrolysisContainer> {

    public ElectrolysisScreen(ElectrolysisContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/electrolysis.png"), 176, 167);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        TileElectrolysis electrolysis = container.getElectrolysis();
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);

        if (electrolysis.isWorking())
            renderModule(70, 35, 16, 0, -calculateBarPixel(electrolysis.getProgress(), 24), 16);
        renderDefaultEnergyBarWithTip(electrolysis.getEnergy(), 152, 78, p_render_1_, p_render_2_);
        renderHoveredToolTip(p_render_1_, p_render_2_);
    }
}
