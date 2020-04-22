package com.dbydd.micro_machinery.energynetwork;

import com.google.common.collect.Table;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EnergyNetWorkSpecialPackge {

    /**
     * -x,north
     * ↑
     * -z,west←   →z,east
     * ↓
     * x,south
     */

    private BlockPos nextBlock;
    private BlockPos[] lastBlocks;
    private int energyTransfer;

    public EnergyNetWorkSpecialPackge(BlockPos nextBlock, BlockPos[] lastBlocks, int energyTransfer) {
        this.nextBlock = nextBlock;
        this.lastBlocks = lastBlocks;
        this.energyTransfer = energyTransfer;
    }

    public BlockPos getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(BlockPos nextBlock) {
        this.nextBlock = nextBlock;
    }

    public BlockPos[] getLastBlock() {
        return lastBlocks;
    }

    public void setLastBlock(BlockPos[] lastBlocks) {
        this.lastBlocks = lastBlocks;
    }

    public int getEnergyTransfer() {
        return energyTransfer;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("EnergyNetwork_NextBlock_X", nextBlock.getX());
        nbt.setInteger("EnergyNetwork_NextBlock_Y", nextBlock.getY());
        nbt.setInteger("EnergyNetwork_NextBlock_Z", nextBlock.getZ());
        for (int i = 0; i <= lastBlocks.length; i++) {
            nbt.setInteger("EnergyNetwork_LastBlock"+i+"_X", lastBlocks[i].getX());
            nbt.setInteger("EnergyNetwork_LastBlock"+i+"_Y", lastBlocks[i].getY());
            nbt.setInteger("EnergyNetwork_LastBlock"+i+"_Z", lastBlocks[i].getZ());
        }
        nbt.setInteger("energyTransfer", energyTransfer);
        nbt.setInteger("BlockCounts", lastBlocks.length);
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.nextBlock = new BlockPos(nbt.getInteger("EnergyNetwork_NextBlock_X"), nbt.getInteger("EnergyNetwork_NextBlock_Y"), nbt.getInteger("EnergyNetwork_NextBlock_Z"));
        for(int i = 0;i<=nbt.getInteger("BlockCounts");i++) {
            this.lastBlocks[i] = new BlockPos(nbt.getInteger("EnergyNetwork_LastBlock"+i+"_X"), nbt.getInteger("EnergyNetwork_LastBlock"+i+"_Y"), nbt.getInteger("EnergyNetwork_LastBlock"+i+"_Z"));
        }
        this.energyTransfer = nbt.getInteger("energyTransfer");
    }

}
