package com.dbydd.micro_machinery.gui.ForgingAnvil;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.gui.GuiBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIElementryForgingAnvil extends GuiBase {
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/anvil.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    ;

    public GUIElementryForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileEntity) {
        super(new ContainerElementryForgingAnvil(player, tileEntity), tileEntity, TEXTURES);
        this.xSize = 176;
        this.ySize = 160;
    }

}
