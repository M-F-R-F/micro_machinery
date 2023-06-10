package mfrf.micro_machinery.blocks.machines.single_block_machines.electrolysis;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.electrolysis.ElectrolysisContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileElectrolysis extends MMTileBase implements IItemHandler, MenuProvider {
    private final FEContainer energy = new FEContainer(0, 120000) {
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
    private final ItemStackHandler items = new ItemStackHandler(2);
    private IntegerContainer progress = new IntegerContainer();
    private ItemStack result = ItemStack.EMPTY;
    private boolean isWorking = false;

    public TileElectrolysis(BlockPos pos, BlockState state) {
        super(RegisteredBlockEntityTypes.TILE_ELECTROLYSIS.get(), pos, state);
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
    public void load(CompoundTag compound) {
        items.deserializeNBT(compound.getCompound("item"));
        energy.deserializeNBT(compound.getCompound("energy"));
        isWorking = compound.getBoolean("is_working");
        if (compound.contains("progress")) {
            progress = new IntegerContainer();
            progress.deserializeNBT(compound.getCompound("progress"));
        }
        result = ItemStack.of(compound.getCompound("result"));
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("item", items.serializeNBT());
        pTag.put("energy", energy.serializeNBT());
        pTag.putBoolean("is_working", isWorking);
        if (progress != null) {
            pTag.put("progress", progress.serializeNBT());
        }
        pTag.put("result", result.serializeNBT());
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileElectrolysis electrolysis) {
            if (electrolysis.isWorking) {
                if (!electrolysis.progress.atMaxValue()) {
                    if (electrolysis.energy.getCurrent() >= 1024) {
                        electrolysis.energy.selfSubtract();
                        electrolysis.progress.selfAdd();
                        electrolysis.markDirty2();
                    }
                } else {
                    if (electrolysis.items.insertItem(Slot.OUTPUT.index, electrolysis.result, true) == ItemStack.EMPTY) {
                        electrolysis.items.insertItem(Slot.OUTPUT.index, electrolysis.result, false);
                        electrolysis.result = ItemStack.EMPTY;
                        electrolysis.progress.resetValue();
                        electrolysis.isWorking = false;
                        electrolysis.markDirty2();
                    }
                }
            } else {
                ElectrolysisRecipe electrolysisRecipe = RecipeHelper.getElectrolysisRecipe(electrolysis.items.getStackInSlot(Slot.INPUT.index), world.getRecipeManager());
                if (electrolysisRecipe != null) {
                    electrolysis.result = electrolysisRecipe.getOutput();
                    electrolysis.progress = new IntegerContainer(0, electrolysisRecipe.getTime());
                    electrolysis.isWorking = true;
                    electrolysis.markDirty2();
                }
            }
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
    public Component getDisplayName() {
        return new TranslatableComponent("electrolysis");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
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
