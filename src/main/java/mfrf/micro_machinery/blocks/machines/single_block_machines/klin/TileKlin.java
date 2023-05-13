package mfrf.micro_machinery.blocks.machines.single_block_machines.klin;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.klin.KlinContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.entity.player.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.MenuProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.StringTextComponent;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileKlin extends MMTileBase implements IItemHandler, IFluidHandler, MenuProvider {

    private final FluidTank fluidHandler = new FluidTank(2000);
    private final ItemStackHandler itemhandler = new ItemStackHandler(5);
    private FluidStack result = FluidStack.EMPTY;
    private KlinFluidToItemRecipe recipe = null;
    private int meltTime = 0;
    private int currentMeltTime = 0;
    private int currentBurnTime = 0;
    private int maxBurnTime = 0;
    private int pouringCoolDown = 0;
    private int currentcooldown = 0;
    private boolean isBurning = false;
    private final KlinProgressBarNumArray progressBarNumArray = new KlinProgressBarNumArray();

    public TileKlin(BlockPos pos, BlockState state) {
        super(RegisteredBlockEntityTypes.TILE_KLIN_TYPE.get(), pos, state);
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
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> (T) this);
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> (T) this);
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag compound) {
        this.fluidHandler.readFromNBT(compound.getCompound("fluidhandler"));
        this.itemhandler.deserializeNBT(compound.getCompound("itemhandler"));
        this.result = FluidStack.loadFluidStackFromNBT(compound.getCompound("result"));
        this.meltTime = compound.getInt("melttime");
        this.currentMeltTime = compound.getInt("currentmelttime");
        this.currentBurnTime = compound.getInt("currentburntime");
        this.maxBurnTime = compound.getInt("maxburntime");
        this.pouringCoolDown = compound.getInt("pouringcooldown");
        this.currentcooldown = compound.getInt("currentcooldown");
        this.isBurning = compound.getBoolean("isburning");

        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("fluidhandler", fluidHandler.writeToNBT(new CompoundTag()));
        pTag.put("itemhandler", itemhandler.serializeNBT());
        pTag.put("result", result.writeToNBT(new CompoundTag()));
        pTag.putInt("melttime", meltTime);
        pTag.putInt("currentmeltime", currentMeltTime);
        pTag.putInt("currentburntime", currentBurnTime);
        pTag.putInt("maxburntime", maxBurnTime);
        pTag.putInt("pouringcooldown", pouringCoolDown);
        pTag.putInt("currentcooldown", currentcooldown);
        pTag.putBoolean("isburning", isBurning);

    }

    public boolean issmelting() {
        return result != FluidStack.EMPTY && meltTime != 0 && isBurning;
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
                this.maxBurnTime = maxburntime;
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

            klin.progressBarNumArray.set(0, klin.currentMeltTime);
            klin.progressBarNumArray.set(1, klin.meltTime);
            klin.progressBarNumArray.set(2, klin.currentBurnTime);
            klin.progressBarNumArray.set(3, klin.maxBurnTime);
            if (klin.isBurning) {
                klin.currentBurnTime++;
                if (klin.issmelting()) {
                    klin.currentMeltTime++;
                    if (klin.currentMeltTime >= klin.meltTime) {
                        if (klin.fluidHandler.fill(klin.result, IFluidHandler.FluidAction.SIMULATE) == klin.result.getAmount()) {
                            klin.fluidHandler.fill(klin.result, IFluidHandler.FluidAction.EXECUTE);
                            klin.result = FluidStack.EMPTY;
                            klin. currentMeltTime = 0;
                            klin.markDirty2();
                        } else {
                            klin.currentMeltTime--;
                            klin.markDirty2();
                        }
                        klin.markDirty2();
                    }
                } else {
                    KlinItemToFluidRecipe recipeinsmelting = klin.tryToGetRecipe();
                    if (recipeinsmelting != null && klin.fluidHandler.fill(recipeinsmelting.getOutputfluidstack(), IFluidHandler.FluidAction.SIMULATE) == recipeinsmelting.getOutputfluidstack().getAmount()) {
                        klin.result = recipeinsmelting.getOutputfluidstack();
                        klin.meltTime = recipeinsmelting.getMelttime();
                        klin.extractMaterial(recipeinsmelting);
                        klin.markDirty2();
                    }
                }
                if (klin.currentBurnTime >= klin.maxBurnTime) {
                    klin.currentBurnTime = 0;
                    klin.maxBurnTime = 0;
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
                        klin.pouringCoolDown = klin.recipe.getCoolbelow();
                    }
                    klin.markDirty2();
                }
            } else if (klin.currentcooldown < klin.pouringCoolDown && klin.isBurning()) {
                klin.currentcooldown++;
                klin.markDirty2();
            } else {
                if (RecipeHelper.canInsert(klin.itemhandler.getStackInSlot(3), klin.recipe.getOutput())) {
                    klin.insertResult(3, klin.recipe.getOutput());
                    klin.fluidHandler.drain(klin.recipe.getInputfluid(), IFluidHandler.FluidAction.EXECUTE);
                    klin.currentcooldown = 0;
                    klin.pouringCoolDown = 0;
                    klin.recipe = null;
                    klin.markDirty2();
                } else {
                    klin.currentcooldown--;
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
        return new TranslatableComponent("container.klin");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int sycID, Inventory inventory, Player player) {
        return new KlinContainer(sycID, inventory, this.getBlockPos(), this.level, progressBarNumArray);
    }

    public ItemStackHandler getItemHandler() {
        return itemhandler;
    }

//    public static class KlinProgressBarNumArray implements IIntArray { //todo renew system
//        private final int[] iArray = {0, 0, 0, 0};
//
//        @Override
//        public int get(int index) {
//            return iArray[index];
//        }
//
//        @Override
//        public void set(int index, int value) {
//            iArray[index] = value;
//        }
//
//        @Override
//        public int size() {
//            return 4;
//        }
//    }
}
