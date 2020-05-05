package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockEnergyCableWithoutGenerateForce extends BlockContainer implements IHasModel {
    public static final EnumMMFETileEntityStatus[] CABLE_STATUS_LIST = {EnumMMFETileEntityStatus.NULL, EnumMMFETileEntityStatus.CABLE, EnumMMFETileEntityStatus.CABLE_OUTPUT, EnumMMFETileEntityStatus.CABLE_INPUT, EnumMMFETileEntityStatus.ENERGYNET_OUTPUT};

    //    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_UP = PropertyEnum.create("statue_up", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_DOWN = PropertyEnum.create("statue_down", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_SOUTH = PropertyEnum.create("statue_south", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_NORTH = PropertyEnum.create("statue_north", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_WEST = PropertyEnum.create("statue_west", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    public static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_EAST = PropertyEnum.create("statue_east", EnumMMFETileEntityStatus.class, CABLE_STATUS_LIST);
//    protected static final PropertyDirection FACES = PropertyDirection.create("faces");
    public static final PropertyBool STATUE_UP = PropertyBool.create("statue_up");
    public static final PropertyBool STATUE_DOWN = PropertyBool.create("statue_down");
    public static final PropertyBool STATUE_SOUTH = PropertyBool.create("statue_sout");
    public static final PropertyBool STATUE_NORTH = PropertyBool.create("statue_nort");
    public static final PropertyBool STATUE_WEST = PropertyBool.create("statue_west");
    public static final PropertyBool STATUE_EAST = PropertyBool.create("statue_east");


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
                .withProperty(STATUE_UP, Boolean.FALSE)
                .withProperty(STATUE_DOWN, Boolean.FALSE)
                .withProperty(STATUE_SOUTH, Boolean.FALSE)
                .withProperty(STATUE_NORTH, Boolean.FALSE)
                .withProperty(STATUE_WEST, Boolean.FALSE)
                .withProperty(STATUE_EAST, Boolean.FALSE);
    }

    public static IBlockState setFacingProperty(EnumFacing facing, Boolean canconnect, IBlockState state) {
        switch (facing.getName()) {
            case "up": {
                return state.withProperty(STATUE_UP, canconnect);
            }
            case "down": {
                return state.withProperty(STATUE_DOWN, canconnect);
            }
            case "south": {
                return state.withProperty(STATUE_SOUTH, canconnect);
            }
            case "north": {
                return state.withProperty(STATUE_NORTH, canconnect);
            }
            case "west": {
                return state.withProperty(STATUE_WEST, canconnect);
            }
            case "east": {
                return state.withProperty(STATUE_EAST, canconnect);
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
        return BlockRenderLayer.SOLID;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ((TileEntityEnergyCableWithoutGenerateForce) worldIn.getTileEntity(pos)).onBlockPlacedBy();
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
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (worldIn.getBlockState(pos).getBlock() instanceof BlockEnergyCableWithoutGenerateForce) {
                state = setFacingProperty(facing, Boolean.TRUE, state);
            }
        }
        return state;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUE_UP, STATUE_DOWN, STATUE_WEST, STATUE_EAST, STATUE_NORTH, STATUE_SOUTH);
//        return new BlockStateContainer(this);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
