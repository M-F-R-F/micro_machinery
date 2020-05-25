package com.dbydd.micro_machinery.worldsaveddatas;

import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
import com.dbydd.micro_machinery.exceptions.ErrorNumberException;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;

public class EnergyNetSavedData extends WorldSavedData {

    private List<EnergyNetworkSign> networkSignList = new ArrayList<>();

    public EnergyNetSavedData(String name) {
        super(name);
    }

    public static EnergyNetSavedData getData(World world) {
        WorldSavedData data = world.getMapStorage().getOrLoadData(EnergyNetSavedData.class, "MMEnergyNetworkSavedData");
        if (data == null) {
            data = new EnergyNetSavedData("MMEnergyNetworkSavedData");
            world.getMapStorage().setData("MMEnergyNetworkSavedData", data);
        }
        return (EnergyNetSavedData) data;
    }

    public static void updateEnergyNetCapacity(World world, int energyCapacityAdd, int sign) {
        EnergyNetSavedData data = EnergyNetSavedData.getData(world);
        if (data.getSign(sign) != null) {
            data.getSign(sign).addMaxEnergyCapacityOfNetwork(energyCapacityAdd);
            if (data.getSign(sign).getMaxEnergyCapacityOfNetwork() <= 0) {
                RemoveSign(sign, world);
            }
            data.markDirty();
        }
    }

    public static void updateEnergyNetEnergy(World world, int energyAdd, int sign) {
        EnergyNetSavedData data = getData(world);
        if (data.getSign(sign) != null) {
            data.getSign(sign).addEnergyStoragedOfNetwork(energyAdd);
            data.markDirty();
        }
    }

    public static void RemoveSign(int sign, World world) {
        EnergyNetSavedData data = getData(world);
        List<EnergyNetworkSign> list = data.getNetworkSignList();
        for (EnergyNetworkSign energyNetworkSign : list) {
            if (energyNetworkSign.getSIGN() == sign) {
                list.remove(energyNetworkSign);
                data.markDirty();
                break;
            }
        }
    }

    public static void addSign(World world, EnergyNetworkSign sign) {
        EnergyNetSavedData data = EnergyNetSavedData.getData(world);
        data.addSign(sign);
        data.markDirty();
    }

    public static void mergeEnergyNet(int signMergeIn, int signNeedToMerge, World world) {
        EnergyNetSavedData data = getData(world);
        EnergyNetworkSign sign1 = data.getSign(signMergeIn);
        EnergyNetworkSign sign2 = data.getSign(signNeedToMerge);
        sign1.addMaxEnergyCapacityOfNetwork(sign2.getMaxEnergyCapacityOfNetwork());
        sign1.addEnergyStoragedOfNetwork(sign2.getEnergyStoragedOfNetwork());
        RemoveSign(signNeedToMerge, world);
        data.markDirty();
    }

    public static int ExtractEnergy(int Sign, int maxExtract, boolean simulate, World world) {
        EnergyNetSavedData data = EnergyNetSavedData.getData(world);
        EnergyNetworkSign sign = data.getSign(Sign);
        if(sign != null) {
            int i = sign.extractEnergy(maxExtract, simulate);
            data.markDirty();
            return i;
        }
        return 0;
    }

    public static int ReciveEnergy(int Sign, int maxRecive, boolean simulate, World world) {
        EnergyNetSavedData data = EnergyNetSavedData.getData(world);
        EnergyNetworkSign sign = data.getSign(Sign);
        if(sign != null) {
            int i = sign.receiveEnergy(maxRecive, simulate);
            data.markDirty();
            return i;
        }else return 0;
    }

    public static boolean splitTwoEnergyNet(EnergyNetworkSign sign1, int amountOfSign1, int capacityOfSign1, EnergyNetworkSign sign2, int amountOfSign2, int capacityOfSign2, int signSIGNNeedToSplit, World world) throws ErrorNumberException {
        EnergyNetSavedData data = getData(world);
        EnergyNetworkSign signNeedToSplit = data.getSign(signSIGNNeedToSplit);
        if (capacityOfSign1 + capacityOfSign2 == signNeedToSplit.getMaxEnergyCapacityOfNetwork()) {
            int amountOfAllCable = amountOfSign1 + amountOfSign2;
            int EnergyStorageOfSign1 = Math.round(signNeedToSplit.getEnergyStoragedOfNetwork() * (amountOfSign1 / amountOfAllCable));
            int EnergyStorageOfSign2 = Math.round(signNeedToSplit.getEnergyStoragedOfNetwork() * (amountOfSign2 / amountOfAllCable));

            EnergyNetworkSign SignNeedToSplit = data.getSign(signNeedToSplit.getSIGN());
            sign1.addMaxEnergyCapacityOfNetwork(capacityOfSign1);
            sign1.addEnergyStoragedOfNetwork(EnergyStorageOfSign1);

            sign2.addMaxEnergyCapacityOfNetwork(capacityOfSign2);
            sign2.addEnergyStoragedOfNetwork(EnergyStorageOfSign2);

            data.addSign(sign1);
            data.addSign(sign2);
            RemoveSign(signNeedToSplit.getSIGN(), world);
            return true;
        }else throw new ErrorNumberException("The capacity of energynet has wrong, pleast commit issuse");

    }

    public List<EnergyNetworkSign> getNetworkSignList() {
        return networkSignList;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        networkSignList.clear();
        NBTTagList list = (NBTTagList) nbt.getTag("MMEnergyNetworkSignList");
        if (list == null) {
            list = new NBTTagList();
        }

        for (NBTBase nbtBase : list) {
            NBTTagCompound NBT = (NBTTagCompound) nbtBase;
            networkSignList.add(new EnergyNetworkSign(NBT));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (EnergyNetworkSign energyNetworkSign : networkSignList) {
            list.appendTag(energyNetworkSign.writeToNBT());
        }

        compound.setTag("MMEnergyNetworkSignList", list);

        return compound;
    }

    public void addSign(EnergyNetworkSign sign) {
        networkSignList.add(sign);
        this.markDirty();
    }

    public EnergyNetworkSign getSign(int sign) {
        for (EnergyNetworkSign energyNetworkSign : networkSignList) {
            if (energyNetworkSign.getSIGN() == sign) {
                return energyNetworkSign;
            }
        }
        return null;
    }

}



