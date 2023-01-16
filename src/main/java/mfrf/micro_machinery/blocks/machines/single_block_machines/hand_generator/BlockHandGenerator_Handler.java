package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;

public class BlockHandGenerator_Handler extends MMBlockBase {
    public BlockHandGenerator_Handler() {
        super("hand_generator_1", true);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }
}
