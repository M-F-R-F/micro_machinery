package mfrf.micro_machinery.network.tile_sync_to_server;

import mfrf.micro_machinery.block.machines.MMTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TileClientToServerSyncPackage {
    private final CompoundTag nbt;
    private final BlockPos pos;

    public TileClientToServerSyncPackage(FriendlyByteBuf buffer) {
        nbt = buffer.readAnySizeNbt();
        pos = buffer.readBlockPos();
    }

    public TileClientToServerSyncPackage(CompoundTag nbt, BlockPos pos) {
        this.nbt = nbt;
        this.pos = pos;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(nbt);
        buf.writeBlockPos(pos);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        if (context.getDirection().getReceptionSide() == LogicalSide.SERVER)
            context.enqueueWork(() -> {
                Level world = context.getSender().level();
                if (world.isLoaded(pos)) {
                    BlockEntity tile = world.getBlockEntity(pos);
                    if (tile instanceof MMTileBase) {
                        ((MMTileBase) tile).handleNetWorkSyncFromClient(nbt);
                    }
                }
            });
    }
}
