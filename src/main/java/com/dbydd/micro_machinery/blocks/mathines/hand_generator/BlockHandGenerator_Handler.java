package com.dbydd.micro_machinery.blocks.mathines.hand_generator;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

public class BlockHandGenerator_Handler extends MMBlockBase {
    public BlockHandGenerator_Handler() {
        super("hand_generator_1", true);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }
}
