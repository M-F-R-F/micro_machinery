package org.mfrf.micro_machienry.blocks.machines.multi_block_old_system.pump;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPump extends MMMultiBlockHolderBase {
    public static final BooleanProperty HEAD_OR_PIPE = BooleanProperty.create("head_or_pipe");

    public BlockPump(Properties properties) {
        super(properties, "pump", true, true, false);
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(IS_PLACEHOLDER, false).with(HEAD_OR_PIPE, false));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (!state.get(IS_PLACEHOLDER)) {
            return new TilePump();
        } else {
            return new TilePlaceHolder();
        }
    }

    @Override
    public BlockPos getMainPartPos(BlockState state, BlockPos currentPos) {
        if (state.get(IS_PLACEHOLDER)) {
            if (state.get(HEAD_OR_PIPE)) {
                return currentPos.offset(Direction.DOWN);
            } else {
                return currentPos.offset(state.get(FACING));
            }
        }
        return super.getMainPartPos(state, currentPos);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (state.get(IS_PLACEHOLDER)) {
            if (state.get(HEAD_OR_PIPE)) {
                worldIn.destroyBlock(pos.offset(Direction.DOWN), false, player);
                worldIn.destroyBlock(pos.offset(Direction.DOWN).offset(state.get(FACING).getOpposite()), false, player);
            } else {
                worldIn.destroyBlock(pos.offset(state.get(FACING)), false, player);
                worldIn.destroyBlock(pos.offset(Direction.UP).offset(state.get(FACING)), false, player);
            }
        } else {
            worldIn.destroyBlock(pos.offset(Direction.UP), false, player);
            worldIn.destroyBlock(pos.offset(state.get(FACING).getOpposite()), false, player);
        }
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        if (state.get(IS_PLACEHOLDER)) {
            if (state.get(HEAD_OR_PIPE)) {
                worldIn.destroyBlock(pos.offset(Direction.DOWN), false);
                worldIn.destroyBlock(pos.offset(Direction.DOWN).offset(state.get(FACING).getOpposite()), false);
            } else {
                worldIn.destroyBlock(pos.offset(state.get(FACING)), false);
                worldIn.destroyBlock(pos.offset(Direction.UP).offset(state.get(FACING)), false);
            }
        } else {
            worldIn.destroyBlock(pos.offset(Direction.UP), false);
            worldIn.destroyBlock(pos.offset(state.get(FACING).getOpposite()), false);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Direction direction = state.get(FACING);
        worldIn.setBlockState(pos.offset(direction.getOpposite()), getDefaultState().with(FACING, direction).with(IS_PLACEHOLDER, true).with(HEAD_OR_PIPE, false));
        worldIn.setBlockState(pos.offset(Direction.UP), getDefaultState().with(FACING, direction).with(IS_PLACEHOLDER, true).with(HEAD_OR_PIPE, true));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HEAD_OR_PIPE);
    }
}
