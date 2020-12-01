package mfrf.dbydd.micro_machinery.blocks.machines;

import mfrf.dbydd.micro_machinery.utils.MultiBlockStructureMaps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MMMultiBlockBase extends MMBlockTileProviderBase {
    public static final BooleanProperty IS_PLACEHOLDER = BooleanProperty.create("is_place_holder");
    public static List<Block> PLACE_HOLDER_LIST = new ArrayList<>();
    public static Map<String,Block> MAIN_PART_LIST = new HashMap<>();

    //I think I messed up :(
    public MMMultiBlockBase(Properties properties, String name, boolean isPlaceHolder, boolean autoRegisterItem, boolean requireJSON) {
        super(properties, name, autoRegisterItem);
        if (isPlaceHolder) {
            PLACE_HOLDER_LIST.add(this);
        }
        if (requireJSON) {
            MultiBlockStructureMaps.NAMES.add(name);
            MAIN_PART_LIST.put(name,this);
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
