package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockEnergyCableWithoutGenerateForce extends Block implements IHasModel {
    public static final EnumMMFETileEntityStatus[] CABLE_STATUS_LIST = {EnumMMFETileEntityStatus.NULL, EnumMMFETileEntityStatus.CABLE, EnumMMFETileEntityStatus.CABLE_OUTPUT, EnumMMFETileEntityStatus.CABLE_INPUT, EnumMMFETileEntityStatus.ENERGYNET_OUTPUT};

    //    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_UP = PropertyEnum.create("statue_up", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_DOWN = PropertyEnum.create("statue_down", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_SOUTH = PropertyEnum.create("statue_south", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_NORTH = PropertyEnum.create("statue_north", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_WEST = PropertyEnum.create("statue_west", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_EAST = PropertyEnum.create("statue_east", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyDirection FACES = PropertyDirection.create("faces");
    public static final PropertyBool STATUS_UP = PropertyBool.create("status_up");
    public static final PropertyBool STATUS_DOWN = PropertyBool.create("status_down");
    public static final PropertyBool STATUS_SOUTH = PropertyBool.create("status_south");
    public static final PropertyBool STATUS_NORTH = PropertyBool.create("status_north");
    public static final PropertyBool STATUS_WEST = PropertyBool.create("status_west");
    public static final PropertyBool STATUS_EAST = PropertyBool.create("status_east");

    public static final AxisAlignedBB EMPTYAABB = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    protected final int transferEnergyMaxValue;

    public BlockEnergyCableWithoutGenerateForce(String name, Material materialIn, int transferEnergyMaxValue) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        this.transferEnergyMaxValue = transferEnergyMaxValue;
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        this.setDefaultState(getDefaultBlockState(this.blockState.getBaseState()));
    }

    private static IBlockState getDefaultBlockState(IBlockState state) {
//        return state.withProperty(STATUE_UP, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_DOWN, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_SOUTH, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_NORTH, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_WEST, EnumMMFETileEntityStatus.NULL).withProperty(STATUE_EAST, EnumMMFETileEntityStatus.NULL);
        return state
                .withProperty(STATUS_UP, Boolean.FALSE)
                .withProperty(STATUS_DOWN, Boolean.FALSE)
                .withProperty(STATUS_SOUTH, Boolean.FALSE)
                .withProperty(STATUS_NORTH, Boolean.FALSE)
                .withProperty(STATUS_WEST, Boolean.FALSE)
                .withProperty(STATUS_EAST, Boolean.FALSE);
    }

    public static IBlockState setFacingProperty(EnumFacing facing, Boolean canconnect, IBlockState state) {
        switch (facing.getName()) {
            case "up": {
                return state.withProperty(STATUS_UP, canconnect);
            }
            case "down": {
                return state.withProperty(STATUS_DOWN, canconnect);
            }
            case "south": {
                return state.withProperty(STATUS_SOUTH, canconnect);
            }
            case "north": {
                return state.withProperty(STATUS_NORTH, canconnect);
            }
            case "west": {
                return state.withProperty(STATUS_WEST, canconnect);
            }
            case "east": {
                return state.withProperty(STATUS_EAST, canconnect);
            }
        }
        return state;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(!worldIn.isRemote) {
            ((TileEntityEnergyCableWithoutGenerateForce) worldIn.getTileEntity(pos)).onBlockPlacedBy();
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos)).onNeighborChanged(neighbor);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(STATUS_UP, canconnect(worldIn, pos, EnumFacing.UP))
                .withProperty(STATUS_DOWN, canconnect(worldIn, pos, EnumFacing.DOWN))
                .withProperty(STATUS_SOUTH, canconnect(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(STATUS_NORTH, canconnect(worldIn, pos, EnumFacing.NORTH))
                .withProperty(STATUS_WEST, canconnect(worldIn, pos, EnumFacing.WEST))
                .withProperty(STATUS_EAST, canconnect(worldIn, pos, EnumFacing.EAST));
    }

    private boolean canconnect(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return canconnect(world, pos, facing);
    }

//    @Override
//    public IBlockState withRotation(IBlockState state, Rotation rot) {
//        switch (rot) {
//            case CLOCKWISE_180:
//                return state.withProperty(STATUS_NORTH, state.getValue(STATUS_SOUTH)).withProperty(STATUS_EAST, state.getValue(STATUS_WEST)).withProperty(STATUS_SOUTH, state.getValue(STATUS_NORTH)).withProperty(STATUS_WEST, state.getValue(STATUS_EAST));
//            case COUNTERCLOCKWISE_90:
//                return state.withProperty(STATUS_NORTH, state.getValue(STATUS_EAST)).withProperty(STATUS_EAST, state.getValue(STATUS_SOUTH)).withProperty(STATUS_SOUTH, state.getValue(STATUS_WEST)).withProperty(STATUS_WEST, state.getValue(STATUS_NORTH));
//            case CLOCKWISE_90:
//                return state.withProperty(STATUS_NORTH, state.getValue(STATUS_WEST)).withProperty(STATUS_EAST, state.getValue(STATUS_NORTH)).withProperty(STATUS_SOUTH, state.getValue(STATUS_EAST)).withProperty(STATUS_WEST, state.getValue(STATUS_SOUTH));
//
//            default:
//                return state;
//        }
//    }
//
//    @Override
//    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
//        switch (mirrorIn)
//        {
//            case LEFT_RIGHT:
//                return state.withProperty(STATUS_NORTH, state.getValue(STATUS_SOUTH)).withProperty(STATUS_SOUTH, state.getValue(STATUS_NORTH));
//            case FRONT_BACK:
//                return state.withProperty(STATUS_EAST, state.getValue(STATUS_WEST)).withProperty(STATUS_WEST, state.getValue(STATUS_EAST));
//            default:
//                return super.withMirror(state, mirrorIn);
//        }
//    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUS_UP, STATUS_DOWN, STATUS_WEST, STATUS_EAST, STATUS_NORTH, STATUS_SOUTH);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultBlockState(this.getDefaultState());
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}
