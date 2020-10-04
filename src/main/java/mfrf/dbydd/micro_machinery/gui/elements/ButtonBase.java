package mfrf.dbydd.micro_machinery.gui.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class ButtonBase extends Button {
    protected final ResourceLocation MODULES = new ResourceLocation(Micro_Machinery.NAME, "textures/gui/module.png");

    public ButtonBase(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
        super(widthIn, heightIn, width, height, text, onPress);
    }

    private boolean isPressable(double mouseX, double mouseY)
    {
        return this.active&&this.visible&&mouseX >= this.x&&mouseY >= this.y&&mouseX < this.x+this.width&&mouseY < this.y+this.height;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if(this.visible){
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(MODULES);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.isHovered = isPressable(mouseX, mouseY);
        }
    }
}
