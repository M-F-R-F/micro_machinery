package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumInfluenceDirection;
import com.dbydd.micro_machinery.EnumType.EnumMMFECableStatus;
import com.dbydd.micro_machinery.blocks.machine.TestCable;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.IEnergyStorage;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class MMFEMachineBase extends TileEntity implements IMMFEStorage, ITickable {

    private EnumMMFECableStatus status;
    private int fortage;
    private int furrect;
    private int lossValue;
    private EnumInfluenceDirection influenceDirection;
    private Deque<IEnergyStorage> inputs = new ArrayDeque<>();

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
        return FENeedToLoss - (this.furrect / (int) Math.round(Math.sqrt(this.fortage))) * this.lossValue;
    }

    @Override
    public EnumInfluenceDirection generateInfluences() {
        return this.influenceDirection;
    }

}
