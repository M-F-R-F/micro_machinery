package mfrf.micro_machinery.blocks.machines.multiblock_new_system.test;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TestTileMainPart extends MMTileMainPartBase {
    public TestTileMainPart() {
        super(RegisteredBlockEntityTypes.TEST.get());
    }

    @Override
    protected void releaseDataOnUnpack(World world, BlockPos breakPos) {

    }

    @Override
    public void redstoneSignalChange(int changed, Vec3i key) {

    }
}
