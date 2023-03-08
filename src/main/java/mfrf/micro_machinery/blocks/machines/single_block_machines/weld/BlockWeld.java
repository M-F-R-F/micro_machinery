package mfrf.micro_machinery.blocks.machines.single_block_machines.weld;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockWeld extends MMBlockTileProviderBase {
    public BlockWeld(Properties properties) {
        super(properties, "weld");
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof TileWeld) {
                NetworkHooks.openGui((ServerPlayer) player, (TileWeld) tileEntity, (FriendlyByteBuf packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileEntity.getPos());
                });
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileWeld(pPos, pState)
    }
}
