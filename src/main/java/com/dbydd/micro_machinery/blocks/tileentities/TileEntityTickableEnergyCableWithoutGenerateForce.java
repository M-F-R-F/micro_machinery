package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {
    private boolean needUpdate = false;


    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (!needUpdate) {
                notifyNearbyCablesUpdateEnergyNetFlow();
            }
         PushEnergyToSurrondingMachine();
        }
    }

    @Override
    public void notifyNearbyCablesUpdateEnergyNetFlow() {
        //todo 重写
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        super.onNeighborChanged(neighbor);
        for(EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null) {
                if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
                    if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()) && te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.ENERGYNET_OUTPUT);
                        markDirty();
                    }
                }
            }
        }
    }

    private int PushEnergy(EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if(te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
            return te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).receiveEnergy(this.extractEnergy(maxEnergyCapacity, false), false);
        }
        return 0;
    }

    private void PushEnergyToSurrondingMachine(){
        for(EnumFacing facing : states.getNetOutputFacings()){
            this.receiveEnergy(PushEnergy(facing),false);
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return EnergyNetSavedData.ExtractEnergy(sign, maxExtract, simulate, world);
    }

}
