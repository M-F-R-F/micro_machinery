package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class FireGenerator extends BlockContainerBase {
    private static final PropertyBool GENERATING = PropertyBool.create("generating");

    public FireGenerator() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setUnlocalizedName("firegenerator");
        setRegistryName("firegenerator");
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        setHardness(3.0f);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(GENERATING, false));
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active)
            worldIn.setBlockState(pos, ModBlocks.KLIN.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(GENERATING, true), 3);
        else
            worldIn.setBlockState(pos, ModBlocks.KLIN.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(GENERATING, false), 3);

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, GENERATING, FACING);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFireGenerator(25600);
    }

}
