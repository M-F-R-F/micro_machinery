package mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.test;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.ComponentEvent;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class MMTestTileMainMart extends MMTileMainPartBase {
    public MMTestTileMainMart(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TEST.get(), pos, state);
    }

    @Override
    protected void releaseDataOnUnpack(LevelAccessor world, BlockPos breakPos) {

    }

    @Override
    public void componentEvent(ComponentEvent event) {

    }


}
