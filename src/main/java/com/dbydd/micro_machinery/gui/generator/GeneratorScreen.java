package com.dbydd.micro_machinery.gui.generator;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.mathines.generator.TileGenerator;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.gui.ScreenBase;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GeneratorScreen extends ScreenBase<GeneratorContainer> {
    public GeneratorScreen(GeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/firegenerator.png"), 176, 178);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        TileGenerator generator = container.getGenerator();
        IIntArray intArray = container.getIntArray();
        initBase();
        if(generator.isBurning()){

        }
        super.render(mouseX, mouseY, partialTicks);
        renderFluidTank(generator.getTank(), 17,75, 16,60);
        renderTankGauage(17,15,16,60);
        renderFluidTankTooltip(generator.getTank(),mouseX, mouseY, 17,15, 16,60);
        renderHoveredToolTip(mouseX, mouseY);
    }

    private enum GeneratorArrayEnum {
        MAX_BURN_TIME(0),
        CURRENT_BURN_TIME(1),
        CURRENT_MELTTIME(2),
        MELT_TIME(3);


        private final int num;

        GeneratorArrayEnum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
