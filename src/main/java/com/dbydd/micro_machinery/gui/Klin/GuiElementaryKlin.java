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
        this.renderFluidTank(tileentity.fluidhandler, x, y, tankWidth, tankHeight);
        this.rendergauage(x, y, 210, 3, tankWidth, tankHeight);
        this.renderFluidTankTooltip(tileentity.fluidhandler, mouseX, mouseY, x, y, 16, 60);
//       this.drawModalRectWithCustomSizedTexture(this.guiLeft + 152, this.guiTop + 3, 210,3, 16, 60,16, 60);
        //todo

    }


}
