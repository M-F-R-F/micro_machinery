package com.dbydd.micro_machinery.temp;

import com.dbydd.micro_machinery.blocks.machine.TestCable;
import com.dbydd.micro_machinery.blocks.tileentities.MMFEMachineBase;
import com.dbydd.micro_machinery.interfaces.IMMFEStorage;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoveToOtherSide {
    public static int getOtherSideCables(BlockPos pos, World world) {
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            if (world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock() instanceof TestCable)
                count++;
        }
        for (int y = -1; y <= 1; y++) {
            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock() instanceof TestCable)
                count++;
        }
        for (int z = -1; z <= 1; z++) {
            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock() instanceof TestCable)
                count++;
        }
        return count;
    }

    public static int getOtherSideFortages(BlockPos pos, World world) {
        int sumFortage = 0;
        for (int x = -1; x <= 1; x++) {
            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
            }
        }
        for (int y = -1; y <= 1; y++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
            }
        }
        for (int z = -1; z <= 1; z++) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
            if (block instanceof TestCable) {
                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
            }
        }
        return sumFortage;
    }
}
