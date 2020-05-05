package com.dbydd.micro_machinery.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EnergyNetWorkUtils {
    private static final EnumFacing[] facings = {EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.EAST};

    private EnergyNetWorkUtils() {
    }

    public static EnumFacing[] getFacings() {
        return facings;
    }


    public static EnumFacing getFacing(BlockPos pos, BlockPos neighbor) {
        int x = neighbor.getX() - pos.getX();
        int y = neighbor.getY() - pos.getY();
        int z = neighbor.getZ() - pos.getZ();

        if (x > 0) {
            return EnumFacing.EAST;
        } else if (x < 0) {
            return EnumFacing.WEST;
        }

        if (y > 0) {
            return EnumFacing.UP;
        } else if (y < 0) {
            return EnumFacing.DOWN;
        }

        if (z > 0) {
            return EnumFacing.SOUTH;
        } else {
            return EnumFacing.NORTH;
        }

    }
}
