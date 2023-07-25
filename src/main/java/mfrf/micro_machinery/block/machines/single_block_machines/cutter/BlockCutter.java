package mfrf.micro_machinery.block.machines.single_block_machines.cutter;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.block.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCutter extends MMBlockTileProviderBase {
    public static BooleanProperty WORKING = BooleanProperty.create("working");

    public BlockCutter(Properties properties) {
        super(properties);
        registerDefaultState(getStateToRegistry().setValue(WORKING, false));
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            NetworkHooks.openScreen((ServerPlayer) player, (TileCutter) worldIn.getBlockEntity(pos), (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(pos);
            });
        }
        return InteractionResult.SUCCESS;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_CUTTER.get(), pBlockEntityType, TileCutter::tick);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WORKING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileCutter(pPos, pState);
    }
}
