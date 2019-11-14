package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.recipes.KlinRecipe;
import com.dbydd.micro_machinery.recipes.RecipeHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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


    private static KlinRecipe recipeinsmelting = null;
    private int melttime = -1;
    private int currentmelttime = -1;
    private int burntime = 0;
    private FluidTank fluidhandler = new FluidTank(2000);
    private ItemStackHandler itemhandler = new ItemStackHandler(4);
    private World worldObj;

    public static KlinRecipe getRecipeinsmelting() {
        return recipeinsmelting;
    }

    public ItemStackHandler getItemhandler() {
        return itemhandler;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemhandler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.melttime = compound.getInteger("melt time needed");
        this.currentmelttime = compound.getInteger("current melt time");
        this.burntime = compound.getInteger("burntime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("melt time needed", melttime);
        compound.setInteger("current melt time", currentmelttime);
        compound.setInteger("burntime", burntime);
        compound.setTag("Inventory", this.itemhandler.serializeNBT());

        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return true;
        else return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.itemhandler;
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this.fluidhandler;
        return super.getCapability(capability, facing);
    }

    @Override
    public int getSlots() {
        return 4;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return itemhandler.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
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
//        if (!this.worldObj.isRemote) {
        if (burntime == 0) {
            if (itemhandler.getStackInSlot(2).getItem() == Items.COAL && recipeinsmelting != null) {
                burntime += 600;
                extractItem(2, 1, false);
                markDirty();
            }
        } else burntime--;

        if (((recipeinsmelting = RecipeHelper.CanKlinSmelt(itemhandler.getStackInSlot(0), itemhandler.getStackInSlot(1), fluidhandler)).outputfluidstack != null) && (currentmelttime == -1)) {
            this.melttime = recipeinsmelting.melttime;
            currentmelttime = 0;
            currentmelttime++;
            markDirty();
        }

        if ((currentmelttime != 0 && currentmelttime != melttime) && burntime != 0) {
            currentmelttime++;
            markDirty();
        } else if (currentmelttime == melttime) {
            RecipeHelper.KlinSmelt(this);
            recipeinsmelting = null;
            melttime = 0;
            currentmelttime = -1;
            markDirty();
        }
//        }
    }


    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return melttime;
            case 1:
                return currentmelttime;
            case 2:
                return burntime;
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
                break;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

}
