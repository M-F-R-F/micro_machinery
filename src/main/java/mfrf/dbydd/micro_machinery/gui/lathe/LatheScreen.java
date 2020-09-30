package mfrf.dbydd.micro_machinery.gui.lathe;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LatheScreen extends ScreenBase<LatheContainer> {
    public LatheScreen(LatheContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(Micro_Machinery.NAME, "textures/gui/lathe.png"), 176, 182);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        initBase();
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    protected void init() {
        super.init();
        drawbutton(65, 46, 14, 14, "", 214, 126, 214, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.TURNING);
            container.getLathe().markDirty2();
        });// turning
        drawbutton(81, 46, 14, 14, "", 228, 126, 228, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.GRINDING);
            container.getLathe().markDirty2();
        });// grinding
        drawbutton(97, 46, 14, 14, "", 242, 126, 242, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.PLANING);
            container.getLathe().markDirty2();
        });// planing
        drawbutton(65, 62, 14, 14, "", 172, 126, 172, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.BORING);
            container.getLathe().markDirty2();
        });// boring
        drawbutton(81, 62, 14, 14, "", 186, 126, 186, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.DRILLING);
            container.getLathe().markDirty2();
        });// drilling
        drawbutton(97, 62, 14, 14, "", 200, 126, 200, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.MILLING);
            container.getLathe().markDirty2();
        });// milling

        // TODO: 9/30/2020 make it could use 
    }
}
