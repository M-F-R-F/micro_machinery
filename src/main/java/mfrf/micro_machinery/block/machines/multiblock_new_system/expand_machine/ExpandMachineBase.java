package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class ExpandMachineBase extends MMBlockTileProviderBase {

    public static final BooleanProperty IS_EXPAND_PART = BooleanProperty.create("is_expand_part");
    public static final Set<ExpandMachineBase> main_parts = new HashSet<>();

    public ExpandMachineBase(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(IS_EXPAND_PART, false).setValue(FACING, Direction.NORTH));
        main_parts.add(this);
    }

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState);

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        if (!assertPlaceable(pContext)) return null;
        return super.getStateForPlacement(pContext);
    }

    protected abstract boolean assertPlaceable(BlockPlaceContext placeContext);

    protected abstract void onExpand(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack);

    @Nullable
    @Override
    public abstract <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType);

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        onExpand(pLevel, pPos, pState, pPlacer, pStack);
    }


    protected abstract void linkDelegate(LevelAccessor pLevel, BlockPos dest, BlockPos src, BlockState pState);


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onDestroyBlockEvent(BlockEvent.BreakEvent event) {
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        if (level.getBlockState(pos).getBlock() instanceof ExpandMachineBase) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null) {
                if (level.getBlockState(pos).getValue(IS_EXPAND_PART)) {
                    ((DelegateTile) blockEntity).destoy();
                } else {
                    ((MainTile) blockEntity).destoy();
                }
            }
        }
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(IS_EXPAND_PART);
    }
}
