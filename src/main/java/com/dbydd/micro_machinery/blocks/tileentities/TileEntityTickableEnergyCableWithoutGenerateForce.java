package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {
    boolean checked = false;


    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        this.checked = compound.getBoolean("checked");
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        compound.setBoolean("checked", checked);
        super.readFromNBT(compound);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if(!checked){
                onBlockPlacedBy();
            }
            PushEnergyToSurrondingMachine();
        }
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        updateState();
    }

    @Override
    public void onBlockPlacedBy() {

        UpdateSequence();
        updateState();

        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null) {
                if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
                    if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()) && te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
                        setStatusInFacing(facing, EnumMMFETileEntityStatus.CABLE_HEAD);
                    }
                }
            }
        }

        int i = 0;
        boolean hasUpdated = false;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                i++;
                int sign1 = ((TileEntityEnergyCableWithoutGenerateForce) te).getSign();
                //unsafe todo 合并电网
                if (!hasUpdated && sign1 != this.sign) {
                    this.sign = sign1;
                    EnergyNetSavedData.updateEnergyNetCapacity(world, maxEnergyCapacity, this.sign);
                    EnergyNetSavedData.updateEnergyNetEnergy(world, energyStored, this.sign);
                    hasUpdated = true;
                    markDirty();
                } else {
                    notifyNearbyCableMergeSign(this.sign, sequence, facing);
                }
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

        checked = true;
        markDirty();
    }

    private int PushEnergy(EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
            return te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).receiveEnergy(this.extractEnergy(maxEnergyCapacity, false), false);
        }
        return 0;
    }

    private void PushEnergyToSurrondingMachine() {
        for (EnumFacing facing : states.getCableHeadFacings()) {
            this.receiveEnergy(PushEnergy(facing), false);
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return EnergyNetSavedData.ExtractEnergy(sign, maxExtract, simulate, world);
    }

}
