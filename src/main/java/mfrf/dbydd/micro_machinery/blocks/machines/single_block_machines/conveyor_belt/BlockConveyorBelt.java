package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.TriFields;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockConveyorBelt extends MMBlockBase {
    public static EnumProperty<EnumConveyorConnectState> NORTH_STATE = EnumProperty.create("north", EnumConveyorConnectState.class);
    public static EnumProperty<EnumConveyorConnectState> SOUTH_STATE = EnumProperty.create("south", EnumConveyorConnectState.class);
    public static EnumProperty<EnumConveyorConnectState> EAST_STATE = EnumProperty.create("west", EnumConveyorConnectState.class);
    public static EnumProperty<EnumConveyorConnectState> WEST_STATE = EnumProperty.create("east", EnumConveyorConnectState.class);
    public final TriFields<Integer, Integer, Integer> properties_speed_stack_interval_supplier;

    public BlockConveyorBelt(Properties properties, String name, TriFields<Integer, Integer, Integer> speed_stack_interval) {
        super(properties, name);
        this.properties_speed_stack_interval_supplier = speed_stack_interval;
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH_STATE, EnumConveyorConnectState.IN));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(NORTH_STATE);
        builder.add(SOUTH_STATE);
        builder.add(WEST_STATE);
        builder.add(EAST_STATE);
    }

    //todo determine state while place
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//
//    }

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
