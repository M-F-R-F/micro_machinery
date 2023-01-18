package mfrf.micro_machinery.blocks.machines.multi_block_old_system;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MMMultiBlockHolderBase extends MMBlockTileProviderBase {
    public static final BooleanProperty IS_PLACEHOLDER = BooleanProperty.create("is_place_holder");
    public static List<Block> PLACE_HOLDER_LIST = new ArrayList<>();
    public static Map<String, Block> MAIN_PART_LIST = new HashMap<>();

    //I think I messed up :(
    public MMMultiBlockHolderBase(Properties properties, String name, boolean addToPlaceHolderList, boolean haveBlockItem, boolean addToStructureMaps) {
        super(properties, name, haveBlockItem);
        if (addToPlaceHolderList) {
            PLACE_HOLDER_LIST.add(this);
        }
        if (addToStructureMaps) {
            DeprecatedMultiBlockStructureMaps.NAMES.add(name);
            MAIN_PART_LIST.put(name, this);
        }
    }

    public BlockPos getMainPartPos(BlockState state, BlockPos currentPos) {
        return currentPos;
    }

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState);

    @Override
    public abstract @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_PLACEHOLDER);
        super.createBlockStateDefinition(pBuilder);
    }

}
