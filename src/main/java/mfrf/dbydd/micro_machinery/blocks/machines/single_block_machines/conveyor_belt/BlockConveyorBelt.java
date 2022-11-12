package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockConveyorBelt extends MMBlockBase {
    public static EnumProperty<EnumConveyorConnectState> IN_STATE = EnumProperty.create("in_state", EnumConveyorConnectState.class);
    public static EnumProperty<Direction> IN_DIRECTION = EnumProperty.create("in_direction", Direction.class, Direction.Plane.HORIZONTAL);
    public static EnumProperty<EnumConveyorConnectState> OUT_STATE = EnumProperty.create("out_state", EnumConveyorConnectState.class);
    public static EnumProperty<Direction> OUT_DIRECTION = EnumProperty.create("out_direction", Direction.class, Direction.Plane.HORIZONTAL);

    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, String name, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties, name);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.setDefaultState(
                this.stateContainer.getBaseState()
                        .with(IN_STATE, EnumConveyorConnectState.CONNECTED).with(IN_DIRECTION, Direction.NORTH)
                        .with(OUT_STATE, EnumConveyorConnectState.CONNECTED).with(OUT_DIRECTION, Direction.SOUTH)
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IN_STATE);
        builder.add(IN_DIRECTION);
        builder.add(OUT_DIRECTION);
        builder.add(OUT_STATE);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileConveyBelt();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
