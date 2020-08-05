package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.gui.ScreenBase;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class KlinScreen extends ScreenBase<KlinContainer> {

    public KlinScreen(KlinContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/klin.png"), 176, 166);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        TileKlin klin = container.getKlin();
        renderBackground();
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
//        blit(this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);





        super.render(mouseX, mouseY, partialTicks);
        renderFluidTank(klin.getFluidInTank(0), klin.getTankCapacity(0), guiLeft+152,guiTop+47, 16, 60);
        renderHoveredToolTip(mouseX,mouseY);
    }

}
