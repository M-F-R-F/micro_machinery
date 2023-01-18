package mfrf.micro_machinery.gui.generator;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GeneratorScreen extends ScreenBase<GeneratorContainer> {
    public GeneratorScreen(GeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(MicroMachinery.MODID, "textures/gui/firegenerator.png"), 176, 178);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        TileGenerator generator = container.getGenerator();
//        IIntArray intArray = container.getIntArray();
        initBase();
        if(generator.isBurning()){
            renderModule(71,55, 40,14, 16,calculateBurnFireHeight());
        }
        renderModule(157,83, 243,70, 5,calculateEnergyBarHeight());
        super.render(mouseX, mouseY, partialTicks);
        renderFluidTank(generator.getTank(), 17, 75, 16, 60);
        renderTankGauge(17, 15, 16, 60);
        renderFluidTankTooltip(generator.getTank(), mouseX, mouseY, 17, 15, 16, 60);
        renderEnergyBarTooltip(generator.getEnergyContainer(), mouseX, mouseY, 157,13,5,70);
        renderHoveredToolTip(mouseX, mouseY);
    }

    private int calculateBurnFireHeight(){
        IntegerContainer burnTimeContainer = container.getGenerator().getBurnTimeContainer();
        int currentBurnTime = burnTimeContainer.getCurrent();
        int maxBurnTime = burnTimeContainer.getMax();
        return -16 + Math.round(16f * (float) currentBurnTime / (float) maxBurnTime);
    }

    private int calculateEnergyBarHeight(){
        IntegerContainer energyContainer = container.getGenerator().getEnergyContainer();
        int currentEnergy = energyContainer.getCurrent();
        int maxEnergy = energyContainer.getMax();
        return -Math.round(70f * (float) currentEnergy / (float) maxEnergy);
    }

}
