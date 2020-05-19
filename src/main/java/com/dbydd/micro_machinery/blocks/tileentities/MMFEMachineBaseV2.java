package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.energynetwork.FluxFlowVector;
import com.dbydd.micro_machinery.energynetwork.FluxPowerVector;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public abstract class MMFEMachineBaseV2 extends TileEntity implements IMMFETransfer, IMMFEStorage {
    protected int maxEnergyCapacity;
    protected int energyStored;
    protected SurrondingsState states;

    MMFEMachineBaseV2(int maxEnergyCapacity, SurrondingsState states) {
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.states = states;
    }

    public SurrondingsState getStates() {
        return states;
    }

    public int getMaxEnergyCapacity() {
        return maxEnergyCapacity;
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energystored", energyStored);
        compound.setInteger("maxEnergyCapacity", maxEnergyCapacity);
        compound = states.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.maxEnergyCapacity = compound.getInteger("maxEnergyCapacity");
        this.energyStored = compound.getInteger("energystored");
        states.readFromNBT(compound);
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
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return true;
        return super.hasCapability(capability, facing);
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

    @Override
    public FluxPowerVector generateInfluences() {
        return null;
    }

    @Override
    public void ActionForgneticForce(FluxPowerVector force) {

    }

    @Override
    public FluxFlowVector getPreviousForgneticForce() {
        return null;
    }

    @Override
    public FluxFlowVector getForgneticForce() {
        return null;
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
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public void updateState() {

    }
}
