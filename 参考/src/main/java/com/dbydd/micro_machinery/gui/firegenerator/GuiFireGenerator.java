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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderFluidTankTooltip(tileentity.getTank(), mouseX, mouseY, guiLeft + 17, guiTop + 15, 16, 60);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        renderFluidTank(tileentity.getTank(), guiLeft + 17, guiTop + 15, 16, 60);
        rendergauage(this.guiLeft + 17, this.guiTop + 15, 192, 18, 16, 60);
        drawBarFromLowerToHigher(157, 14, 176, 0, 5, 70, tileentity.getMaxEnergyCapacity(),tileentity.getMaxEnergyCapacity() -  tileentity.getEnergyStored());
        if (tileentity.isGenerating()) {
            drawBarFromLowerToHigher(70, 39, 201, 0, 16, 16, tileentity.getMaxBurnTime(), tileentity.getCurrentBurnTime());
        }
    }

}
