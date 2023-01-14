package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockAccessoryPlaceHolder extends BlockPlaceHolder {


    public BlockAccessoryPlaceHolder(BlockBehaviour.Properties properties, String name, boolean addToPlaceHolderList, boolean haveBlockItem, boolean addToStructureMaps) {
        super(properties, name, addToPlaceHolderList, haveBlockItem, addToStructureMaps);
    }

    public void LinkToMainPart(BlockPos pos, Level world, String arg1, String arg2, String arg3) {

    }

}
