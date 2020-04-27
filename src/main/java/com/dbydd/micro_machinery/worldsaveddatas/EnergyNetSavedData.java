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

    private static List<EnergyNetworkSign> networkSignList = new ArrayList<>();

    public EnergyNetSavedData(String name) {
        super(name);
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

    public static EnergyNetSavedData getData(World world){
        WorldSavedData data = world.getMapStorage().getOrLoadData(EnergyNetSavedData.class, "MMEnergyNetworkSavedData");
        if (data == null)
        {
            data = new EnergyNetSavedData("MMEnergyNetworkSavedData");
            world.getMapStorage().setData("MMEnergyNetworkSavedData", data);
        }
        return (EnergyNetSavedData) data;
    }

}
