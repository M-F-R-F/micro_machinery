package org.mfrf.micro_machienry.blocks.machines;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public abstract class MMBlockTileProviderBase extends MMBlockBase {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public MMBlockTileProviderBase(Properties properties, String name) {
        super(properties, name);
    }

    public MMBlockTileProviderBase(Properties properties, String name, boolean noItem) {
        super(properties, name, noItem);
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

    protected BlockState getStateToRegistry(){
        return this.stateContainer.getBaseState().with(FACING, Direction.NORTH);
    }
}
