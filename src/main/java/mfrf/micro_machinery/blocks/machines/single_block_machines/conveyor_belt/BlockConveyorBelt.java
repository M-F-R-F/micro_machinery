package mfrf.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.micro_machinery.utils.TriFields;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class BlockConveyorBelt extends MMBlockTileProviderBase {
    public static EnumProperty<EnumConveyorConnectState> OUT_STATE = EnumProperty.create("out_state", EnumConveyorConnectState.class);
    public static BooleanProperty LEFT_STATE = BooleanProperty.create("left_state");
    public static BooleanProperty RIGHT_STATE = BooleanProperty.create("right_state");
    public static BooleanProperty BACK_STATE = BooleanProperty.create("back_state");

    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, String name, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties, name);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(OUT_STATE, EnumConveyorConnectState.NULL).setValue(FACING, Direction.SOUTH)
                        .setValue(LEFT_STATE, false).setValue(RIGHT_STATE, false).setValue(BACK_STATE, true)
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(OUT_STATE);
        builder.add(LEFT_STATE);
        builder.add(RIGHT_STATE);
        builder.add(BACK_STATE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.canPlace()) {
            Direction facing = context.getPlacementHorizontalFacing();
            BlockPos pos = context.getPos();
            World world = context.getWorld();
            BlockState blockState = world.getBlockState(pos.m_142300_(facing));
            BlockEntity tileEntity = world.getBlockEntity(pos.m_142300_(facing));
            boolean up = false;
            boolean down = false;
            boolean findAnyWhere = false;
            boolean connect = false;
            boolean back = false;
            boolean left = false;
            boolean right = false;

            if (!(blockState.getBlock() instanceof BlockConveyorBelt)) {
                if (tileEntity != null && tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite()).isPresent()) {
                    connect = true;
                } else {
                    BlockPos.m_142300_ = pos.m_142300_(facing);
                    BlockState upFacing = world.getBlockState.m_142300_.up());
                    BlockEntity tileUp = world.getBlockEntity.m_142300_.up());

                    BlockState downFacing = world.getBlockState.m_142300_.down());
                    BlockEntity tileDown = world.getBlockEntity.m_142300_.down());

                    if (upFacing.getBlock() instanceof BlockConveyorBelt && upFacing.get(FACING) != facing.getOpposite() || (tileUp != null && tileUp.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite()).isPresent())) {
                        up = true;
                    }
                    if (downFacing.getBlock() instanceof BlockConveyorBelt && downFacing.get(FACING) != facing.getOpposite() || (tileDown != null && tileDown.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite()).isPresent())) {
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
//                    BlockState facingBlock = world.getBlockState(pos.m_142300_(direction));
//                    BlockState facingUp = world.getBlockState(pos.m_142300_(direction).up());
//                    BlockState facingDown = world.getBlockState(pos.m_142300_(direction).down());
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
                    Direction direction = Direction.byHorizontalIndex(facing.getHorizontalIndex() + i);
                    if (direction != facing) {

                        for (int j = 0; j < 3; j++) {
                            BlockPos facingPos = null;
                            switch (j) {
                                case 0: {
                                    facingPos = pos.m_142300_(facing);
                                }
                                case 1: {
                                    facingPos = pos.m_142300_(facing).up();
                                    break;
                                }
                                case 2: {
                                    facingPos = pos.m_142300_(facing).down();
                                    break;
                                }
                            }
                            BlockState facingState = world.getBlockState(facingPos);
                            BlockEntity facingTile = world.getBlockEntity(facingPos);
                            //todo determine state
                            if (facingState.getBlock() instanceof BlockConveyorBelt && facingState.get(FACING) == direction.getOpposite() || (facingTile != null && facingTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite()).isPresent())) {
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
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileConveyBelt();
    }

    @Override
    public boolean hasBlockEntity(BlockState state) {
        return true;
    }
}
