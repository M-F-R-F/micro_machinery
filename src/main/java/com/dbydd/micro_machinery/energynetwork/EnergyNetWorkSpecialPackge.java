package com.dbydd.micro_machinery.energynetwork;

import com.google.common.collect.Table;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EnergyNetWorkSpecialPackge {

    /**
     *          -x,north
     *          ↑
     * -z,west←   →z,east
     *          ↓
     *          x,south
     */

    private BlockPos lastBlock;
    EnumFacing fromLastBlockFacing;
    private int energyTransfer;

    public EnergyNetWorkSpecialPackge(BlockPos lastBlock, EnumFacing fromLastBlockFacing, int energyTransfer) {
        this.lastBlock = lastBlock;
        this.fromLastBlockFacing = fromLastBlockFacing;
        this.energyTransfer = energyTransfer;
    }

    public BlockPos getLastBlock() {
        return lastBlock;
    }

    public EnumFacing getFromLastBlockFacing() {
        return fromLastBlockFacing;
    }

    public int getEnergyTransfer() {
        return energyTransfer;
    }
}
