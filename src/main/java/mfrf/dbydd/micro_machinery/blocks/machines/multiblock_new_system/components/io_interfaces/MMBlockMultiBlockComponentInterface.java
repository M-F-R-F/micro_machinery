package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MMBlockMultiBlockComponentInterface extends MMBlockTileProviderBase {
    public static BooleanProperty CONSTRUCTED = BooleanProperty.create("constructed");

    public MMBlockMultiBlockComponentInterface(Properties properties, String name) {
        super(properties, name, false);
        this.setDefaultState(getStateToRegistry());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(CONSTRUCTED);
    }

    @Override
    protected BlockState getStateToRegistry() {
        return super.getStateToRegistry().with(CONSTRUCTED, false);
    }

    public void link(BlockPos mainPart, World accessor, BlockPos current) {
        accessor.setBlockState(current, accessor.getBlockState(current).with(CONSTRUCTED, true));
        linkTo(current, accessor, current);
    }


    protected void linkTo(BlockPos mainPart, World accessor, BlockPos currentPos) {
        MMTileMultiBlockComponentInterface tileEntity = (MMTileMultiBlockComponentInterface) accessor.getTileEntity(currentPos);
        tileEntity.linkTo(mainPart, accessor);
    }
}
