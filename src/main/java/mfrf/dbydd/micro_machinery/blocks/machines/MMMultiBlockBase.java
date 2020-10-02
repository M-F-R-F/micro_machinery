package mfrf.dbydd.micro_machinery.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.List;

public abstract class MMMultiBlockBase extends MMBlockTileProviderBase {
    protected static final BooleanProperty IS_PLACEHOLDER = BooleanProperty.create("is_place_holder");
    public static List<Block> PlaceHolderList = new ArrayList<>();

    public MMMultiBlockBase(Properties properties, String name, boolean addPlaceHolder, boolean autoRegisterItem) {
        super(properties, name, autoRegisterItem);
        if (addPlaceHolder) {
            PlaceHolderList.add(this);
        }
    }

    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(IS_PLACEHOLDER);
        super.fillStateContainer(builder);
    }

}
