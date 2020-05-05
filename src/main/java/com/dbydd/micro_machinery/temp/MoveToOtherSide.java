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
//    public static void setFacingProperty(EnumFacing facing, EnumMMFETileEntityStatus status, BlockPos pos, World world) {
//        IBlockState state = world.getBlockState(pos);
//        switch (facing.getName()) {
//            case "up": {
//                world.setBlockState(pos, state.withProperty(STATUE_UP, status));
//                break;
//            }
//            case "down": {
//                world.setBlockState(pos, state.withProperty(STATUE_DOWN, status));
//                break;
//            }
//            case "south": {
//                world.setBlockState(pos, state.withProperty(STATUE_SOUTH, status));
//                break;
//            }
//            case "north": {
//                world.setBlockState(pos, state.withProperty(STATUE_NORTH, status));
//                break;
//            }
//            case "west": {
//                world.setBlockState(pos, state.withProperty(STATUE_WEST, status));
//                break;
//            }
//            case "east": {
//                world.setBlockState(pos, state.withProperty(STATUE_EAST, status));
//                break;
//            }
//        }
//        world.setBlockState(pos, state);
//    }
//
//
//    protected static PropertyEnum getProperty(EnumFacing facing) {
//        switch (facing.getName()) {
//            case "up": {
//                return STATUE_UP;
//            }
//            case "down": {
//                return STATUE_DOWN;
//            }
//            case "south": {
//                return STATUE_SOUTH;
//            }
//            case "north": {
//                return STATUE_NORTH;
//            }
//            case "west": {
//                return STATUE_WEST;
//            }
//            case "east": {
//                return STATUE_EAST;
//            }
//        }
//        return STATUE_UP;
//    }
//    protected static final EnumMMFETileEntityStatus[] CABLE_STATUS_LIST = {EnumMMFETileEntityStatus.NULL, EnumMMFETileEntityStatus.CABLE, EnumMMFETileEntityStatus.CABLE_OUTPUT, EnumMMFETileEntityStatus.CABLE_INPUT, EnumMMFETileEntityStatus.ENERGYNET_OUTPUT};
//
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_UP = PropertyEnum.create("statue_up", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_DOWN = PropertyEnum.create("statue_down", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_SOUTH = PropertyEnum.create("statue_south", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_NORTH = PropertyEnum.create("statue_north", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_WEST = PropertyEnum.create("statue_west", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_EAST = PropertyEnum.create("statue_east", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyDirection FACES = PropertyDirection.create("faces");
//    private static IBlockState getDefaultBlockState(IBlockState state) {
//        return state.withProperty(STATUE_UP, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_DOWN, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_SOUTH, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_NORTH, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_WEST, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_EAST, EnumMMFETileEntityStatus.NULL);
//    }
//