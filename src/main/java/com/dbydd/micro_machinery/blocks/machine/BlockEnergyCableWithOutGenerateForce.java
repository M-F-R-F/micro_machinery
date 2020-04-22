package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithOutGenerateForce;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEnergyCableWithOutGenerateForce extends BlockContainer {
    private final int transferEnergyValue;

    public BlockEnergyCableWithOutGenerateForce(Material materialIn, int transferEnergyValue) {
        super(materialIn);
        this.transferEnergyValue = transferEnergyValue;
    }

    public SurrondingsState updateState(BlockPos pos, World world) {
        SurrondingsState state = new SurrondingsState(pos, world);
        //todo 完善各个面的blockstate更新
        return state;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergyCableWithOutGenerateForce(transferEnergyValue);
    }
}
