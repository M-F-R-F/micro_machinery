package mfrf.micro_machinery.blocks.machines.multi_block_old_system.pump;

import mfrf.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;

public class BlockPump extends MMMultiBlockHolderBase {
    public static final BooleanProperty HEAD_OR_PIPE = BooleanProperty.create("head_or_pipe");

    public BlockPump(Properties properties) {
        super(properties, "pump", true, true, false);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.SOUTH).setValue(IS_PLACEHOLDER, false).setValue(HEAD_OR_PIPE, false));
    }

    @Override
    public BlockPos getMainPartPos(BlockState state, BlockPos currentPos) {
        if (state.getValue(IS_PLACEHOLDER)) {
            if (state.getValue(HEAD_OR_PIPE)) {
                return currentPos.m_142300_(Direction.DOWN);
            } else {
                return currentPos.m_142300_(state.getValue(FACING));
            }
        }
        return super.getMainPartPos(state, currentPos);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (!pState.getValue(IS_PLACEHOLDER)) {
            return new TilePump(pPos, pState);
        } else {
            return new TilePlaceHolder();
        }
    }

    //todo migrate
    @Override
    public @org.jetbrains.annotations.Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pState.getValue(IS_PLACEHOLDER)) {
        return TileHelper.createTicker(pLevel,pBlockEntityType,TilePump.tick)
        } else {
            return new TilePlaceHolder();
        }
    }

    @Override
    public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(worldIn, player, pos, state, pBlockEntity, pTool);
        if (state.getValue(IS_PLACEHOLDER)) {
            if (state.getValue(HEAD_OR_PIPE)) {
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN), false, player);
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN).m_142300_(state.getValue(FACING).getOpposite()), false, player);
            } else {
                worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING)), false, player);
                worldIn.destroyBlock(pos.m_142300_(Direction.UP).m_142300_(state.getValue(FACING)), false, player);
            }
        } else {
            worldIn.destroyBlock(pos.m_142300_(Direction.UP), false, player);
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).getOpposite()), false, player);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level worldIn, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        super.onDestroyedByPlayer(state, worldIn, pos, player, willHarvest, fluid);
        if (state.getValue(IS_PLACEHOLDER)) {
            if (state.getValue(HEAD_OR_PIPE)) {
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN), false, player);
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN).m_142300_(state.getValue(FACING).getOpposite()), false, player);
            } else {
                worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING)), false, player);
                worldIn.destroyBlock(pos.m_142300_(Direction.UP).m_142300_(state.getValue(FACING)), false, player);
            }
        } else {
            worldIn.destroyBlock(pos.m_142300_(Direction.UP), false, player);
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).getOpposite()), false, player);
        }
        return true;
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        super.destroy(worldIn, pos, state);
        if (state.getValue(IS_PLACEHOLDER)) {
            if (state.getValue(HEAD_OR_PIPE)) {
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN), false);
                worldIn.destroyBlock(pos.m_142300_(Direction.DOWN).m_142300_(state.getValue(FACING).getOpposite()), false);
            } else {
                worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING)), false);
                worldIn.destroyBlock(pos.m_142300_(Direction.UP).m_142300_(state.getValue(FACING)), false);
            }
        } else {
            worldIn.destroyBlock(pos.m_142300_(Direction.UP), false);
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).getOpposite()), false);
        }
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(worldIn, pos, state, pPlacer, pStack);
        Direction direction = state.getValue(FACING);
        worldIn.setBlockAndUpdate(pos.m_142300_(direction.getOpposite()), defaultBlockState().setValue(FACING, direction).setValue(IS_PLACEHOLDER, true).setValue(HEAD_OR_PIPE, false));
        worldIn.setBlockAndUpdate(pos.m_142300_(Direction.UP), defaultBlockState().setValue(FACING, direction).setValue(IS_PLACEHOLDER, true).setValue(HEAD_OR_PIPE, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(HEAD_OR_PIPE);
    }
}
