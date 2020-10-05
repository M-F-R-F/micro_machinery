package mfrf.dbydd.micro_machinery.blocks.machines.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.lathe.LatheContainer;
import mfrf.dbydd.micro_machinery.recipes.lathe.LatheRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.ActionContainer;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.dbydd.micro_machinery.world_saved_data.LatheRecipesWorldSavedData;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Random;

public class TileLathe extends MMTileBase implements INamedContainerProvider {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private ActionContainer actionContainer = new ActionContainer();
    private FEContainer FEContainer = new FEContainer(0, 25600) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    };
    private IntegerContainer wasteMaterialValueConatiner = new IntegerContainer(0, 100);
    private ItemStackHandler itemHander = new ItemStackHandler(2);
    private LatheRecipe recipe = null;

    public TileLathe() {
        super(Registered_Tileentitie_Types.TILE_LATHE.get());
    }

    public mfrf.dbydd.micro_machinery.utils.FEContainer getFEContainer() {
        return FEContainer;
    }

    public IntegerContainer getWasteMaterialValueConatiner() {
        return wasteMaterialValueConatiner;
    }

    public ItemStackHandler getItemHander() {
        return itemHander;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fe_container", FEContainer.serializeNBT());
        compound.put("waste_material_container", wasteMaterialValueConatiner.serializeNBT());
        compound.put("item_handler", itemHander.serializeNBT());
        compound.put("action_container", actionContainer.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        FEContainer.deserializeNBT(compound.getCompound("fe_container"));
        wasteMaterialValueConatiner.deserializeNBT(compound.getCompound("waste_material_container"));
        itemHander.deserializeNBT(compound.getCompound("item_handler"));
        actionContainer.deserializeNBT(compound.getCompound("action_container"));
        super.read(compound);
    }

    @Override
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.LATHE.getNameTextComponent();
    }

    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new LatheContainer(sycID, inventory, this.pos, this.world);
    }

    public ActionContainer getActionContainer() {
        return actionContainer;
    }

    public void finishCraft() {
        LatheRecipe recipeType = checkRecipeType();
        ItemStack result = getRecipeResult();
        ItemStack stackInSlot = itemHander.getStackInSlot(RESULT_SLOT);
        itemHander.getStackInSlot(INPUT_SLOT).shrink(1);
        if (stackInSlot.isEmpty() || (stackInSlot.isItemEqual(result) && stackInSlot.getMaxStackSize() >= stackInSlot.getCount() + result.getCount() && itemHander.getSlotLimit(RESULT_SLOT) >= stackInSlot.getCount() + result.getCount())) {
            itemHander.insertItem(RESULT_SLOT, result, false);
            markDirty();
        } else {
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), result));
        }
    }

    public ItemStack getRecipeResult() {
        LatheRecipe recipeType = checkRecipeType();
        return recipeType == null ? ItemStack.EMPTY : recipeType.getResult(itemHander.getStackInSlot(INPUT_SLOT));
    }

    public LatheRecipe checkRecipeType() {
            ItemStack stackInSlot = itemHander.getStackInSlot(INPUT_SLOT);
            if (!stackInSlot.isEmpty()) {
                return LatheRecipesWorldSavedData.getRecipeType(world, stackInSlot);
            }
            return null;
            //todo 重写
    }

    private void getAction(Action action) {
        LatheRecipe recipeType = checkRecipeType();
        if (recipeType != null) {
            wasteMaterialValueConatiner.add(action.getWasteValue(), false);
            actionContainer.addStep(action);
            if (recipeType.getWasteValueNeeded() == wasteMaterialValueConatiner.getCurrent()) {
                if (actionContainer.test(recipeType.getAction1(), recipeType.getAction2())) {
                    finishCraft();
                }
            }
            if (wasteMaterialValueConatiner.atMaxValue()) {
                wasteMaterialValueConatiner.resetValue();
                itemHander.getStackInSlot(0).shrink(1);
                actionContainer.reset();
            }
            markDirty();
        }
    }

    @Override
    public void handleNetWorkSyncFromClient(CompoundNBT tag) {
        if (tag.contains("action", Constants.NBT.TAG_STRING)) {
            getAction(Action.valueOf(tag.getString("action")));
        }
    }

    public enum Action {
        DRILLING(5),
        TURNING(10),
        MILLING(7),
        GRINDING(2),
        PLANING(4),
        BORING(3),
        EMPTY(0);

        private final int wasteValue;

        Action(int wasteValue) {
            this.wasteValue = wasteValue;
        }

        public static Action random(Random random) {

            Action value = Action.values()[random.nextInt(Action.values().length)];
            while (value == EMPTY) {
                value = Action.values()[random.nextInt(Action.values().length)];
            }

            return value;
        }

        public int getWasteValue() {
            return wasteValue;
        }
    }

}
