package com.dbydd.micro_machinery.vector;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class FluxPowerVector extends Vector3d {

    private final double powerStength;

    public FluxPowerVector(double v, double v1, double v2, double powerStrength) {
        super(v, v1, v2);
        this.powerStength = powerStrength;
    }


}
