package com.dbydd.micro_machinery.interfaces;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.FluxFlowVector;
import com.dbydd.micro_machinery.energynetwork.FluxPowerVector;
import net.minecraftforge.energy.IEnergyStorage;

public interface IMMFEStorage extends IEnergyStorage {

    FluxPowerVector generateInfluences();

    void ActionForgneticForce(FluxPowerVector force);

    void updateState();

    EnumMMFETileEntityStatus updateStatue();

    FluxFlowVector getPreviousForgneticForce();

    FluxFlowVector getForgneticForce();

}
