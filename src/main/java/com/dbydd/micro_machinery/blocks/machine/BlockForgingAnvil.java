package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

public class BlockForgingAnvil extends BlockContainer implements IHasModel {
    private static final PropertyDirection FACING = BlockHorizontal.FACING;
    private int level;

    public BlockForgingAnvil(String name, int level) {
        super(Material.ANVIL);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.ANVIL);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void registerModels() {
//        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "invenroty");
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty<?>[]
                {FACING}, new IUnlistedProperty<?>[]
                {OBJModel.OBJProperty.INSTANCE});
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        IExtendedBlockState oldstate = (IExtendedBlockState) state;
        return null;
        //todo
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityForgingAnvil(level);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(Micro_Machinery.instance, Reference.GUI_ForgingAnvil, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

//    @Override
//    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
//        if (!worldIn.isRemote) {
//            IBlockState north = worldIn.getBlockState(pos.north());
//            IBlockState south = worldIn.getBlockState(pos.south());
//            IBlockState west = worldIn.getBlockState(pos.west());
//            IBlockState east = worldIn.getBlockState(pos.east());
//            EnumFacing face = (EnumFacing) state.getValue(FACING);
//
//            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
//            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
//            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
//            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
//            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
//        }
//    }

//    @Override
//    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
//        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
//    }
//
//    @Override
//    public IBlockState withRotation(IBlockState state, Rotation rot) {
//        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
//    }
//
//    @Override
//    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
//        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
//    }

//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        EnumFacing facing = EnumFacing.getFront(meta);
//        if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
//        return this.getDefaultState().withProperty(FACING, facing);
//    }

//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return ((EnumFacing) state.getValue(FACING)).getIndex();
//    }


}

