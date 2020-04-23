package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityTickableEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockEnergyCableWithOutGenerateForce extends BlockContainer {
    protected final int transferEnergyMaxValue;

    public BlockEnergyCableWithOutGenerateForce(String name,Material materialIn, int transferEnergyMaxValue) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        this.transferEnergyMaxValue = transferEnergyMaxValue;
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ((TileEntityEnergyCableWithoutGenerateForce)worldIn.getTileEntity(pos)).updateState();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return super.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TileEntityEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }
}
