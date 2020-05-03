package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {
    private boolean needUpdate = false;


    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity);
    }

    @Override
    public void update() {
        if (world.isRemote) {
            if (!needUpdate) {
                notifyNearbyCablesUpdateEnergyNetFlow();
            }
        }
    }

    @Override
    public void notifyNearbyCablesUpdateEnergyNetFlow() {
        //todo 重写
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        //todo 重写
    }

    private int PushEnergy(EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (!te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) return 0;
        IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
        return storage.receiveEnergy(this.extractEnergy(maxEnergyCapacity, false), false);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return EnergyNetSavedData.ExtractEnergy(sign, maxExtract, simulate, world);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return EnergyNetSavedData.ReciveEnergy(this.sign, maxReceive, simulate, world);
    }
}
