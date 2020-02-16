package com.dbydd.micro_machinery.gui.ForgingAnvil;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GUIElementryForgingAnvil extends GuiBase {
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/anvil.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    ;

    public GUIElementryForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileEntity) {
        super(new ContainerElementryForgingAnvil(player, tileEntity), tileEntity, TEXTURES);
        this.xSize = 176;
        this.ySize = 160;
    }

    @Override
    public void initGui() {
        super.initGui();
        drawbutton(1, this.guiLeft + 101, this.guiTop + 14, 20,20,"",176,14,196,14);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
}
