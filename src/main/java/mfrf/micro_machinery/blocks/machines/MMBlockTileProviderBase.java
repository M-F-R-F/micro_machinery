package mfrf.micro_machinery.blocks.machines;

import mfrf.micro_machinery.blocks.MMBlockBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public abstract class MMBlockTileProviderBase extends MMBlockBase implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MMBlockTileProviderBase(Properties properties, String name) {
        super(properties, name);
    }

    public MMBlockTileProviderBase(Properties properties, String name, boolean noItem) {
        super(properties, name, noItem);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public abstract BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_);

    @Nullable
    @Override
    public abstract <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_);

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return defaultBlockState().setValue(FACING, p_49820_.getClickedFace().getOpposite());
    }

    protected BlockState getStateToRegistry() {
        return this.defaultBlockState().setValue(FACING, Direction.NORTH);
    }
}
