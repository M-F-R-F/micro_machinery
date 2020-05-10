package com.dbydd.micro_machinery.interfaces;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public interface IMMFETransfer {

    static SurrondingsState getNearbyCables(BlockPos pos, World world) {
        SurrondingsState state = new SurrondingsState();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof IMMFETransfer) {
                state.setStatusInFacing(facing, EnumMMFETileEntityStatus.CABLE);
            }
        }
        return state;
    }

    default SurrondingsState getNearbyCablesWithoutFacing(BlockPos pos, World world) {
        return new SurrondingsState(pos, world);
    }

    default IEnergyStorage getOffsetBlockEnergyCapacity(EnumFacing facing, BlockPos pos, World world) {
        return world.getTileEntity(pos.offset(facing)).getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
    }

    default boolean isOffsetBlockHasEnergyCapacity(EnumFacing facing, BlockPos pos, World world) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te != null && !(te instanceof IMMFETransfer)) {
            return te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
        }
        return false;
    }

    default boolean isOffsetBlockCable(EnumFacing facing, BlockPos pos, World world) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te != null && te instanceof IMMFETransfer) {
            return true;
        }
        return false;
    }

    EnergyNetWorkSpecialPackge generatePackage(EnumFacing facing);

    public void notifyNearbyCablesUpdateEnergyNetFlow();

    public void notifyNearByCableUpdateEnergyNetFlow(EnumFacing facing);

    public int notifyByNearbyCablesUpdateEnergyNetFlow(EnergyNetWorkSpecialPackge pack);

    public void notifyNearbyCablesUpdateSign(int Sign);

    public void notifyByNearbyCablesUpdateSign(int Sign);

    public void onBlockPlacedBy();

    public void onNeighborChanged(BlockPos neighbor);

    void setStatusInFacing(EnumFacing facing, EnumMMFETileEntityStatus status);

    EnumMMFETileEntityStatus getStatusInFacing(EnumFacing facing);

}
