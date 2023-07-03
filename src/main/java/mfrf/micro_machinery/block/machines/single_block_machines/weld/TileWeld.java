package mfrf.micro_machinery.block.machines.single_block_machines.weld;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.gui.weld.WeldContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.weld.WeldRecipe;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
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
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileWeld extends MMTileBase implements MenuProvider {
    private final ItemStackHandler input = new ItemStackHandler(6);
    private final ItemStackHandler output = new ItemStackHandler(1);
    private final IntegerContainer progress = new IntegerContainer();
    private final FEContainer feContainer = new FEContainer(0, 80000) {
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


    public TileWeld(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_WELD.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileWeld weld) {

            if (weld.isWorking) {
                weld.progress.selfAdd();
                weld.setChanged();
            } else {
                RecipeHelper.weldRecipeAndShrinkItemStacks weldRecipeAndShrinkItemStacks = RecipeHelper.getWeldRecipe(world.getRecipeManager(), weld.input);
                if (weldRecipeAndShrinkItemStacks != null) {
                    WeldRecipe weldRecipe = weldRecipeAndShrinkItemStacks.weldRecipe;
                    int[] shrinkCounts = weldRecipeAndShrinkItemStacks.shrinkCounts;
                    weld.progress.setMax(weldRecipe.getTime());
                    weld.isWorking = true;
                    weld.result = weldRecipe.getOutput();
                    for (int i = 0; i < shrinkCounts.length; i++) {
                        weld.input.extractItem(i, shrinkCounts[i], false);
                    }
                    weld.setChanged();
                }
            }

            if (weld.progress.atMaxValue()) {
                if (weld.output.insertItem(0, weld.result, true).isEmpty()) ;
                weld.output.insertItem(0, weld.result, false);
                weld.result = ItemStack.EMPTY;
                weld.progress.resetValue();
                weld.isWorking = false;
                weld.setChanged();
            }


        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
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
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("input", input.serializeNBT());
        pTag.put("output", output.serializeNBT());
        pTag.put("progress", progress.serializeNBT());
        pTag.put("fe_container", feContainer.serializeNBT());
        pTag.putBoolean("is_working", isWorking);
        if (result.isEmpty()) {
            pTag.put("result", result.serializeNBT());
        }
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
        if (cap == ForgeCapabilities.ENERGY && side == getBackDirection()) {
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
    public Component getDisplayName() {
        return new TranslatableComponent("gui.name.weld");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new WeldContainer(p_createMenu_1_, p_createMenu_2_, worldPosition, level);
    }
}
