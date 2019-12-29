package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiElementaryKlin extends GuiBase<TileEntityKlin> {

    //指定自定义背景贴图位置
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/klin.png";
    // private static final String TEXTURE_COMP = Reference.MODID + ":" + "rua!";
    //创建自定义贴图的ResourceLocation标识。
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    //private static final ResourceLocation TEXTURECOMP = new ResourceLocation(TEXTURE_COMP);
    private int k = 0;

    public GuiElementaryKlin(EntityPlayer player, TileEntityKlin tileentity) {
        super(new ContainerElementaryKlin(player, tileentity), tileentity, TEXTURES);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int x = this.guiLeft + 152;
        int y = this.guiTop + 3;
        int tankWidth = 16;
        int tankHeight = 60;
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        if (TileEntityKlin.isBurning(tileentity)) {
            mc.getTextureManager().bindTexture(TEXTURES);
            k = getBurnLeftScaled(13) ;
            renderProgressBar(this.guiLeft + 82, this.guiTop + 29 - k, 176, 28 - k, 14, k + 1);
        }
        renderFluidTank(tileentity.fluidhandler, x, y, tankWidth, tankHeight);
        rendergauage(x, y, 210, 3, tankWidth, tankHeight);
        renderFluidTankTooltip(tileentity.fluidhandler, mouseX, mouseY, x, y, 16, 60);
    }

    private int getBurnLeftScaled(int pixels) {
        int burntime = tileentity.getField(2);
        int maxburntime = tileentity.getField(3);
        if (maxburntime == 0) return 0;
         return pixels - (burntime * pixels / maxburntime);
        //return burntime * pixels / maxburntime;
    }

    private int getMeltProgressScaled(int pixels) {
        int maxmelttime = tileentity.getField(1);
        int melttime = tileentity.getField(0);
        return melttime != 0 && maxmelttime != 0 ? maxmelttime * pixels / melttime : 0;
    }


}
