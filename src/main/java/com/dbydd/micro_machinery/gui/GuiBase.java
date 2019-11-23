package com.dbydd.micro_machinery.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Arrays;
import java.util.Collections;

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

    public void renderFluidTank(final IFluidTank tank, final int x, final int y, final int tankWidth, final int tankHeight) {
        if (tank.getFluid() == null || tank == null)
            return;

        TextureAtlasSprite fluidSprite = this.mc.getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill().toString());
        this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        int scaledHeight = tankHeight * tank.getFluidAmount() / tank.getCapacity();
        this.drawTexturedModalRect(x, y + tankHeight - scaledHeight, fluidSprite, tankWidth, scaledHeight);
    }

    void renderFluidTankTooltip(final IFluidTank tank, final int x, final int y) {
        FluidStack stack = tank.getFluid();
        if (stack != null) {
            String name = stack.getLocalizedName();
            int amount = stack.amount;
            String[] info = new String[] {I18n.format("gui.fluid.name", name), I18n.format("gui.fluid.amount", amount)};
            this.drawHoveringText(Arrays.asList(info), x - guiLeft, y - guiTop);
        } else {
            this.drawHoveringText(Collections.singletonList(I18n.format("gui.fluid.null")), x - guiLeft, y - guiTop);
        }
    }
}