package com.dbydd.micro_machinery.energynetwork;

import javax.vecmath.Vector3d;

public class FluxFlowVector extends Vector3d {
    private final int fluxFlow;

    public FluxFlowVector(double v, double v1, double v2, int fluxFlow) {
        super(v, v1, v2);
        this.fluxFlow = fluxFlow;
    }

    public int getFluxFlow() {
        return fluxFlow;
    }

    public FluxPowerVector generateFluxPower(){
        double fluxPower = Math.sqrt(Math.sqrt(fluxFlow));
        return new FluxPowerVector(0,0,0,fluxPower);
    }

}
