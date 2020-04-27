package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityTickableEnergyCableWithoutGenerateForce extends TileEntityEnergyCableWithoutGenerateForce implements ITickable {

    public TileEntityTickableEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity);
    }

    @Override
    public void update() {

    }
}
