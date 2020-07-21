package com.dbydd.micro_machinery.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenBase<T extends Container> extends ContainerScreen<T> {

    private final ResourceLocation TEXTURES;
    private final int textureHeight;
    private final int textureWidth;

    public ScreenBase(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation TEXTURES, int textureWidth, int textureHeight) {
        super(screenContainer, inv, titleIn);
        this.TEXTURES = TEXTURES;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.blendColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
    }
}
