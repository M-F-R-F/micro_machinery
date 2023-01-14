package mfrf.micro_machinery.blocks.machines;

import mfrf.micro_machinery.utils.FEContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class MMTileBase extends BlockEntity {
    public MMTileBase(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    public void handleNetWorkSyncFromClient(CompoundTag tag) {

    }

    public void setChanged2() {
        level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 2);
        super.setChanged();
    }

    protected boolean isBackDirection(Direction side) {
        return side == level.getBlockState(getBlockPos()).getValue(MMBlockTileProviderBase.FACING).getOpposite();
    }

    protected FEContainer pushEnergyToDirection(Direction direction, FEContainer container) {
        BlockEntity tileEntity = level.getBlockEntity(getBlockPos().relative(direction));
        if (tileEntity != null) {
            LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite());
            capability.ifPresent(iEnergyStorage -> {
                if (iEnergyStorage.canReceive() && container.canExtract()) {
                    int extractEnergy = container.extractEnergy(container.getMaxEnergyStored(), false);
                    int receiveEnergy = iEnergyStorage.receiveEnergy(extractEnergy, false);
                    container.receiveEnergy(extractEnergy - receiveEnergy, false);
                }
            });
        }
        return container;
    }

    protected Direction getBackDirection() {
        Direction facingDirection = getFacingDirection();
        return facingDirection == null ? null : facingDirection.getOpposite();
    }

    protected Direction getFacingDirection() {
        BlockState blockState = level.getBlockState(getBlockPos());
        if (blockState.getBlock() instanceof MMBlockTileProviderBase) {
            Direction direction = blockState.getValue(MMBlockTileProviderBase.FACING);
            return direction;
        }

        return null;
    }

    public boolean isUsableByPlayer(Player playerIn) {
        return this.level.getBlockEntity(this.getBlockPos()) == this && playerIn.distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D, (double) this.getBlockPos().getZ() + 0.5D) <= 64.0D;
    }
}
