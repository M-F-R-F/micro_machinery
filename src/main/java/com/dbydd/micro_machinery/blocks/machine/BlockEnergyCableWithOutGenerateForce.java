package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockEnergyCableWithOutGenerateForce extends BlockContainer {
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_UP = PropertyEnum.create("statue_up", EnumMMFETileEntityStatus.class);
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_DOWN = PropertyEnum.create("statue_down", EnumMMFETileEntityStatus.class);
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_SOUTH = PropertyEnum.create("statue_south", EnumMMFETileEntityStatus.class);
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_NORTH = PropertyEnum.create("statue_north", EnumMMFETileEntityStatus.class);
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_WEST = PropertyEnum.create("statue_west", EnumMMFETileEntityStatus.class);
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE_EAST = PropertyEnum.create("statue_east", EnumMMFETileEntityStatus.class);
//    protected static final PropertyDirection FACES = PropertyDirection.create("faces");

    protected final int transferEnergyMaxValue;

    public BlockEnergyCableWithOutGenerateForce(String name, Material materialIn, int transferEnergyMaxValue) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        this.transferEnergyMaxValue = transferEnergyMaxValue;
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        this.setDefaultState(getDefaultBlockState(this.blockState.getBaseState()));
    }

    public static void setFacingProperty(EnumFacing facing, EnumMMFETileEntityStatus status, BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        switch (facing.getName()) {
            case "up": {
                world.setBlockState(pos, state.withProperty(STATUE_UP, status));
                break;
            }
            case "down": {
                world.setBlockState(pos, state.withProperty(STATUE_DOWN, status));
                break;
            }
            case "south": {
                world.setBlockState(pos, state.withProperty(STATUE_SOUTH, status));
                break;
            }
            case "north": {
                world.setBlockState(pos, state.withProperty(STATUE_NORTH, status));
                break;
            }
            case "west": {
                world.setBlockState(pos, state.withProperty(STATUE_WEST, status));
                break;
            }
            case "east": {
                world.setBlockState(pos, state.withProperty(STATUE_EAST, status));
                break;
            }
        }
    }

    public static IBlockState setFacingProperty(EnumFacing facing, EnumMMFETileEntityStatus status, IBlockState state) {
        switch (facing.getName()) {
            case "up": {
                return state.withProperty(STATUE_UP, status);
            }
            case "down": {
                return state.withProperty(STATUE_DOWN, status);
            }
            case "south": {
                return state.withProperty(STATUE_SOUTH, status);
            }
            case "north": {
                return state.withProperty(STATUE_NORTH, status);
            }
            case "west": {
                return state.withProperty(STATUE_WEST, status);
            }
            case "east": {
                return state.withProperty(STATUE_EAST, status);
            }
        }
        return state;
    }

    private static IBlockState getDefaultBlockState(IBlockState state) {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            state = setFacingProperty(facing, EnumMMFETileEntityStatus.NULL, state);
        }
        return state;
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
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultBlockState(this.blockState.getBaseState());
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUE_UP, STATUE_DOWN, STATUE_WEST, STATUE_EAST, STATUE_NORTH, STATUE_SOUTH);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }
}
