package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFETineEntityStatus;
import com.dbydd.micro_machinery.blocks.machine.TestCable;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.IEnergyStorage;

import javax.vecmath.Vector3d;
import java.util.ArrayDeque;
import java.util.Deque;

public abstract class MMFEMachineBase extends TileEntity implements IMMFEStorage{

    protected final int level;
    protected final int maxEnergyCapacity;
    protected int energyStored;
    protected final int maxFortage;
    protected int fortage;
    protected final int maxFurrect;
    protected int furrect;
    protected EnumMMFETineEntityStatus status;
    protected final int materialLossValue;
    protected int lossValue;
    protected EnumInfluenceDirection influenceDirection;

    MMFEMachineBase(int level, int maxEnergyCapacity, int maxFortage, int maxFurrect, EnumMMFETineEntityStatus status, int materialLossValue) {
        this.level = level;
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.maxFortage = maxFortage;
        this.maxFurrect = maxFurrect;
        this.status = status;
        this.materialLossValue = materialLossValue;
    }

    @Override
    public int FEConversion(int Ft, int Fr) {
        return Fr * Fr;
    }

    @Override
    public int getFortage() {
        return this.fortage;
    }

    @Override
    public int getFurrect() {
        return this.furrect;
    }

    @Override
    public int getPreviousFortage() {
        int sumFortage = 0;
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
                count++;
            }
        }
        for (int y = -1; y <= 1; y++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
                count++;
            }
        }
        for (int z = -1; z <= 1; z++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
                count++;
            }
        }
        if (count == 0) return 0;
        return sumFortage / count;
    }

    @Override
    public int getPreviousFurrect() {
        int sumFurrect = 0;
        for (int x = -1; x <= 1; x++) {
            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
            }
        }
        for (int y = -1; y <= 1; y++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
            }
        }
        for (int z = -1; z <= 1; z++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
            if (block instanceof TestCable) {
                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
            }
        }
        return sumFurrect;
    }

    @Override
    public int loss(int FENeedToLoss) {
        return FENeedToLoss - (this.furrect / (int) Math.round(Math.sqrt(this.fortage))) * this.getLossValue();
    }

    @Override
    public EnumInfluenceDirection generateInfluences() {
        return this.influenceDirection;
    }

    @Override
    public boolean canExtract() {
        return status != EnumMMFETineEntityStatus.INPUT;
    }

    @Override
    public boolean canReceive() {
        return status == EnumMMFETineEntityStatus.INPUT;
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergyCapacity;
    }

    @Override
    public void ActionForgneticForce(Vector3d force) {
    }

    @Override
    public EnumMMFETineEntityStatus updateStatue() {
        return null;
    }

    @Override
    public void updateState() {

    }

    @Override
    public Vector3d getPreviousForgneticForce() {
        return new Vector3d(0, 0, 0);
    }

    @Override
    public Vector3d getForgneticForce() {
        return new Vector3d(0, 0, 0);
    }

    @Override
    public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);

    @Override
    public abstract void readFromNBT(NBTTagCompound compound);

    @Override
    public abstract int getLossValue();
}
