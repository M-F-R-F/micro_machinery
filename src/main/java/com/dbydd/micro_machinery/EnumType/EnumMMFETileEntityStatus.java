package com.dbydd.micro_machinery.EnumType;

import net.minecraft.util.IStringSerializable;

public enum EnumMMFETileEntityStatus implements IStringSerializable {
    CABLE_OUTPUT("cable_output"),
    CABLE_INPUT("cable_input"),
    CABLE("cable"),
    BOTH("both"),
    NULL("null"),
    ENERGYNET_OUTPUT("energynet_output"),
    ENERGYNET_INPUT("energynet_input"),
    FLUID_OUTPUT("fluid_output"),
    FLUID_INPUT("fluid_input"),
    ITEM_OUTPUT("item_output"),
    ITEM_INPUT("item_input");

    private final String name;

    EnumMMFETileEntityStatus(String name) {
        this.name = name;
    }

    public static boolean canRecive(EnumMMFETileEntityStatus status) {
        return status == EnumMMFETileEntityStatus.CABLE_INPUT || status == EnumMMFETileEntityStatus.BOTH || status == EnumMMFETileEntityStatus.CABLE;
    }

    public static boolean canExtract(EnumMMFETileEntityStatus status) {
        return status == EnumMMFETileEntityStatus.CABLE_OUTPUT || status == EnumMMFETileEntityStatus.BOTH || status == EnumMMFETileEntityStatus.CABLE;
    }

    @Override
    public String getName() {
        return this.name;
    }
}


