package mfrf.micro_machinery.gui.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ButtonBase extends Button {
    protected final ResourceLocation MODULES = new ResourceLocation(MicroMachinery.MODID, "textures/gui/module.png");
    private final String name;
    private final ScreenBase screen;
    private final int holdTextureX;
    private final int holdTextureY;
    private final int textureX;
    private final int textureY;

    public ButtonBase(int x, int y, int width, int height, Component buttonText, String name, int holdTextureX, int holdTextureY, int textureX, int textureY, OnPress onPress, ScreenBase screen) {
        super(x, y, width, height, buttonText, onPress, DEFAULT_NARRATION);
        this.holdTextureX = holdTextureX;
        this.holdTextureY = holdTextureY;
        this.textureX = textureX;
        this.textureY = textureY;
        this.name = name;
        this.screen = screen;
    }

    private boolean isPressable(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if (isPressable(mouseX, mouseY)) {
                guiGraphics.blit(MODULES, this.getX(), this.getY(), holdTextureX, holdTextureY, this.width, this.height);
            } else {
                guiGraphics.blit(MODULES, this.getX(), this.getY(), textureX, textureY, this.width, this.height);
            }
            if (isMouseOver(mouseX, mouseY)) {
                screen.renderButtonToolTip = () -> {
                    guiGraphics.renderTooltip(screen.getMinecraft().font, Component.translatable(this.name), mouseX, mouseY);
                    screen.renderButtonToolTip = null;
                };
            }
        }
    }

    @Override
    public void onClick(double p_onClick_1_, double p_onClick_3_) {
        super.onClick(p_onClick_1_, p_onClick_3_);
    }

}
