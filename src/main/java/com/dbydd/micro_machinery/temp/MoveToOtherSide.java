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
//static EnumMMFETileEntityStatus getStatusInThisPos(BlockPos pos, World world) {
//        int blockX = pos.getX();
//        int blockY = pos.getY();
//        int blockZ = pos.getZ();
//        int outputCount = 0;
//        int inputCount = 0;
//        for (int x = -1; x <= 1; x++) {
//        BlockPos position = new BlockPos(blockX + x, blockY, blockZ);
//        if (world.getTileEntity(position) instanceof MMFEMachineBase) {
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.OUTPUT) {
//        outputCount++;
//        }
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.INPUT) {
//        inputCount++;
//        }
//        }
//        }
//        for (int y = -1; y <= 1; y++) {
//        BlockPos position = new BlockPos(blockX, blockY + y, blockZ);
//        if (world.getTileEntity(position) instanceof MMFEMachineBase) {
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.OUTPUT) {
//        outputCount++;
//        }
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.INPUT) {
//        inputCount++;
//        }
//        }
//        }
//        for (int z = -1; z <= 1; z++) {
//        BlockPos position = new BlockPos(blockX, blockY, blockZ + z);
//        if (world.getTileEntity(position) instanceof MMFEMachineBase) {
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.OUTPUT) {
//        outputCount++;
//        }
//        if (((MMFEMachineBase) world.getTileEntity(position)).getStatus() == EnumMMFETileEntityStatus.INPUT) {
//        inputCount++;
//        }
//        }
//        }
//        if (outputCount > 0 && inputCount > 0) return EnumMMFETileEntityStatus.CABLE;
//        else if (outputCount > 0) return EnumMMFETileEntityStatus.INPUT;
//        else if (inputCount > 0) return EnumMMFETileEntityStatus.OUTPUT;
//        return EnumMMFETileEntityStatus.CABLE;
//        }
