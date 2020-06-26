package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.exceptions.ErrorNumberException;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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
import java.util.List;
import java.util.Objects;

public class BlockEnergyCableWithoutGenerateForce extends Block implements IHasModel {
    public static final EnumMMFETileEntityStatus[] CABLE_STATUS_LIST = {EnumMMFETileEntityStatus.NULL, EnumMMFETileEntityStatus.CABLE, EnumMMFETileEntityStatus.CABLE_OUTPUT, EnumMMFETileEntityStatus.CABLE_INPUT, EnumMMFETileEntityStatus.CABLE_HEAD};
    public static final AxisAlignedBB CENTER_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 0.375D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625D, 0.375D, 0.375D, 1D, 0.625D, 0.625D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.625D, 0.625D, 0.625D, 1D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.375D, 0.375D, 0.375D, 0.625D, 0.625D);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.375D, 0.625D, 0.375D, 0.625D, 1D, 0.625D);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);

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
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (!isActualState) {
            state = state.getActualState(worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, CENTER_AABB);

        if (((Boolean) state.getValue(STATUS_UP)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);
        }
        if (((Boolean) state.getValue(STATUS_DOWN)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, DOWN_AABB);
        }
        if (((Boolean) state.getValue(STATUS_SOUTH)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }
        if (((Boolean) state.getValue(STATUS_NORTH)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }
        if (((Boolean) state.getValue(STATUS_WEST)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
        if (((Boolean) state.getValue(STATUS_EAST)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }
    }

    /**public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = this.getActualState(state, source, pos);

        double x1 = 0.375;
        double x2 = 0.625;
        double y1 = 0.375;
        double y2 = 0.625;
        double z1 = 0.375;
        double z2 = 0.625;

        if (((Boolean) state.getValue(STATUS_UP)).booleanValue()) {

        }
        if (((Boolean) state.getValue(STATUS_DOWN)).booleanValue()) {

        }
        if (((Boolean) state.getValue(STATUS_SOUTH)).booleanValue()) {

        }
        if (((Boolean) state.getValue(STATUS_NORTH)).booleanValue()) {

        }
        if (((Boolean) state.getValue(STATUS_WEST)).booleanValue()) {

        }
        if (((Boolean) state.getValue(STATUS_EAST)).booleanValue()) {

        }

        return new AxisAlignedBB();
                //todo 在不同的连接的时候动态生成aabb

    }
     **/

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
        if (!worldIn.isRemote) {
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
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        try {
            ((TileEntityEnergyCableWithoutGenerateForce)worldIn.getTileEntity(pos)).OnBlockDestroyed();
        } catch (ErrorNumberException e) {
            e.printStackTrace();
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return canconnect(world, pos, facing);
    }

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
