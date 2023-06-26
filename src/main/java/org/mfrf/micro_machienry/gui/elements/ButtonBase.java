package org.mfrf.micro_machienry.gui.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class ButtonBase extends Button {
    protected final ResourceLocation MODULES = new ResourceLocation(Micro_Machinery.NAME, "textures/gui/module.png");
    private final String name;
    private ScreenBase screen;
    private int holdTextureX;
    private int holdTextureY;
    private int textureX;
    private int textureY;

    public ButtonBase(int x, int y, int width, int height, String buttonText, String name, int holdTextureX, int holdTextureY, int textureX, int textureY, IPressable onPress, ScreenBase screen) {
        super(x, y, width, height, buttonText, onPress);
        this.holdTextureX = holdTextureX;
        this.holdTextureY = holdTextureY;
        this.textureX = textureX;
        this.textureY = textureY;
        this.name = name;
        this.screen = screen;
    }

    private boolean isPressable(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(MODULES);
            FontRenderer fontrenderer = mc.fontRenderer;
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (isPressable(mouseX, mouseY)) {
                blit(this.x, this.y, holdTextureX, holdTextureY, this.width, this.height);
            } else {
                blit(this.x, this.y, textureX, textureY, this.width, this.height);
            }
            if (isMouseOver(mouseX, mouseY)) {
                screen.renderButtonToolTip = () -> {
                    screen.renderTooltip(I18n.format(this.name), mouseX, mouseY);
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
