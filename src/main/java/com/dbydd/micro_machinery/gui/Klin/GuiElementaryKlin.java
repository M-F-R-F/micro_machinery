package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.GuiBase;
import com.dbydd.micro_machinery.network.KlinButtonEventPackage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiElementaryKlin extends GuiBase<TileEntityKlin> {

    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/klin.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);
    private int k = 0;

    public GuiElementaryKlin(EntityPlayer player, TileEntityKlin tileentity) {
        super(new ContainerElementaryKlin(player, tileentity), tileentity, TEXTURES);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        drawbutton(0, this.guiLeft + 152, this.guiTop + 65, 16, 16, "", 176, 65, 176, 48);
    }

    @Override
    protected void actionPerformed(GuiButton p_actionPerformed_1_) throws IOException {
        tileentity.drain(tileentity.fluidhandler.getFluidAmount(), true);
        NBTTagCompound tag = tileentity.fluidhandler.writeToNBT(new NBTTagCompound());
        this.tileentity.writeToNBT(tag);
        Micro_Machinery.getNetwork().sendToServer(new KlinButtonEventPackage(tag, this.tileentity.getPos(), tileentity.getWorld().provider.getDimension()));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int x = this.guiLeft + 152;
        int y = this.guiTop + 3;
        int tankWidth = 16;
        int tankHeight = 60;
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        if (TileEntityKlin.isBurning(tileentity)) {
            mc.getTextureManager().bindTexture(TEXTURES);
            k = getBurnLeftScaled(13);
            renderProgressBar(this.guiLeft + 82, this.guiTop + 29 - k, 176, 28 - k, 14, k + 1);
        }
        k = getMeltProgressScaled(22);
        renderProgressBar(this.guiLeft + 78, this.guiTop + 31, 176, 31, k, 16);
        renderFluidTank(tileentity.fluidhandler, x, y, tankWidth, tankHeight);
        rendergauage(x, y, 210, 3, tankWidth, tankHeight);
        renderFluidTankTooltip(tileentity.fluidhandler, mouseX, mouseY, x, y, 16, 60);
    }

    private int getBurnLeftScaled(int pixels) {
        int burntime = tileentity.getField(2);
        int maxburntime = tileentity.getField(3);
        if (maxburntime == 0) return 0;
        return pixels - (burntime * pixels / maxburntime);
        //return burntime * pixels / maxburntime;
    }

    private int getMeltProgressScaled(int pixels) {
        int maxmelttime = tileentity.getField(0);
        int melttime = tileentity.getField(1);
        if (maxmelttime == 0) return 0;
        return melttime * pixels / maxmelttime;
    }


}
