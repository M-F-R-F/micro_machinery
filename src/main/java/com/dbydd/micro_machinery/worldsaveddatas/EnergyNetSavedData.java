package com.dbydd.micro_machinery.worldsaveddatas;

import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
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
        EnergyNetSavedData data = getData(world);
        if (data.getSign(sign) != null) {
            data.getSign(sign).addMaxEnergyCapacityOfNetwork(energyCapacityAdd);
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
            }
        }
    }

    public static void addSign(World world, EnergyNetworkSign sign) {
        EnergyNetSavedData data = EnergyNetSavedData.getData(world);
        data.addSign(sign);
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



