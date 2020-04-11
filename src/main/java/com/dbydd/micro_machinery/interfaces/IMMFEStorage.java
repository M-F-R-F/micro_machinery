package com.dbydd.micro_machinery.interfaces;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.vector.FluxFlowVector;
import com.dbydd.micro_machinery.vector.FluxPowerVector;
import net.minecraftforge.energy.IEnergyStorage;

import javax.vecmath.Vector3d;

public interface IMMFEStorage extends IEnergyStorage {

    int loss(int FENeedToLoss);

    int getLossValue();

    FluxPowerVector generateInfluences();

    void ActionForgneticForce(FluxPowerVector force);

    void updateState();

    EnumMMFETileEntityStatus updateStatue();

    FluxFlowVector getPreviousForgneticForce();

    FluxFlowVector getForgneticForce();

}
