package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityTickableEnergyCableWithoutGenerateForce;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTickableEnergyCableWithoutGenerateForce extends BlockEnergyCableWithOutGenerateForce {
    public BlockTickableEnergyCableWithoutGenerateForce(String name, Material materialIn, int transferEnergyMaxValue) {
        super(name,materialIn, transferEnergyMaxValue);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTickableEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }
}
