package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {


    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void updateState() {

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
//        for (EnumFacing facing : states.getNotNullFacings()) {
//            int energyRecived = ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos.offset(facing))).notifyByNearbyCables(generatePackage(facing, states.getOutputFacingCounts(), false));
//            energyStored -= energyRecived;
//            markDirty();
//        }
            // todo 重写
    }

    @Override
    public void notifyNearByCable(EnumFacing facing) {
//        TileEntity te = world.getTileEntity(pos.offset(facing));
//        if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//            ((TileEntityEnergyCableWithoutGenerateForce) te).notifyByNearbyCables(new EnergyNetWorkSpecialPackge(pos, facing));
//        }
            //todo 重写
    }

    @Override
    public int notifyByNearbyCables(EnergyNetWorkSpecialPackge pack) {
        return 0;
        //todo 重写
    }

    public void onBlockPlacedBy() {
//        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
//            TileEntity te = world.getTileEntity(pos.offset(facing));
//            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//                ((TileEntityEnergyCableWithoutGenerateForce) te).notifyByNearbyCables(generatePackage(facing, 0, true));
//                setStatusInFacing(facing, EnumMMFETileEntityStatus.CABLE);
//            } else if (te == null) {
//                setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
//            } else if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
//                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
//                if (storage.canReceive()) {
//                    this.transferToTickable();
//                    //todo 重写，接下来输出由Tickable接管
//                }
//            }
//        }
        //todo 重写
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
//        TileEntity te = world.getTileEntity(neighbor);
//        if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
//            if (te == null) {
//                for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
//                    if (pos.offset(facing) == neighbor) {
//                        setStatusInFacing(facing, EnumMMFETileEntityStatus.NULL);
//                    }
//                }
//            } else {
//                //todo 如果是机器
//            }
//        } else if (te instanceof TileEntityTickableEnergyCableWithoutGenerateForce) {
//            //todo tickable的电线,(重新理逻辑)
//        }
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
