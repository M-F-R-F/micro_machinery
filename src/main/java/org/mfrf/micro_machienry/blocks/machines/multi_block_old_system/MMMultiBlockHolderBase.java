package org.mfrf.micro_machienry.blocks.machines.multi_block_old_system;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

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
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(IS_PLACEHOLDER);
        super.fillStateContainer(builder);
    }

}
