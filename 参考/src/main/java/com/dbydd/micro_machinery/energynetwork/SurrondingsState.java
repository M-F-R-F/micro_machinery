package com.dbydd.micro_machinery.energynetwork;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurrondingsState {
    private Map<EnumFacing, EnumMMFETileEntityStatus> map = new HashMap<EnumFacing, EnumMMFETileEntityStatus>();

    public SurrondingsState(Map.Entry<EnumFacing, EnumMMFETileEntityStatus>... entry) {
        for (Map.Entry<EnumFacing, EnumMMFETileEntityStatus> entryset : entry) {
            map.put(entryset.getKey(), entryset.getValue());
        }
        findNullFacing();
    }

    public SurrondingsState() {
        findNullFacing();
    }

    public SurrondingsState(BlockPos pos, World world) {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                map.put(facing, EnumMMFETileEntityStatus.CABLE);
            } else if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                IEnergyStorage tile = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                if (tile.canReceive()) map.put(facing, EnumMMFETileEntityStatus.CABLE_OUTPUT);
            }
        }
        findNullFacing();
    }

    public static List<EnumFacing> getOutputFacings(SurrondingsState state) {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (state.map.get(facing) == EnumMMFETileEntityStatus.CABLE_OUTPUT)
                list.add(facing);
        }
        return list;
    }

    public static List<EnumFacing> getCableHeadFacings(SurrondingsState state) {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (state.map.get(facing) == EnumMMFETileEntityStatus.CABLE_HEAD)
                list.add(facing);
        }
        return list;
    }

    public static List<EnumFacing> getNotInputFacings(SurrondingsState state) {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (state.map.get(facing) != EnumMMFETileEntityStatus.CABLE_INPUT)
                list.add(facing);
        }
        return list;
    }

    public List<EnumFacing> getFacings( EnumMMFETileEntityStatus status) {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (this.map.get(facing) == status)
                list.add(facing);
        }
        return list;
    }

    public List<EnumFacing> getOutputFacings() {
        return getOutputFacings(this);
    }

    public List<EnumFacing> getCableHeadFacings() {
        return getCableHeadFacings(this);
    }

    private void findNullFacing() {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            map.put(facing, EnumMMFETileEntityStatus.NULL);
        }
    }

    public final SurrondingsState setStatusInFacing(EnumFacing facing, EnumMMFETileEntityStatus status) {
        map.put(facing, status);
        return this;
    }

    public EnumMMFETileEntityStatus getStatusInFacing(EnumFacing facing) {
        return map.get(facing);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
        for (Map.Entry<EnumFacing, EnumMMFETileEntityStatus> entry : map.entrySet()) {
            nbtTag.setString("Cable_State_" + entry.getKey(), entry.getValue().name());
        }
        return nbtTag;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            this.setStatusInFacing(facing, EnumMMFETileEntityStatus.valueOf(nbt.getString("Cable_State_" + facing)));
        }
    }

    public List<EnumFacing> getInputFacings() {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (map.get(facing) == EnumMMFETileEntityStatus.CABLE_INPUT) list.add(facing);
        }
        return list;
    }

    public int getOutputFacingCounts() {
        return getOutputFacings(this).size();
    }

    public List<EnumFacing> getNullFacings() {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (map.get(facing) == EnumMMFETileEntityStatus.NULL) list.add(facing);
        }
        return list;
    }

    public List<EnumFacing> getNotNullFacings() {
        List<EnumFacing> list = new ArrayList<>();
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (map.get(facing) != EnumMMFETileEntityStatus.NULL) list.add(facing);
        }
        return list;
    }

}
