package org.mfrf.micro_machienry.gui.cutter;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.cutter.TileCutter;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CutterScreen extends ScreenBase<CutterContainer> {
    public CutterScreen(CutterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME,"textures/gui/cutter.png"), 176, 179);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        TileCutter tileEntity = container.getTileEntity();
        if(tileEntity.working()){
            IntegerContainer progress = tileEntity.getProgress();
            int i = (int) (((float)progress.getCurrent() / (float) progress.getMax()) * (float)15);
            renderModule(72,40,92,0,16,i);
        }
        renderDefaultEnergyBarWithTip(tileEntity.getEnergyContainer(),157,83,p_render_1_,p_render_2_);
        renderHoveredToolTip(p_render_1_,p_render_2_);
    }
}
