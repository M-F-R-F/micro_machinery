package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.blocks.machine.BlockEnergyCableWithOutGenerateForce;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TileEntityEnergyCableWithOutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {

    protected EnergyNetWorkSpecialPackge pack = null;
    private int energyFlow = 0;

    public TileEntityEnergyCableWithOutGenerateForce(int maxEnergyCapacity,SurrondingsState state) {
        super(maxEnergyCapacity, state);
        //todo
        pack = askForPackage();
        energyFlow = pack.getEnergyTransfer();
    }
    public TileEntityEnergyCableWithOutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
        //todo
        pack = askForPackage();
        energyFlow = pack.getEnergyTransfer();
        updateSurrondingState();
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
            return (T) this;
        }
        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        pack.readFromNBT(compound.getCompoundTag("EnergyNetWorkPackage"));
        energyFlow = compound.getInteger("energyFlow");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("EnergyNetWorkPackage",pack.writeToNBT(new NBTTagCompound()));
        compound.setInteger("energyFlow",energyFlow);
        return super.writeToNBT(compound);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        //todo 更新state
        updateSurrondingState();
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return pack.getEnergyTransfer();
    }

    @Override
    public EnergyNetWorkSpecialPackge askForPackage() {
        ArrayList<BlockPos> posList = new ArrayList<>();
        return this.traverseNearbyCable(() -> {
            int Energy = 0;
            for(EnumFacing facing: EnergyNetWorkUtils.getFacings()){
                BlockPos poooos = pos.offset(facing);
                TileEntity te = world.getTileEntity(poooos);
                if(te != null){
                    if(te instanceof TileEntityEnergyCableWithOutGenerateForce){
                        TileEntityEnergyCableWithOutGenerateForce cable = (TileEntityEnergyCableWithOutGenerateForce) te;
                        Energy += cable.getEnergyStored();
                        posList.add(poooos);
                    }
                }
            }
            return new EnergyNetWorkSpecialPackge(null, (BlockPos[]) posList.toArray(),energyFlow);
        });
    }

    @Override
    public EnergyNetWorkSpecialPackge sendPackage() {
        return null;
    }

    @Override
    public void notifyNearByCable() {

    }

    @Override
    public void notifyByLastCable(EnergyNetWorkSpecialPackge facing) {
    }

    private SurrondingsState updateSurrondingState(){
        return ((BlockEnergyCableWithOutGenerateForce)blockType).updateState(pos,world);
    }

    @Override
    public void updateState() {
        states = getNearbyCablesWithoutFacing(pos,world);
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return null;
    }

    @Override
    public int getEnergyStored() {
        return energyFlow;
    }
}
