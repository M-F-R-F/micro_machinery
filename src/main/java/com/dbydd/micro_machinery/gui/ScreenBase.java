package com.dbydd.micro_machinery.gui;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Arrays;

public class ScreenBase<T extends Container> extends ContainerScreen<T> {

    protected final ResourceLocation MODULES = new ResourceLocation(Micro_Machinery.NAME, "textures/gui/module.png");
    protected final ResourceLocation TEXTURES;
    protected final int textureHeight;
    protected final int textureWidth;
    private final T screenContainer;

    public ScreenBase(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation TEXTURES, int textureWidth, int textureHeight) {
        super(screenContainer, inv, titleIn);
        this.TEXTURES = TEXTURES;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.screenContainer = screenContainer;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.blendColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
    }

    protected void renderFluidTank(IFluidTank tank, int capacity, int x, int y, int tankWidth, int tankHeight) {
        if (tank == null || tank.getFluid().isEmpty()) {
            return;
        }
        TextureAtlasSprite fluidSprite = this.minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(tank.getFluid().getFluid().getAttributes().getStillTexture());
        this.minecraft.getTextureManager().bindTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
        int scaledHeight = tankHeight * tank.getFluid().getAmount() / capacity;
        renderFluid(scaledHeight, x, y, tankWidth, fluidSprite);
    }

    private void renderFluid(int scaledHeight, int beginx, int beginy, int tankWidth, TextureAtlasSprite fluidSprite) {
        int i = scaledHeight / 16;
        int j = scaledHeight % 16;
        for (int i1 = 0; i1 < i; i1++) {
            blit(beginx, beginy - 16 * i1, 0, tankWidth, 16, fluidSprite);
        }
        blit(beginx, beginy - 16 * (i - 1), 0, tankWidth, -j, fluidSprite);
    }

    protected void renderFluidTankTooltip(final IFluidTank tank, final int mouthx, final int mouthy, final int x, final int y, final int tankWidth, final int tankHeight) {
        FluidStack fluid = tank.getFluid();
        int amount = tank.getFluidAmount();
        int max = tank.getCapacity();
        if (!fluid.isEmpty() && (mouthy - y) <= tankHeight && (mouthy - y) >= 0 && (mouthx - x) <= tankWidth && (mouthx - x) >= 0) {
            String name = fluid.getDisplayName().getString();
            String[] info = new String[]{I18n.format("gui.fluid.name", name), TextFormatting.DARK_GRAY + I18n.format("gui.fluid.amount", amount, max)};
            this.renderTooltip(Arrays.asList(info), mouthx, mouthy);
        }
    }

    protected void renderTankGauage(int beginX, int beginY, int texture_width, int texture_height) {
        this.minecraft.getTextureManager().bindTexture(MODULES);
        blit(beginX, beginY, 0, 0, texture_width, texture_height);
    }

    protected void renderModule(int beginX, int beginY, int u, int v, int texture_width, int texture_height) {
        this.minecraft.getTextureManager().bindTexture(MODULES);
        blit(beginX, beginY, u, v, texture_width, texture_height);
    }

}
