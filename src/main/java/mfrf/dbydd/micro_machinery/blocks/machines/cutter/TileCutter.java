package mfrf.dbydd.micro_machinery.blocks.machines.cutter;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.cutter.CutterContainer;
import mfrf.dbydd.micro_machinery.items.SawBladeBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileCutter extends MMTileBase implements ITickableTileEntity, IItemHandler, INamedContainerProvider {
    private ItemStackHandler sawBladeHandler = new ItemStackHandler(1);
    private ItemStackHandler itemHandler = new ItemStackHandler(2);
    private IntegerContainer progress = new IntegerContainer();
    private ItemStack result = ItemStack.EMPTY;
    private FEContainer energyContainer = new FEContainer(0, 40000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }

        @Override
        public int selfSubtract() {
            return minus(128, true) == 128 ? minus(128, false) : 0;
        }
    };

    public TileCutter() {
        super(RegisteredTileEntityTypes.TILE_CUTTER.get());
    }

    public ItemStackHandler getSawBladeHandler() {
        return sawBladeHandler;
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    public FEContainer getEnergyContainer() {
        return energyContainer;
    }

    public boolean working(){
        return !getProgress().atMinValue() && result != ItemStack.EMPTY;
    }

    @Override
    public CompoundNBT write(CompoundNBT compoundNBT) {
        compoundNBT.put("saw_blade", sawBladeHandler.serializeNBT());
        compoundNBT.put("item_handler", itemHandler.serializeNBT());
        compoundNBT.put("progress", progress.serializeNBT());
        compoundNBT.put("energy", energyContainer.serializeNBT());
        if (!result.isEmpty()) {
            compoundNBT.put("result", result.serializeNBT());
        }
        return super.write(compoundNBT);
    }

    @Override
    public void read(CompoundNBT compoundNBT) {
        super.read(compoundNBT);
        sawBladeHandler.deserializeNBT(compoundNBT.getCompound("saw_blade"));
        itemHandler.deserializeNBT(compoundNBT.getCompound("item_handler"));
        progress.deserializeNBT(compoundNBT.getCompound("progress"));
        energyContainer.deserializeNBT(compoundNBT.getCompound("energy"));
        if (compoundNBT.contains("result")) {
            result = ItemStack.read(compoundNBT.getCompound("result"));
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (!result.isEmpty()) {

                if (progress.atMaxValue() && result == ItemStack.EMPTY) {

                    if (itemHandler.insertItem(1, result, true).isEmpty()) {
                        itemHandler.insertItem(1, result, false);
                        result = ItemStack.EMPTY;
                        progress.resetValue();
                        world.setBlockState(pos, world.getBlockState(pos).with(BlockCutter.WORKING, false));
                    } else {
                        progress.selfSubtract();
                    }

                    markDirty();
                } else {

                    if (energyContainer.selfSubtract() == 128) {
                        progress.selfAdd();
                        markDirty();
                    }

                }

            } else if (!sawBladeHandler.getStackInSlot(0).isEmpty()) {
                CutterRecipe cutterRecipe = RecipeHelper.getCutterRecipe(itemHandler.getStackInSlot(0), world.getRecipeManager());
                if (cutterRecipe != null) {
                    progress.setMax(((int) (cutterRecipe.getTickUse() * ((SawBladeBase) sawBladeHandler.getStackInSlot(0).getItem()).getCombinedSawEfficiency().get())));
                    result = cutterRecipe.getOutput();
                    world.setBlockState(pos, world.getBlockState(pos).with(BlockCutter.WORKING, true));
                    markDirty();
                    //todo gui
                }
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == getBackDirection() && cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> energyContainer).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side != getBackDirection()) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public int getSlots() {
        return 2;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int i) {
        return itemHandler.getStackInSlot(i);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int i, @Nonnull ItemStack itemStack, boolean b) {
        if (i == 0) {
            return itemHandler.insertItem(0, itemStack, b);
        } else return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int i, int i1, boolean b) {
        if (i == 1) {
            return itemHandler.extractItem(1, i1, b);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int i) {
        return itemHandler.getSlotLimit(i);
    }

    @Override
    public boolean isItemValid(int i, @Nonnull ItemStack itemStack) {
        return itemHandler.isItemValid(i, itemStack);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cutter_gui");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new CutterContainer(i, playerInventory, this.pos, this.world);
    }
}
