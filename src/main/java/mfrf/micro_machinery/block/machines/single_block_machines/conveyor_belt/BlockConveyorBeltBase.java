package mfrf.micro_machinery.block.machines.single_block_machines.conveyor_belt;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import mfrf.micro_machinery.utils.TriFields;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

public class BlockConveyorBeltBase extends MMBlockTileProviderBase {
    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;
    public static final EnumProperty<EnumConveyorConnectState> CONNECT_STATE = EnumProperty.create("connect_state", EnumConveyorConnectState.class);


    public BlockConveyorBeltBase(Properties properties, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(CONNECT_STATE, EnumConveyorConnectState.STRAIGHT)
                        .setValue(FACING, Direction.SOUTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(CONNECT_STATE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Direction horizontalDirection = context.getHorizontalDirection();
        Direction clickedFace = context.getClickedFace();
        Player player = context.getPlayer();
        Level level = context.getLevel();

        BlockState state = defaultBlockState().setValue(FACING, horizontalDirection).setValue(CONNECT_STATE, EnumConveyorConnectState.STRAIGHT);

        if (context.canPlace()) {
            boolean shiftKeyDown = player.isShiftKeyDown();

            if (clickedFace == horizontalDirection.getOpposite()) {
                return state = state.setValue(FACING, shiftKeyDown ? clickedFace : clickedFace.getOpposite());
            }

            {
                BlockPos shifted = clickedPos.relative(horizontalDirection);
                BlockState below = level.getBlockState(shifted.below());
                BlockState above = level.getBlockState(shifted.above());
                if (below.getBlock() instanceof BlockConveyorBeltBase) {
                    state = state.setValue(FACING, below.getValue(FACING)).setValue(CONNECT_STATE, EnumConveyorConnectState.DOWN);
                    return state;
                } else if (above.getBlock() instanceof BlockConveyorBeltBase) {
                    state = state.setValue(FACING, above.getValue(FACING)).setValue(CONNECT_STATE, EnumConveyorConnectState.UP);
                    return state;
                }

            }

            {
                BlockState l = level.getBlockState(clickedPos.relative(horizontalDirection.getCounterClockWise()));
                BlockState r = level.getBlockState(clickedPos.relative(horizontalDirection.getClockWise()));

                if (l.getBlock() instanceof BlockConveyorBeltBase) {
                    Direction l_facing = l.getValue(FACING);
                    if (r.getBlock() instanceof BlockConveyorBeltBase) {
                        Direction r_facing = r.getValue(FACING);

                        if (l_facing == r_facing) {
                            state = state.setValue(FACING, r_facing);
                            return state;
                        } else if (l_facing.getOpposite() == r_facing) {
                            if (l_facing == horizontalDirection.getClockWise()) {
                                state = state.setValue(FACING, horizontalDirection).setValue(CONNECT_STATE, EnumConveyorConnectState.MERGE);
                            } else {
                                state = state.setValue(FACING, horizontalDirection.getOpposite()).setValue(CONNECT_STATE, EnumConveyorConnectState.DIVIDE);
                            }
                            return state;
                        }
                    } else {
                        state = state.setValue(FACING, l_facing);
                        return state;
                    }
                } else if (r.getBlock() instanceof BlockConveyorBeltBase) {
                    state = state.setValue(FACING, r.getValue(FACING));
                    return state;
                }
            }


        }
        return state;
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileConveyBelt(pPos, pState);

    }

    @Nullable
    @Override
    //todo use different ticker
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_CONVEY_BELT.get(), pBlockEntityType, TileConveyBelt::tick);
    }
}
