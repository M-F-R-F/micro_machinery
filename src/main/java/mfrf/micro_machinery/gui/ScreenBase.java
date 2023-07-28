package mfrf.micro_machinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.interfaces.VoidSupplier;
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
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.lwjgl.system.SharedLibrary;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ScreenBase<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    protected final ResourceLocation MODULES = new ResourceLocation(MicroMachinery.MODID, "textures/gui/module.png");
    protected final ResourceLocation TEXTURES;
    public VoidSupplier renderButtonToolTip = null;

    public ScreenBase(T screenContainer, Inventory inv, Component titleIn, ResourceLocation TEXTURES, int textureWidth, int textureHeight) {
        super(screenContainer, inv, titleIn);
        this.TEXTURES = TEXTURES;
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);

        int i = this.leftPos;
        int j = this.topPos;
        guiGraphics.blit(TEXTURES, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected void renderFluidTank(GuiGraphics guiGraphics, IFluidTank tank, int x, int y, int tankWidth, int tankHeight) {
        if (tank == null || tank.getFluid().isEmpty()) {
            return;
        }
        IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(tank.getFluid().getFluid());
        int color = properties.getTintColor();
        TextureAtlasSprite fluidSprite = this.minecraft.getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(properties.getStillTexture());
        RenderSystem.setShaderColor((color >> 16 & 255) / 255.0f, (color >> 8 & 255) / 255.0f, (color & 255) / 255.0f, (color >> 24 & 255) / 255.0f);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        int scaledHeight = Math.round((float) tankHeight * ((float) tank.getFluid().getAmount() / (float) tank.getCapacity()));
        renderFluid(guiGraphics, scaledHeight, leftPos + x, topPos + y, tankWidth, fluidSprite);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderFluid(GuiGraphics guiGraphics, int scaledHeight, int beginx, int beginy, int tankWidth, TextureAtlasSprite fluidSprite) {
        int heightLayerTarget = scaledHeight / 16;
        int i = scaledHeight - heightLayerTarget * 16;
        for (int heightLayer = 0; heightLayer < heightLayerTarget; heightLayer++) {
            guiGraphics.blit(beginx, beginy - 16 * heightLayer, 0, tankWidth, -16, fluidSprite);
        }
        guiGraphics.blit(beginx, beginy - 16 * (heightLayerTarget), 0, tankWidth, -i, fluidSprite);
    }

    protected void renderFluidTankTooltip(GuiGraphics guiGraphics, IFluidTank tank, int mousex, int mousey, int x, int y, int tankWidth, int tankHeight) {
        FluidStack fluid = tank.getFluid();
        int amount = tank.getFluidAmount();
        int max = tank.getCapacity();
        if (!fluid.isEmpty() && (mousey - (topPos + y)) <= tankHeight && (mousey - (topPos + y)) >= 0 && (mousex - (leftPos + x)) <= tankWidth && (mousex - (leftPos + x)) >= 0) {
            String name = fluid.getDisplayName().getString();
            String[] info = new String[]{I18n.get("gui.fluid.name", name), ChatFormatting.GRAY + I18n.get("gui.fluid.amount", amount, max)};
            this.renderTooltip(guiGraphics, mousex, mousey);//todo format
        }
    }

    protected void renderEnergyBarTooltip(GuiGraphics guiGraphics, FEContainer container, int mouseX, int mouseY, int x, int y, int barWidth, int barHeight) {
        if ((mouseY - (topPos + y)) <= barHeight && (mouseY - (topPos + y)) >= 0 && (mouseX - (leftPos + x)) <= barWidth && (mouseX - (leftPos + x)) >= 0) {
            int current = container.getCurrent();
            int max = container.getMaxEnergyStored();
            guiGraphics.renderTooltip(this.font, Component.literal(current + "/" + max + " FE"), mouseX, mouseY);
        }
    }

    protected void renderTankGauge(GuiGraphics guiGraphics, int beginX, int beginY, int texture_width, int texture_height) {
        guiGraphics.blit(MODULES, leftPos + beginX, topPos + beginY, 0, 0, texture_width, texture_height);
    }

    protected void renderTankWithGaugeAndToolTip(GuiGraphics guiGraphics, IFluidTank tank, int mouthX, int mouthY, int x, int y, int width, int height) {
        renderFluidTank(guiGraphics, tank, x, y, width, height);
        renderTankGauge(guiGraphics, x, y, width, height);
        renderFluidTankTooltip(guiGraphics, tank, mouthX, mouthY, x, y, width, height);
    }

    protected void renderDefaultEnergyBarWithTip(GuiGraphics guiGraphics, FEContainer container, int beginX, int beginY, int mouseX, int mouseY) {
        renderModule(guiGraphics, beginX, beginY, 243, 70, 5, calculateBarPixel(container, 70));
        renderEnergyBarTooltip(guiGraphics, container, mouseX, mouseY, beginX, beginY - 70, 5, 70);
    }

    protected void renderModule(GuiGraphics guiGraphics, int beginX, int beginY, int u, int v, int texture_width, int texture_height) {
        guiGraphics.blit(MODULES, leftPos + beginX, topPos + beginY, u, v, texture_width, texture_height);
    }

    protected void initBase(GuiGraphics guiGraphics) {
        renderBackground(guiGraphics);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        guiGraphics.blit(TEXTURES, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected int calculateBarPixel(IntegerContainer container, float pixel) {
        int current = container.getCurrent();
        int max = container.getMax();
        return -Math.round(pixel * (float) current / (float) max);
    }

}
