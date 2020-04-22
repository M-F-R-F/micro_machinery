package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import net.minecraft.util.ITickable;

public class TileEntityTickableEnergyCableWithOutGenerateForce extends TileEntityEnergyCableWithOutGenerateForce implements ITickable {

    protected static int Timer = 0;
    protected int energyInTransferThisSec = 0;
    protected int energyInTransferLastSec = 0;
    protected int energyRecivedInThisTick = 0;

    public TileEntityTickableEnergyCableWithOutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void update() {
        Timer++;
        energyInTransferThisSec += energyRecivedInThisTick;
        if (Timer == 20) {
            if (energyInTransferLastSec != energyInTransferThisSec) {
                notifyNearByCable();
            }
            Timer = 0;
            energyInTransferLastSec = energyInTransferThisSec;
            energyInTransferThisSec = 0;
        }
        markDirty();
        syncToTrackingClients();
    }

}
