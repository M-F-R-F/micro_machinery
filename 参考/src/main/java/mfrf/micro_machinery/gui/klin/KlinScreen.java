package mfrf.micro_machinery.gui.klin;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.gui.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.IIntArray;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.DataSlot;

public class KlinScreen extends ScreenBase<KlinContainer> {

    public KlinScreen(KlinContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/klin.png"), 176, 166);
    }

    @Override
    public void m_6305_(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        TileKlin klin = menu.getKlin();
        initBase(pPoseStack);
        super.m_6305_(pPoseStack, mouseX, mouseY, partialTicks);
        if (klin.issmelting()) {
            renderModule(pPoseStack, 78, 31, 70, 0, calcProgressBarWidth(), 16);
        }
        if (klin.isBurning()) {
            renderModule(pPoseStack, 82, 29, 56, 12, 14, calcBurnProgressBarHeight());
        }
        renderFluidTank(pPoseStack, klin.getFluidHandler(), 152, 63, 16, 60);
        renderTankGauge(pPoseStack, 152, 3, 16, 60);
        renderFluidTankTooltip(pPoseStack, klin.getFluidHandler(), mouseX, mouseY, 152, 3, 16, 60);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    private int calcProgressBarWidth() {
        DataSlot intArray = menu.getIntArray();
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
