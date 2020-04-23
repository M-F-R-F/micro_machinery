package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import net.minecraft.util.ITickable;

public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {

    protected static int Timer = 0;
    protected int energyInTransferThisSec = 0;
    protected int energyInTransferLastSec = 0;
    protected int energyRecivedInThisTick = 0;

    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void update() {
        Timer++;
        energyInTransferThisSec += energyRecivedInThisTick;
        if (Timer == 20) {
            if (energyInTransferLastSec != energyInTransferThisSec) {
                notifyNearbyCables();
            }
            Timer = 0;
            energyInTransferLastSec = energyInTransferThisSec;
            energyInTransferThisSec = 0;
        }
        markDirty();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(!simulate) {
            if (maxReceive + energyStored >= maxEnergyCapacity) {
                energyRecivedInThisTick += maxEnergyCapacity = energyStored;
                energyStored = maxEnergyCapacity;
                markDirty();
                return maxReceive - (maxEnergyCapacity-energyStored);
            } else {
                energyRecivedInThisTick += maxReceive;
                energyStored += maxReceive;
                markDirty();
                return maxReceive;
            }
        }else {
            if (maxReceive + energyStored >= maxEnergyCapacity) {
                return maxReceive - (maxEnergyCapacity-energyStored);
            } else {
                return 0;
            }
        }
    }
}
