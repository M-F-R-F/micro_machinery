package mfrf.micro_machinery.block.machines.multiblock_new_system.components.attach_components.multi_level_lever;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockMultiLevelLever extends MMBlockTileProviderBase {

    public BlockMultiLevelLever(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }
}
