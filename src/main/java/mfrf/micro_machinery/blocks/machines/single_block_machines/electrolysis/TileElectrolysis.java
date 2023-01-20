package mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.electrolysis.ElectrolysisContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileElectrolysis extends MMTileBase implements ITickableBlockEntity, IItemHandler, INamedContainerProvider {
    private FEContainer energy = new FEContainer(0, 120000) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            markDirty2();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            markDirty2();
            return super.receiveEnergy(maxReceive, simulate);
        }

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
            return this.minus(1024, false);
        }
    };
    private ItemStackHandler items = new ItemStackHandler(2);
    private IntegerContainer progress = new IntegerContainer();
    private ItemStack result = ItemStack.EMPTY;
    private boolean isWorking = false;

    public TileElectrolysis() {
        super(RegisteredBlockEntityTypes.TILE_ELECTROLYSIS.get());
    }

    public FEContainer getEnergy() {
        return energy;
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && isBackDirection(side)) return LazyOptional.of(() -> energy).cast();
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundTag compound) {
        items.deserializeNBT(compound.getCompound("item"));
        energy.deserializeNBT(compound.getCompound("energy"));
        isWorking = compound.getBoolean("is_working");
        if (compound.contains("progress")) {
            progress = new IntegerContainer();
            progress.deserializeNBT(compound.getCompound("progress"));
        }
        result = ItemStack.read(compound.getCompound("result"));
        super.read(compound);
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag write = super.write(compound);
        write.put("item", items.serializeNBT());
        write.put("energy", energy.serializeNBT());
        write.putBoolean("is_working", isWorking);
        if (progress != null) {
            write.put("progress", progress.serializeNBT());
        }
        write.put("result", result.serializeNBT());
        return write;
    }

    @Override
    public void tick() {
//        if (!world.isRemote()) {
            if (isWorking) {
                if (!progress.atMaxValue()) {
                    if (energy.getCurrent() >= 1024) {
                        energy.selfSubtract();
                        progress.selfAdd();
                        markDirty2();
                    }
                } else {
                    if (items.insertItem(Slot.OUTPUT.index, result, true) == ItemStack.EMPTY) {
                        items.insertItem(Slot.OUTPUT.index, result, false);
                        result = ItemStack.EMPTY;
                        progress.resetValue();
                        isWorking = false;
                        markDirty2();
                    }
                }
            } else {
                ElectrolysisRecipe electrolysisRecipe = RecipeHelper.getElectrolysisRecipe(items.getStackInSlot(Slot.INPUT.index), world.getRecipeManager());
                if (electrolysisRecipe != null) {
                    result = electrolysisRecipe.getOutput();
                    progress = new IntegerContainer(0, electrolysisRecipe.getTime());
                    isWorking = true;
                    markDirty2();
                }
            }
//        }
    }

    @Override
    public int getSlots() {
        return 2;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return items.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == Slot.OUTPUT.index) return stack;
        ItemStack itemStack = items.insertItem(slot, stack, simulate);
        setChanged();
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot == Slot.INPUT.index) return ItemStack.EMPTY;
        ItemStack itemStack = items.extractItem(slot, amount, simulate);
        setChanged();
        return itemStack;
    }

    @Override
    public int getSlotLimit(int slot) {
        return items.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return items.isItemValid(slot, stack);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("electrolysis");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, Player p_createMenu_3_) {
        return new ElectrolysisContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }

    public enum Slot {
        INPUT(0), OUTPUT(1);

        private final int index;

        Slot(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
