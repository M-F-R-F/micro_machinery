package com.dbydd.micro_machinery.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Arrays;

public class GuiBase<T extends TileEntity> extends GuiContainer {

    protected final T tileentity;
    protected final ResourceLocation TEXTURES;

    public GuiBase(Container inventorySlotsIn, T tileentity, ResourceLocation TEXTURES) {
        super(inventorySlotsIn);
        this.tileentity = tileentity;
        this.TEXTURES = TEXTURES;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    //dammmmmmmmmmmmit
    public void renderFluidTank(final IFluidTank tank, final int x, final int y, final int tankWidth, final int tankHeight) {
        if (tank.getFluid() == null || tank == null)
            return;

        TextureAtlasSprite fluidSprite = this.mc.getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill().toString());
        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        int scaledHeight = tankHeight * tank.getFluidAmount() / tank.getCapacity();
        this.drawTexturedModalRect(x, y + tankHeight - scaledHeight, fluidSprite, tankWidth, scaledHeight);
    }

    public void renderFluidTankTooltip(final IFluidTank tank, final int mouthx, final int mouthy, final int x, final int y, final int tankWidth, final int tankHeight) {
        FluidStack fluid = tank.getFluid();
        int amount = tank.getFluidAmount();
        int max = tank.getCapacity();
        if (fluid != null && (mouthy - y) <= tankHeight && (mouthy - y) >= 0 && (mouthx - x) <= tankWidth && (mouthx - x) >= 0) {
            String name = fluid.getLocalizedName();
            String[] info = new String[]{I18n.format("gui.fluid.name", name), TextFormatting.DARK_GRAY + I18n.format("gui.fluid.amount", amount, max)};
            this.drawHoveringText(Arrays.asList(info), mouthx, mouthy);
        }
    }

    public void rendergauage(final int x, final int y, final int gauagex, final int gauagey, final int tankWidth, final int tankHeight) {
        //render gauage
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(x, y, gauagex, gauagey, tankWidth, tankHeight);
    }

    public void renderProgressBar(int sidex, int sidey, int textureX, int textureY, int barwidth, int barheight) {
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(sidex, sidey, textureX, textureY, barwidth, barheight);
    }

    public void drawbutton(int buttonid, int x, int y, int width, int height, String buttontext, int holdtexturex, int holdtexturey, int texturex, int texturey) {
        this.buttonList.add(new GuiButton(buttonid, x, y, width, height, "") {
            @Override
            public void drawButton(Minecraft p_drawButton_1_, int mouseX, int mouseY, float p_drawButton_4_) {
                GlStateManager.color(1.0F, 1.0F, 1.0F);
                p_drawButton_1_.getTextureManager().bindTexture(TEXTURES);
                int x = mouseX - this.x, y = mouseY - this.y;
                if (this.visible) {
                    if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
                        this.drawTexturedModalRect(this.x, this.y, holdtexturex, holdtexturey, this.width, this.height);
                    } else {
                        this.drawTexturedModalRect(this.x, this.y, texturex, texturey, this.width, this.height);
                    }
                }
            }
        });
    }

    protected int caculateLeftScaled(int pixels, int maxValue, int currentValue) {
        if (maxValue == 0) return 0;
        return pixels - (currentValue * pixels / maxValue);
    }

    protected void drawBarFromLowerToHigher(int sidex, int sidey, int textureX, int textureY, int barwidth, int barheight, int maxValue, int currentValue){
        int k = caculateLeftScaled(barheight,maxValue,currentValue);
        int leftBelowY = sidey + barheight;
        int textureLeftBelowY = textureY+barheight;
        renderProgressBar(this.guiLeft + sidex, this.guiTop + leftBelowY - k, textureX, textureLeftBelowY - k, barwidth, k + 1);
    }

}