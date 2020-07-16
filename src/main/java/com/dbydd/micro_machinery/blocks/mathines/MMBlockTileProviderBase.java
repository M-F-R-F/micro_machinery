package com.dbydd.micro_machinery.blocks.mathines;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MMBlockTileProviderBase extends MMBlockBase {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public MMBlockTileProviderBase(Properties properties, String name, RenderType renderType) {
        super(properties, name, renderType);
        this.setDefaultState(get_DefaultState());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    protected BlockState get_DefaultState(){
        return this.stateContainer.getBaseState().with(FACING, Direction.NORTH);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.fillStateContainer(builder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote) {
            worldIn.setBlockState(pos, this.getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()), 2);
            worldIn.markChunkDirty(pos, worldIn.getTileEntity(pos));
            super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        }
    }


}
