package com.dbydd.micro_machinery.energynetwork;

import javax.vecmath.Vector3d;

public class FluxPowerVector extends Vector3d {

    private final double powerStength;

    public FluxPowerVector(double v, double v1, double v2, double powerStrength) {
        super(v, v1, v2);
        this.powerStength = powerStrength;
    }


}
