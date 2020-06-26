package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.blocks.machine.BlockKlin;
import com.dbydd.micro_machinery.recipes.RecipeHelper;
import com.dbydd.micro_machinery.recipes.klin.KlinFluidRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityKlin extends TileEntity implements IItemHandler, IFluidHandler, ITickable {


    private FluidTank fluidhandler = new FluidTank(2000);
    private FluidStack result = null;
    private KlinFluidRecipe recipe = null;
    private int melttime = 0;
    private int currentmelttime = 0;
    private int burntime = 0;
    private int maxburntime = 0;
    private ItemStackHandler itemhandler = new ItemStackHandler(5);
    private int pouringcooldown = 0;
    private int currentcooldown = 0;
    private boolean isBurning = false;

    public FluidTank getFluidhandler() {
        return fluidhandler;
    }

    public FluidStack getResult() {
        return result;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        isBurning = compound.getBoolean("isBurning");
        itemhandler.deserializeNBT(compound.getCompoundTag("Inventory"));
        melttime = compound.getInteger("melt time needed");
        currentmelttime = compound.getInteger("current melt time");
        burntime = compound.getInteger("burntime");
        maxburntime = compound.getInteger("maxburntime");
        pouringcooldown = compound.getInteger("pouringcooldown");
        currentcooldown = compound.getInteger("currentcooldown");
        fluidhandler = fluidhandler.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("melt time needed", melttime);
        compound.setInteger("current melt time", currentmelttime);
        compound.setInteger("burntime", burntime);
        compound.setInteger("maxburntime", maxburntime);
        compound.setTag("Inventory", itemhandler.serializeNBT());
        compound.setInteger("pouringcooldown", pouringcooldown);
        compound.setInteger("currentcooldown", currentcooldown);
        compound.setBoolean("isBurning", isBurning);
        return super.writeToNBT(fluidhandler.writeToNBT(compound));
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.itemhandler;
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this.fluidhandler;
        return super.getCapability(capability, facing);
    }

    public boolean isBurning() {
        return isBurning;
    }

    public boolean issmelting() {
        return getResult() != null && melttime != 0 && isBurning;
    }

    @Override
    public int getSlots() {
        return 5;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return itemhandler.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return itemhandler.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return itemhandler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemhandler.getSlotLimit(slot);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return fluidhandler.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return fluidhandler.fill(resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return fluidhandler.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return fluidhandler.drain(maxDrain, doDrain);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (isBurning) {
                this.burntime++;
                if (issmelting()) {
                    currentmelttime++;
                    if (currentmelttime >= melttime) {
                        if (fluidhandler.canFillFluidType(result)) {
                            this.fluidhandler.fill(result, true);
                            result = null;
                            currentmelttime = 0;
                            markDirty();
                            this.syncToTrackingClients();
                        } else {
                            currentmelttime--;
                            markDirty();
                            this.syncToTrackingClients();
                        }
                        markDirty();
                        this.syncToTrackingClients();
                    }
                } else {
                    KlinRecipe recipeinsmelting = tryToGetRecipe();
                    if (recipeinsmelting != null && fluidhandler.fill(recipeinsmelting.outputfluidstack, false) == recipeinsmelting.outputfluidstack.amount) {
                        this.result = recipeinsmelting.outputfluidstack;
                        this.melttime = recipeinsmelting.melttime;
                        extractMaterial(recipeinsmelting);
                        markDirty();
                        this.syncToTrackingClients();
                    }
                }
                if (burntime >= maxburntime) {
                    burntime = 0;
                    maxburntime = 0;
                    isBurning = false;
                    BlockKlin.setState(isBurning, world, this.getPos());
                    markDirty();
                    this.syncToTrackingClients();
                }
            } else {
                if (tryToGetRecipe() != null)
                    tryToGetFuel(this.itemhandler, 2);
                markDirty();
                this.syncToTrackingClients();
            }

            if (recipe == null) {
                if (fluidhandler.getFluidAmount() != 0 && getStackInSlot(4) != ItemStack.EMPTY) {
                    recipe = RecipeHelper.GetKlinFluidRecipe(this.fluidhandler.getFluid(), getStackInSlot(4));
                    if (recipe != null) pouringcooldown = recipe.cooldown;
                    markDirty();
                    this.syncToTrackingClients();
                }
            } else if (currentcooldown < pouringcooldown) {
                currentcooldown++;
                markDirty();
                this.syncToTrackingClients();
            } else {
                if (RecipeHelper.canInsert(getStackInSlot(3), recipe.output)) {
                    insertResult(3, recipe.output);
                    fluidhandler.drain(recipe.cast.amount, true);
                    currentcooldown = 0;
                    pouringcooldown = 0;
                    recipe = null;
                    markDirty();
                    this.syncToTrackingClients();
                } else {
                    currentcooldown--;
                    markDirty();
                    this.syncToTrackingClients();
                }
            }
        }
    }

    private void insertResult(int slot, ItemStack output) {
        itemhandler.setStackInSlot(slot, new ItemStack(output.getItem(), getStackInSlot(slot).getCount() + output.getCount()));
    }


    private void extractMaterial(KlinRecipe recipeinsmelting) {
        if (itemhandler.getStackInSlot(0).getItem() == recipeinsmelting.input1.getItem()) {
            itemhandler.extractItem(1, recipeinsmelting.input2.getCount(), false);
            itemhandler.extractItem(0, recipeinsmelting.input1.getCount(), false);
        } else {
            itemhandler.extractItem(1, recipeinsmelting.input1.getCount(), false);
            itemhandler.extractItem(0, recipeinsmelting.input2.getCount(), false);
        }
    }

    private KlinRecipe tryToGetRecipe() {
        return RecipeHelper.GetKlinRecipe(itemhandler.getStackInSlot(0), itemhandler.getStackInSlot(1));
    }

    private void tryToGetFuel(ItemStackHandler handler, int index) {
        if (handler.getStackInSlot(index) != ItemStack.EMPTY) {
            maxburntime = TileEntityFurnace.getItemBurnTime(handler.getStackInSlot(index));
            if (maxburntime != 0) {
                handler.extractItem(index, 1, false);
                isBurning = true;
                BlockKlin.setState(isBurning, world, this.getPos());
            }
        }
    }

    public ItemStack getCast() {
        return itemhandler.getStackInSlot(5);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock() && super.shouldRefresh(world, pos, oldState, newState);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return melttime;
            case 1:
                return currentmelttime;
            case 2:
                return burntime;
            case 3:
                return maxburntime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.melttime = value;
                break;
            case 1:
                this.currentmelttime = value;
                break;
            case 2:
                this.burntime = value;
//            case 3:
//                this.maxburntime = value;
                break;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        nbtTag = this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.getNbtCompound();
        this.readFromNBT(tag);
    }

    protected final void syncToTrackingClients() {
        if (!this.world.isRemote) {
            SPacketUpdateTileEntity packet = this.getUpdatePacket();
            PlayerChunkMapEntry trackingEntry = ((WorldServer) this.world).getPlayerChunkMap().getEntry(this.pos.getX() >> 4, this.pos.getZ() >> 4);
            if (trackingEntry != null) {
                for (EntityPlayerMP player : trackingEntry.getWatchingPlayers()) {
                    player.connection.sendPacket(packet);
                }
            }
        }
    }
}