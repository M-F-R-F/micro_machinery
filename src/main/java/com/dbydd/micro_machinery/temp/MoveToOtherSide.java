//package com.dbydd.micro_machinery.temp;
//
//import com.dbydd.micro_machinery.blocks.machine.FireGenerator;
//import com.dbydd.micro_machinery.blocks.tileentities.MMFEMachineBase;
//import net.minecraft.block.Block;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class MoveToOtherSide {
//    public static int getOtherSideCables(BlockPos pos, World world) {
//        int count = 0;
//        for (int x = -1; x <= 1; x++) {
//            if (world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock() instanceof FireGenerator)
//                count++;
//        }
//        for (int y = -1; y <= 1; y++) {
//            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock() instanceof FireGenerator)
//                count++;
//        }
//        for (int z = -1; z <= 1; z++) {
//            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock() instanceof FireGenerator)
//                count++;
//        }
//        return count;
//    }
//
//    public static int getOtherSideFortages(BlockPos pos, World world) {
//        int sumFortage = 0;
//        for (int x = -1; x <= 1; x++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//            }
//        }
//        for (int y = -1; y <= 1; y++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//            }
//        }
//        for (int z = -1; z <= 1; z++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//            }
//        }
//        return sumFortage;
//    }
//
//    public int getPreviousFortage(World world, BlockPos pos) {
//        int sumFortage = 0;
//        int count = 0;
//        for (int x = -1; x <= 1; x++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//                count++;
//            }
//        }
//        for (int y = -1; y <= 1; y++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//                count++;
//            }
//        }
//        for (int z = -1; z <= 1; z++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFortage += ((MMFEMachineBase) world.getTileEntity(pos)).getFortage();
//                count++;
//            }
//        }
//        if (count == 0) return 0;
//        return sumFortage / count;
//    }
//
//    public int getPreviousFurrect(World world, BlockPos pos) {
//        int sumFurrect = 0;
//        for (int x = -1; x <= 1; x++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
//            }
//        }
//        for (int y = -1; y <= 1; y++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
//            }
//        }
//        for (int z = -1; z <= 1; z++) {
//            Block block = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock();
//            if (block instanceof FireGenerator) {
//                sumFurrect += ((MMFEMachineBase) world.getTileEntity(pos)).getFurrect();
//            }
//        }
//        return sumFurrect;
//    }
//
//}
