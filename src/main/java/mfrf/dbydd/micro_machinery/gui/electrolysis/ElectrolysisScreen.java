package mfrf.dbydd.micro_machinery.gui.electrolysis;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectrolysisScreen extends ScreenBase<ElectrolysisContainer> {

    public ElectrolysisScreen(ElectrolysisContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/electrolysis.png"), 176, 167);
    }
}
