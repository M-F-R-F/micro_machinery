package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import scala.tools.nsc.doc.model.Public;

import java.util.List;

public class Cable_Heads extends BlockBase {
    public static final PropertyBool STATUS_UP = PropertyBool.create("status_up");
    public static final PropertyBool STATUS_DOWN = PropertyBool.create("status_down");
    public static final PropertyBool STATUS_SOUTH = PropertyBool.create("status_south");
    public static final PropertyBool STATUS_NORTH = PropertyBool.create("status_north");
    public static final PropertyBool STATUS_WEST = PropertyBool.create("status_west");
    public static final PropertyBool STATUS_EAST = PropertyBool.create("status_east");

    public Cable_Heads(String name) {
        super(name, Material.IRON);
        this.setDefaultState(getDefaultBlockState(this.blockState.getBaseState()));
    }

    public IBlockState InitState(List<EnumFacing> list){
        IBlockState state = this.getDefaultState();
        for(EnumFacing facing : list){
            state = setFacingProperty(facing, true, state);
        }
        return state;
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

    public static IBlockState setFacingProperty(EnumFacing facing, Boolean actived, IBlockState state) {
        switch (facing.getName()) {
            case "up": {
                return state.withProperty(STATUS_UP, actived);
            }
            case "down": {
                return state.withProperty(STATUS_DOWN, actived);
            }
            case "south": {
                return state.withProperty(STATUS_SOUTH, actived);
            }
            case "north": {
                return state.withProperty(STATUS_NORTH, actived);
            }
            case "west": {
                return state.withProperty(STATUS_WEST, actived);
            }
            case "east": {
                return state.withProperty(STATUS_EAST, actived);
            }
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUS_UP, STATUS_DOWN, STATUS_WEST, STATUS_EAST, STATUS_NORTH, STATUS_SOUTH);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
