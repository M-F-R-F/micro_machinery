package com.dbydd.micro_machinery.EnumType;

public enum EnumMMFETileEntityStatus {
    OUTPUT, INPUT, CABLE, BOTH, NULL;

    public static boolean canRecive(EnumMMFETileEntityStatus status) {
        return status == EnumMMFETileEntityStatus.INPUT || status == EnumMMFETileEntityStatus.BOTH || status == EnumMMFETileEntityStatus.CABLE;
    }

    public static boolean canExtract(EnumMMFETileEntityStatus status) {
        return status == EnumMMFETileEntityStatus.OUTPUT || status == EnumMMFETileEntityStatus.BOTH || status == EnumMMFETileEntityStatus.CABLE;
    }
}


