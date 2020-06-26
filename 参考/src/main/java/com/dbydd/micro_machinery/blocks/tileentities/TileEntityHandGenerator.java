package com.dbydd.micro_machinery.blocks.tileentities;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityHandGenerator extends TileEntity implements ITickable, IEnergyStorage {

    private int progress = 0;
    private boolean isGenerating = false;
    private int HAND_GENERATOR_MAX_ROTATE_TIME = 20;

    public int getHAND_GENERATOR_MAX_ROTATE_TIME() {
        return HAND_GENERATOR_MAX_ROTATE_TIME;
    }

    private void pushEnergyBehind() {
        EnumFacing behind = EnumFacing.fromAngle(world.getBlockState(pos).getValue(BlockHorizontal.FACING).getHorizontalAngle() + 90f);
        TileEntity te = world.getTileEntity(pos.offset(behind));
        if (te != null) {
            if (te.hasCapability(CapabilityEnergy.ENERGY, behind.getOpposite())) {
                te.getCapability(CapabilityEnergy.ENERGY, behind.getOpposite()).receiveEnergy(20, false);
                world.playerEntities.get(0).sendMessage(new TextComponentString("facing == " + behind.getOpposite()));
            }
        }
    }

    public IBlockState getBlockState() {
        return world.getBlockState(pos);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        progress = compound.getInteger("progress");
        isGenerating = compound.getBoolean("isgenerating");
        HAND_GENERATOR_MAX_ROTATE_TIME = compound.getInteger("max_rotate_time");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("progress", progress);
        compound.setBoolean("isgenerating", isGenerating);
        compound.setInteger("max_rotate_time", HAND_GENERATOR_MAX_ROTATE_TIME);
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (facing == world.getBlockState(pos).getValue(BlockHorizontal.FACING).getOpposite())
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing == world.getBlockState(pos).getValue(BlockHorizontal.FACING).getOpposite())
            return (T) this;
        return super.getCapability(capability, facing);
    }

    public void actived() {
        isGenerating = true;
        markDirty();
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (isGenerating) {
                if (progress >= HAND_GENERATOR_MAX_ROTATE_TIME) {
                    isGenerating = false;
                    progress = 0;
                    markDirty();
                } else {
                    progress++;
                    pushEnergyBehind();
                    markDirty();
                }
                syncToTrackingClients();
            }
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    public int getProgress() {
        return this.progress;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
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
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
}
