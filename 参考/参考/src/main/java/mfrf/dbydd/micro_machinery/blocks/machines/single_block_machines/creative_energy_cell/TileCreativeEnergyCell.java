package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.creative_energy_cell;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileCreativeEnergyCell extends MMTileBase implements ITickableTileEntity, IEnergyStorage {
    public TileCreativeEnergyCell() {
        super(RegisteredTileEntityTypes.TILE_ENERGY_CELL.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            for (Direction direction : Direction.values()) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity != null) {
                    LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite());
                    capability.ifPresent(iEnergyStorage -> {
                        if (iEnergyStorage.canReceive()) {
                            int i = iEnergyStorage.receiveEnergy(Integer.MAX_VALUE / 2, true);
                            iEnergyStorage.receiveEnergy(i, false);
                        }
                    });
                }
            }
        }

    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap) {
        if (cap == CapabilityEnergy.ENERGY) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityEnergy.ENERGY) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return maxReceive;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return maxExtract;
    }

    @Override
    public int getEnergyStored() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
