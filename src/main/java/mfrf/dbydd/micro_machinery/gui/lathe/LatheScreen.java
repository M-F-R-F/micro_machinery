package mfrf.dbydd.micro_machinery.gui.lathe;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.gui.ScreenBase;
import mfrf.dbydd.micro_machinery.gui.elements.ButtonBase;
import mfrf.dbydd.micro_machinery.network.tile_sync_to_server.TileClientToServerSyncChannel;
import mfrf.dbydd.micro_machinery.network.tile_sync_to_server.TileClientToServerSyncPackage;
import mfrf.dbydd.micro_machinery.recipes.lathe.LatheRecipe;
import mfrf.dbydd.micro_machinery.utils.ActionContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
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
        TileLathe lathe = container.getLathe();

        renderActionsNeeded(lathe.getRecipe());
        renderAction(lathe.getActionContainer());
        renderDefaultEnergyBarWithTip(lathe.getFEContainer(), 8, 90, p_render_1_, p_render_2_);
        renderWasteValueBar(lathe.getRecipe(), lathe.getWasteMaterialValueConatiner().getCurrent());

        renderHoveredToolTip(p_render_1_, p_render_2_);

        if (renderButtonToolTip != null) {
            renderButtonToolTip.invoke();
        }
    }

    @Override
    protected void init() {
        super.init();

        addButton(new ButtonBase(this.guiLeft + 65, this.guiTop + 46, 14, 14, "", "button.action.turning", 214, 126, 214, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.TURNING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));

        addButton(new ButtonBase(this.guiLeft + 81, this.guiTop + 46, 14, 14, "", "button.action.grinding", 228, 126, 228, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.GRINDING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));

        addButton(new ButtonBase(this.guiLeft + 97, this.guiTop + 46, 14, 14, "", "button.action.planing", 242, 126, 242, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.PLANING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));

        addButton(new ButtonBase(this.guiLeft + 65, this.guiTop + 62, 14, 14, "", "button.action.boring", 172, 126, 172, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.BORING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));

        addButton(new ButtonBase(this.guiLeft + 81, this.guiTop + 62, 14, 14, "", "button.action.drilling", 186, 126, 186, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.DRILLING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));

        addButton(new ButtonBase(this.guiLeft + 97, this.guiTop + 62, 14, 14, "", "button.action.milling", 200, 126, 200, 112, button -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("action", TileLathe.Action.MILLING.name());
            TileClientToServerSyncChannel.INSTANCE.sendToServer(new TileClientToServerSyncPackage(compoundNBT, container.getLathe().getPos()));
        }, this));
    }

    private void renderActionsNeeded(LatheRecipe.SubRecipe recipe) {
        TileLathe.Action action1 = recipe.getAction1();
        TileLathe.Action action2 = recipe.getAction2();
        ActionToUV actionToUV1 = ActionToUV.get(action1);
        ActionToUV actionToUV2 = ActionToUV.get(action2);

        if (actionToUV1 != null) {
            renderModule(81, 8, actionToUV1.u, actionToUV1.v, 14, 14);
        }

        if (actionToUV2 != null) {
            renderModule(98, 8, actionToUV2.u, actionToUV2.v, 14, 14);
        }

    }

    private void renderAction(ActionContainer container) {
        ActionToUV actionToUV1 = ActionToUV.get(container.getAction1());
        ActionToUV actionToUV2 = ActionToUV.get(container.getAction2());
        ActionToUV actionToUV3 = ActionToUV.get(container.getAction3());

        if (actionToUV1 != null) {
            renderModule(64, 28, actionToUV1.u, actionToUV1.v, 14, 14);
        }

        if (actionToUV2 != null) {
            renderModule(81, 28, actionToUV2.u, actionToUV2.v, 14, 14);
        }

        if (actionToUV3 != null) {
            renderModule(98, 28, actionToUV3.u, actionToUV3.v, 14, 14);
        }
    }

    private void renderWasteValueBar(LatheRecipe.SubRecipe recipe, int current){
        renderModule(35 + calculateWasteValueBar(recipe.getWasteValueNeed()),81, 167,130,5,5);
        renderModule(35 + calculateWasteValueBar(current),92, 167,135,5,5);
    }

    private int calculateWasteValueBar(float num) {
        return Math.round(100f * (num / 100f));
    }

    private enum ActionToUV {
        DRILLING(TileLathe.Action.DRILLING, 186, 140),
        TURNING(TileLathe.Action.TURNING, 214, 140),
        MILLING(TileLathe.Action.MILLING, 200, 140),
        GRINDING(TileLathe.Action.GRINDING, 228, 140),
        PLANING(TileLathe.Action.PLANING, 242, 140),
        BORING(TileLathe.Action.BORING, 172, 140);

        private final TileLathe.Action key;
        private final int u;
        private final int v;

        ActionToUV(TileLathe.Action key, int u, int v) {
            this.key = key;
            this.u = u;
            this.v = v;
        }

        public static ActionToUV get(TileLathe.Action action) {
            if (action != TileLathe.Action.EMPTY) {
                for (ActionToUV value : values()) {
                    if (value.key == action) return value;
                }
            }
            return null;
        }
    }

}
