//package com.dbydd.micro_machinery.temp;
//
//import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
//import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
//import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
//import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.ITickable;
//import net.minecraftforge.energy.CapabilityEnergy;
//import net.minecraftforge.energy.IEnergyStorage;
//
//public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {
//
//    protected int energyInTransferLastSec = 0;
//    protected int energyInTransferThisSec = 0;
//    protected short timer1 = 0;
//    protected short timer2 = 0;
//
//    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
//        super(maxEnergyCapacity, new SurrondingsState());
//    }
//
//    @Override
//    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
//        compound.setInteger("energyInTransferLastSec", energyInTransferLastSec);
//        compound.setInteger("energyInTransferThisSec", energyInTransferThisSec);
//        compound.setShort("timer1", timer1);
//        compound.setShort("timer2", timer2);
//        return super.writeToNBT(compound);
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound compound) {
//        energyInTransferLastSec = compound.getInteger("energyInTransferLastSec");
//        energyInTransferThisSec = compound.getInteger("energyInTransferThisSec");
//        timer1 = compound.getShort("timer1");
//        timer2 = compound.getShort("timer2");
//        super.readFromNBT(compound);
//    }
//
//    @Override
//    public void update() {
//        timer1++;
//        timer2++;
//        updateSurrondingState();
//
//        if (timer2 >= 40) {
//            timer2 = 0;
//            if (energyInTransferThisSec != energyInTransferLastSec) {
//                notifyNearbyCables();
//            }
//        }
//
//        if (timer1 >= 20) {
//            timer1 = 0;
//            energyInTransferLastSec = energyInTransferThisSec;
//            energyInTransferThisSec = 0;
//        }
//
//        if (states.getOutputFacingCounts() == 0) {
//            energyStored -= energyStored;
//        } else {
//            energyStored -= (energyStored / states.getOutputFacingCounts());
//        }
//
//        markDirty();
//    }
//
//    @Override
//    public int receiveEnergy(int maxReceive, boolean simulate) {
//        if (!simulate) {
//            if (maxReceive + energyStored >= maxEnergyCapacity) {
//                states.setStatusInFacing(tempfacing, EnumMMFETileEntityStatus.INPUT);
//                tempfacing = null;
//                energyInTransferThisSec += (maxEnergyCapacity - energyStored);
//                energyStored = maxEnergyCapacity;
//                markDirty();
//                return (maxReceive - maxEnergyCapacity - energyStored);
//            } else {
//                states.setStatusInFacing(tempfacing, EnumMMFETileEntityStatus.INPUT);
//                tempfacing = null;
//                energyInTransferThisSec += maxReceive;
//                energyStored += maxReceive;
//                markDirty();
//                return maxReceive;
//            }
//        } else {
//            if (maxReceive + energyStored >= maxEnergyCapacity) {
//                return maxReceive - (maxEnergyCapacity - energyStored);
//            } else {
//                return 0;
//            }
//        }
//    }
//
//    @Override
//    public int extractEnergy(int maxExtract, boolean simulate) {
//        if (states.getStatusInFacing(tempfacing) != EnumMMFETileEntityStatus.NETOUT) {
//            states.setStatusInFacing(tempfacing, EnumMMFETileEntityStatus.NETOUT);
//            tempfacing = null;
//            markDirty();
//        }
//        if (states.getOutputFacingCounts() == 0) {
//            return energyInTransferLastSec;
//        } else {
//            return energyInTransferLastSec / states.getOutputFacingCounts();
//        }
//
//        //todo 完善一下输出端
//    }
//
//    @Override
//    public void updateState() {
//        updateSurrondingState();
//        notifyNearbyCables();
//    }
//
//
//    @Override
//    protected SurrondingsState updateSurrondingState() {
//        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
//            if (world.getTileEntity(pos.offset(facing)) != null) {
//                TileEntity te = world.getTileEntity(pos.offset(facing));
//                if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//                    TileEntityEnergyCableWithoutGenerateForce cable = (TileEntityEnergyCableWithoutGenerateForce) te;
//                    if (cable.getStates().getStatusInFacing(facing.getOpposite()) == EnumMMFETileEntityStatus.NULL) {
//                        cable.getStates().setStatusInFacing(facing.getOpposite(), EnumMMFETileEntityStatus.OUTPUT);
//                        world.setTileEntity(pos.offset(facing), cable);
//                        cable.markDirty();
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
//                        markDirty();
//                    } else {
//                        cable.getStates().setStatusInFacing(facing.getOpposite(), EnumMMFETileEntityStatus.INPUT);
//                        world.setTileEntity(pos.offset(facing), cable);
//                        cable.markDirty();
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.OUTPUT);
//                        markDirty();
//                    }
//                } else if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
//                    IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
//                    if (energyStorage.canReceive()) {
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NETOUT);
//                        markDirty();
//                        if (energyStorage.canExtract()) {
//                            states.setStatusInFacing(facing, EnumMMFETileEntityStatus.BOTH);
//                            markDirty();
//                        }
//                    } else if (energyStorage.canExtract()) {
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
//                        markDirty();
//                    }
//                }
//            }
//        }
//        return states;
//    }
//
//}
