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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {

    protected int sign;

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

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (T) this;
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
    public void notifyNearbyCablesUpdateEnergyNetFlow() {
    }

    @Override
    public void notifyNearByCableUpdateEnergyNetFlow(EnumFacing facing) {
    }

    @Override
    public int notifyByNearbyCablesUpdateEnergyNetFlow(EnergyNetWorkSpecialPackge pack) {
        return 0;
        //todo 重写
    }

    @Override
    public void notifyNearbyCablesUpdateSign(int Sign) {
        for (EnumFacing face : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(face));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSign() != Sign){
                    ((TileEntityEnergyCableWithoutGenerateForce) te).setSign(Sign);
                }
            }
        }

    }

    @Override
    public void notifyByNearbyCablesUpdateSign(int Sign) {
        this.setSign(Sign);
        notifyNearbyCablesUpdateSign(Sign);
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
        markDirty();
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
        TileEntity te = world.getTileEntity(neighbor);
        if (te != null) {
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSign() != this.sign) {
                    EnergyNetSavedData.mergeEnergyNet(((TileEntityEnergyCableWithoutGenerateForce) te).getSign(), this.sign, world);
                    this.sign = ((TileEntityEnergyCableWithoutGenerateForce) te).getSign();
                    notifyNearbyCablesUpdateSign(((TileEntityEnergyCableWithoutGenerateForce) te).getSign());
                    markDirty();
                }
            } else if (te.hasCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor))) {
                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor));
                if (storage.canReceive()) transferToTickable();
            }
        }
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
        transferToTickable();
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        transferToTickable();
        return 0;
    }

}
