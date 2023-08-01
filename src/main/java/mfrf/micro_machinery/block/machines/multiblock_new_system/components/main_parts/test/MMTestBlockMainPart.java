package mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.test;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMMultiBlockMainPartBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MMTestBlockMainPart extends MMMultiBlockMainPartBase {
    public MMTestBlockMainPart(Properties properties, String structureID) {
        super(properties, structureID);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MMTestTileMainMart(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return null;
    }
}
