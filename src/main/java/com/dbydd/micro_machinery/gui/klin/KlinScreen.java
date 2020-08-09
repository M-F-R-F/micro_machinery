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
        initBase();
        super.render(mouseX, mouseY, partialTicks);
        if (klin.issmelting()) {
            renderModule(78, 31, 70, 0, calcProgressBarWidth(), 16);
        }
        if (klin.isBurning()) {
            renderModule(82, 29, 56, 12, 14, calcBurnProgressBarHeight());
        }
        renderFluidTank(klin.getFluidHandler(), 152, 63, 16, 60);
        renderTankGauage(152, 3, 16, 60);
        renderFluidTankTooltip(klin.getFluidHandler(), mouseX, mouseY, 152, 3, 16, 60);
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
