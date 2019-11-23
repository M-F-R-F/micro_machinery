package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiElementaryKlin extends GuiBase {

    //指定自定义背景贴图位置
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/klin.png";
    // private static final String TEXTURE_COMP = Reference.MODID + ":" + "rua!";
    //创建自定义贴图的ResourceLocation标识。
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    private TileEntityKlin tileentity;
    //private static final ResourceLocation TEXTURECOMP = new ResourceLocation(TEXTURE_COMP);

    public GuiElementaryKlin(EntityPlayer player, TileEntityKlin tileentity) {
        super(new ContainerElementaryKlin(player, tileentity));
        this.tileentity = tileentity;
        //设置GUI背景贴图的大小（方便后续计算GUI元素与背景贴图的相对位置）
        this.xSize = 176;
        this.ySize = 166;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.renderFluidTank(tileentity.getFluidhandler(), this.guiTop, this.guiLeft, 64, 128);
        //todo
//        if(TileEntityKlin.isBurning(tileentity))
//        {
//            int k = this.getBurnLeftScaled(13);
//            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 - k, 176, 12 - k, 14, k + 1);
//        }
//
//        int l = this.getCookProgressScaled(24);
//        this.drawTexturedModalRect(this.guiLeft + 44, this.guiTop + 36, 176, 14, l + 1, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

//    @Override
//    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        //设置渲染混合模式及颜色模式（该处代码解释请查看lwjgl及OpenGL相关文档）
//        GL11.glPushMatrix();
//        GL11.glEnable(GL11.GL_BLEND);
//        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        //将自定义背景贴图与Minecraft材质管理器绑定
//        this.mc.getTextureManager().bindTexture(TEXTUREBACK);
//        //计算相对位置（以背景贴图左上角为(0,0)点）
//        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
//        //绘制背景贴图（参数说明：在游戏中的XY位置；贴图在贴图文件中的XY位置；贴图的大小）
//        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
//        //结束渲染
//        GL11.glPopMatrix();
//    }
//
//    @Override
//    public void initGui() {
//        super.initGui();
//        //计算相对位置（以背景贴图左上角为(0,0)点）
//        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
//        //创建一个新的GuiButton对象并加入buttonList中（参数说明：按钮ID；在游戏中XY位置；按钮大小；按钮上文字（如果为自定义样式则该参数无效））
//        this.buttonList.add(new GuiButton(0, offsetX + 219, offsetY + 129, 32, 32, "") {
//            //覆写drawButton()内部方法（当然你也可以创建一个新的类使其继承GuiButton类来进行更多的自定义操作）
//            @Override
//            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
//                //判断按钮是否可见（默认可见）
//                if (this.visible) {
//                    //设置渲染混合模式及颜色模式（该处代码解释请查看lwjgl及OpenGL相关文档）
//                    GL11.glPushMatrix();
//                    GL11.glEnable(GL11.GL_BLEND);
//                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
//                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                    //绑定组件贴图至贴图管理器
//                    mc.getTextureManager().bindTexture(TEXTURECOMP);
//                    //绘制按钮贴图
//                    this.drawTexturedModalRect(this.x, this.y, 0, 0, this.width, this.height);
//                    //结束渲染
//                    GL11.glPopMatrix();
//                }
//            }
//        });
//    }

//    private int getBurnLeftScaled(int pixels)
//    {
//        int i = this.tileentity.getField(1);
//        if(i == 0) i = 200;
//        return this.tileentity.getField(2) * pixels / i;
//    }

}
