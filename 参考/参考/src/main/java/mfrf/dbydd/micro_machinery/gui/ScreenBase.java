package mfrf.dbydd.micro_machinery.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.interfaces.Consumer;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Arrays;

public class ScreenBase<T extends Container> extends ContainerScreen<T> {

    protected final ResourceLocation MODULES = new ResourceLocation(Micro_Machinery.NAME, "textures/gui/module.png");
    protected final ResourceLocation TEXTURES;
    private final T screenContainer;
    public Consumer renderButtonToolTip = null;

    public ScreenBase(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation TEXTURES, int textureWidth, int textureHeight) {
        super(screenContainer, inv, titleIn);
        this.TEXTURES = TEXTURES;
        this.xSize = textureWidth;
        this.ySize = textureHeight;
        this.screenContainer = screenContainer;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.blendColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
    }

    protected void renderFluidTank(IFluidTank tank, int x, int y, int tankWidth, int tankHeight) {
        if (tank == null || tank.getFluid().isEmpty()) {
            return;
        }
        FluidAttributes attributes = tank.getFluid().getFluid().getAttributes();
        int color = attributes.getColor(tank.getFluid());
        TextureAtlasSprite fluidSprite = this.minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(attributes.getStillTexture());
        RenderSystem.color4f((color >> 16 & 255) / 255.0f, (color >> 8 & 255) / 255.0f, (color & 255) / 255.0f, (color >> 24 & 255) / 255.0f);
        this.minecraft.getTextureManager().bindTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
        int scaledHeight = Math.round((float) tankHeight * ((float) tank.getFluid().getAmount() / (float) tank.getCapacity()));
        renderFluid(scaledHeight, guiLeft + x, guiTop + y, tankWidth, fluidSprite);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderFluid(int scaledHeight, int beginx, int beginy, int tankWidth, TextureAtlasSprite fluidSprite) {
        int heightLayerTarget = scaledHeight / 16;
        int i = scaledHeight - heightLayerTarget * 16;
        for (int heightLayer = 0; heightLayer < heightLayerTarget; heightLayer++) {
            blit(beginx, beginy - 16 * heightLayer, 0, tankWidth, -16, fluidSprite);
        }
        blit(beginx, beginy - 16 * (heightLayerTarget), 0, tankWidth, -i, fluidSprite);
    }

    protected void renderFluidTankTooltip(IFluidTank tank, int mouthx, int mouthy, int x, int y, int tankWidth, int tankHeight) {
        FluidStack fluid = tank.getFluid();
        int amount = tank.getFluidAmount();
        int max = tank.getCapacity();
        if (!fluid.isEmpty() && (mouthy - (guiTop + y)) <= tankHeight && (mouthy - (guiTop + y)) >= 0 && (mouthx - (guiLeft + x)) <= tankWidth && (mouthx - (guiLeft + x)) >= 0) {
            String name = fluid.getDisplayName().getString();
            String[] info = new String[]{I18n.format("gui.fluid.name", name), TextFormatting.DARK_GRAY + I18n.format("gui.fluid.amount", amount, max)};
            this.renderTooltip(Arrays.asList(info), mouthx, mouthy);
        }
    }

    protected void renderEnergyBarTooltip(FEContainer container, int mouseX, int mouseY, int x, int y, int barWidth, int barHeight) {
        if ((mouseY - (guiTop + y)) <= barHeight && (mouseY - (guiTop + y)) >= 0 && (mouseX - (guiLeft + x)) <= barWidth && (mouseX - (guiLeft + x)) >= 0) {
            int current = container.getCurrent();
            int max = container.getMaxEnergyStored();
            this.renderTooltip(current + "/" + max + " FE", mouseX, mouseY);
        }
    }

    protected void renderTankGauge(int beginX, int beginY, int texture_width, int texture_height) {
        this.minecraft.getTextureManager().bindTexture(MODULES);
        blit(guiLeft + beginX, guiTop + beginY, 0, 0, texture_width, texture_height);
    }

    protected void renderTankWithGaugeAndToolTip(IFluidTank tank, int mouthX, int mouthY, int x, int y, int width, int height) {
        renderFluidTank(tank, x, y, width, height);
        renderTankGauge(x, y, width, height);
        renderFluidTankTooltip(tank, mouthX, mouthY, x, y, width, height);
    }

    protected void renderDefaultEnergyBarWithTip(FEContainer container, int beginX, int beginY, int mouseX, int mouseY) {
        renderModule(beginX, beginY, 243, 70, 5, calculateBarPixel(container, 70));
        renderEnergyBarTooltip(container, mouseX, mouseY, beginX, beginY - 70, 5, 70);
    }

    protected void renderModule(int beginX, int beginY, int u, int v, int texture_width, int texture_height) {
        this.minecraft.getTextureManager().bindTexture(MODULES);
        blit(guiLeft + beginX, guiTop + beginY, u, v, texture_width, texture_height);
    }

    protected void initBase() {
        renderBackground();
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    protected int calculateBarPixel(IntegerContainer container, float pixel) {
        int current = container.getCurrent();
        int max = container.getMax();
        return -Math.round(pixel * (float) current / (float) max);
    }

}
