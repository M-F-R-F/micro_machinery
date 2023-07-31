package mfrf.micro_machinery.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class MMDirectionalBlock extends HorizontalDirectionalBlock {
    public MMDirectionalBlock(Properties pProperties) {
        super(pProperties);
    }

    public MMDirectionalBlock() {
        super(MMBlockBase.DEFAULT_PROPERTIES);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction horizontalDirection = pContext.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, horizontalDirection.getOpposite());
    }
}
