package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.tileentities.TileentityHandGenerator;
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
        super("handgenerator", Material.IRON);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
       //todo 判断位置
        ((TileentityHandGenerator)worldIn.getTileEntity(pos)).actived();

        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileentityHandGenerator();
    }
}
