package mfrf.micro_machinery.gui.generator;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.machines.single_block_machines.generator.TileGenerator;
import mfrf.micro_machinery.gui.ScreenBase;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends ScreenBase<GeneratorContainer> {
    public GeneratorScreen(GeneratorContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/firegenerator.png"), 176, 178);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        TileGenerator generator = menu.getGenerator();
//        IIntArray intArray = container.getIntArray();
        initBase(guiGraphics);
        if (generator.isBurning()) {
            renderModule(guiGraphics, 71, 55, 40, 14, 16, calculateBurnFireHeight());
        }
        renderModule(guiGraphics, 157, 83, 243, 70, 5, calculateEnergyBarHeight());
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderFluidTank(guiGraphics, generator.getTank(), 17, 75, 16, 60);
        renderTankGauge(guiGraphics, 17, 15, 16, 60);
        renderFluidTankTooltip(guiGraphics, generator.getTank(), mouseX, mouseY, 17, 15, 16, 60);
        renderEnergyBarTooltip(guiGraphics, generator.getEnergyContainer(), mouseX, mouseY, 157, 13, 5, 70);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private int calculateBurnFireHeight() {
        IntegerContainer burnTimeContainer = menu.getGenerator().getBurnTimeContainer();
        int currentBurnTime = burnTimeContainer.getCurrent();
        int maxBurnTime = burnTimeContainer.getMax();
        return -16 + Math.round(16f * (float) currentBurnTime / (float) maxBurnTime);
    }

    private int calculateEnergyBarHeight() {
        IntegerContainer energyContainer = menu.getGenerator().getEnergyContainer();
        int currentEnergy = energyContainer.getCurrent();
        int maxEnergy = energyContainer.getMax();
        return -Math.round(70f * (float) currentEnergy / (float) maxEnergy);
    }

}
