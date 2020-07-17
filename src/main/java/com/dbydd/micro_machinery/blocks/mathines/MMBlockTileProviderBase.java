package com.dbydd.micro_machinery.blocks.mathines;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.registery_lists.BlockRenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class MMBlockTileProviderBase extends MMBlockBase {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public MMBlockTileProviderBase(Properties properties, String name, RenderType renderType) {
        super(properties, name, renderType);
        BlockRenderTypes.blockRenderTypeMap.put(this, renderType);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.fillStateContainer(builder);
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
}
