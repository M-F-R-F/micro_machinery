package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityHandGenerator;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHandGenerator extends BlockContainerBase {

    public BlockHandGenerator() {
        super("hand_generator", Material.IRON);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing == EnumFacing.fromAngle(state.getValue(BlockHorizontal.FACING).getHorizontalAngle() - 90f) && !worldIn.isRemote) {
            ((TileEntityHandGenerator) worldIn.getTileEntity(pos)).actived();
        }

        return true;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityHandGenerator();
    }
}
