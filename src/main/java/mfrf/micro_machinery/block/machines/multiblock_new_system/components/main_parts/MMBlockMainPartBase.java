package mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public abstract class MMBlockMainPartBase extends MMBlockMultiBlockPart {
    public static HashMap<String, MMBlockMultiBlockPart> MAP = new HashMap<>();

    public MMBlockMainPartBase(Properties properties, String structureID) {
        super(properties);
        MAP.put(structureID, this);
    }

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState);

    @Override
    public abstract @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType);
}
