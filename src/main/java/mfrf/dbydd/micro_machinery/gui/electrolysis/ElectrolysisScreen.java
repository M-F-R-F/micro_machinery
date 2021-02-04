package mfrf.dbydd.micro_machinery.gui.electrolysis;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectrolysisScreen extends ScreenBase<ElectrolysisContainer> {

    public ElectrolysisScreen(ElectrolysisContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/electrolysis.png"), 176, 167);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        renderHoveredToolTip(p_render_1_, p_render_2_);
        FEContainer energy = container.getElectrolysis().getEnergy();
        renderDefaultEnergyBarWithTip(container.getElectrolysis().getEnergy(),152,78,p_render_1_,p_render_2_);
    }
}
