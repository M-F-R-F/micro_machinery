package mfrf.micro_machinery.blocks.machines.single_block_machines.weld;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.weld.WeldContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.weld.WeldRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.MenuProvider;
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
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileWeld extends MMTileBase implements  MenuProvider {
    private ItemStackHandler input = new ItemStackHandler(6);
    private ItemStackHandler output = new ItemStackHandler(1);
    private IntegerContainer progress = new IntegerContainer();
    private FEContainer feContainer = new FEContainer(0, 80000) {
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
            setChanged();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            setChanged();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int selfSubtract() {
            int minus = this.minus(80, false);
            setChanged();
            return minus;
        }

    };
    private ItemStack result = ItemStack.EMPTY;
    private boolean isWorking = false;


    public TileWeld() {
        super(RegisteredBlockEntityTypes.TILE_WELD.get());
    }

    @Override
    public void tick() {
        if (!world.isClientSide()) {

            if (isWorking) {
                progress.selfAdd();
                setChanged();
            } else {
                RecipeHelper.weldRecipeAndShrinkItemStacks weldRecipeAndShrinkItemStacks = RecipeHelper.getWeldRecipe(world.getRecipeManager(), input);
                if (weldRecipeAndShrinkItemStacks != null) {
                    WeldRecipe weldRecipe = weldRecipeAndShrinkItemStacks.weldRecipe;
                    int[] shrinkCounts = weldRecipeAndShrinkItemStacks.shrinkCounts;
                    progress.setMax(weldRecipe.getTime());
                    isWorking = true;
                    result = weldRecipe.getOutput();
                    for (int i = 0; i < shrinkCounts.length; i++) {
                        input.extractItem(i, shrinkCounts[i], false);
                    }
                    setChanged();
                }
            }

            if (progress.atMaxValue()) {
                if (output.insertItem(0, result, true).isEmpty()) ;
                output.insertItem(0, result, false);
                result = ItemStack.EMPTY;
                progress.resetValue();
                isWorking = false;
                setChanged();
            }


        }
    }

    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
        input.deserializeNBT(compound.getCompound("input"));
        output.deserializeNBT(compound.getCompound("output"));
        progress.deserializeNBT(compound.getCompound("progress"));
        feContainer.deserializeNBT(compound.getCompound("fe_container"));
        isWorking = compound.getBoolean("is_working");
        if (compound.contains("result")) {
            result = ItemStack.of(compound.getCompound("result"));
        }
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag write = super.write(compound);
        write.put("input", input.serializeNBT());
        write.put("output", output.serializeNBT());
        write.put("progress", progress.serializeNBT());
        write.put("fe_container", feContainer.serializeNBT());
        write.putBoolean("is_working", isWorking);
        if (result.isEmpty()) {
            write.put("result", result.serializeNBT());
        }
        return write;
    }

    public ItemStackHandler getInput() {
        return input;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    public FEContainer getFeContainer() {
        return feContainer;
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && side == getBackDirection()) {
            return LazyOptional.of(() -> feContainer).cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == Direction.UP) {
            return LazyOptional.of(() -> input).cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == Direction.DOWN) {
            return LazyOptional.of(() -> output).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslatableComponent("gui.name.weld");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new WeldContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }
}
