package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {


    private EnumFacing tempfacing;

    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity, SurrondingsState state) {
        super(maxEnergyCapacity, state);
    }

    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.CABLE;
        }
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            this.tempfacing = facing;
            return (T) this;
        }
        return null;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        //todo 更新state
        SurrondingsState newstate = states.setStatusInFacing(tempfacing, EnumMMFETileEntityStatus.INPUT);
        updateSurrondingState(newstate);
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (states.getStatusInFacing(tempfacing) != EnumMMFETileEntityStatus.OUTPUT)
            states.setStatusInFacing(tempfacing, EnumMMFETileEntityStatus.OUTPUT);
        return energyStored / states.getOutputFacingCounts();
    }

    @Override
    public EnergyNetWorkSpecialPackge askForPackage(EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
            return ((TileEntityEnergyCableWithoutGenerateForce) te).replyPackage(facing.getOpposite());
        }
        return null;
    }

    @Override
    public EnergyNetWorkSpecialPackge replyPackage(EnumFacing facing) {

        if (states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.OUTPUT)
            states.setStatusInFacing(facing, EnumMMFETileEntityStatus.OUTPUT);
        return new EnergyNetWorkSpecialPackge(pos, facing.getOpposite(), energyStored);
    }

    @Override
    public void notifyNearbyCables() {
        List<EnumFacing> list = states.getOutputFacings();
        for (EnumFacing facing : list) {
           TileEntity te =  world.getTileEntity(pos.offset(facing));
            if(te instanceof TileEntityEnergyCableWithoutGenerateForce)
            ((TileEntityEnergyCableWithoutGenerateForce)te).notifyByNearbyCables(new EnergyNetWorkSpecialPackge(pos, facing.getOpposite(), energyStored / list.size()));
        }
    }

    @Override
    public void notifyByNearbyCables(EnergyNetWorkSpecialPackge pack) {
        int energy = pack.getEnergyTransfer();
        for (EnumFacing facing : states.getInputFacings()) {
            if (facing != pack.getFromLastBlockFacing()) {
                EnergyNetWorkSpecialPackge tempPack = askForPackage(facing);
                if (tempPack == null) states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
                else energy += tempPack.getEnergyTransfer();
            }
        }
        this.energyStored = energy;
        markDirty();
    }

    @Override
    public void notifyByLastCables() {
        int energy = 0;
        for (EnumFacing facing : states.getInputFacings()) {
            EnergyNetWorkSpecialPackge tempPack = askForPackage(facing);
            if (tempPack == null) states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
            else energy += tempPack.getEnergyTransfer();
        }
        this.energyStored = energy;
        markDirty();
    }


    private SurrondingsState updateSurrondingState(SurrondingsState state) {
        this.states = state;
        markDirty();
        return states;
    }

    private SurrondingsState updateSurrondingState() {
        SurrondingsState newstate = new SurrondingsState();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if(world.getTileEntity(pos.offset(facing)) != null) {
                TileEntity te = world.getTileEntity(pos.offset(facing));
                if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                    TileEntityEnergyCableWithoutGenerateForce cable = (TileEntityEnergyCableWithoutGenerateForce) te;
                    if (cable.getStates().getStatusInFacing(facing.getOpposite()) == EnumMMFETileEntityStatus.NULL) {
                        cable.getStates().setStatusInFacing(facing.getOpposite(), EnumMMFETileEntityStatus.OUTPUT);
                        newstate.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
                    }
                }
            }
        }
        this.states = newstate;
        markDirty();
        return newstate;
    }

    @Override
    public void updateState() {
        updateSurrondingState();
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return EnumMMFETileEntityStatus.CABLE;
    }

}
