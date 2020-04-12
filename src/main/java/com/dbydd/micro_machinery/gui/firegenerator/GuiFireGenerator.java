package com.dbydd.micro_machinery.gui.firegenerator;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiFireGenerator extends GuiBase<TileEntityFireGenerator> {
    //todo
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/位置";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    private int k = 0;

    public GuiFireGenerator(EntityPlayer player, TileEntityFireGenerator tileentity) {
        super(new ContainerFireGenerator(player, tileentity), tileentity, TEXTURES);
        //todo
        xSize = data;
        ySize = data;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }

}
