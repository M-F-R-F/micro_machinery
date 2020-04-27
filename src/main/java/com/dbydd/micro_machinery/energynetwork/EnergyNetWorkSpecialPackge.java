package com.dbydd.micro_machinery.energynetwork;

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

    EnumFacing fromLastBlockFacing;

    private BlockPos lastBlock;

    public EnergyNetWorkSpecialPackge(BlockPos lastBlock, EnumFacing fromLastBlockFacing) {
        this.lastBlock = lastBlock;
        this.fromLastBlockFacing = fromLastBlockFacing;
    }

    public BlockPos getLastBlock() {
        return lastBlock;
    }

    public EnumFacing getFromLastBlockFacing() {
        return fromLastBlockFacing;
    }

}
