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
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {

    protected int sign;
    protected int sequence;

    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.sequence = compound.getInteger("sequence");
        this.sign = compound.getInteger("EnergyNetSign");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("sequence", sequence);
        compound.setInteger("EnergyNetSign", sign);
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return (T) this;
        return null;
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
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSign() != Sign) {
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

        UpdateSequence();

        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null) {
                if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
                    if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()) && te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
                        transferToTickable();
                    }
                }
            }
        }

        int i = 0;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                i++;
                this.sign = ((TileEntityEnergyCableWithoutGenerateForce) te).getSign();
                //unsafe todo 合并电网
                EnergyNetSavedData.updateEnergyNetCapacity(world, maxEnergyCapacity, this.sign);
                EnergyNetSavedData.updateEnergyNetCapacity(world, energyStored, this.sign);
                markDirty();
                break;
            }
        }

        if (i == 0) {
            EnergyNetworkSign sign = new EnergyNetworkSign();
            this.sign = sign.getSIGN();
            markDirty();
            sign.addEnergyStoragedOfNetwork(energyStored);
            sign.addMaxEnergyCapacityOfNetwork(maxEnergyCapacity);
            EnergyNetSavedData.addSign(world, sign);
        }

    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        TileEntity te = world.getTileEntity(neighbor);
        if (te != null) {
            if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce) && te.hasCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor))) {
                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor));
                if (storage.canReceive()) transferToTickable();
            }
        }


    }

    public boolean askForZeroSequence() {
        if (this.sequence == 0) return true;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() < this.sequence) {
                    if (((TileEntityEnergyCableWithoutGenerateForce) te).askForZeroSequence()) return true;
                }
            }
        }
        return false;
    }

    public void OnNeighborDestoryed() {
        boolean canReciveZeroSequencd = askForZeroSequence();
        //todo 拆分电网
        if (canReciveZeroSequencd) world.playerEntities.get(0).sendMessage(new TextComponentString("yes"));
    }

    public void OnBlockDestroyed() {
        EnergyNetSavedData.updateEnergyNetCapacity(world, -maxEnergyCapacity, sign);
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                ((TileEntityEnergyCableWithoutGenerateForce) te).OnNeighborDestoryed();
            }
        }
    }

    protected void UpdateSequence() {
        int tempsequence = 0;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() == 0) {
                    this.sequence = 1;
                    markDirty();
                } else {
                    if (tempsequence == 0 || tempsequence > ((TileEntityEnergyCableWithoutGenerateForce) te).getSequence()) {
                        tempsequence = ((TileEntityEnergyCableWithoutGenerateForce) te).getSequence();
                    }
                }
            }
        }
        if (tempsequence != 0) {
            this.sequence = tempsequence + 1;
            markDirty();
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
        return EnergyNetSavedData.ReciveEnergy(this.sign, maxReceive, simulate, world);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        transferToTickable();
        return 0;
    }

}
