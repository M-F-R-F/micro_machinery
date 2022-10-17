package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockConveyorBelt extends MMBlockBase {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static EnumProperty<EnumConveyorConnectState> LIFT = EnumProperty.create("lift", EnumConveyorConnectState.class);
    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, String name, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties, name);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIFT, EnumConveyorConnectState.CONNECTED));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(LIFT);
    }

    //todo determine state while place


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        boolean sneaking = context.getPlayer().isSneaking();
        boolean isAirBorne = context.getPlayer().isAirBorne;
        Direction clickedFace = context.getFace();
        boolean isHorizontal = Direction.Plane.HORIZONTAL.test(clickedFace);
        Direction horizontalFacing = context.getPlacementHorizontalFacing();
        boolean revert = sneaking && (isAirBorne && !isHorizontal);
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos offset = pos.offset(horizontalFacing);
        if (world.getBlockState(offset).getBlock() instanceof BlockConveyorBelt) {
            return getDefaultState().with(FACING, horizontalFacing).with(LIFT, EnumConveyorConnectState.CONNECTED);
        } else if (world.getBlockState(offset.up()).getBlock() instanceof BlockConveyorBelt) {
            return getDefaultState().with(FACING, horizontalFacing).with(LIFT, EnumConveyorConnectState.UP);
        } else if (world.getBlockState(offset.down()).getBlock() instanceof BlockConveyorBelt) {
            return getDefaultState().with(FACING, horizontalFacing).with(LIFT, EnumConveyorConnectState.DOWN);
        } else if (revert) {
            return getDefaultState().with(FACING, horizontalFacing.getOpposite()).with(LIFT, EnumConveyorConnectState.CONNECTED);
        } else {
            for (Direction value : Direction.Plane.HORIZONTAL) {
                BlockState blockState = world.getBlockState(pos.offset(value));
                if (!(blockState.getBlock() instanceof BlockConveyorBelt)) {
                    for (Direction vertical : Direction.Plane.VERTICAL) {
                        return getDefaultState().with(FACING, blockState.get(FACING)).with(LIFT, EnumConveyorConnectState.thonk(vertical));
                    }
                } else {
                    return getDefaultState().with(FACING, blockState.get(FACING)).with(LIFT, EnumConveyorConnectState.CONNECTED);
                }
            }
            return getDefaultState();
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return super.createTileEntity(state, world);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
