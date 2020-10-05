package mfrf.dbydd.micro_machinery.blocks.machines;

import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class MMTileBase extends TileEntity {
    public MMTileBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    public void handleNetWorkSyncFromClient(CompoundNBT tag){

    }

    public void markDirty2() {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
        super.markDirty();
    }

    protected boolean isBackDirection(Direction side) {
        return side == world.getBlockState(pos).get(MMBlockTileProviderBase.FACING).getOpposite();
    }

    protected FEContainer pushEnergyToDirection(Direction direction, FEContainer container) {
        TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
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

    protected Direction getBackDirection(){
        BlockState blockState = world.getBlockState(pos);
        if(blockState.getBlock() instanceof MMBlockTileProviderBase){
            Direction direction = blockState.get(MMBlockTileProviderBase.FACING);
            return direction.getOpposite();
        }

        return null;
    }

    public boolean isUsableByPlayer(PlayerEntity playerIn) {
        return this.world.getTileEntity(this.pos) == this && playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }
}
