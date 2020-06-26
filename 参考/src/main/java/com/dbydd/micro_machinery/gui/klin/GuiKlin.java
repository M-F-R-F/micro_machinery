package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.GuiBase;
import com.dbydd.micro_machinery.network.KlinButtonEventPackage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiKlin extends GuiBase<TileEntityKlin> {

    private static final String TEXTURE_BACK = Reference.MODID + ":" + "textures/gui/klin.png";
    private static final ResourceLocation TEXTURES = new ResourceLocation(TEXTURE_BACK);

    public GuiKlin(EntityPlayer player, TileEntityKlin tileentity) {
        super(new ContainerKlin(player, tileentity), tileentity, TEXTURES);
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
        tileentity.drain(tileentity.getFluidhandler().getFluidAmount(), true);
        NBTTagCompound tag = this.tileentity.writeToNBT(new NBTTagCompound());
        Micro_Machinery.getNetwork().sendToServer(new KlinButtonEventPackage(tag, this.tileentity.getPos(), tileentity.getWorld().provider.getDimension()));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int x = this.guiLeft + 152;
        int y = this.guiTop + 3;
        int tankWidth = 16;
        int tankHeight = 60;
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        if (tileentity.isBurning()) {
            mc.getTextureManager().bindTexture(TEXTURES);
            int k = getBurnLeftScaled(13);
            renderProgressBar(this.guiLeft + 82, this.guiTop + 29 - k, 176, 28 - k, 14, k + 1);
        }
        int k = getMeltProgressScaled(22);
        renderProgressBar(this.guiLeft + 78, this.guiTop + 31, 176, 31, k, 16);
        renderFluidTank(tileentity.getFluidhandler(), x, y, tankWidth, tankHeight);
        rendergauage(x, y, 210, 3, tankWidth, tankHeight);
        renderFluidTankTooltip(tileentity.getFluidhandler(), mouseX, mouseY, x, y, 16, 60);
    }

    private int getBurnLeftScaled(int pixels) {
        int burntime = tileentity.getField(2);
        int maxburntime = tileentity.getField(3);
        return pixels - (burntime * pixels / maxburntime);
    }

    private int getMeltProgressScaled(int pixels) {
        int maxmelttime = tileentity.getField(0);
        int melttime = tileentity.getField(1);
        if (maxmelttime == 0) return 0;
        return melttime * pixels / maxmelttime;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("" + tileentity.getBlockType().getLocalizedName());
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 3, 0x000000);
    }

}
