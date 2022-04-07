package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockUtilPlaceHolder extends BlockPlaceHolder {


    public BlockUtilPlaceHolder(Properties properties, String name, boolean addToPlaceHolderList, boolean haveBlockItem, boolean addToStructureMaps) {
        super(properties, name, addToPlaceHolderList, haveBlockItem, addToStructureMaps);
    }

    public abstract void LinkToMainPart(BlockPos pos, World world, int arg1, int arg2, String arg3);


}
