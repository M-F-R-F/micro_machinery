package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import com.dbydd.micro_machinery.energynetwork.FluxFlowVector;
import com.dbydd.micro_machinery.energynetwork.FluxPowerVector;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class MMFEMachineBase extends TileEntity implements IMMFEStorage {

    protected int maxEnergyCapacity;
    protected int energyStored;
    protected EnumMMFETileEntityStatus status;

    MMFEMachineBase(int maxEnergyCapacity, EnumMMFETileEntityStatus status) {
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.status = status;
    }

    public EnumMMFETileEntityStatus getStatus() {
        return status;
    }
//    protected EnumInfluenceDirection influenceDirection;

    public int getMaxEnergyCapacity() {
        return maxEnergyCapacity;
    }

    @Override
    public boolean canExtract() {
        return EnumMMFETileEntityStatus.canExtract(status);
    }

    @Override
    public boolean canReceive() {
        return EnumMMFETileEntityStatus.canRecive(status);
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergyCapacity;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) return 0;
        if (maxReceive + energyStored >= energyStored) {
            if (!simulate) energyStored = maxEnergyCapacity;
            return maxReceive + energyStored - maxEnergyCapacity;
        } else {
            if (!simulate)
                energyStored += maxReceive;
            return 0;
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract()) return 0;
        int output = 0;
        if (maxExtract >= energyStored) {
            output = energyStored;
            if (!simulate) energyStored = 0;
            return output;
        } else {
            if (!simulate)
                energyStored -= maxExtract;
            return maxExtract;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energystored", energyStored);
        compound.setInteger("maxEnergyCapacity", maxEnergyCapacity);
        compound.setString("status", status.name());
//        compound.setString("influenceDirection",influenceDirection.name() );
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
//        this.influenceDirection = EnumInfluenceDirection.valueOf(compound.getString("influenceDirection"));
        this.status = EnumMMFETileEntityStatus.valueOf(compound.getString("status"));
        this.maxEnergyCapacity = compound.getInteger("maxEnergyCapacity");
        this.energyStored = compound.getInteger("energystored");
    }

    @Override
    public FluxPowerVector generateInfluences() {
        return null;
    }

    @Override
    public void ActionForgneticForce(FluxPowerVector force) {

    }

    @Override
    public FluxFlowVector getForgneticForce() {
        return null;
    }

    @Override
    public FluxFlowVector getPreviousForgneticForce() {
        return null;
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return null;
    }

    @Override
    public void updateState() {

    }

    protected boolean pushEnergy(BlockPos pos, EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            if (te.hasCapability(CapabilityEnergy.ENERGY, facing)) {
                int energyRecived = te.getCapability(CapabilityEnergy.ENERGY, facing).receiveEnergy(energyStored, false);
                energyStored -= energyRecived;
                return energyRecived != 0;
            }
        }
        return false;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
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

    public int getField(int level) {
        return 0;
    }

    public void setField(int id, int data) {
    }
}

