package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.pump;

import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.DelegateTile;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.ExpandMachineBase;
import mfrf.micro_machinery.block.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockPump extends ExpandMachineBase {
    public BlockPump(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return (pState.getValue(IS_EXPAND_PART)) ? new DelegateTile(pPos, pState) : new TilePump(pPos, pState);
    }

    @Override
    protected boolean assertPlaceable(BlockPlaceContext placeContext) {
        BlockPos clickedPos = placeContext.getClickedPos();
        Level level = placeContext.getLevel();
        return level.getBlockState(clickedPos.above()).canBeReplaced();
    }

    @Override
    protected void onExpand(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        BlockPos above = pPos.above();
        pLevel.setBlock(above, pState.setValue(IS_EXPAND_PART, true), 3);
        linkDelegate(pLevel, above, pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pState.getValue(IS_EXPAND_PART) ? null : (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_PUMP.get(), pBlockEntityType, TileElectrolysis::tick);
    }

    @Override
    protected void linkDelegate(LevelAccessor pLevel, BlockPos dest, BlockPos src, BlockState pState) {
        DelegateTile blockEntity = (DelegateTile) pLevel.getBlockEntity(dest);
        blockEntity.link(src);
    }

}
