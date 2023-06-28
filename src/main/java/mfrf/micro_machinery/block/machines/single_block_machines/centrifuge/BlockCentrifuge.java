package mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class BlockCentrifuge extends MMBlockTileProviderBase {

    public BlockCentrifuge(Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileCentrifuge(pPos, pState);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, RegisteredBlockEntityTypes.TILE_CENTRIFUGE.get(), pBlockEntityType, TileCentrifuge::tick);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            NetworkHooks.openGui((ServerPlayer) pPlayer, (TileCentrifuge) pLevel.getBlockEntity(pPos), (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(pPos);
            });
        }
        return InteractionResult.SUCCESS;
    }
}
