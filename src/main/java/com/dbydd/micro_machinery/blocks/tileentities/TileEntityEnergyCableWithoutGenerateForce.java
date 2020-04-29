package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {

    private int sign;

    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.sign = compound.getInteger("EnergyNetSign");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("EnergyNetSign", sign);
        return super.writeToNBT(compound);
    }

    @Override
    public void updateState() {
    //todo 重写
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return EnumMMFETileEntityStatus.CABLE;
    }

    @Override
    public EnergyNetWorkSpecialPackge generatePackage(EnumFacing facing) {
        return new EnergyNetWorkSpecialPackge(pos, facing);
    }

    @Override
    public void notifyNearbyCables() {
    }

    @Override
    public void notifyNearByCable(EnumFacing facing) {
    }

    @Override
    public int notifyByNearbyCables(EnergyNetWorkSpecialPackge pack) {
        return 0;
        //todo 重写
    }

    public int getSign() {
        return sign;
    }

    public void onBlockPlacedBy() {
        int i = 0;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                i++;
                this.sign = ((TileEntityEnergyCableWithoutGenerateForce) te).getSign();
                markDirty();
            }
        }
        if (i == 0) {
            EnergyNetworkSign sign = new EnergyNetworkSign();
            markDirty();
            sign.addEnergyStoragedOfNetwork(energyStored);
            sign.addMaxEnergyCapacityOfNetwork(maxEnergyCapacity);
            EnergyNetSavedData.addSign(world, sign);
        }
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        //todo 重写
    }

    @Override
    public void setStatusInFacing(EnumFacing facing, EnumMMFETileEntityStatus status) {
        states.setStatusInFacing(facing, status);
        markDirty();
    }

    @Override
    public EnumMMFETileEntityStatus getStatusInFacing(EnumFacing facing) {
        return states.getStatusInFacing(facing);
    }

    private void transferToTickable() {
        world.setBlockState(pos, ModBlocks.test1.getDefaultState());
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        this.transferToTickable();
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
//        if (!simulate) {
//
//            if (energyStored - maxExtract < 0) {
//                int tempInt = energyStored;
//                energyStored = 0;
//                markDirty();
//                return tempInt;
//            } else {
//                energyStored -= maxExtract;
//                markDirty();
//                return maxExtract;
//            }
//
//        } else {
//
//            if (energyStored - maxExtract < 0) {
//                int tempInt = energyStored;
//                energyStored = 0;
//                return tempInt;
//            } else {
//                energyStored -= maxExtract;
//                return maxExtract;
//            }
//
//        }
        //todo 重写,世界附加存储
        return 0;
    }
}
