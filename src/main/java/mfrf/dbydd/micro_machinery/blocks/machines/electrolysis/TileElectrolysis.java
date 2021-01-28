package mfrf.dbydd.micro_machinery.blocks.machines.electrolysis;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.electrolysis.ElectrolysisContainer;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
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

public class TileElectrolysis extends MMTileBase implements ITickableTileEntity, IItemHandler, INamedContainerProvider {
    private FEContainer energy = new FEContainer(0, 120000) {
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
    private IntegerContainer progress;
    private boolean isWorking = false;

    public TileElectrolysis() {
        super(Registered_Tileentitie_Types.TILE_ELECTROLYSIS.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && isBackDirection(side)) return LazyOptional.of(() -> energy).cast();
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundNBT compound) {
        items.deserializeNBT(compound.getCompound("item"));
        energy.deserializeNBT(compound.getCompound("energy"));
        isWorking = compound.getBoolean("is_working");
        if (compound.contains("progress")) {
            progress = new IntegerContainer();
            progress.deserializeNBT(compound.getCompound("progress"));
        }
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("item", items.serializeNBT());
        write.put("energy", energy.serializeNBT());
        write.putBoolean("is_working", isWorking);
        if (progress != null) {
            write.put("progress", progress.serializeNBT());
        }
        return write;
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {

        }
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
        markDirty();
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot == Slot.INPUT.index) return ItemStack.EMPTY;
        ItemStack itemStack = items.extractItem(slot, amount, simulate);
        markDirty();
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
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ElectrolysisContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }

    public enum Slot {
        INPUT(0), OUTPUT(1);

        private final int index;

        Slot(int index) {
            this.index = index;
        }
    }
}
