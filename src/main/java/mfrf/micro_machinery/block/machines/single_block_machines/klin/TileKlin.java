package mfrf.micro_machinery.block.machines.single_block_machines.klin;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.gui.klin.KlinContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileKlin extends MMTileBase implements IItemHandler, IFluidHandler, MenuProvider {

    private final FluidTank fluidHandler = new FluidTank(2000);
    private final ItemStackHandler itemhandler = new ItemStackHandler(5);
    private FluidStack result = FluidStack.EMPTY;
    private KlinFluidToItemRecipe recipe = null;
    private IntegerContainer meltTime = new IntegerContainer(0);
    private IntegerContainer burnTime = new IntegerContainer(0);
    private IntegerContainer coolDown = new IntegerContainer(0);
    private boolean isBurning = false;

    public TileKlin(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_KLIN_TYPE.get(), pos, state);
    }

    public boolean isBurning() {
        return isBurning;
    }

    public FluidTank getFluidHandler() {
        return fluidHandler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return LazyOptional.of(() -> (T) this);
        } else if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return LazyOptional.of(() -> (T) this);
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag compound) {
        this.fluidHandler.readFromNBT(compound.getCompound("fluidhandler"));
        this.itemhandler.deserializeNBT(compound.getCompound("itemhandler"));
        this.result = FluidStack.loadFluidStackFromNBT(compound.getCompound("result"));
        this.isBurning = compound.getBoolean("isburning");
        meltTime.deserializeNBT(compound.getCompound("melt_time"));
        burnTime.deserializeNBT(compound.getCompound("burn_time"));
        coolDown.deserializeNBT(compound.getCompound("cooldown"));

        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("fluidhandler", fluidHandler.writeToNBT(new CompoundTag()));
        pTag.put("itemhandler", itemhandler.serializeNBT());
        pTag.put("result", result.writeToNBT(new CompoundTag()));
        pTag.putBoolean("isburning", isBurning);
        pTag.put("melt_time", meltTime.serializeNBT());
        pTag.put("burn_time", burnTime.serializeNBT());
        pTag.put("cooldown", coolDown.serializeNBT());

    }

    public boolean issmelting() {
        return result != FluidStack.EMPTY && meltTime.atMinValue() && isBurning;
    }

    private KlinItemToFluidRecipe tryToGetRecipe() {
        return RecipeHelper.GetKlinItemToFluidRecipe(itemhandler.getStackInSlot(0), itemhandler.getStackInSlot(1), level.getRecipeManager());
    }

    private void tryToExtractFuel(ItemStackHandler handler, int index) {
        if (handler.getStackInSlot(index) != ItemStack.EMPTY) {
            int maxburntime = ForgeHooks.getBurnTime(handler.getStackInSlot(index), RecipeType.SMELTING);
            if (maxburntime != 0) {
                handler.extractItem(index, 1, false);
                this.isBurning = true;
                burnTime.setMax(maxburntime);
                BlockKlin.setState(isBurning, level, this.getBlockPos());
            }
        }
    }

    private void extractMaterial(KlinItemToFluidRecipe recipeinsmelting) {
        if (recipeinsmelting.isIssingle()) {
            int amount = recipeinsmelting.getCount();
            int extracted1 = itemhandler.extractItem(0, amount, false).getCount();
            amount -= extracted1;
            if (amount > 0) {
                itemhandler.extractItem(1, amount, false);
            }
        } else {

            if (RecipeHelper.testItemStackWithIngredient(itemhandler.getStackInSlot(0), recipeinsmelting.getInput1(), recipeinsmelting.getCount1())) {
                itemhandler.extractItem(0, recipeinsmelting.getCount1(), false);
                itemhandler.extractItem(1, recipeinsmelting.getCount2(), false);
            } else {
                itemhandler.extractItem(0, recipeinsmelting.getCount2(), false);
                itemhandler.extractItem(1, recipeinsmelting.getCount1(), false);
            }

        }
    }


    private void insertResult(int slot, ItemStack output) {
        itemhandler.setStackInSlot(slot, new ItemStack(output.getItem(), itemhandler.getStackInSlot(slot).getCount() + output.getCount()));
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide && blockEntity instanceof TileKlin klin) {

            if (klin.isBurning) {
                klin.burnTime.selfAdd();
                if (klin.issmelting()) {
                    klin.meltTime.selfAdd();
                    if (!klin.meltTime.atMinValue()) {
                        if (klin.fluidHandler.fill(klin.result, IFluidHandler.FluidAction.SIMULATE) == klin.result.getAmount()) {
                            klin.fluidHandler.fill(klin.result, IFluidHandler.FluidAction.EXECUTE);
                            klin.result = FluidStack.EMPTY;
                            klin.meltTime.resetValue();
                            klin.markDirty2();
                        } else {
                            klin.meltTime.selfSubtract();
                            klin.markDirty2();
                        }
                        klin.markDirty2();
                    }
                } else {
                    KlinItemToFluidRecipe recipeinsmelting = klin.tryToGetRecipe();
                    if (recipeinsmelting != null && klin.fluidHandler.fill(recipeinsmelting.getOutputfluidstack().copy(), IFluidHandler.FluidAction.SIMULATE) == recipeinsmelting.getOutputfluidstack().getAmount()) {
                        klin.result = recipeinsmelting.getOutputfluidstack().copy();
                        klin.meltTime.setMax(recipeinsmelting.getMelttime());
                        klin.extractMaterial(recipeinsmelting);
                        klin.markDirty2();
                    }
                }
                if (klin.burnTime.atMaxValue()) {
                    klin.burnTime.resetValue();
                    klin.burnTime.setMax(0);
                    klin.isBurning = false;
                    BlockKlin.setState(klin.isBurning, world, klin.getBlockPos());
                    klin.markDirty2();
                }
            } else {
                if (klin.tryToGetRecipe() != null) {
                    klin.tryToExtractFuel(klin.itemhandler, 2);
                }
                klin.markDirty2();
            }

            if (klin.recipe == null) {
                if (klin.fluidHandler.getFluidAmount() != 0 && klin.itemhandler.getStackInSlot(4) != ItemStack.EMPTY) {
                    klin.recipe = RecipeHelper.GetKlinFluidRecipe(klin.fluidHandler.getFluid(), klin.itemhandler.getStackInSlot(4), world.getRecipeManager());
                    if (klin.recipe != null) {
                        klin.coolDown.setMax(klin.recipe.getCoolbelow());
                    }
                    klin.markDirty2();
                }
            } else if (!klin.coolDown.atMaxValue() && klin.isBurning()) {
                klin.coolDown.selfAdd();
                klin.markDirty2();
            } else {
                if (RecipeHelper.canInsert(klin.itemhandler.getStackInSlot(3), klin.recipe.getOutput().copy())) {
                    klin.insertResult(3, klin.recipe.getOutput().copy());
                    klin.fluidHandler.drain(klin.recipe.getInputfluid(), IFluidHandler.FluidAction.EXECUTE);
                    klin.coolDown.setCurrent(0);
                    klin.coolDown.setMax(0);
                    klin.recipe = null;
                    klin.markDirty2();
                } else {
                    klin.coolDown.selfSubtract();
                    klin.markDirty2();
                }
            }
        }
    }

    @Override
    public int getSlots() {
        return itemhandler.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return itemhandler.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        ItemStack itemStack = itemhandler.insertItem(slot, stack, simulate);
        markDirty2();
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack itemStack = itemhandler.extractItem(slot, amount, simulate);
        markDirty2();
        return itemStack;
    }

    @Override
    public int getSlotLimit(int slot) {
        int slotLimit = itemhandler.getSlotLimit(slot);
        return slotLimit;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return itemhandler.isItemValid(slot, stack);
    }

    @Override
    public int getTanks() {
        return fluidHandler.getTanks();
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return fluidHandler.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return fluidHandler.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return fluidHandler.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        int fill = fluidHandler.fill(resource, action);
        markDirty2();
        return fill;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        FluidStack drain = fluidHandler.drain(resource, action);
        markDirty2();
        return drain;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        FluidStack drain = fluidHandler.drain(maxDrain, action);
        markDirty2();
        return drain;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.klin");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int sycID, Inventory inventory, Player player) {
        return new KlinContainer(sycID, inventory, this.getBlockPos(), this.level);
    }

    public ItemStackHandler getItemHandler() {
        return itemhandler;
    }

    public IntegerContainer getMeltTime() {
        return meltTime;
    }

    public IntegerContainer getBurnTime() {
        return burnTime;
    }

    public IntegerContainer getCoolDown() {
        return coolDown;
    }
}
