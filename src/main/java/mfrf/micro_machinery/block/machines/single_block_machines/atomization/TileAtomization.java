package mfrf.micro_machinery.block.machines.single_block_machines.atomization;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.gui.atomization.AtomizationContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.atomization.AtomizationRecipe;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class TileAtomization extends MMTileBase implements MenuProvider {
    private final FEContainer feContainer = new FEContainer(0, 80000) {
        @Override
        public boolean canExtract() {
            return false;
        }

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
        public boolean canReceive() {
            return true;
        }

        @Override
        public int selfSubtract() {
            int current = getCurrent();
            if (current >= 256) {
                minus(256, false);
                return current;
            } else {
                return -1;
            }
        }
    };
    private final FluidTank input = new FluidTank(Config.ATOMIZATION_FLUID_CONTAINER.get());
    private final ItemStackHandler output = new ItemStackHandler(1);
    private final IntegerContainer progress = new IntegerContainer();
    private boolean isWorking = false;
    private ResourceLocation recipe = null;

    public TileAtomization(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_ATOMIZATION.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!level.isClientSide() && blockEntity instanceof TileAtomization tileAtomization) {

            if (tileAtomization.isWorking) {

                if (tileAtomization.feContainer.selfSubtract() != -1) {
                    tileAtomization.progress.selfAdd();
                    tileAtomization.setChanged();
                }

                if (tileAtomization.progress.atMaxValue()) {
                    Optional<? extends Recipe<?>> recipe = level.getRecipeManager().byKey(tileAtomization.recipe);
                    recipe.ifPresent(iRecipe -> {
                        AtomizationRecipe atomizationRecipe = (AtomizationRecipe) iRecipe;
                        if (tileAtomization.output.insertItem(0, atomizationRecipe.result, true).isEmpty()) {
                            tileAtomization.output.insertItem(0, atomizationRecipe.result, false);
                            tileAtomization.isWorking = false;
                            tileAtomization.recipe = null;
                            tileAtomization.progress.resetValue();
                        } else {
                            tileAtomization.progress.selfSubtract();
                        }
                        tileAtomization.setChanged();
                    });
                }
            } else {
                if (!tileAtomization.input.isEmpty()) {
                    AtomizationRecipe atomizationRecipe = RecipeHelper.getAtomizationRecipe(tileAtomization.input.getFluid(), level.getRecipeManager());
                    if (atomizationRecipe != null) {
                        tileAtomization.input.drain(atomizationRecipe.input.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        tileAtomization.progress.setMax(atomizationRecipe.time);
                        tileAtomization.isWorking = true;
                        tileAtomization.recipe = atomizationRecipe.getId();
                        tileAtomization.setChanged();
                    }
                }
            }

        }
    }

    public FEContainer getFeContainer() {
        return feContainer;
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    public FluidTank getInput() {
        return input;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("fe_container", feContainer.serializeNBT());
        pTag.put("input", input.writeToNBT(new CompoundTag()));
        pTag.put("output", output.serializeNBT());
        pTag.putBoolean("is_working", isWorking);
        pTag.put("progress", progress.serializeNBT());
        if (recipe != null) {
            pTag.putString("recipe", recipe.toString());
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        feContainer.deserializeNBT(nbt.getCompound("fe_container"));
        input.readFromNBT(nbt.getCompound("input"));
        output.deserializeNBT(nbt.getCompound("output"));
        isWorking = nbt.getBoolean("is_working");
        progress.deserializeNBT(nbt.getCompound("progress"));
        if (nbt.contains("recipe")) {
            recipe = ResourceLocation.tryParse(nbt.getString("recipe"));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        Direction backDirection = getBackDirection();
        if (side == backDirection) {
            if (cap == ForgeCapabilities.ITEM_HANDLER)
                return LazyOptional.of(() -> output).cast();

        } else if (side == backDirection.getCounterClockWise()) {
            if (cap == ForgeCapabilities.ENERGY)
                return LazyOptional.of(() -> feContainer).cast();

        } else if (side == backDirection.getOpposite()) {
            if (cap == ForgeCapabilities.FLUID_HANDLER)
                return LazyOptional.of(() -> input).cast();
        }

        return super.getCapability(cap, side);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AtomizationContainer(pContainerId, pInventory, getBlockPos(), level);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("atomization");
    }
}
