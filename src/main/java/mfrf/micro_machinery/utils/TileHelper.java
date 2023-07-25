package mfrf.micro_machinery.utils;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

public abstract class TileHelper extends BaseEntityBlock {
    protected TileHelper(Properties pProperties) {
        super(pProperties);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level pLevel, BlockEntityType<T> argType, BlockEntityType<? extends BlockEntity> clientType, BlockEntityTicker<BlockEntity> ticker) {
        return !pLevel.isClientSide ? createTickerHelper(argType, clientType, ticker) : null;
    }
}
