package mfrf.dbydd.micro_machinery.blocks.machines.etcher;

import jdk.nashorn.internal.objects.annotations.Getter;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEtcher extends MMTileBase implements ITickableTileEntity {
    private FEContainer feContainer = new FEContainer(0, 320000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    };
    private ItemStack recipeInProgress = null;
    private IntegerContainer progress = new IntegerContainer();
    private IntegerContainer plugProgress = new IntegerContainer(0, 100);
    private int feNeedPerTick = 0;

    public IntegerContainer getPlugProgress() {
        return plugProgress;
    }

    public State getState() {
        return state;
    }

    private State state = State.WAITING;
    private ItemStackHandler slot = new ItemStackHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

//        @Nonnull
//        @Override
//        public ItemStack extractItem(int slot, int amount, boolean simulate) {
//            if (state != State.WAITING) {
//                return ItemStack.EMPTY;
//            } else {
//                return super.extractItem(slot, amount, simulate);
//            }
//        }
    };

    public TileEtcher() {
        super(Registered_Tileentitie_Types.TILE_ETCHER.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        state = State.valueOf(compound.getString("state"));
        feContainer.deserializeNBT(compound.getCompound("energy"));
        slot.deserializeNBT(compound.getCompound("slot"));
        progress.deserializeNBT(compound.getCompound("progress"));
        feNeedPerTick = compound.getInt("fe_need_per_tick");
        plugProgress.deserializeNBT(compound.getCompound("eject_progress"));
        if (compound.contains("recipe")) {
            recipeInProgress = ItemStack.read(compound.getCompound("recipe"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("state", state.name());
        compound.put("energy", feContainer.serializeNBT());
        compound.put("output", slot.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        compound.putInt("fe_need_per_tick", feNeedPerTick);
        compound.put("eject_progress", plugProgress.serializeNBT());
        if (recipeInProgress != null) {
            compound.put("recipe", recipeInProgress.write(new CompoundNBT()));
        }
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> slot).cast();
        }

        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == getBlockState().get(BlockEtcher.FACING).getOpposite() && cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> feContainer).cast();
        }
        return super.getCapability(cap, side);
    }

    public ItemStack getCurrentItemStackInSlot() {
        return slot.getStackInSlot(0);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {

            if (state == State.WORKING) {
                if (progress.atMaxValue()) {
                    EtcherRecipe currentRecipe = getCurrentRecipe();
                    if (currentRecipe != null) {
                        slot.setStackInSlot(0, currentRecipe.getOutput());
                        feNeedPerTick = 0;
                        progress.resetValue();
                        state = State.EJECTING;
                        plugProgress.self_add();
                    } else {
                        state = State.FINISHED;
                    }
                    markDirty2();
                } else {
                    if (feContainer.getCurrent() >= feNeedPerTick) {
                        feContainer.extractEnergy(feNeedPerTick, false);
                        progress.self_add();
                        markDirty2();
                    }
                }

            } else if (state == State.SEARCHING) {
                EtcherRecipe currentRecipe = getCurrentRecipe();
                if (currentRecipe != null) {
                    progress.setMax(currentRecipe.getTime());
                    feNeedPerTick = currentRecipe.getFePerTick();
                    state = State.WORKING;
                    markDirty2();
                }
            } else {
                plugProgress.self_add();
                markDirty2();
            }

            if (plugProgress.atMaxValue()) {
                if (state == State.PLUGGING) {
                    if (recipeInProgress != null)
                        state = State.WORKING;
                    else {
                        state = State.SEARCHING;
                    }
                } else {
                    state = State.FINISHED;
                }
                plugProgress.resetValue();
                markDirty2();
            }
        }
    }

    private EtcherRecipe getCurrentRecipe() {
        return RecipeHelper.getEtcherRecipe(slot.getStackInSlot(0), world.getRecipeManager());
    }

    public void onBlockActivated(PlayerEntity player, Hand handIn) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (heldItem.isEmpty()) {
            if (this.state == State.FINISHED) {
                ItemHandlerHelper.giveItemToPlayer(player, slot.getStackInSlot(0));
                slot.setStackInSlot(0, ItemStack.EMPTY);
                this.state = State.WAITING;
                markDirty2();
            }
        } else {
            if (this.state == State.WAITING) {
                slot.insertItem(0, new ItemStack(heldItem.getItem()), false);
                heldItem.shrink(1);
                if (getCurrentRecipe() != null) {
                    this.state = State.PLUGGING;
                } else {
                    this.state = State.FINISHED;
                }
                plugProgress.self_add();
                markDirty2();
            }
        }
    }

    public enum State {
        WAITING, PLUGGING, EJECTING, SEARCHING, WORKING, FINISHED;
    }
}
