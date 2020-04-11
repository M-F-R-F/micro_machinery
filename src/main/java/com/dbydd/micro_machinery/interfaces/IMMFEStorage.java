package com.dbydd.micro_machinery.interfaces;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFETineEntityStatus;
import net.minecraftforge.energy.IEnergyStorage;

import javax.vecmath.Vector3d;

public interface IMMFEStorage extends IEnergyStorage {

    /**
     *
     * @param Ft
     *    Forge + Voltage = fortage
     *    Yes, it's likely Voltage.
     * @param Fr
     *    Forge + Currect = Furrect
     *    It's not "furrect"
     *
     * @return FE = Ft * Fr
     */
    int FEConversion(int Ft, int Fr);

    int getFortage();


    int getFurrect();

    int getPreviousFortage();

    int getPreviousFurrect();

    int loss(int FENeedToLoss);

    int getLossValue();

    EnumInfluenceDirection generateInfluences();

    void ActionForgneticForce(Vector3d force);

    void updateState();

    EnumMMFETineEntityStatus updateStatue();

    Vector3d getPreviousForgneticForce();

    Vector3d getForgneticForce();

}
