package com.dbydd.micro_machinery.blocks;

import com.dbydd.micro_machinery.blocks.machine.BlockContainerBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockWithFacing extends BlockContainerBase {
    public BlockWithFacing(String name, Material material) {
        super(name, material);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity() {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return null;
    }
}
