package com.dbydd.micro_machinery.util;

import net.minecraft.util.EnumFacing;

public class EnergyNetWorkUtils {
    private static final EnumFacing[] facings = {EnumFacing.UP, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.DOWN};

    private EnergyNetWorkUtils() {
    }

    public static EnumFacing[] getFacings() {
        return facings;
    }


}
