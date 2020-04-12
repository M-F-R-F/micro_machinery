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
        renderFluidTank(tileentity.getTank(), guiLeft + 17, guiTop + 15, 16, 60);//这里是添加水槽
        rendergauage(this.guiLeft + 17, this.guiTop + 15, 190, 16, 16, 60);//这里是刻度表
        if (tileentity.isGenerating()) {
            int k = 0;
            mc.getTextureManager().bindTexture(TEXTURES);
            k = caculateLeftScaled(13, tileentity.getMaxBurnTime(), tileentity.getCurrentBurnTime());
            renderProgressBar(this.guiLeft + 71, this.guiTop + 39 - k, 201, 1 - k, 16, k + 1);
        }
        int wow = 0;
        wow = 70 * (tileentity.getMaxEnergyCapacity() - tileentity.getEnergyStored()) / tileentity.getMaxEnergyCapacity();
        drawTexturedModalRect(this.guiLeft + 157, this.guiTop + 14 - wow, 176, 1 - wow, 5, 70 - wow);

    }

}
