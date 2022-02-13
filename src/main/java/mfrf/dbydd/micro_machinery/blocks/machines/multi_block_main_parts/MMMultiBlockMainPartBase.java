package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockHolderBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

public abstract class MMMultiBlockMainPartBase extends MMMultiBlockHolderBase {
    public static final BooleanProperty MODEL_PLACEHOLDER = BooleanProperty.create("model_place_holder");

    public MMMultiBlockMainPartBase(Properties properties, String name, boolean addToPlaceHolderList, boolean haveBlockItem, boolean addToStructureMaps) {
        super(properties, name, addToPlaceHolderList, haveBlockItem, addToStructureMaps);
        this.setDefaultState(getDefaultState().with(MODEL_PLACEHOLDER, false));
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(MODEL_PLACEHOLDER);
    }
}
