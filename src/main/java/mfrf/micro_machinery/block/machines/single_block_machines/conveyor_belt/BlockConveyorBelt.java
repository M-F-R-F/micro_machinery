package mfrf.micro_machinery.block.machines.single_block_machines.conveyor_belt;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import mfrf.micro_machinery.utils.TriFields;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class BlockConveyorBelt extends MMBlockTileProviderBase {
    public static EnumProperty<EnumConveyorConnectState> OUT_STATE = EnumProperty.create("out_state", EnumConveyorConnectState.class);
    public static BooleanProperty LEFT_STATE = BooleanProperty.create("left_state");
    public static BooleanProperty RIGHT_STATE = BooleanProperty.create("right_state");
    public static BooleanProperty BACK_STATE = BooleanProperty.create("back_state");

    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(OUT_STATE, EnumConveyorConnectState.NULL).setValue(FACING, Direction.SOUTH)
                        .setValue(LEFT_STATE, false).setValue(RIGHT_STATE, false).setValue(BACK_STATE, true)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OUT_STATE);
        builder.add(LEFT_STATE);
        builder.add(RIGHT_STATE);
        builder.add(BACK_STATE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.canPlace()) {
            Direction facing = context.getHorizontalDirection();
            BlockPos pos = context.getClickedPos();
            Level world = context.getLevel();
            BlockState blockState = world.getBlockState(pos.relative(facing));
            BlockEntity tileEntity = world.getBlockEntity(pos.relative(facing));
            boolean up = false;
            boolean down = false;
            boolean findAnyWhere = false;
            boolean connect = false;
            boolean back = false;
            boolean left = false;
            boolean right = false;

            if (!(blockState.getBlock() instanceof BlockConveyorBelt)) {
                if (tileEntity != null && tileEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, facing.getOpposite()).isPresent()) {
                    connect = true;
                } else {
                    BlockPos offset = pos.relative(facing);
                    BlockState upFacing = world.getBlockState(offset.above());
                    BlockEntity tileUp = world.getBlockEntity(offset.above());

                    BlockState downFacing = world.getBlockState(offset.below());
                    BlockEntity tileDown = world.getBlockEntity(offset.below());

                    if (upFacing.getBlock() instanceof BlockConveyorBelt && upFacing.getValue(FACING) != facing.getOpposite() || (tileUp != null && tileUp.getCapability(ForgeCapabilities.ITEM_HANDLER, facing.getOpposite()).isPresent())) {
                        up = true;
                    }
                    if (downFacing.getBlock() instanceof BlockConveyorBelt && downFacing.getValue(FACING) != facing.getOpposite() || (tileDown != null && tileDown.getCapability(ForgeCapabilities.ITEM_HANDLER, facing.getOpposite()).isPresent())) {
                        down = true;
                    }
//                    else {
//                        findAnyWhere = true;
//                    }
                }
            } else {
                connect = true;
            }


//            if (findAnyWhere) {
//                for (Direction direction : Direction.Plane.HORIZONTAL) {
//                    BlockState facingBlock = world.getBlockState(pos.relative(direction));
//                    BlockState facingUp = world.getBlockState(pos.relative(direction).above());
//                    BlockState facingDown = world.getBlockState(pos.relative(direction).below());
//                    if (facingBlock.getBlock() instanceof BlockConveyorBelt) {
//                        if (facingBlock.get(FACING).getOpposite() != direction) {
//                            facing = direction;
//                            break;
//                        }
//                    } else if (facingUp.getBlock() instanceof BlockConveyorBelt && facingUp.get(FACING).getOpposite() != direction) {
//                        up = true;
//                        facing = direction;
//                        break;
//
//                    } else if (facingDown.getBlock() instanceof BlockConveyorBelt && facingDown.get(FACING).getOpposite() != direction) {
//                        down = true;
//                        facing = direction;
//                        break;
//
//                    }
//                }
//            }

            if (!up && !down) {
                for (int i = 0; i < 4; i++) {
                    Direction direction = Direction.from2DDataValue(facing.get2DDataValue() + i);
                    if (direction != facing) {

                        for (int j = 0; j < 3; j++) {
                            BlockPos facingPos = null;
                            switch (j) {
                                case 0: {
                                    facingPos = pos.relative(facing);
                                }
                                case 1: {
                                    facingPos = pos.relative(facing).above();
                                    break;
                                }
                                case 2: {
                                    facingPos = pos.relative(facing).below();
                                    break;
                                }
                            }
                            BlockState facingState = world.getBlockState(facingPos);
                            BlockEntity facingTile = world.getBlockEntity(facingPos);
                            //todo determine state
                            if (facingState.getBlock() instanceof BlockConveyorBelt && facingState.getValue(FACING) == direction.getOpposite() || (facingTile != null && facingTile.getCapability(ForgeCapabilities.ITEM_HANDLER, direction.getOpposite()).isPresent())) {
                                switch (i) {
                                    case 1:
                                        left = true;
                                        break;
                                    case 2:
                                        back = true;
                                        break;
                                    case 3:
                                        right = true;
                                        break;
                                }
                            }
                        }


                    }
                }
            }
            EnumConveyorConnectState outState;
            if (up) {
                outState = EnumConveyorConnectState.UP;
            } else if (down) {
                outState = EnumConveyorConnectState.DOWN;
            } else if (connect) {
                outState = EnumConveyorConnectState.CONNECTED;
            } else {
                outState = EnumConveyorConnectState.NULL;
            }
            return defaultBlockState().setValue(FACING, facing).setValue(OUT_STATE, outState).setValue(LEFT_STATE, left).setValue(RIGHT_STATE, right).setValue(BACK_STATE, back);
        }

        return super.getStateForPlacement(context);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileConveyBelt(pPos, pState);

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_CONVEY_BELT.get(), pBlockEntityType, TileConveyBelt::tick);
    }
}
