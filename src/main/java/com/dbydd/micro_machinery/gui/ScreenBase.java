package com.dbydd.micro_machinery.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;

public class ScreenBase<T extends Container> extends ContainerScreen<T> {

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

    protected void renderFluidTank(FluidStack stack, int capacity, int x, int y, int tankWidth, int tankHeight) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        TextureAtlasSprite fluidSprite = this.minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(stack.getFluid().getAttributes().getStillTexture());
        this.minecraft.getTextureManager().bindTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
        int scaledHeight = tankHeight * stack.getAmount() / capacity;
        renderFluid(scaledHeight, x, y, tankWidth, fluidSprite);
    }

    private void renderFluid(int scaledHeight, int beginx, int beginy, int tankWidth, TextureAtlasSprite fluidSprite) {
        int i = scaledHeight / 16;
        int j = scaledHeight % 16;
        for (int i1 = 0; i1 < i; i1++) {
            blit(beginx, beginy - 16 * i1, 0, tankWidth, 16, fluidSprite);
        }
        blit(beginx, beginy-16*(i-1), 0, tankWidth, -j, fluidSprite);
    }
}
