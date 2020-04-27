package com.dbydd.micro_machinery.energynetwork;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyNetworkSign {
    private static int i = 0;
    private int SIGN;
    private int energyStoragedOfNetwork = 0;
    private int maxEnergyCapacityOfNetwork = 0;

    public EnergyNetworkSign(int i) {
        this.SIGN = i++;
    }

    public EnergyNetworkSign(NBTTagCompound nbtTagCompound) {
        this.readFromNBT(nbtTagCompound);
    }

    public int getSIGN() {
        return SIGN;
    }

    public int getEnergyStoragedOfNetwork() {
        return energyStoragedOfNetwork;
    }

    public int getMaxEnergyCapacityOfNetwork() {
        return maxEnergyCapacityOfNetwork;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound NBT) {
        NBT.setInteger("sign", SIGN);
        NBT.setInteger("energyStoragedOfNetwork", energyStoragedOfNetwork);
        NBT.setInteger("maxEnergyCapacityOfNetwork", maxEnergyCapacityOfNetwork);
        return NBT;
    }

    public NBTTagCompound writeToNBT(){
        NBTTagCompound NBT = new NBTTagCompound();
        NBT.setInteger("sign", SIGN);
        NBT.setInteger("energyStoragedOfNetwork", energyStoragedOfNetwork);
        NBT.setInteger("maxEnergyCapacityOfNetwork", maxEnergyCapacityOfNetwork);
        return NBT;
    }

    public void readFromNBT(NBTTagCompound NBT) {
        this.SIGN = NBT.getInteger("sign");
        this.energyStoragedOfNetwork = NBT.getInteger("energyStoragedOfNetwork");
        this.maxEnergyCapacityOfNetwork = NBT.getInteger("maxEnergyCapacityOfNetwork");
    }

}
