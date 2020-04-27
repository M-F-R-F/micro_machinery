//package com.dbydd.micro_machinery.temp;
//
//import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
//import com.dbydd.micro_machinery.blocks.tileentities.MMFEMachineBaseV2;
//import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
//import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
//import com.dbydd.micro_machinery.init.ModBlocks;
//import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
//import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.energy.CapabilityEnergy;
//import net.minecraftforge.energy.IEnergyStorage;
//
//import javax.annotation.Nullable;
//import java.util.List;
//
//public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {
//
//
//    protected EnumFacing tempfacing = null;
//
//    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity, SurrondingsState state) {
//        super(maxEnergyCapacity, state);
//    }
//
//    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
//        super(maxEnergyCapacity, new SurrondingsState());
//    }
//
//    @Override
//    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
//        if (capability == CapabilityEnergy.ENERGY) {
//            return states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.CABLE;
//        }
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
//        if (capability == CapabilityEnergy.ENERGY) {
//            tempfacing = facing;
//            return (T) this;
//        }
//        return null;
//    }
//
//    @Override
//    public int receiveEnergy(int maxReceive, boolean simulate) {
//        world.setBlockState(pos, ModBlocks.test1.getDefaultState());
//        return 0;
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
//            return energyStored;
//        } else {
//            return energyStored / states.getOutputFacingCounts();
//        }
//    }
//
//    @Override
//    public EnergyNetWorkSpecialPackge askForPackage(EnumFacing facing) {
//        TileEntity te = world.getTileEntity(pos.offset(facing));
//        if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//            states.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
//            markDirty();
//            return ((TileEntityEnergyCableWithoutGenerateForce) te).replyPackage(facing.getOpposite());
//        }
//        return null;
//    }
//
//    @Override
//    public EnergyNetWorkSpecialPackge replyPackage(EnumFacing facing) {
//
//        if (states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.OUTPUT)
//            states.setStatusInFacing(facing, EnumMMFETileEntityStatus.OUTPUT);
//        return new EnergyNetWorkSpecialPackge(pos, facing.getOpposite(), energyStored);
//    }
//
//    @Override
//    public void notifyNearbyCables() {
//        List<EnumFacing> list = SurrondingsState.getNotInputFacings(states);
//        for (EnumFacing facing : list) {
//            TileEntity te = world.getTileEntity(pos.offset(facing));
//            if (te != null) {
//                if (te instanceof TileEntityEnergyCableWithoutGenerateForce)
//                    ((TileEntityEnergyCableWithoutGenerateForce) te).notifyByNearbyCables(new EnergyNetWorkSpecialPackge(pos, facing.getOpposite(), energyStored / list.size()));
//            }
//        }
//    }
//
//    @Override
//    public void notifyByNearbyCables(EnergyNetWorkSpecialPackge pack) {
//        updateSurrondingState();
//        int energy = pack.getEnergyWantTransfer();
//        states.setStatusInFacing(pack.getFromLastBlockFacing(), EnumMMFETileEntityStatus.INPUT);
//        for (EnumFacing facing : states.getInputFacings()) {
//            if (facing != pack.getFromLastBlockFacing()) {
//                EnergyNetWorkSpecialPackge tempPack = askForPackage(facing);
////                if (tempPack == null) states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
////                else {
//                    energy += tempPack.getEnergyWantTransfer();
//                    states.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
////                }
//            }
//        }
//        this.energyStored = energy;
//        markDirty();
//        notifyNearbyCables();
//    }
//
//    @Override
//    public void notifyByLastCables() {
//        int energy = 0;
//        for (EnumFacing facing : states.getInputFacings()) {
//            EnergyNetWorkSpecialPackge tempPack = askForPackage(facing);
//            if (tempPack == null) states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
//            else energy += tempPack.getEnergyWantTransfer();
//        }
//        this.energyStored = energy;
//        markDirty();
//    }
//
//
//    private SurrondingsState updateSurrondingState(SurrondingsState state) {
//        this.states = state;
//        markDirty();
//        return states;
//    }
//
//    protected SurrondingsState updateSurrondingState() {
//        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
//            if (world.getTileEntity(pos.offset(facing)) != null) {
//                TileEntity te = world.getTileEntity(pos.offset(facing));
//                if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//                    TileEntityEnergyCableWithoutGenerateForce cable = (TileEntityEnergyCableWithoutGenerateForce) te;
//                    if (cable.getStates().getStatusInFacing(facing.getOpposite()) != EnumMMFETileEntityStatus.INPUT) {
//                        cable.getStates().setStatusInFacing(facing.getOpposite(), EnumMMFETileEntityStatus.INPUT);
//                        cable.markDirty();
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.OUTPUT);
//                        markDirty();
//                    }
//                } else if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
//                    IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
//                    if (energyStorage.canReceive()) {
////                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.NETOUT);
//                        world.setBlockState(pos, ModBlocks.test1.getDefaultState());
//                        return null;
//                    }
//                    if (energyStorage.canExtract()) {
//                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.INPUT);
//                        if (energyStorage.canReceive()) {
//                            world.setBlockState(pos, ModBlocks.test1.getDefaultState());
//                            return null;
//                        }
//                    }
//                }
//            }
//        }
//        return states;
//    }
//
//    boolean isOutPut() {
//        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
//            if (world.getTileEntity(pos.offset(facing)) != null) {
//                TileEntity te = world.getTileEntity(pos.offset(facing));
//                if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()) && !(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
//                    if (te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void updateState() {
//        if (isOutPut()) {
//            world.setBlockState(pos, ModBlocks.test1.getDefaultState());
//        }
//        updateSurrondingState();
//    }
//
//    @Override
//    public EnumMMFETileEntityStatus updateStatue() {
//        return EnumMMFETileEntityStatus.CABLE;
//    }
//
//}
