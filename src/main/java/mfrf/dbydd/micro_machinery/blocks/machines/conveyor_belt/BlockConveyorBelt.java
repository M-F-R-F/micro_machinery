package mfrf.dbydd.micro_machinery.blocks.machines.conveyor_belt;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import org.apache.commons.math3.util.Pair;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BlockConveyorBelt extends MMBlockBase {
    public static EnumProperty<Direction> CONVEYOR_HORIZONTAL_DIRECTION_STATE = EnumProperty.create("horizontal_direction", Direction.class, Direction.Plane.HORIZONTAL::test);
    public static EnumProperty<EnumConveyorConnectState> CONNECT_STATE = EnumProperty.create("connect_state", EnumConveyorConnectState.class);
    private final Supplier<Pair<Integer, Integer>> propertiesSupplier;

    public BlockConveyorBelt(Properties properties, String name, Supplier<Pair<Integer, Integer>> speed_group) {
        super(properties, name);
        this.propertiesSupplier = speed_group;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(CONVEYOR_HORIZONTAL_DIRECTION_STATE);
        builder.add(CONNECT_STATE);
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
