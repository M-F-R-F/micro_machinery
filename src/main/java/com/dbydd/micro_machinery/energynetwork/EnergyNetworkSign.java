package com.dbydd.micro_machinery.energynetwork;

import com.dbydd.micro_machinery.util.RandomUtils;
import net.minecraft.nbt.NBTTagCompound;

public class EnergyNetworkSign {
    private int SIGN;
    private int energyStoragedOfNetwork = 0;
    private int maxEnergyCapacityOfNetwork = 0;

    public EnergyNetworkSign() {
        this.SIGN = RandomUtils.nextRandomInt();
    }

    public EnergyNetworkSign(int energyStoragedOfNetwork, int maxEnergyCapacityOfNetwork) {
        this.SIGN = RandomUtils.nextRandomInt();
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
        this.energyStoragedOfNetwork += energyStorageAdd;
    }

    public int getMaxEnergyCapacityOfNetwork() {
        return maxEnergyCapacityOfNetwork;
    }

    public void addMaxEnergyCapacityOfNetwork(int maxEnergyCapacityAdd) {
        this.maxEnergyCapacityOfNetwork += maxEnergyCapacityAdd;
        if (this.energyStoragedOfNetwork > maxEnergyCapacityOfNetwork) {
            this.energyStoragedOfNetwork = maxEnergyCapacityOfNetwork;
        }
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

    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!simulate) {
            if (energyStoragedOfNetwork - maxExtract < 0) {
                int tempInt = energyStoragedOfNetwork;
                energyStoragedOfNetwork = 0;
                return tempInt;
            } else {
                energyStoragedOfNetwork -= maxExtract;
                return maxExtract;
            }
        } else {
            if (energyStoragedOfNetwork - maxExtract < 0) {
                int tempInt = energyStoragedOfNetwork;
                energyStoragedOfNetwork = 0;
                return tempInt;
            } else {
                energyStoragedOfNetwork -= maxExtract;
                return maxExtract;
            }
        }
    }

    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!simulate) {
            if (energyStoragedOfNetwork + maxReceive > maxEnergyCapacityOfNetwork) {
                int i = maxEnergyCapacityOfNetwork - energyStoragedOfNetwork;
                energyStoragedOfNetwork = maxEnergyCapacityOfNetwork;
                return i;
            } else {
                energyStoragedOfNetwork += maxReceive;
                return maxReceive;
            }
        } else {
            if (energyStoragedOfNetwork + maxReceive > maxEnergyCapacityOfNetwork) {
                return maxEnergyCapacityOfNetwork - energyStoragedOfNetwork;

            } else {
                return maxReceive;
            }
        }
    }

}
