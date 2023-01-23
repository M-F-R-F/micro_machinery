package mfrf.micro_machinery.blocks.machines.single_block_machines.cutter;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.cutter.CutterContainer;
import mfrf.micro_machinery.items.SawBladeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslatableComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileCutter extends MMTileBase implements ITickableBlockEntity, IItemHandler, INamedContainerProvider {
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

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            markDirty2();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            markDirty2();
            return super.extractEnergy(maxExtract, simulate);
        }
    };

    public TileCutter() {
        super(RegisteredBlockEntityTypes.TILE_CUTTER.get());
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
    public CompoundTag write(CompoundTag compoundNBT) {
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
    public void read(CompoundTag compoundNBT) {
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
        if (!world.isClientSide()) {
            if (!result.isEmpty()) {

                if (progress.atMaxValue()) {

                    if (itemHandler.insertItem(1, result, true).isEmpty()) {
                        itemHandler.insertItem(1, result, false);
                        result = ItemStack.EMPTY;
                        progress.resetValue();
                        world.setBlockState(pos, world.getBlockState(pos).setValue(BlockCutter.WORKING, false));
                    } else {
                        progress.selfSubtract();
                    }

                    markDirty2();
                } else {

                    if (energyContainer.selfSubtract() == 128) {
                        progress.selfAdd();
                        markDirty2();
                    }

                }

            } else if (!sawBladeHandler.getStackInSlot(0).isEmpty()) {
                CutterRecipe cutterRecipe = RecipeHelper.getCutterRecipe(itemHandler.getStackInSlot(0), world.getRecipeManager());
                if (cutterRecipe != null) {
                    progress.setMax(((int) (cutterRecipe.getTickUse() * ((SawBladeBase) sawBladeHandler.getStackInSlot(0).getItem()).getCombinedSawEfficiency().get())));
                    result = cutterRecipe.getOutput().copy();
                    itemHandler.getStackInSlot(0).shrink(cutterRecipe.getInput().getCount());
                    world.setBlockState(pos, world.getBlockState(pos).setValue(BlockCutter.WORKING, true));
                    setChanged();
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
        return new TranslatableComponent("cutter_gui");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, Player playerEntity) {
        return new CutterContainer(i, playerInventory, this.pos, this.world);
    }
}
