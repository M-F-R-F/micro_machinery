package mfrf.micro_machinery.blocks.machines.multiblock_new_system.test;

import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class TestTileMainPart extends MMTileMainPartBase {
    public TestTileMainPart(BlockPos pos, BlockState state) {
        super(RegisteredBlockEntityTypes.TEST.get(), pos, state);
    }

    @Override
    protected void releaseDataOnUnpack(LevelAccessor world, BlockPos breakPos) {

    }

    @Override
    public void redstoneSignalChange(int changed, Vec3i key) {

    }
}
