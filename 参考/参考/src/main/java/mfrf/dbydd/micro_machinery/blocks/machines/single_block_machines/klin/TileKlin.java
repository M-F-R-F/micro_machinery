package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.klin.KlinContainer;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
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

public class TileKlin extends MMTileBase implements ITickableTileEntity, IItemHandler, IFluidHandler, INamedContainerProvider {

    private FluidTank fluidHandler = new FluidTank(2000);
    private ItemStackHandler itemhandler = new ItemStackHandler(5);
    private FluidStack result = FluidStack.EMPTY;
    private KlinFluidToItemRecipe recipe = null;
    private int meltTime = 0;
    private int currentMeltTime = 0;
    private int currentBurnTime = 0;
    private int maxBurnTime = 0;
    private int pouringCoolDown = 0;
    private int currentcooldown = 0;
    private boolean isBurning = false;
    private KlinProgressBarNumArray progressBarNumArray = new KlinProgressBarNumArray();

    public TileKlin() {
        super(RegisteredTileEntityTypes.TILE_KLIN_TYPE.get());
    }

    public boolean isBurning() {
        return isBurning;
    }

    public FluidTank getFluidHandler() {
        return fluidHandler;
    }

    public void onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        player.sendMessage(new StringTextComponent("actived"));
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
    public void read(CompoundNBT compound) {
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

        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluidhandler", fluidHandler.writeToNBT(new CompoundNBT()));
        compound.put("itemhandler", itemhandler.serializeNBT());
        compound.put("result", result.writeToNBT(new CompoundNBT()));
        compound.putInt("melttime", meltTime);
        compound.putInt("currentmeltime", currentMeltTime);
        compound.putInt("currentburntime", currentBurnTime);
        compound.putInt("maxburntime", maxBurnTime);
        compound.putInt("pouringcooldown", pouringCoolDown);
        compound.putInt("currentcooldown", currentcooldown);
        compound.putBoolean("isburning", isBurning);

        return super.write(compound);
    }

    public boolean issmelting() {
        return result != FluidStack.EMPTY && meltTime != 0 && isBurning;
    }

    private KlinItemToFluidRecipe tryToGetRecipe() {
        return RecipeHelper.GetKlinItemToFluidRecipe(itemhandler.getStackInSlot(0), itemhandler.getStackInSlot(1), world.getRecipeManager());
    }

    private void tryToExtractFuel(ItemStackHandler handler, int index) {
        if (handler.getStackInSlot(index) != ItemStack.EMPTY) {
            int maxburntime = ForgeHooks.getBurnTime(handler.getStackInSlot(index));
            if (maxburntime != 0) {
                handler.extractItem(index, 1, false);
                this.isBurning = true;
                this.maxBurnTime = maxburntime;
                BlockKlin.setState(isBurning, world, this.getPos());
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

    @Override
    public void tick() {
        if (!world.isRemote) {
            this.progressBarNumArray.set(0, this.currentMeltTime);
            this.progressBarNumArray.set(1, this.meltTime);
            this.progressBarNumArray.set(2, this.currentBurnTime);
            this.progressBarNumArray.set(3, this.maxBurnTime);
            if (isBurning) {
                this.currentBurnTime++;
                if (issmelting()) {
                    currentMeltTime++;
                    if (currentMeltTime >= meltTime) {
                        if (fluidHandler.fill(result, IFluidHandler.FluidAction.SIMULATE) == result.getAmount()) {
                            this.fluidHandler.fill(result, IFluidHandler.FluidAction.EXECUTE);
                            result = FluidStack.EMPTY;
                            currentMeltTime = 0;
                            markDirty2();
                        } else {
                            currentMeltTime--;
                            markDirty2();
                        }
                        markDirty2();
                    }
                } else {
                    KlinItemToFluidRecipe recipeinsmelting = tryToGetRecipe();
                    if (recipeinsmelting != null && fluidHandler.fill(recipeinsmelting.getOutputfluidstack(), IFluidHandler.FluidAction.SIMULATE) == recipeinsmelting.getOutputfluidstack().getAmount()) {
                        this.result = recipeinsmelting.getOutputfluidstack();
                        this.meltTime = recipeinsmelting.getMelttime();
                        extractMaterial(recipeinsmelting);
                        markDirty2();
                    }
                }
                if (currentBurnTime >= maxBurnTime) {
                    currentBurnTime = 0;
                    maxBurnTime = 0;
                    isBurning = false;
                    BlockKlin.setState(isBurning, world, this.getPos());
                    markDirty2();
                }
            } else {
                if (tryToGetRecipe() != null) {
                    tryToExtractFuel(this.itemhandler, 2);
                }
                markDirty2();
            }

            if (recipe == null) {
                if (fluidHandler.getFluidAmount() != 0 && itemhandler.getStackInSlot(4) != ItemStack.EMPTY) {
                    recipe = RecipeHelper.GetKlinFluidRecipe(this.fluidHandler.getFluid(), itemhandler.getStackInSlot(4), world.getRecipeManager());
                    if (recipe != null) {
                        pouringCoolDown = recipe.getCooldown();
                    }
                    markDirty2();
                }
            } else if (currentcooldown < pouringCoolDown && this.isBurning()) {
                currentcooldown++;
                markDirty2();
            } else {
                if (RecipeHelper.canInsert(itemhandler.getStackInSlot(3), recipe.getOutput())) {
                    insertResult(3, recipe.getOutput());
                    fluidHandler.drain(recipe.getInputfluid(), IFluidHandler.FluidAction.EXECUTE);
                    currentcooldown = 0;
                    pouringCoolDown = 0;
                    recipe = null;
                    markDirty2();
                } else {
                    currentcooldown--;
                    markDirty2();
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
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.KLIN.getNameTextComponent();
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new KlinContainer(sycID, inventory, this.pos, this.world, progressBarNumArray);
    }

    public ItemStackHandler getItemHandler() {
        return itemhandler;
    }

    public static class KlinProgressBarNumArray implements IIntArray {
        private int[] iArray = {0, 0, 0, 0};

        @Override
        public int get(int index) {
            return iArray[index];
        }

        @Override
        public void set(int index, int value) {
            iArray[index] = value;
        }

        @Override
        public int size() {
            return 4;
        }
    }
}
