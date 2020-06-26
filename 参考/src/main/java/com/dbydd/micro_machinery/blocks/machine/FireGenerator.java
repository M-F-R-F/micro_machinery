package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

public class FireGenerator extends BlockContainerBase {
    public static final PropertyBool GENERATING = PropertyBool.create("generating");

    public FireGenerator() {
        super("firegenerator", Material.IRON);
        setSoundType(SoundType.METAL);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        setHardness(3.0f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(GENERATING, false));
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active)
            worldIn.setBlockState(pos, ModBlocks.FIREGENERATOR.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(GENERATING, true), 3);
        else
            worldIn.setBlockState(pos, ModBlocks.FIREGENERATOR.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(GENERATING, false), 3);

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
                TileEntityFireGenerator te = ((TileEntityFireGenerator) worldIn.getTileEntity(pos));
                if (te.addWater()) {
                    playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                }
            } else if (!playerIn.isSneaking()) {
                playerIn.openGui(Micro_Machinery.instance, Reference.GUI_FireGenerator, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.FIREGENERATOR);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.FIREGENERATOR);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, GENERATING, FACING);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityFireGenerator(25600);
    }
}
