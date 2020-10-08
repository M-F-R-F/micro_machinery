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
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Random;

public class TileLathe extends MMTileBase implements INamedContainerProvider {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private ActionContainer actionContainer = new ActionContainer() {
        @Override
        public void addStep(Action action) {
            super.addStep(action);
            markDirty2();
        }
    };
    private FEContainer FEContainer = new FEContainer(0, 25600) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int i = super.receiveEnergy(maxReceive, simulate);
            markDirty2();
            return i;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int i = super.extractEnergy(maxExtract, simulate);
            markDirty2();
            return i;
        }
    };
    private IntegerContainer wasteMaterialValueConatiner = new IntegerContainer(0, 100);
    private LatheRecipe.SubRecipe recipe = LatheRecipe.SubRecipe.createEmptyRecipe();
    private ItemStackHandler itemHander = new ItemStackHandler(2) {
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            super.setStackInSlot(slot, stack);
            resetEveryThing();
            checkRecipeType();
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            ItemStack itemStack = super.insertItem(slot, stack, simulate);
            resetEveryThing();
            return itemStack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack itemStack = super.extractItem(slot, amount, simulate);
            checkRecipeType();
            return itemStack;
        }
    };

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

    public LatheRecipe.SubRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fe_container", FEContainer.serializeNBT());
        compound.put("waste_material_container", wasteMaterialValueConatiner.serializeNBT());
        compound.put("item_handler", itemHander.serializeNBT());
        compound.put("action_container", actionContainer.serializeNBT());
        compound.put("recipe_in_progress", recipe.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        FEContainer.deserializeNBT(compound.getCompound("fe_container"));
        wasteMaterialValueConatiner.deserializeNBT(compound.getCompound("waste_material_container"));
        itemHander.deserializeNBT(compound.getCompound("item_handler"));
        actionContainer.deserializeNBT(compound.getCompound("action_container"));
        recipe.deserializeNBT(compound.getCompound("recipe_in_progress"));
        super.read(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        if (!blockState.get(BlockLathe.IS_PLACEHOLDER) && side == blockState.get(BlockLathe.FACING).rotateY()) {
            return LazyOptional.of(() -> this.FEContainer).cast();
        }
        return super.getCapability(cap, side);
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
//        if (checkRecipeType()) {
            ItemStack result = getRecipeResult();
            ItemStack stackInSlot = itemHander.getStackInSlot(RESULT_SLOT);
            itemHander.getStackInSlot(INPUT_SLOT).shrink(1);
            if (stackInSlot.isEmpty() || (stackInSlot.isItemEqual(result) && stackInSlot.getMaxStackSize() >= stackInSlot.getCount() + result.getCount() && itemHander.getSlotLimit(RESULT_SLOT) >= stackInSlot.getCount() + result.getCount())) {
                itemHander.insertItem(RESULT_SLOT, result, false);
            } else {
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), result));
            }
            resetEveryThing();
            markDirty2();
//        }
    }

    public ItemStack getRecipeResult() {
        return recipe.getResult();
    }

    public boolean checkRecipeType() {
        if (!world.isRemote()) {
            ItemStack stackInSlot = itemHander.getStackInSlot(INPUT_SLOT);
            LatheRecipe recipeType = LatheRecipesWorldSavedData.getRecipeType(world, stackInSlot);
            if (recipeType != null) {
                LatheRecipe.SubRecipe subRecipeGet = recipeType.getSubRecipe(stackInSlot);
                if (!subRecipeGet.equals(this.recipe)) {
                    resetEveryThing();
                    this.recipe = subRecipeGet;
                    markDirty2();
                    return true;
                }
            } else {
                resetEveryThing();
                return false;
            }
        }
        return true;
    }

    private void getAction(Action action) {
        if (checkRecipeType() && FEContainer.getCurrent() >= 6400) {
            FEContainer.add(-6400, false);
            wasteMaterialValueConatiner.add(action.getWasteValue(), false);
            actionContainer.addStep(action);
            if (recipe.getWasteValueNeed() == wasteMaterialValueConatiner.getCurrent()) {
                if (actionContainer.test(recipe.getAction1(), recipe.getAction2())) {
                    finishCraft();
                }
            }
            if (wasteMaterialValueConatiner.atMaxValue()) {
                resetEveryThing();
                itemHander.getStackInSlot(0).shrink(1);
                markDirty2();
            }
        }
    }

    @Override
    public void handleNetWorkSyncFromClient(CompoundNBT tag) {
        if (tag.contains("action", Constants.NBT.TAG_STRING)) {
            getAction(Action.valueOf(tag.getString("action")));
        }
    }

    private void resetEveryThing() {
        wasteMaterialValueConatiner.resetValue();
        recipe = LatheRecipe.SubRecipe.createEmptyRecipe();
        actionContainer.reset();
        markDirty2();
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
