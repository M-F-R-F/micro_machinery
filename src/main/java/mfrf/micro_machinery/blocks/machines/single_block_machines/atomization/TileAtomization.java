package mfrf.micro_machinery.blocks.machines.single_block_machines.atomization;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.atomization.AtomizationContainer;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.atomization.AtomizationRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class TileAtomization extends MMTileBase implements ITickableTileEntity, INamedContainerProvider {
    private FEContainer feContainer = new FEContainer(0, 80000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            setChanged2();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged2();
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
    private FluidTank input = new FluidTank(Config.ATOMIZATION_FLUID_CONTAINER.get());
    private ItemStackHandler output = new ItemStackHandler(1);
    private IntegerContainer progress = new IntegerContainer();
    private boolean isWorking = false;
    private ResourceLocation recipe = null;

    public TileAtomization() {
        super(RegisteredTileEntityTypes.TILE_ATOMIZATION.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {

            if (isWorking) {

                if (feContainer.selfSubtract() != -1) {
                    progress.selfAdd();
                    setChanged();
                }

                if (progress.atMaxValue()) {
                    Optional<? extends IRecipe<?>> recipe = world.getRecipeManager().getRecipe(this.recipe);
                    recipe.ifPresent(iRecipe -> {
                        AtomizationRecipe atomizationRecipe = (AtomizationRecipe) iRecipe;
                        if (output.insertItem(0, atomizationRecipe.result, true).isEmpty()) {
                            output.insertItem(0, atomizationRecipe.result, false);
                            this.isWorking = false;
                            this.recipe = null;
                            this.progress.resetValue();
                        } else {
                            progress.selfSubtract();
                        }
                        setChanged();
                    });
                }
            } else {
                if (!input.isEmpty()) {
                    AtomizationRecipe atomizationRecipe = RecipeHelper.getAtomizationRecipe(input.getFluid(), world.getRecipeManager());
                    if (atomizationRecipe != null) {
                        input.drain(atomizationRecipe.input.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        this.progress.setMax(atomizationRecipe.time);
                        this.isWorking = true;
                        this.recipe = atomizationRecipe.getId();
                        setChanged();
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
    public CompoundTag write(CompoundTag compound) {
        CompoundTag CompoundTag = super.write(compound);
        CompoundTag.put("fe_container", feContainer.serializeNBT());
        CompoundTag.put("input", input.writeToNBT(new CompoundTag()));
        CompoundTag.put("output", output.serializeNBT());
        CompoundTag.putBoolean("is_working", isWorking);
        CompoundTag.put("progress", progress.serializeNBT());
        if (recipe != null) {
            CompoundTag.putString("recipe", recipe.toString());
        }
        return CompoundTag;
    }

    @Override
    public void read(CompoundTag nbt) {
        super.read(nbt);
        feContainer.deserializeNBT(nbt.getCompound("fe_container"));
        input.readFromNBT(nbt.getCompound("input"));
        output.deserializeNBT(nbt.getCompound("output"));
        isWorking = nbt.getBoolean("is_working");
        progress.deserializeNBT(nbt.getCompound("progress"));
        if (nbt.contains("recipe")) {
            recipe = ResourceLocation.tryCreate(nbt.getString("recipe"));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        Direction backDirection = getBackDirection();
        if (side == backDirection) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                return LazyOptional.of(() -> output).cast();

        } else if (side == backDirection.rotateYCCW()) {
            if (cap == CapabilityEnergy.ENERGY)
                return LazyOptional.of(() -> feContainer).cast();

        } else if (side == backDirection.getOpposite()) {
            if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                return LazyOptional.of(() -> input).cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("atomization");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new AtomizationContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }
}
