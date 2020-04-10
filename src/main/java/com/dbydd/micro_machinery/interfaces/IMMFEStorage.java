package com.dbydd.micro_machinery.interfaces;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFECableStatus;
import net.minecraftforge.energy.IEnergyStorage;
import org.apache.commons.math3.linear.RealVector;

public interface IMMFEStorage extends IEnergyStorage {

    int FEConversion(int Ft, int Fr);

    int getFortage();

    int getFurrect();

    int getPreviousFortage();

    int getPreviousFurrect();

    int loss(int FENeedToLoss);

    EnumInfluenceDirection generateInfluences();

    void ActionForgneticForce(RealVector force);

    void updateState();

    EnumMMFECableStatus getStatue();

    RealVector getPreviousForgneticForce();

    RealVector getForgneticForce();

}
