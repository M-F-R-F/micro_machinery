package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.gui.ScreenBase;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
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
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
//        blit(this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        super.render(mouseX, mouseY, partialTicks);
        if (klin.issmelting()) {
            renderModule(guiLeft + 78, guiTop + 31, 70, 0, calcProgressBarWidth(), 16);
        }
        if (klin.isBurning()) {
            renderModule(guiLeft + 82, guiTop + 29, 56, 12, 14, calcBurnProgressBarHeight());
        }
        renderFluidTank(klin.getFluidHandler(), klin.getTankCapacity(0), guiLeft + 152, guiTop + 47, 16, 60);
        renderTankGauage(guiLeft + 152, guiTop + 3, 16, 60);
        renderFluidTankTooltip(klin.getFluidHandler(), mouseX, mouseY, guiLeft + 152, this.guiTop + 3, 16, 60);
        renderHoveredToolTip(mouseX, mouseY);
    }

    private int calcProgressBarWidth() {
        IIntArray intArray = container.getIntArray();
        int currentMeltTime = intArray.get(KlinArrayEnum.CURRENT_MELTTIME.getNum());
        int MaxMeltTime = intArray.get(KlinArrayEnum.MELT_TIME.getNum());
        return Math.round(22f * (float) currentMeltTime / (float) MaxMeltTime);
    }

    private int calcBurnProgressBarHeight() {
        IIntArray intArray = container.getIntArray();
        int currentBurnTime = intArray.get(KlinArrayEnum.CURRENT_BURN_TIME.getNum());
        int maxBurnTime = intArray.get(KlinArrayEnum.MAX_BURN_TIME.getNum());
        return -13 + Math.round(13f * (float) currentBurnTime / (float) maxBurnTime);
    }

    private enum KlinArrayEnum {
        CURRENT_MELTTIME(0),
        MELT_TIME(1),
        CURRENT_BURN_TIME(2),
        MAX_BURN_TIME(3);

        private final int num;

        KlinArrayEnum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
