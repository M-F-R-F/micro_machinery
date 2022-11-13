package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockConveyorBelt extends MMBlockTileProviderBase {
    public static EnumProperty<EnumConveyorConnectState> OUT_STATE = EnumProperty.create("in_state", EnumConveyorConnectState.class);
    public static BooleanProperty LEFT_STATE = BooleanProperty.create("left");
    public static BooleanProperty RIGHT_STATE = BooleanProperty.create("right");
    public static BooleanProperty BACK_STATE = BooleanProperty.create("back");

    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, String name, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties, name);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.setDefaultState(
                this.stateContainer.getBaseState()
                        .with(OUT_STATE, EnumConveyorConnectState.CONNECTED).with(FACING, Direction.SOUTH)
                        .with(LEFT_STATE, false).with(RIGHT_STATE, false).with(BACK_STATE, true)
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
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileConveyBelt();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
