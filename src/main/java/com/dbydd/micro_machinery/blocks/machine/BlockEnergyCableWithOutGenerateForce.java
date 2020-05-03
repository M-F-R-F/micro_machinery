package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
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
    protected static final PropertyEnum<EnumMMFETileEntityStatus> STATUE = PropertyEnum.create("statue", EnumMMFETileEntityStatus.class);
    protected static final PropertyDirection FACES = PropertyDirection.create("faces");

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
        world.setBlockState(pos, state.withProperty(STATUE, status));
    }

    private static IBlockState getDefaultBlockState(IBlockState state) {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            state.withProperty(FACES, facing).withProperty(STATUE, EnumMMFETileEntityStatus.NULL);
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
        return new BlockStateContainer(this, FACES, STATUE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergyCableWithoutGenerateForce(transferEnergyMaxValue);
    }
}
