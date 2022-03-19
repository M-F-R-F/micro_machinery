package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockUtilPlaceHolder extends BlockPlaceHolder {


    public BlockUtilPlaceHolder(String name) {
        super(name);
    }

    public abstract void LinkToMainPart(BlockPos pos, World world, int arg1, int arg2);
}
