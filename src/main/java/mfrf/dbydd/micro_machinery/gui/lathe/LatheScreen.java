package mfrf.dbydd.micro_machinery.gui.lathe;

import com.mojang.blaze3d.systems.RenderSystem;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.client.gui.widget.button.Button;
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
        drawButton(65, 46, 14, 14, "", 214, 126, 214, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.TURNING);
            container.getLathe().markDirty2();
        });// turning
        drawButton(81, 46, 14, 14, "", 228, 126, 228, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.GRINDING);
            container.getLathe().markDirty2();
        });// grinding
        drawButton(97, 46, 14, 14, "", 242, 126, 242, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.PLANING);
            container.getLathe().markDirty2();
        });// planing
        drawButton(65, 62, 14, 14, "", 172, 126, 172, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.BORING);
            container.getLathe().markDirty2();
        });// boring
        drawButton(81, 62, 14, 14, "", 186, 126, 186, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.DRILLING);
            container.getLathe().markDirty2();
        });// drilling
        drawButton(97, 62, 14, 14, "", 200, 126, 200, 112, button -> {
            container.getLathe().getActionContainer().addStep(TileLathe.Action.MILLING);
            container.getLathe().markDirty2();
        });// milling

        addButton(new ActionButton(65,46,214,126,214,112,TileLathe.Action.TURNING));

        // TODO: 9/30/2020 make it could use 
    }

    private class ActionButton extends Button {

        private static final int WIDTH = 14;
        private static final int HEIGHT = 14;
        private final int holdTextureY;
        private final int holdTextureX;
        private final int textureX;
        private final int textureY;

        public ActionButton(int x, int y, int holdTextureX, int holdTextureY, int tectureX, int textureY, TileLathe.Action action) {
            super(x, y, WIDTH, HEIGHT, "", p_onPress_1_ -> {
                container.getIntArray().set(2, action.getWasteValue());
            });
            this.holdTextureX = holdTextureX;
            this.holdTextureY = holdTextureY;
            this.textureX = tectureX;
            this.textureY = textureY;
        }

        @Override
        public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
            RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
            minecraft.getTextureManager().bindTexture(MODULES);
            if (isFocused()) {
                blit(this.x + guiLeft, this.y + guiTop, holdTextureX, holdTextureY, this.WIDTH, this.height);
            } else {
                blit(this.x + guiLeft, this.y + guiTop, textureX, textureY, this.WIDTH, this.height);
            }

        }
    }
}
