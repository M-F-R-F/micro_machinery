package com.dbydd.micro_machinery.energynetwork;

import net.minecraft.util.EnumFacing;

public class EnergyNetWorkSpecialPackge {

    /**
     * -x,north
     * ↑
     * -z,west←   →z,east
     * ↓
     * x,south
     */

    private int sign;
    private int sequence;
    private EnumFacing fromFacing;

    public EnergyNetWorkSpecialPackge() {
    }

    public EnergyNetWorkSpecialPackge(int sign, int sequence) {
        this.sign = sign;
        this.sequence = sequence;
    }

    public EnumFacing getFromFacing() {
        return fromFacing;
    }

    public void setFromFacing(EnumFacing fromFacing) {
        this.fromFacing = fromFacing;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
