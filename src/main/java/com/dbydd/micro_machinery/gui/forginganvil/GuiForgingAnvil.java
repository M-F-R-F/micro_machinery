package com.dbydd.micro_machinery.gui.forginganvil;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.gui.GuiBase;
import com.dbydd.micro_machinery.network.AnvilButtonEventPackage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiForgingAnvil extends GuiBase<TileEntityForgingAnvil> {
    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/anvil.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);

    public GuiForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileentity) {
        super(new ContainerForgingAnvil(player, tileentity), tileentity, TEXTURES);
        this.xSize = 176;
        this.ySize = 160;
    }


    @Override
    public void initGui() {
        super.initGui();
        drawbutton(0, this.guiLeft + 101, this.guiTop + 14, 20, 20, "", 176, 14, 101, 14);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        this.tileentity.forge();
        Micro_Machinery.getNetwork().sendToServer(new AnvilButtonEventPackage(tileentity.writeToNBT(new NBTTagCompound()), this.tileentity.getPos(), tileentity.getWorld().provider.getDimension()));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format(tileentity.getBlockType().getLocalizedName());
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
        int forgetime = tileentity.getField("forgetime");
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        for (int i = 0; i < forgetime; i++) {
            renderProgressBar(84 + i * 20, 42, 176, 42, 14, 4);
        }
    }

    private int getForgeTime() {
        return tileentity.getField("forgetime");
    }
}
