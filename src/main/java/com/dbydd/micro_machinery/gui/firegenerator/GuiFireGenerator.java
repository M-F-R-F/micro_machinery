package com.dbydd.micro_machinery.gui.firegenerator;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFireGenerator extends GuiBase<TileEntityFireGenerator> {
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/firegenerator.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);

    public GuiFireGenerator(EntityPlayer player, TileEntityFireGenerator tileentity) {
        super(new ContainerFireGenerator(player, tileentity), tileentity, TEXTURES);
        xSize = 176;
        ySize = 178;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        if (tileentity.isGenerating()) {
            drawBarFromLowerToHigher(填);
        }
        drawBarFromLowerToHigher(填，alt+p查看参数);
        renderFluidTank(tileentity.getTank(), guiLeft + 17, guiTop + 15, 16, 60);
        rendergauage(this.guiLeft + 17, this.guiTop + 15, 190, 16, 16, 60);
        renderFluidTankTooltip(tileentity.getTank(),mouseX,mouseY, guiLeft + 17, guiTop + 15, 16, 60);
    }

}
