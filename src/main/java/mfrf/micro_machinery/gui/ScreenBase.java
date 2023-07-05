package mfrf.micro_machinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.interfaces.Consumer;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Arrays;
import java.util.List;

public class ScreenBase<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    protected final ResourceLocation MODULES = new ResourceLocation(MicroMachinery.MODID, "textures/gui/module.png");
    protected final ResourceLocation TEXTURES;
    public Consumer renderButtonToolTip = null;

    public ScreenBase(T screenContainer, Inventory inv, Component titleIn, ResourceLocation TEXTURES, int textureWidth, int textureHeight) {
        super(screenContainer, inv, titleIn);
        this.TEXTURES = TEXTURES;
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURES);

        int i = this.leftPos;
        int j = this.topPos;
        p_283065_.blit(i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected void renderFluidTank(PoseStack stack, IFluidTank tank, int x, int y, int tankWidth, int tankHeight) {
        if (tank == null || tank.getFluid().isEmpty()) {
            return;
        }
        FluidAttributes attributes = tank.getFluid().getFluid().getAttributes();
        int color = attributes.getColor(tank.getFluid());
        TextureAtlasSprite fluidSprite = this.minecraft.getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(attributes.getStillTexture());
        RenderSystem.setShaderColor((color >> 16 & 255) / 255.0f, (color >> 8 & 255) / 255.0f, (color & 255) / 255.0f, (color >> 24 & 255) / 255.0f);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        int scaledHeight = Math.round((float) tankHeight * ((float) tank.getFluid().getAmount() / (float) tank.getCapacity()));
        renderFluid(stack, scaledHeight, leftPos + x, topPos + y, tankWidth, fluidSprite);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderFluid(PoseStack stack, int scaledHeight, int beginx, int beginy, int tankWidth, TextureAtlasSprite fluidSprite) {
        int heightLayerTarget = scaledHeight / 16;
        int i = scaledHeight - heightLayerTarget * 16;
        for (int heightLayer = 0; heightLayer < heightLayerTarget; heightLayer++) {
            blit(stack, beginx, beginy - 16 * heightLayer, 0, tankWidth, -16, fluidSprite);
        }
        blit(stack, beginx, beginy - 16 * (heightLayerTarget), 0, tankWidth, -i, fluidSprite);
    }

    protected void renderFluidTankTooltip(PoseStack stack, IFluidTank tank, int mouthx, int mouthy, int x, int y, int tankWidth, int tankHeight) {
        FluidStack fluid = tank.getFluid();
        int amount = tank.getFluidAmount();
        int max = tank.getCapacity();
        if (!fluid.isEmpty() && (mouthy - (topPos + y)) <= tankHeight && (mouthy - (topPos + y)) >= 0 && (mouthx - (leftPos + x)) <= tankWidth && (mouthx - (leftPos + x)) >= 0) {
            String name = fluid.getDisplayName().getString();
            String[] info = new String[]{I18n.get("gui.fluid.name", name), ChatFormatting.GRAY + I18n.get("gui.fluid.amount", amount, max)};
            this.renderTooltip(stack, List.of(), mouthx, mouthy);//todo format
        }
    }

    protected void renderEnergyBarTooltip(PoseStack stack, FEContainer container, int mouseX, int mouseY, int x, int y, int barWidth, int barHeight) {
        if ((mouseY - (topPos + y)) <= barHeight && (mouseY - (topPos + y)) >= 0 && (mouseX - (leftPos + x)) <= barWidth && (mouseX - (leftPos + x)) >= 0) {
            int current = container.getCurrent();
            int max = container.getMaxEnergyStored();
            this.renderTooltip(stack, new Component(current + "/" + max + " FE"), mouseX, mouseY);
        }
    }

    protected void renderTankGauge(PoseStack stack, int beginX, int beginY, int texture_width, int texture_height) {
        RenderSystem.setShaderTexture(0, MODULES);
        blit(stack, leftPos + beginX, topPos + beginY, 0, 0, texture_width, texture_height);
    }

    protected void renderTankWithGaugeAndToolTip(PoseStack stack, IFluidTank tank, int mouthX, int mouthY, int x, int y, int width, int height) {
        renderFluidTank(stack, tank, x, y, width, height);
        renderTankGauge(stack, x, y, width, height);
        renderFluidTankTooltip(stack, tank, mouthX, mouthY, x, y, width, height);
    }

    protected void renderDefaultEnergyBarWithTip(PoseStack stack, FEContainer container, int beginX, int beginY, int mouseX, int mouseY) {
        renderModule(stack, beginX, beginY, 243, 70, 5, calculateBarPixel(container, 70));
        renderEnergyBarTooltip(stack, container, mouseX, mouseY, beginX, beginY - 70, 5, 70);
    }

    protected void renderModule(PoseStack stack, int beginX, int beginY, int u, int v, int texture_width, int texture_height) {
        RenderSystem.setShaderTexture(0, MODULES);
        blit(stack, leftPos + beginX, topPos + beginY, u, v, texture_width, texture_height);
    }

    protected void initBase(PoseStack stack) {
        renderBackground(stack);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURES);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected int calculateBarPixel(IntegerContainer container, float pixel) {
        int current = container.getCurrent();
        int max = container.getMax();
        return -Math.round(pixel * (float) current / (float) max);
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {

    }
}
