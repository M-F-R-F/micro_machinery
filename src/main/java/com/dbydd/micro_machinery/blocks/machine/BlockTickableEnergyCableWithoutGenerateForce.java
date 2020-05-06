package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityTickableEnergyCableWithoutGenerateForce;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTickableEnergyCableWithoutGenerateForce extends BlockEnergyCableWithoutGenerateForce {
    public BlockTickableEnergyCableWithoutGenerateForce(String name, Material materialIn, int transferEnergyMaxValue) {
        super(name,materialIn, transferEnergyMaxValue);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTickableEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }
}
