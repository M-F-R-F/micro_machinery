package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

public class BlockBlastFurnace extends MMMultiBlockBase {
    public static BooleanProperty IS_BURNING = BooleanProperty.create("is_burning");

    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(IS_BURNING, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IS_BURNING);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBlastFurnace();
    }
}
