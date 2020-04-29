package com.dbydd.micro_machinery.energynetwork;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyNetworkSign {
    private static int i = 0;
    private int SIGN;
    private int energyStoragedOfNetwork = 0;
    private int maxEnergyCapacityOfNetwork = 0;

    public EnergyNetworkSign() {
        this.SIGN = i++;
    }

    public EnergyNetworkSign(int energyStoragedOfNetwork, int maxEnergyCapacityOfNetwork) {
        this.SIGN = i++;
        this.energyStoragedOfNetwork = energyStoragedOfNetwork;
        this.maxEnergyCapacityOfNetwork = maxEnergyCapacityOfNetwork;
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

    public void addEnergyStoragedOfNetwork(int energyStorageAdd) {
        this.energyStoragedOfNetwork += energyStoragedOfNetwork;
    }

    public int getMaxEnergyCapacityOfNetwork() {
        return maxEnergyCapacityOfNetwork;
    }

    public void addMaxEnergyCapacityOfNetwork(int maxEnergyCapacityAdd) {
        this.maxEnergyCapacityOfNetwork += maxEnergyCapacityOfNetwork;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound NBT) {
        NBT.setInteger("sign", SIGN);
        NBT.setInteger("energyStoragedOfNetwork", energyStoragedOfNetwork);
        NBT.setInteger("maxEnergyCapacityOfNetwork", maxEnergyCapacityOfNetwork);
        return NBT;
    }

    public NBTTagCompound writeToNBT() {
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
